import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Salida {

public static void crearArchivo(List<Evento> lineas,String nombre) throws IOException{
		
		FileWriter entrada= new FileWriter(new File("DATA/"+nombre+".html"));

		int i=1;
		List<Integer> alertas=new ArrayList<Integer>();
		for(Evento linea : lineas){
			if(linea.alerta==1){
				for(int j=i-10;j<i+10;j++){
					if(j>0){
						alertas.add(new Integer(j));
					}
				}
			}
			i++;
		}

		
		entrada.write("<html>");
		entrada.write("<style>");
		entrada.write("body,td { overflow-x: auto;white-space: nowrap;}");
		entrada.write("span { font-size:14px;}");
		entrada.write("</style>");
		entrada.write("<body>");
		entrada.write("<table>");

		for(Evento linea : lineas){
			entrada.write(linea.mostrar());
		}
		entrada.write("</table>");
		entrada.write("</body></html>");
		entrada.close();
		
	}

public static void exportarFicheros(List<Fichero> ficheros,List<Aplicacion> aplicaciones,String analisis, String archivo) throws IOException{

	FileWriter entrada= new FileWriter(archivo);
	entrada.write("<html><body>");
	entrada.write(exportarResumen(aplicaciones,analisis));
	entrada.write("<h2>Ficheros con interconexiones</h2>");
	entrada.write("<table style=\"wid:100%;\">");
	entrada.write("<tr style=\"background-color:blue;color:white;\"><th></th><th>NOMBRE ARCHIVO</th><th>EVENTOS ENCONTRADOS</th></tr>");
	int i=1;
	for(Fichero fichero : ficheros){
		entrada.write("<tr>");
		if(fichero.interconexiones.size()>0){
			entrada.write("<td><a href=\"DATA/"+fichero.getNombre()+".html\">VER</a></td>");
			
		}else{
			entrada.write("<td></td>");
		}
		entrada.write("<td>"+fichero.getNombre()+"</td>");
		entrada.write("<td>"+fichero.verInterconexiones()+"</td>");
		entrada.write("</tr>");
		i++;
	}
	entrada.write("</table>");
	entrada.write("</body></html>");
	entrada.close();
}

public static String exportarResumen(List<Aplicacion> aplicaciones,String analisis) throws IOException{
	int i=0;
	
	String retorno="<table style=\"width:100%;\">"
			+ "<tr>"
			+ "<th style=\"border:1px solid black;\">APLICACION</th>"
			+ "<th style=\"border:1px solid black;\">TIPO</th>"
			+ "<th style=\"border:1px solid black;\">INTERCONEXIONES</th>"
			+ "</tr>";
	for(Aplicacion aplicacion : aplicaciones){
		if(aplicacion.eventos>0){
			retorno+=aplicacion.resumen(analisis);
			i++;
		}
	}
	
	System.out.println("\nRESUMEN APP INTERCONEXIONES:"+i);
	return retorno+"</table>";
}



}
