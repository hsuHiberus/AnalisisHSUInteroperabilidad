import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class Principal {
	
	public static byte NORMAL = 0;
	public static byte ALERT = 1;
	public static byte SQL = 2;
	public static String DIRECTORIO_ROOT="HSU";

	
	
	public static void main(String[] args){
		try{
			System.out.println("ANALIZANDO...");
			// Configuraciones iniciales
			String analisis= Configurador.configurar();
			List<Aplicacion> aplicaciones = Cargador.cargarAplicaciones(analisis);
			List<String> sqls = Cargador.cargarSQL();
			File root = new File(DIRECTORIO_ROOT);
			List<Fichero> ficheros=new ArrayList<Fichero>();
			List<Evento> lineas;
			Configurador.del(new File("DATA"));
			List<File> archivos=Configurador.tree(root,new ArrayList<File>());
			Fichero fichero;
			
			int idFile=1;
			for (File archivo: archivos){
					lineas = new ArrayList<Evento>();
					System.out.print("\n"+idFile + " de "+archivos.size()+" - "+archivo.getName());
					fichero=new Fichero(archivo.getName());
					idFile++;
					BufferedReader entrada= new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
					byte alerta=NORMAL;
					int i=0;
					while(entrada.ready()){
						i++;
						String linea=entrada.readLine().toUpperCase();						
						alerta=NORMAL;
						for(String sql : sqls){
							if(linea.contains(sql)){
								alerta=SQL;
							}
						}
						for(Aplicacion aplicacion : aplicaciones){
							if(linea.contains(" "+aplicacion.getPoaps()+"_") || linea.contains("."+aplicacion.getPoaps()+"_")){
								aplicacion.addApariciones(new Evento(archivo.getName(),i,linea,NORMAL));
								fichero.addInterconexion(aplicacion.getPoaps(), i, linea);
								alerta=ALERT;
							}
						}
						
						lineas.add(new Evento(archivo.getName(),i,linea,alerta));
						
					}
					entrada.close();
					ficheros.add(fichero);
					if(fichero.interconexiones.size()>0){
						Salida.crearArchivo(lineas, archivo.getName());
					}
				
			}
				
			Salida.exportarFicheros(ficheros,aplicaciones,analisis,"INDEX_"+analisis.toUpperCase()+".html");
			int con=0;
			int sin=0;
			for(Fichero f:ficheros){
				if(f.interconexiones.size()>0){
					con++;
				}else{
					sin++;
				}
			}
			System.out.println("FICHEROS CON INTERCONEXION:"+con);
			System.out.println("FICHEROS SIN INTERCONEXION:"+sin);
			System.out.println("FINALIZADO");
			
			
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}catch(IOException e){
			System.out.println(e.getMessage());
		}

	}

}
