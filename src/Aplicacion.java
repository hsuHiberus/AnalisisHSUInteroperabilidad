import java.util.ArrayList;
import java.util.List;


public class Aplicacion {
	
	String poaps;
	String tipo;
	int eventos;
	List<Evento> apariciones;

	public Aplicacion(String poaps, String tipo) {
		this.poaps = poaps;
		this.tipo = tipo;
		this.eventos = 0;
		apariciones = new ArrayList<Evento>();
	}
	
	public String getPoaps() {
		return poaps;
	}

	public void setPoaps(String poaps) {
		this.poaps = poaps;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void addEventos(){
		this.eventos = this.eventos + 1;
	}

	public int getEventos(){
		return this.eventos;
	}

	public List<Evento> getApariciones() {
		return apariciones;
	}

	public void addApariciones(Evento evento) {
		this.apariciones.add(evento);
	}
	
	public String apariciones(String analisis){
		if(this.poaps.equals(analisis)){
			return "";
		}else{
			String retorno = "\n\nAPP >> APLICACION: " + this.poaps + " - APARICIONES ENCONTRADOS: " + this.eventos + "\n";
			String fichero = "";
			for(Evento aparicion: this.apariciones){
				if(!aparicion.getFichero().equals(fichero)){
					fichero=aparicion.getFichero();
					if(fichero.endsWith(".fmb")){
						retorno+="\n\n\t + FORMULARIO: "+ fichero;
					}
					if(fichero.endsWith(".rdf")){
						retorno+="\n\n\t + INFORME: "+ fichero;
					}
					
					retorno+="\n\t ----------------------------------------------------------";
				}
				boolean candidata= false;
				for(String comando : Cargador.cargarSQL()){
					if(aparicion.getContenido().contains(comando)){
						candidata = true;
					}
				}
				if(candidata){
					retorno += "\n\t\t # LINEA (" + aparicion.getLinea() + ") >> " + aparicion.getContenido();
				}else{
					retorno += "\n\t\t - LINEA (" + aparicion.getLinea() + ") >> " + aparicion.getContenido();
				}
			}
			return retorno;
		}
	}
	
	public String resumen(String analisis){
		if(this.poaps.equals(analisis)){
			return "";
		}else{
			return "\n+ APLICACION: " + this.poaps + " -  TIPO: " + this.tipo + " - APARICIONES ENCONTRADOS: " + this.eventos;
		}
	}

}
