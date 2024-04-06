import java.util.ArrayList;
import java.util.List;


public class Aplicacion {
	
	String poaps;
	String tipo;
	int eventos;
	List<Evento> apariciones;

	
	
	public Aplicacion(String poaps, String tipo) {
		this.poaps=poaps;
		this.tipo=tipo;
		this.eventos = 0;
		apariciones=new ArrayList<Evento>();
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
		this.eventos=this.eventos+1;
	}

	
	public List<Evento> getApariciones() {
		return apariciones;
	}

	public void addApariciones(Evento evento) {
		this.addEventos();
		this.apariciones.add(evento);
	}
	
	

	
	public String apariciones(String analisis){
		if(this.poaps.equals(analisis)){
			return "";
		}else{
			String retorno = "<br/>"
					+ "<table ><tr>"
					+ "<th style=\"text-align:left;background-color:blue;color:white;\" colspan=\"2\">APLICACION: "+this.poaps+" - APARICIONES ENCONTRADOS:"+this.eventos+"</th></tr></table>";
			String fichero = "";
			boolean first=true;
			for(Evento aparicion: this.apariciones){
				if(!aparicion.getFichero().equals(fichero)){
					fichero=aparicion.getFichero();
					if(first){
						first=false;
					}else{
						retorno+="</p></details>";
					}
					retorno+="<details><summary>+ FICHERO: "+ fichero+"</summary><p>";					
				}
				boolean candidata= false;
				for(String comando : Cargador.cargarSQL()){
					if(aparicion.getContenido().contains(comando)){
						candidata = true;
					}
				}
				if(candidata){
					retorno +="<table style=\"border: 1px solid black; width:100%;\">"
							+ "<tr>"
							+ "<th style=\"border: 1px solid black;\"> # LINEA ("+aparicion.getLinea()+")</th>"
							+ "</tr>"; 
					retorno +="<tr>"
							+ "<td>"+aparicion.getContenido()+"</td>"
							+ "</tr>"
							+ "</table>"; 
				}else{
					retorno +="<table style=\"border: 1px solid black; width:100%;\">"
							+ "<tr>"
							+ "<th  style=\"border: 1px solid black;\"> LINEA ("+aparicion.getLinea()+")</th>"
							+ "</tr>"; 
					retorno +="<tr>"
							+ "<td>"+aparicion.getContenido()+"</td>"
							+ "</tr>"
							+ "</table>"; 
				}
				retorno += "<br/>";
				
			}
			retorno+="</p></details>";
			return retorno;
		}
	}
	
	
	
	
	
	public String resumen(String analisis){
		if(this.poaps.equals(analisis)){
			return "";
		}else{
			return "<tr>"
					+ "<td style=\"border:1px solid black;\">"+this.poaps+"</td>"
					+ "<td style=\"border:1px solid black;\">"+this.tipo+"</td>"
					+ "<td style=\"border:1px solid black;\">"+this.eventos+"</td>"
					+ "</tr>";
		}
	}
	

}
