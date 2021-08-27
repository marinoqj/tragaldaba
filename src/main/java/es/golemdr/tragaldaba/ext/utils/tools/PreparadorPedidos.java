package es.golemdr.tragaldaba.ext.utils.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import es.golemdr.tragaldaba.domain.Pedido;

public class PreparadorPedidos {

    public static List<String> stringToList(String idsPlatos) {
        List<String> result = new ArrayList<String>();

        idsPlatos = idsPlatos.replace('[', ' ');
        idsPlatos = idsPlatos.replace(']', ' ');

        StringTokenizer strTok = new StringTokenizer(idsPlatos, ",");
        while(strTok.hasMoreTokens()){
            result.add(strTok.nextToken().trim());
        }

        return result;
    }
    
    public static Pedido extraerDatos(String datosPedidoParaGrabar) {
    	Pedido pedido = new Pedido();
    	
       	datosPedidoParaGrabar = datosPedidoParaGrabar.replace("{'numArticulos':", "[");
       	datosPedidoParaGrabar = datosPedidoParaGrabar.replace("'total':", " ");
       	datosPedidoParaGrabar = datosPedidoParaGrabar.replace("}", "]");
       	    	
       	List<String> result = stringToList(datosPedidoParaGrabar);
       	
       	pedido.setNumArticulos(Long.parseLong(result.get(0)));
       	pedido.setTotal(Double.parseDouble(result.get(1)));
       	
       	System.out.println(pedido);	
           	
    	return pedido;
    }      
}
