import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Principal {
	
	
	public static String configurar(){
		Properties prop = new Properties();
        InputStream input = null;
        String aplicacion = null;
        try {
            input = new FileInputStream("environment.properties");
            prop.load(input);
            aplicacion = prop.getProperty("aplicacion");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return aplicacion;
    }
	
	
	public static void main(String[] args){
		try{
			
			String analisis= Principal.configurar();
			List<Aplicacion> aplicaciones = Cargador.cargarAplicaciones();
			
			
			File directorio = new File("HSU");
			File subdirectorios[]= directorio.listFiles();
			List<File> formularios= new ArrayList<File>();
			List<File> reports= new ArrayList<File>();

			for(File subdir : subdirectorios){
				if(subdir.isDirectory()){
					for(File archivo : subdir.listFiles()){
						if(archivo.getName().endsWith(".fmb")){
							formularios.add(archivo);
						}
						if(archivo.getName().endsWith(".rdf")){
							reports.add(archivo);
						}
					}
				}
			}
			
			int totalFormularios= formularios.size();
			int totalReports = reports.size();
			int indice = 0;
			int indice2 = 0;
			
			for(File f : formularios){
				indice++;
				BufferedReader entrada= new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				int i=0;
				while(entrada.ready()){
					i++;
					String linea=entrada.readLine();
				
						for(Aplicacion aplicacion : aplicaciones){
							if(analisis!=null && aplicacion.getPoaps().equals(analisis)){
								break;
							}
							if(linea.contains(aplicacion.getPoaps()+"_")){
								aplicacion.addEventos();
								aplicacion.addApariciones(new Evento(f.getName(),i,linea)); 
							}
						}						
			
					
				}
				entrada.close();
				out(totalFormularios,indice,totalReports, indice2,analisis);
			}
			
			for(File f : reports){
				indice2++;
				BufferedReader entrada= new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				int i=0;
				while(entrada.ready()){
					i++;
					String linea=entrada.readLine();
				
						for(Aplicacion aplicacion : aplicaciones){
							if(linea.contains(aplicacion.getPoaps()+"_")){
								aplicacion.addEventos();
								aplicacion.addApariciones(new Evento(f.getName(),i,linea)); 
							}
						}						
			
					
				}
				out(totalFormularios,indice,totalReports, indice2,analisis);
				entrada.close();				
			}
			
			System.out.println("VOLCANDO DATOS....");
			FileWriter entrada= new FileWriter("Analisis.txt");
			entrada.write("\n----APLICACIONES DETECTADAS --------------------- \n");
			for(Aplicacion aplicacion : aplicaciones){
				if(aplicacion.eventos>0){
					entrada.write(aplicacion.resumen(analisis));
				}
			}
			System.out.println("VOLCANDO APARICIONES....");
			entrada.write("\n\n----APARICIONES --------------------- \n");
			for(Aplicacion aplicacion : aplicaciones){
				if(aplicacion.eventos>0){
					entrada.write(aplicacion.apariciones(analisis));
				}
			}
			System.out.println("FINALIZADO");
			System.out.println("FORMULARIOS ANALIZADOS:"+totalFormularios);
			System.out.println("INFORMES ANALIZADOS:"+totalReports);
			entrada.close();
			
			
			
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
	
	public static void out(int totalFormularios, int indiceFormularios,int totalReports, int indiceReports,String aplicacion) throws InterruptedException, IOException{		
		System.out.println("ANALIZANDO HSU...["+aplicacion+"]");
		System.out.println("- Formularios: "+indiceFormularios +" de "+ totalFormularios);
		System.out.println("- Informes   : "+indiceReports +" de "+ totalReports);
		
	}

}
