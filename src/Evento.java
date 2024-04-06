
public class Evento {
	
	String fichero;
	int linea;
	String contenido;
	byte alerta; 
	
	public Evento(String fichero, int linea,String contenido,byte alerta) {
		
		this.fichero 	= fichero;
		this.linea 		= linea;
		this.contenido 	= contenido;
		this.alerta		= alerta;
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
	
	public String mostrar(){
		if(this.alerta==1 ){
			return "<tr><td><span style=\"color:red;\">["+this.linea+"]###</span></td><td><span style=\"color:red;\"> "+Utils.limpiar(this.contenido)+"</span></td></tr>";
		}else if(this.alerta==2){
			return "<tr><td><span style=\"color:green\";>["+this.linea+"]</span></td><td><span style=\"color:green;\"> "+Utils.limpiar(this.contenido)+"</span></td></tr>";
		}else{
			return "<tr><td><span style=\"color:gray;\">["+this.linea+"]</span></td><td><span style=\"color:gray;\"> "+Utils.limpiar(this.contenido)+"</span></td></tr>";
		}
	}


	
	
	

}
