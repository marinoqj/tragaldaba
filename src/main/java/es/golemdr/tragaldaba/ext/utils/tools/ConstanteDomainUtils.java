package es.golemdr.tragaldaba.ext.utils.tools;

import java.util.ArrayList;
import java.util.List;

import es.golemdr.tragaldaba.domain.Constante;


public class ConstanteDomainUtils {
	
	public static List<Constante> filtrarPorFamilia(String valorFamilia, List<Constante> listaSinFiltrar) {
		List<Constante> listaFiltrada = new ArrayList<>();
		
		for (Constante constante : listaSinFiltrar) {
			if (constante.getFamilia().trim().equalsIgnoreCase(valorFamilia)) {
				listaFiltrada.add(constante);
			}
		}
		
		return listaFiltrada;
	}

}
