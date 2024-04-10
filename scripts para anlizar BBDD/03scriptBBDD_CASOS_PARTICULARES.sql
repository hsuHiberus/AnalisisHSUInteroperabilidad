/**
 * Herramienta utilizada para crear script: DBeaver v23.1
 * IMPORTANTE!! antes de lanzar el script:  
 * 1. Comentar/quitar en la tabla temporal CASOS_PARTICULARES la aplicación que estamos analizando. 
 * 	  Ejemplo, si analizamos APET comentamos/quitamos --> SELECT 'APET' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
 * 2. Cuando se ejecute te solicitará que indiques en el parámetro  :DBA_POAP el esquema de la aplicación a analizar. 
 * 	  Ejemplo 'DBA_APET', O 'DBA_AYMV' o 'DBA_SOIC', etc.
 *
 * Casos particulares que hacen referencia a otros esquemas como puedan ser 
 * sinónimos detectados  que acceden a otros esquemas de otras aplicaciones.
 *  
 *  
 */

WITH CASOS_PARTICULARES AS (
    -- Esta es muy hardcore para indicársela, pongo casos concretos que he detectado revisando las consultas
	-- SELECT SYNONYM_NAME AS CODIGO, TABLE_OWNER || '.' ||TABLE_NAME FROM ALL_SYNONYMS WHERE TABLE_OWNER  LIKE 'DBA_%'  
	SELECT SYNONYM_NAME AS CODIGO, TABLE_OWNER || '.' ||TABLE_NAME as tipo 
	FROM ALL_SYNONYMS 
	where synonym_name IN ('GENTIADMIN', 'GCATUSUARS', 'GCATNACION', 'GCATECIVIL', 'GEXPCOLINT', 'GEXPEDIENT')
	-- si queremos añadir más casos particulares...
--	UNION 
--	SELECT 'CODIGO_A_BUSCAR', 'OBSERVACION' FROM DUAL
)
/**
 * tabla temporal eventos obtiene las ocurrencias de los CASOS_PARTICULARES indicados
 */
, eventos AS (
    SELECT CASOS_PARTICULARES.*, evento.* 
    FROM ALL_source evento
    JOIN CASOS_PARTICULARES  ON UPPER(evento.TEXT) LIKE '% ' || CASOS_PARTICULARES.codigo || '%'  -- Patrón. Ejemplo: '% SOIC_'  
	WHERE evento.OWNER =  :DBA_POAP  --IMPORTANTE: al lanzar el script solicita el esquema de la aplicación a analizar 'DBA_APET'
	AND UPPER(evento.TEXT) NOT  LIKE '%\%TYPE;%' ESCAPE '\'   -- Evitamos resultados tipo SOIC_SOLICITUD.itecoban%type; que nO nos valen
)
/**
 * tabla temporal eventos_detalle que nos dará el contexto de la ocurrencia (lineas por arriba y por abajo)
 */
, eventos_detalle AS ( 
SELECT evento.codigo, evento.tipo, detalle.line, detalle.text, evento.line line_event_id, evento.OWNER , evento.NAME , evento.TYPE
	FROM ALL_source detalle,
	eventos evento
	WHERE detalle.line >= evento.line -15
	AND detalle.line < evento.line +15
	AND detalle.owner = evento.owner
	AND detalle.name = evento.name
    AND detalle.type = evento.TYPE
    AND LENGTH(detalle.TEXT) > 1  -- validamos que no sean lineas con espacios o saltos de linea y con más de un caracter
)
/**
 * Agrupamos las lineas para que por cada evento podamos visualizar en el campo FRAGMENTO_PL el contexto
 */
SELECT OWNER "APLICACION" , OWNER || '_' || codigo || '_XX' "ID_EVENTO",  NAME, TYPE, LINE_EVENT_ID, codigo "POAP", tipo "TIPO POAP",
	LISTAGG(text) WITHIN GROUP (ORDER BY line) FRAGMENTO_PL
FROM eventos_detalle
GROUP BY OWNER, NAME, TYPE, LINE_EVENT_ID, codigo, tipo
ORDER BY codigo, name;


