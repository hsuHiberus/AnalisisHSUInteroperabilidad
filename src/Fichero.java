import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fichero {
	
	String nombre;
	List<Interconexion> interconexiones;
	
	private class Interconexion{
		String aplicacion;
		int indice;
		String linea;
		
		public Interconexion(String aplicacion,int indice,String linea){
			this.aplicacion=aplicacion;
			this.indice=indice;
			this.linea=linea;
		}
	}	
	
	public Fichero(String nombre){
		this.nombre=nombre;
		this.interconexiones=new ArrayList<Interconexion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addInterconexion(String aplicacion, int indice, String linea ){
		this.interconexiones.add(new Interconexion(aplicacion,indice,linea));
	}
	
	public String resumen(String analisis){
		int i=0;
		String retorno="<details><summary>FICHERO:"+nombre+"</summary>"
				+ "<ul>";
		for(Interconexion interconexion: interconexiones){
			if(!interconexion.aplicacion.equals(analisis)){
				retorno+="<li><b>["+interconexion.aplicacion+"]"+" linea ("+interconexion.indice+")</b> - "+Utils.limpiar(interconexion.linea)+"</li>";
				i++;
			}
		}
		retorno+="</ul>";
		return i>0?retorno+"</details>":"";
	}
	

	public String verInterconexiones() {
		Map<String, Integer> inter=new HashMap<String,Integer>();
		for(Interconexion interc : interconexiones){
			if(inter.containsKey(interc.aplicacion)){
				inter.replace(interc.aplicacion, inter.get(interc.aplicacion).intValue()+1);
			}else{
				inter.put(interc.aplicacion, 1);
			}

		}
		return inter.toString();
	}
	
	

}

