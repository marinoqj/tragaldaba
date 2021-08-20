package es.golemdr.tragaldaba.ext.utils.paginacion;

import java.io.Serializable;

public class PaginacionBean implements Serializable{

	private int inicio = 0;
	private int elementosXpagina = 5;
	private int totalRegistros;
	private int totalPaginas;

	
	
	public int getElementosXpagina() {
		return elementosXpagina;
	}
	public void setElementosXpagina(int elementosXpagina) {
		this.elementosXpagina = elementosXpagina;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public int getTotalPaginas() {
		
		double dTotalRegistros = totalRegistros;
		double dElementosXPagina = elementosXpagina;
		
		double resultado = Math.ceil(dTotalRegistros / dElementosXPagina);		
		
		return Double.valueOf(resultado).intValue();
		
	}

	public int getFin(){
		
		return this.inicio + this.elementosXpagina;
	}
	
	public int getPaginaActual(){
		
		Double paginaActual_Double = new Double((this.inicio + this.elementosXpagina - 1)/this.elementosXpagina);
		
		return paginaActual_Double.intValue();
	}
	
}
