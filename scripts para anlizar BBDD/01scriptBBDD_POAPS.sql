/**
 * Herramienta utilizada para crear script: DBeaver v23.1
 * videoTutorial:https://hiberus-my.sharepoint.com/:v:/p/jbernat/EYM7UNJlPsFNpmeqe-POXoQBAN6T06V76cHoWscBZFoOBw?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=rNEfug
 * IMPORTANTE!! antes de lanzar el script:  
 * 1. Comentar/quitar en la tabla temporal POAPS la aplicación que estamos analizando. 
 * 	  Ejemplo, si analizamos APET comentamos/quitamos --> SELECT 'APET' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
 * 2. Cuando se ejecute te solicitará que indiques en el parámetro  :DBA_POAP el esquema de la aplicación a analizar. 
 * 	  Ejemplo 'DBA_APET', O 'DBA_AYMV' o 'DBA_SOIC', etc.
 */

/**
 * Códigos de las aplicaciones en las que buscar coincidencias en la BBDD
 * nota: el tipo os lo deberán indicar. Lote 1 es que pertenecen al analisis de la entrega1 y así sucesivamente.
 */
WITH POAPS AS (
SELECT 'RMIN' AS codigo, 'LOTE 1' AS tipo FROM dual UNION ALL
SELECT 'SIDM' AS codigo, 'LOTE 1' AS tipo FROM dual UNION ALL
SELECT 'RGM' AS codigo, 'LOTE 1' AS tipo FROM dual UNION ALL
SELECT 'GTFN' AS codigo, 'LOTE 1' AS tipo FROM dual UNION ALL
SELECT 'APMV' AS codigo, 'LOTE 1' AS tipo FROM dual UNION ALL
SELECT 'HSSR' AS codigo, 'LOTE 1' AS tipo FROM dual UNION ALL
SELECT 'AYMV' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
SELECT 'SOIC' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
SELECT 'PISO' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
SELECT 'PNC' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
SELECT 'AAME' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
--SELECT 'APET' AS codigo, 'LOTE 2' AS tipo FROM dual UNION ALL
SELECT 'TARD' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'RUAT' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'PLAS' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'CUSO' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'GERC' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'ISDR' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'SIAV' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'BSTE' AS codigo, 'LOTE 3' AS tipo FROM dual UNION ALL
SELECT 'ECSS' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'GTFM' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'XPRE' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'ZINS' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'MAYO' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'BDUC' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'GPME' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'ISIS' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'DICE' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'ENFA' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'RAM' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'GCEM' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'ADIN' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'ADON' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'FOMU' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'ONGS' AS codigo, 'LOTE 4' AS tipo FROM dual UNION ALL
SELECT 'CFR' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'GCP' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'GERP' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'ACUM' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'VOIL' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'SCD' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'ENTI' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'ACES' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'SECE' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'ACOM' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'RMEN' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'CIFE' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'COMU' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'COVA' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'CEPI' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'PAIN' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'ISES' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'CFAR' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'TIFN' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'TIMI' AS codigo, 'LOTE 5' AS tipo FROM dual UNION ALL
SELECT 'EXPE' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'AFC' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'DOCUMENTUM' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'FICA' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'TFN' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'GIS' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'MENTES' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'BO' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'CRB' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'USUG' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'USUI' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'CCC' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'SUCA' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'CATA' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'DOMI' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'ANOTACIONES' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'COVE' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'EREG' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'NOTE' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'ICDA' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'PTFR' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'SMS' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'ADEX' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'SIEX' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'GSTA' AS codigo, 'TRANSVERSAL' AS tipo FROM dual UNION ALL
SELECT 'ACESSOS' AS codigo, 'TRANSVERSAL' AS tipo FROM dual
)
/**
 * tabla temporal eventos obtiene las coincidencias de los POAPS indicados
 */
, eventos AS (
    SELECT POAPS.*, evento.* 
    FROM ALL_source evento
    JOIN POAPS  ON UPPER(evento.TEXT) LIKE '% ' || POAPS.codigo || '!_%' ESCAPE '!'  -- Patrón. Ejemplo: '% SOIC_'  
	WHERE evento.OWNER =  :DBA_POAP  --IMPORTANTE: al lanzar el script solicita el esquema de la aplicación a analizar 'DBA_APET'
	AND UPPER(evento.TEXT) NOT  LIKE '%\%TYPE;%' ESCAPE '\'   -- Evitamos resultados tipo SOIC_SOLICITUD.itecoban%type; que nO nos valen
)
/**
 * tabla temporal eventos_detalle que nos dará el contexto de la coincidencias (lineas por arriba y por abajo)
 */
, eventos_detalle AS ( 
SELECT evento.codigo, evento.tipo, detalle.line, detalle.text, evento.line line_event_id, evento.OWNER , evento.NAME , evento.TYPE, evento.text coincidencia
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
SELECT OWNER "APLICACION" , OWNER || '_' || codigo || '_XX' "ID_EVENTO",  NAME, TYPE, LINE_EVENT_ID, codigo "POAP", tipo "TIPO POAP", coincidencia, 
	LISTAGG(text) WITHIN GROUP (ORDER BY line) FRAGMENTO_PL
FROM eventos_detalle
GROUP BY OWNER, NAME, TYPE, LINE_EVENT_ID, codigo, tipo, coincidencia
ORDER BY codigo, name;




/** NOTA:  POSIBLE MEJORA en tabla eventos_detalle
 * 
 * Que devuelva las líneas entre los puntos y coma por arriba y por abajo para recuperar la select completa.
 * problema: si devuelve una cadena de caracteres muy larga ORA-01489: el resultado de la concatenación de cadena de caracteres es demasiado largo ya se que trata de varchar2 y 
 * está limitado a 4000 caracteres. 
 * 
 * eventos_detalle AS ( 
SELECT evento.codigo, evento.tipo, detalle.line, detalle.text, evento.line line_event_id, evento.OWNER , evento.NAME , evento.TYPE, evento.text coincidencia
	FROM ALL_source detalle,
	eventos evento
	WHERE detalle.line >= (SELECT min(line) FROM ALL_source WHERE owner = detalle.owner AND detalle.name = name AND detalle.TYPE = TYPE AND text like '%;%')
	AND detalle.line < (SELECT max(line) FROM ALL_source WHERE owner = detalle.owner AND detalle.name = name AND detalle.TYPE = TYPE AND text like '%;%')
	AND detalle.owner = evento.owner
	AND detalle.name = evento.name
    AND detalle.type = evento.TYPE
    AND LENGTH(detalle.TEXT) > 1  -- validamos que no sean lineas con espacios o saltos de linea y con más de un caracter
)
**/