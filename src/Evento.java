
public class Evento {
	
	String fichero;
	int linea;
	String contenido;
	
	public Evento(String fichero, int linea,String contenido) {
		this.fichero 	= fichero;
		this.linea 		= linea;
		this.contenido 	= contenido;
	}
	
	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
