package es.golemdr.tragaldaba.ext.utils.tools;

import java.util.Random;

public class GeneradorCodigo {
	
	private static Random aleatorio; 
	
	static {
		aleatorio = new Random();
	}
	
	private GeneradorCodigo() {
		throw new IllegalStateException("GeneradorCodigo class");
	}
	
	public static char mayusculaAlAzar(){

		return (char)(aleatorio.nextInt(25) + 65);
		
		
	}
	
	public static int numeroAlAzar(){
		
		return aleatorio.nextInt(8) + 1;
		
	}

	
	public static String generaCodigoExcepcion(){
		
		StringBuilder resultado = new StringBuilder();
		
		// Generamos un ticket con el formato AA99AA99		
		resultado.append(mayusculaAlAzar());
		resultado.append(mayusculaAlAzar());
		resultado.append(numeroAlAzar());
		resultado.append(numeroAlAzar());
		resultado.append(mayusculaAlAzar());
		resultado.append(mayusculaAlAzar());
		resultado.append(numeroAlAzar());
		resultado.append(numeroAlAzar());
		
		return resultado.toString(); 
		
		
		
	}

}
