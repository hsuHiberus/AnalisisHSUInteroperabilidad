import java.util.ArrayList;
import java.util.List;


public class Cargador {
	
	public static String APP1 = "LOTE 1";
	public static String APP2 = "LOTE 2";
	public static String APP3 = "LOTE 3";
	public static String APP4 = "LOTE 4";
	public static String APP5 = "LOTE 5";
	public static String MOD = "TRANSVERSAL";
	
	public static List<Aplicacion> cargarAplicaciones(){
		List<Aplicacion> aplicaciones = new ArrayList<>();
		
		aplicaciones.add(new Aplicacion("RMIN",APP1));
		aplicaciones.add(new Aplicacion("SIDM",APP1));
		aplicaciones.add(new Aplicacion("RGM", APP1));
		aplicaciones.add(new Aplicacion("GTFN",APP1));	
		aplicaciones.add(new Aplicacion("APMV",APP1));	
		aplicaciones.add(new Aplicacion("HSSR",APP1));	
		
		aplicaciones.add(new Aplicacion("AYMV",APP2));	
		aplicaciones.add(new Aplicacion("SOIC",APP2));	
		aplicaciones.add(new Aplicacion("PISO",APP2));	
		aplicaciones.add(new Aplicacion("PNC",APP2));	
		aplicaciones.add(new Aplicacion("AAME",APP2));	
		aplicaciones.add(new Aplicacion("APET",APP2));
		
		aplicaciones.add(new Aplicacion("TARD",APP3));	
		aplicaciones.add(new Aplicacion("RUAT",APP3));	
		aplicaciones.add(new Aplicacion("PLAS",APP3));	
		aplicaciones.add(new Aplicacion("CUSO",APP3));	
		aplicaciones.add(new Aplicacion("GERC",APP3));	
		aplicaciones.add(new Aplicacion("ISDR",APP3));	
		aplicaciones.add(new Aplicacion("SIAV",APP3));	
		aplicaciones.add(new Aplicacion("BSTE",APP3));	

		aplicaciones.add(new Aplicacion("ECSS",APP4));	
		aplicaciones.add(new Aplicacion("GTFM",APP4));	
		aplicaciones.add(new Aplicacion("XPRE",APP4));	
		aplicaciones.add(new Aplicacion("ZINS",APP4));	
		aplicaciones.add(new Aplicacion("MAYO",APP4));	
		aplicaciones.add(new Aplicacion("BDUC",APP4));	
		aplicaciones.add(new Aplicacion("GPME",APP4));	
		aplicaciones.add(new Aplicacion("ISIS",APP4));
		aplicaciones.add(new Aplicacion("DICE",APP4));	
		aplicaciones.add(new Aplicacion("ENFA",APP4));	
		aplicaciones.add(new Aplicacion("RAM",APP4));	
		aplicaciones.add(new Aplicacion("GCEM",APP4));
		aplicaciones.add(new Aplicacion("ADIN",APP4));	
		aplicaciones.add(new Aplicacion("ADON",APP4));	
		aplicaciones.add(new Aplicacion("FOMU",APP4));	
		aplicaciones.add(new Aplicacion("ONGS",APP4));

		aplicaciones.add(new Aplicacion("CFR",APP5));
		aplicaciones.add(new Aplicacion("GCP",APP5));
		aplicaciones.add(new Aplicacion("GERP",APP5));
		aplicaciones.add(new Aplicacion("ACUM",APP5));
		aplicaciones.add(new Aplicacion("VOIL",APP5));
		aplicaciones.add(new Aplicacion("SCD",APP5));
		aplicaciones.add(new Aplicacion("ENTI",APP5));
		aplicaciones.add(new Aplicacion("ACES",APP5));
		aplicaciones.add(new Aplicacion("SECE",APP5));
		aplicaciones.add(new Aplicacion("ACOM",APP5));
		aplicaciones.add(new Aplicacion("RMEN",APP5));
		aplicaciones.add(new Aplicacion("CIFE",APP5));
		aplicaciones.add(new Aplicacion("COMU",APP5));
		aplicaciones.add(new Aplicacion("COVA",APP5));
		aplicaciones.add(new Aplicacion("CEPI",APP5));
		aplicaciones.add(new Aplicacion("PAIN",APP5));
		aplicaciones.add(new Aplicacion("ISES",APP5));
		aplicaciones.add(new Aplicacion("CFAR",APP5));
		aplicaciones.add(new Aplicacion("TIFN",APP5));
		aplicaciones.add(new Aplicacion("TIMI",APP5));
		
		aplicaciones.add(new Aplicacion("EXPE",MOD));
		aplicaciones.add(new Aplicacion("AFC",MOD));
		aplicaciones.add(new Aplicacion("DOCUMENTUM",MOD));
		aplicaciones.add(new Aplicacion("FICA",MOD));
		aplicaciones.add(new Aplicacion("TFN",MOD));
		aplicaciones.add(new Aplicacion("GIS",MOD));
		aplicaciones.add(new Aplicacion("MENTES",MOD));
		aplicaciones.add(new Aplicacion("BO",MOD));
		aplicaciones.add(new Aplicacion("CRB",MOD));
		aplicaciones.add(new Aplicacion("USUG",MOD));
		aplicaciones.add(new Aplicacion("USUI",MOD));
		aplicaciones.add(new Aplicacion("CCC",MOD));
		aplicaciones.add(new Aplicacion("SUCA",MOD));
		aplicaciones.add(new Aplicacion("CATA",MOD));
		aplicaciones.add(new Aplicacion("DOMI",MOD));
		aplicaciones.add(new Aplicacion("ANOTACIONES",MOD));
		aplicaciones.add(new Aplicacion("COVE",MOD));
		aplicaciones.add(new Aplicacion("EREG",MOD));
		aplicaciones.add(new Aplicacion("NOTE",MOD));
		aplicaciones.add(new Aplicacion("ICDA",MOD));
		aplicaciones.add(new Aplicacion("PTFR",MOD));
		aplicaciones.add(new Aplicacion("SMS",MOD));
		aplicaciones.add(new Aplicacion("ADEX",MOD));
		aplicaciones.add(new Aplicacion("SIEX",MOD));
		aplicaciones.add(new Aplicacion("ACESSOS",MOD));
		
		return aplicaciones;
	}

	public static List<String> cargarSQL(){
		List<String> sql = new ArrayList<String>();
		
		sql.add("SELECT ");
		sql.add("FROM ");
		sql.add("WHERE ");
		sql.add("AND ");
		sql.add("OR ");
		sql.add("GROUP BY ");	
		sql.add("INSERT ");	
		sql.add("UPDATE ");	
		sql.add("DELETE ");	
		sql.add("_CALL ");	
		return sql;
	}

}
