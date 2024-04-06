import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class Configurador {
	
	public static List<File> tree(File root,List<File> archivos){
		
		for(File dir : root.listFiles()){
			if(!dir.isDirectory()){
				if(!verificarExtension(dir.getName().toLowerCase())){
					archivos.add(dir);
				}
			}else{
				tree(dir,archivos);
			}
		}
		return archivos;
	}
	
	public static void del(File root){
		System.out.println("ENCONTRADOS: "+root.listFiles().length);
		int i=0;
		for(File archivo : root.listFiles()){
			archivo.delete();
			i++;
		}
		System.out.println("ELIMINADOS: "+i);
	}
	
	public static String configurar() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
        String aplicacion = null;
        try (InputStream input = new FileInputStream("environment.properties")){
            prop.load(input);
            aplicacion = prop.getProperty("aplicacion");

        } 
        return aplicacion;
    }
	
	
	private static boolean verificarExtension(String fichero){
		for(String extension : Cargador.extensionesProhibidas()){
			if(fichero.endsWith(extension)){
				return true;
			}
		}
		return false;
	}

}
