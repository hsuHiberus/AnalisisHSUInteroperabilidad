import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;


public class Principal {

	public static String configurar(){
		Properties prop = new Properties();
		InputStream input = null;
		String aplicacion = null;
/* 		String svn_user = null;
		String svn_pass = null;
		String svn_url = null; */
		try {
			input = new FileInputStream("environment.properties");
			prop.load(input);
			aplicacion = prop.getProperty("aplicacion");
/* 			svn_url = prop.getProperty("svn_repo");
			svn_user = prop.getProperty("svn_username");
			svn_pass = prop.getProperty("svn_password"); */
			//GestorSVN gestor = GestorSVN.getInstancia();
			//gestor.init(svn_url, svn_user, svn_pass);
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
		//try{
			//String analisis = Principal.configurar();
			List<Aplicacion> aplicaciones = Cargador.cargarAplicaciones();
			
			String app = seleccionarAplicacion(aplicaciones);
			GestorSVN.getInstancia().init(app);

			List<String> lista = GestorSVN.getInstancia().listarDirectorios();
			List<String> seleccion = seleccionarCarpetas(lista);

			try {
				System.out.print("\nEjecución en curso: ");
				long inicio = System.nanoTime();
				CompletableFuture<Map<String, byte[]>> futureArchivos = GestorSVN.getInstancia().obtenerArchivosAsync(seleccion);
			
				Map<String, byte[]> listaArchivos = futureArchivos.get();
				long fin = System.nanoTime();

				System.out.println("\nRecuperación de archivos completada.");
				System.out.println("Número de ficheros: " + listaArchivos.size());
    		System.out.println("Tiempo empleado: " + getTiempoEmpleado(inicio, fin) + "\n");
				System.out.println("Tamaño total: " + calcularTamanoTotal(listaArchivos) + "\n");

				GestorSVN.getInstancia().shutdownExecutor();

				Lector.procesarArchivos(listaArchivos, aplicaciones, "Analisis.txt", app);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private static String seleccionarAplicacion(List<Aplicacion> aplicaciones) {
    String app = "";
    try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			int maxAncho = 0;
			for (Aplicacion aplicacion : aplicaciones) {
				int largoActual = (aplicacion.getPoaps().length() + 4);
				if (largoActual > maxAncho) {
					maxAncho = largoActual;
				}
			}

			while (true) {
				System.out.println("Seleccione una aplicación:");
				for (int inicio = 0; inicio < 10; inicio++) {
					for (int j = inicio; j < aplicaciones.size(); j += 10) {
						String item = (j + 1) + "." + aplicaciones.get(j).getPoaps();
						System.out.print(String.format("%-" + maxAncho + "s", item));
					}
					System.out.println();
				}

				System.out.print("Ingrese el número de la aplicación: ");
				String input = reader.readLine();
				int seleccion = Integer.parseInt(input) - 1;
				if (seleccion >= 0 && seleccion < aplicaciones.size()) {
					System.out.println("\nAplicación seleccionada: " + aplicaciones.get(seleccion).getPoaps() + "\n");
					app = aplicaciones.get(seleccion).getPoaps().toLowerCase();
					break;
				} else {
					System.out.println("\nSelección inválida. Por favor, intentelo de nuevo.\n");
				}
			}
    } catch (Exception e) {
        e.printStackTrace();
    }
    return app;
	}

	private static List<String> seleccionarCarpetas(List<String> carpetas) {
		List<String> carpetasSeleccionadas = new ArrayList<>();
		try {				
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (carpetasSeleccionadas.isEmpty()) {
				System.out.println("Seleccione las carpetas a explorar:");
				for (int i = 0; i < carpetas.size(); i++) {
					System.out.println("   " + (i + 1) + ". " + carpetas.get(i));
				}
				System.out.print("Indique los índices (Ej: 1,2,3): ");
				String input = reader.readLine();

				carpetasSeleccionadas.clear();
    		boolean seleccionValida = true;

				String[] indices = input.split(",");
				for (String indiceStr : indices) {
						try {
								int indice = Integer.parseInt(indiceStr.trim()) - 1;
								if (indice >= 0 && indice < carpetas.size()) {
										carpetasSeleccionadas.add(carpetas.get(indice));
								} else {
										System.out.println("\nÍndice fuera de rango: " + (indice + 1) + ". Por favor, intentelo de nuevo.\n");
										seleccionValida = false;
										break;
								}
						} catch (NumberFormatException e) {
								System.out.println("\nEntrada no válida: " + indiceStr + ". Por favor, use solo números separados por comas.\n");
								seleccionValida = false;
								break;
						}
				}

				if (!seleccionValida) {
						carpetasSeleccionadas.clear();
				} else {
					if (!carpetasSeleccionadas.isEmpty()) {
						System.out.println("\nCarpetas seleccionadas: ");
						for (String nombre: carpetasSeleccionadas) {
							System.out.println("    - " + nombre);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carpetasSeleccionadas;
	}

	public static String getTiempoEmpleado(long inicio, long fin) {
    long tiempoSegundosTotal = (fin - inicio) / 1_000_000_000;
    if (tiempoSegundosTotal < 60) {
			return String.format("%d segundos", tiempoSegundosTotal);
    } else {
			long minutos = tiempoSegundosTotal / 60;
			long segundos = tiempoSegundosTotal % 60;
			return String.format("%d minutos y %d segundos", minutos, segundos);
    }
	}

	public static String calcularTamanoTotal(Map<String, byte[]> listaArchivos) {
    long totalBytes = 0;

    for (byte[] contenido : listaArchivos.values()) {
        if (contenido != null) {
            totalBytes += contenido.length;
        }
    }

    if (totalBytes < 1024) {
        return totalBytes + " bytes";
    } else if (totalBytes < 1024 * 1024) {
        return String.format("%.2f KB", totalBytes / 1024.0);
    } else if (totalBytes < 1024 * 1024 * 1024) {
        return String.format("%.2f MB", totalBytes / (1024.0 * 1024));
    } else {
        return String.format("%.2f GB", totalBytes / (1024.0 * 1024 * 1024));
    }
}

}
