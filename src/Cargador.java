import java.util.ArrayList;
import java.util.List;


public class Cargador {
	
	public static String APP1 = "LOTE 1";
	public static String APP2 = "LOTE 2";
	public static String APP3 = "LOTE 3";
	public static String APP4 = "LOTE 4";
	public static String APP5 = "LOTE 5";
	public static String MOD = "TRANSVERSAL";
	
	public static List<Aplicacion> cargarAplicaciones(String analisis){
		List<Aplicacion> aplicaciones = new ArrayList();
		if(!analisis.equals("RMIN"))	aplicaciones.add(new Aplicacion("RMIN",APP1));
		if(!analisis.equals("SIDM"))	aplicaciones.add(new Aplicacion("SIDM",APP1));
		if(!analisis.equals("RGM"))		aplicaciones.add(new Aplicacion("RGM", APP1));
		if(!analisis.equals("GTFN"))	aplicaciones.add(new Aplicacion("GTFN",APP1));	
		if(!analisis.equals("APMV"))	aplicaciones.add(new Aplicacion("APMV",APP1));	
		if(!analisis.equals("HSSR"))	aplicaciones.add(new Aplicacion("HSSR",APP1));	
		
		if(!analisis.equals("AYMV"))	aplicaciones.add(new Aplicacion("AYMV",APP2));	
		if(!analisis.equals("SOIC"))	aplicaciones.add(new Aplicacion("SOIC",APP2));	
		if(!analisis.equals("PISO"))	aplicaciones.add(new Aplicacion("PISO",APP2));	
		if(!analisis.equals("PNC"))		aplicaciones.add(new Aplicacion("PNC",APP2));	
		if(!analisis.equals("AAME"))	aplicaciones.add(new Aplicacion("AAME",APP2));	
		if(!analisis.equals("APET"))	aplicaciones.add(new Aplicacion("APET",APP2));
		
		if(!analisis.equals("TARD"))	aplicaciones.add(new Aplicacion("TARD",APP3));	
		if(!analisis.equals("RUAT"))	aplicaciones.add(new Aplicacion("RUAT",APP3));	
		if(!analisis.equals("PLAS"))	aplicaciones.add(new Aplicacion("PLAS",APP3));	
		if(!analisis.equals("CUSO"))	aplicaciones.add(new Aplicacion("CUSO",APP3));	
		if(!analisis.equals("GERC"))	aplicaciones.add(new Aplicacion("GERC",APP3));	
		if(!analisis.equals("ISDR"))	aplicaciones.add(new Aplicacion("ISDR",APP3));	
		if(!analisis.equals("SIAV"))	aplicaciones.add(new Aplicacion("SIAV",APP3));	
		if(!analisis.equals("BSTE"))	aplicaciones.add(new Aplicacion("BSTE",APP3));	

		if(!analisis.equals("ECSS"))	aplicaciones.add(new Aplicacion("ECSS",APP4));	
		if(!analisis.equals("GTFM"))	aplicaciones.add(new Aplicacion("GTFM",APP4));	
		if(!analisis.equals("XPRE"))	aplicaciones.add(new Aplicacion("XPRE",APP4));	
		if(!analisis.equals("ZINS"))	aplicaciones.add(new Aplicacion("ZINS",APP4));	
		if(!analisis.equals("MAYO"))	aplicaciones.add(new Aplicacion("MAYO",APP4));	
		if(!analisis.equals("BDUC"))	aplicaciones.add(new Aplicacion("BDUC",APP4));	
		if(!analisis.equals("GPME"))	aplicaciones.add(new Aplicacion("GPME",APP4));	
		if(!analisis.equals("ISIS"))	aplicaciones.add(new Aplicacion("ISIS",APP4));
		if(!analisis.equals("DICE"))	aplicaciones.add(new Aplicacion("DICE",APP4));	
		if(!analisis.equals("ENFA"))	aplicaciones.add(new Aplicacion("ENFA",APP4));	
		if(!analisis.equals("RAM"))		aplicaciones.add(new Aplicacion("RAM",APP4));	
		if(!analisis.equals("GCEM"))	aplicaciones.add(new Aplicacion("GCEM",APP4));
		if(!analisis.equals("ADIN"))	aplicaciones.add(new Aplicacion("ADIN",APP4));	
		if(!analisis.equals("ADON"))	aplicaciones.add(new Aplicacion("ADON",APP4));	
		if(!analisis.equals("FOMU"))	aplicaciones.add(new Aplicacion("FOMU",APP4));	
		if(!analisis.equals("ONGS"))	aplicaciones.add(new Aplicacion("ONGS",APP4));

		if(!analisis.equals("CFR"))	aplicaciones.add(new Aplicacion("CFR",APP5));
		if(!analisis.equals("GCP"))	aplicaciones.add(new Aplicacion("GCP",APP5));
		if(!analisis.equals("GERP"))	aplicaciones.add(new Aplicacion("GERP",APP5));
		if(!analisis.equals("ACUM"))	aplicaciones.add(new Aplicacion("ACUM",APP5));
		if(!analisis.equals("VOIL"))	aplicaciones.add(new Aplicacion("VOIL",APP5));
		if(!analisis.equals("SCD"))	aplicaciones.add(new Aplicacion("SCD",APP5));
		if(!analisis.equals("ENTI"))	aplicaciones.add(new Aplicacion("ENTI",APP5));
		if(!analisis.equals("ACES"))	aplicaciones.add(new Aplicacion("ACES",APP5));
		if(!analisis.equals("SECE"))	aplicaciones.add(new Aplicacion("SECE",APP5));
		if(!analisis.equals("ACOM"))	aplicaciones.add(new Aplicacion("ACOM",APP5));
		if(!analisis.equals("RMEN"))	aplicaciones.add(new Aplicacion("RMEN",APP5));
		if(!analisis.equals("CIFE"))	aplicaciones.add(new Aplicacion("CIFE",APP5));
		if(!analisis.equals("COMU"))	aplicaciones.add(new Aplicacion("COMU",APP5));
		if(!analisis.equals("COVA"))	aplicaciones.add(new Aplicacion("COVA",APP5));
		if(!analisis.equals("CEPI"))	aplicaciones.add(new Aplicacion("CEPI",APP5));
		if(!analisis.equals("PAIN"))	aplicaciones.add(new Aplicacion("PAIN",APP5));
		if(!analisis.equals("ISES"))	aplicaciones.add(new Aplicacion("ISES",APP5));
		if(!analisis.equals("CFAR"))	aplicaciones.add(new Aplicacion("CFAR",APP5));
		if(!analisis.equals("TIFN"))	aplicaciones.add(new Aplicacion("TIFN",APP5));
		if(!analisis.equals("TIMI"))	aplicaciones.add(new Aplicacion("TIMI",APP5));
		
		if(!analisis.equals("EXPE"))	aplicaciones.add(new Aplicacion("EXPE",MOD));
		if(!analisis.equals("AFC"))		aplicaciones.add(new Aplicacion("AFC",MOD));
		if(!analisis.equals("DOCUMENTUM"))	aplicaciones.add(new Aplicacion("DOCUMENTUM",MOD));
		if(!analisis.equals("FICA"))	aplicaciones.add(new Aplicacion("FICA",MOD));
		if(!analisis.equals("TFN"))		aplicaciones.add(new Aplicacion("TFN",MOD));
		if(!analisis.equals("GIS"))		aplicaciones.add(new Aplicacion("GIS",MOD));
		if(!analisis.equals("MENTES"))	aplicaciones.add(new Aplicacion("MENTES",MOD));
		if(!analisis.equals("BO"))		aplicaciones.add(new Aplicacion("BO",MOD));
		if(!analisis.equals("CRB"))		aplicaciones.add(new Aplicacion("CRB",MOD));
		if(!analisis.equals("USUG"))	aplicaciones.add(new Aplicacion("USUG",MOD));
		if(!analisis.equals("USUI"))	aplicaciones.add(new Aplicacion("USUI",MOD));
		if(!analisis.equals("CCC"))		aplicaciones.add(new Aplicacion("CCC",MOD));
		if(!analisis.equals("SUCA"))	aplicaciones.add(new Aplicacion("SUCA",MOD));
		if(!analisis.equals("CATA"))	aplicaciones.add(new Aplicacion("CATA",MOD));
		if(!analisis.equals("DOMI"))	aplicaciones.add(new Aplicacion("DOMI",MOD));
		if(!analisis.equals("ANOTACIONES"))	aplicaciones.add(new Aplicacion("ANOTACIONES",MOD));
		if(!analisis.equals("COVE"))	aplicaciones.add(new Aplicacion("COVE",MOD));
		if(!analisis.equals("EREG"))	aplicaciones.add(new Aplicacion("EREG",MOD));
		if(!analisis.equals("NOTE"))	aplicaciones.add(new Aplicacion("NOTE",MOD));
		if(!analisis.equals("ICDA"))	aplicaciones.add(new Aplicacion("ICDA",MOD));
		if(!analisis.equals("PTFR"))	aplicaciones.add(new Aplicacion("PTFR",MOD));
		if(!analisis.equals("SMS"))		aplicaciones.add(new Aplicacion("SMS",MOD));
		if(!analisis.equals("ADEX"))	aplicaciones.add(new Aplicacion("ADEX",MOD));
		if(!analisis.equals("SIEX"))	aplicaciones.add(new Aplicacion("SIEX",MOD));
		if(!analisis.equals("ACESSOS"))	aplicaciones.add(new Aplicacion("ACESSOS",MOD));
		
		return aplicaciones;
	}

	public static List<String> cargarSQL(){
		List<String> sql = new ArrayList<String>();
		
		sql.add("SELECT ");
		sql.add("FROM ");
		sql.add("WHERE ");
		sql.add("JOIN ");
		
		sql.add("INSERT ");	
		sql.add("UPDATE ");	
		sql.add("DELETE ");	
		sql.add("_CALL ");	
		sql.add("FUNCT ");
		sql.add("PROC ");
		sql.add("VALUES ");

		return sql;
	}
	
	public static List<String> cargarInteroperabilidad(){
		List<String> interop = new ArrayList<String>();
		
		interop.add("APIS");
		interop.add("BBDD");
		interop.add("WS");
		interop.add("OTROS");
		return interop;
	}
	
	
	public static List<String> extensionesProhibidas(){
		List<String> extensiones = new ArrayList<String>();
		
		extensiones.add(".svn");
		extensiones.add(".doc");
		extensiones.add(".docx");
		extensiones.add(".pdf");
		extensiones.add(".bmp");
		extensiones.add(".png");
		extensiones.add(".jpg");
		return extensiones;
	}

}
