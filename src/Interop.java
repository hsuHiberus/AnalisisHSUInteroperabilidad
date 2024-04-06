
public class Interop {
	
	String tipo;
	String archivo;
	int indice;
	String linea;
	
	public Interop(String tipo, String archivo, int indice, String linea) {
		this.tipo = tipo;
		this.archivo = archivo;
		this.indice = indice;
		this.linea = linea;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	public String mostrar(){
		return "\n("+tipo+") - "+archivo+" Linea ("+indice+"):"+linea;
	}
	
	

}
