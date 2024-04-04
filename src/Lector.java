import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;

import org.mozilla.universalchardet.UniversalDetector;

public class Lector {

  public static void procesarArchivos(Map<String, byte[]> listaArchivos, List<Aplicacion> aplicaciones, String rutaArchivoSalida, String app) {
    for (Map.Entry<String, byte[]> entrada : listaArchivos.entrySet()) {
      String nombreArchivo = entrada.getKey();
      byte[] contenidoArchivo = entrada.getValue();
      String codificacion = detectarCodificacion(contenidoArchivo);
      
      int indiceLinea = 0;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(contenidoArchivo), Charset.forName(codificacion)))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
          linea = Normalizer.normalize(linea, Normalizer.Form.NFC);
          indiceLinea++;
          for (Aplicacion aplicacion : aplicaciones) {
            if (app != null && aplicacion.getPoaps().equals(app)) {
              continue;
            }
            if (linea.contains(aplicacion.getPoaps() + "_")) {
              aplicacion.addEventos();
              aplicacion.addApariciones(new Evento(nombreArchivo, indiceLinea, linea));
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaArchivoSalida), StandardCharsets.UTF_8)) {
      writer.write("\n----APLICACIONES DETECTADAS --------------------- \n");
      for (Aplicacion aplicacion : aplicaciones) {
        if (aplicacion.getEventos() > 0) {
          writer.write(aplicacion.resumen(app) + "\n");
        }
      }

      writer.write("\n\n----APARICIONES --------------------- \n");
      for (Aplicacion aplicacion : aplicaciones) {
        if (aplicacion.getEventos() > 0) {
          writer.write(aplicacion.apariciones(app) + "\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("FINALIZADO");
  }

  private static String detectarCodificacion(byte[] bytes) {
    UniversalDetector detector = new UniversalDetector(null);
    detector.handleData(bytes, 0, bytes.length);
    detector.dataEnd();
    String encoding = detector.getDetectedCharset();
    detector.reset();
    return encoding != null ? encoding : StandardCharsets.UTF_8.name();
  }

}
