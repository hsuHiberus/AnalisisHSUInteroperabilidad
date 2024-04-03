import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class GestorSVN {

  private static GestorSVN instancia = null;
  private SVNRepository repository = null;
  private ExecutorService executor = Executors.newCachedThreadPool();
  private static final List<String> extensionesExcluidas = Arrays.asList(".gif", ".png", ".jpg", ".jpeg", ".mp4", ".avi", ".mov", ".doc", ".pdf");
  private AtomicInteger contadorArchivos = new AtomicInteger(0);
  private final int archivosPorPunto = 50;

  private GestorSVN() {}

  public static synchronized GestorSVN getInstancia() {
    if (instancia == null) {
      instancia = new GestorSVN();
    }
    return instancia;
  }
  
  public void init(String app) {
    try {
      SVNURL svnUrl = SVNURL.parseURIEncoded("https://subversion01.madrid.org:8443/svn/" + app + "/v0/" + app);
      repository = SVNRepositoryFactory.create(svnUrl);
      ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager("e_air26", new String("qwert123").toCharArray());
      repository.setAuthenticationManager(authManager);
    } catch (SVNException e) {
      e.printStackTrace();
    }
  }

  public void shutdownExecutor() {
    executor.shutdown();
    try {
      if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        executor.shutdownNow();
      } 
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }
  }

  public List<String> listarDirectorios() {
    List<String> aplicaciones = new ArrayList<>();
    try {
      if (repository != null) {
        Collection<SVNDirEntry> entries = repository.getDir("", -1, null, (Collection) null);
        for (SVNDirEntry entry : entries) {
          if (entry.getKind() == SVNNodeKind.DIR) {
            aplicaciones.add(entry.getName());
          }
        }
      } else {
        System.out.println("El repositorio SVN no ha sido inicializado.");
      }
    } catch (SVNException e) {
      e.printStackTrace();
    }
    return aplicaciones;
  }

  public CompletableFuture<Map<String, byte[]>> obtenerArchivosAsync(List<String> seleccion) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Map<String, byte[]> archivos = new HashMap<>();
        listarDirectoriosRecursivamente("", archivos, seleccion, false);
        return archivos;
      } catch (SVNException e) {
        throw new RuntimeException("Error al obtener los archivos", e);
      }
    }, executor);
  }

  private void listarDirectoriosRecursivamente(String path, Map<String, byte[]> archivos, List<String> seleccion, boolean explorarSubdirectorios) throws SVNException {
    Collection<SVNDirEntry> entries = repository.getDir(path, -1, null, (Collection) null);
    List<CompletableFuture<Void>> futures = new ArrayList<>();

    for (SVNDirEntry entry : entries) {
      String fullPath = path.isEmpty() ? entry.getName() : path + "/" + entry.getName();

      if(debeExcluirse(entry.getName())){
        continue;
      }

      boolean esDirectorioSeleccionado = seleccion.contains(entry.getName()) || explorarSubdirectorios;
      
      if (entry.getKind() == SVNNodeKind.DIR && esDirectorioSeleccionado) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
          try {
            listarDirectoriosRecursivamente(fullPath, archivos, seleccion, true);
          } catch (SVNException e) {
            e.printStackTrace();
          }
        }, executor);
        futures.add(future);
      } else if (entry.getKind() != SVNNodeKind.DIR && esDirectorioSeleccionado) {
        //System.out.print(entry.getName() + "  ");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
          try {
            synchronized (archivos) {
              archivos.put(entry.getName(), leerContenidoArchivo(fullPath));
              if (contadorArchivos.incrementAndGet() % archivosPorPunto == 0) {
                System.out.print(".");
              }
            }
          } catch (SVNException e) {
            e.printStackTrace();
          }
        }, executor);
        futures.add(future);
      }
    }

    CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    try {
      allDone.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private byte[] leerContenidoArchivo(String filePath) throws SVNException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    repository.getFile(filePath, -1, null, baos);
    return baos.toByteArray();
  }

  private boolean debeExcluirse(String nombreArchivo) {
    String nombreArchivoEnMinusculas = nombreArchivo.toLowerCase();
    String extension = "." + nombreArchivoEnMinusculas.replaceAll("^.*\\.", "");

    return extensionesExcluidas.contains(extension);
  }

}
