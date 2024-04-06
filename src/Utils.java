
public class Utils {
	public static  String limpiar(String linea){
		String resultado="";
		boolean salto=false;
		for (int i = 0; i < linea.length(); i++) {
            char caracter = linea.charAt(i);
            if(caracter> 48 && caracter <= 90){
            	resultado+=caracter;
            	salto=false;
            }else if(caracter ==' ' 
            		|| caracter =='.' 
            		|| caracter ==',' 
            		|| caracter ==';'
            		|| caracter =='(' 
                    || caracter ==')'
            		|| caracter =='='
            		|| caracter =='<'
            		|| caracter =='>'
            		|| caracter =='?'
            		|| caracter =='\''
            		|| caracter =='"'
            		|| caracter =='*'
            		|| caracter =='-'
            		|| caracter =='_'){
            	resultado+=caracter;
            	salto=false;
            }else{
            	if(!salto){
            		resultado+=" ";
            		salto=true;
            	}
            	
            }
        }
		return resultado;
	}
	
}
