package es.golemdr.tragaldaba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.golemdr.tragaldaba.config.AppInitializer;
import es.golemdr.tragaldaba.config.WebMvcConfig;
import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = { AppInitializer.class, WebMvcConfig.class})
public class ConstantesServiceTest {
	
	@Autowired
	protected ConstantesService constantesService;
	
    @BeforeEach
    void setup() {
    	
    	assertNotNull(constantesService);
       
    }
	
    @Test
	void getConstantes() throws Exception {
		
		List<Constante> constantes = constantesService.getConstantes();		
		assertNotNull(constantes);
	}
    
    @Test
	void getConstantesPaginacion() throws Exception {
    	
    	PaginacionBean paginacionBean = new PaginacionBean();
    	paginacionBean.setInicio(0);
    	
    	List<Constante> constantes = constantesService.getConstantes(paginacionBean);
    	assertEquals(paginacionBean.getElementosXpagina(), constantes.size());
    	
    	
    }
    
    @Test
	void getTotalConstantes() throws Exception {
    	
    	
    	int total = constantesService.getTotalConstantes();
    	assertTrue(total > 0);
    	
    	
    }
    
    
    @Test    
    //@Rollback(true)  // Descomentar si queremos que los cambios persistan en BBDD (por defecto se hace rollback)
	void manejarConstante() throws Exception {
    	
		Constante constante = new Constante();		
		constante.setFamilia("Familia");
		constante.setAtributo("Atributo");
		constante.setLiteral("Literal");
		constante.setValor(1L);
		constante.setActiva("1");
		
		Constante recuperada = null;
    	
		
		// 1.- Crear
		constantesService.insertarActualizarConstante(constante);				
		assertNotNull(constante.getIdConstante());
		
		// 2.- Recuperar
		assertNotNull(constantesService.getById(constante.getIdConstante()));
		recuperada = constantesService.getById(constante.getIdConstante());
		assertEquals(recuperada.getIdConstante(), constante.getIdConstante());
		
		// 3.- Actualizar
		String propiedad = "Prueba update";		
		constante.setLiteral(propiedad);
		
		constantesService.insertarActualizarConstante(constante);
		recuperada = constantesService.getById(constante.getIdConstante());
		assertEquals(propiedad, recuperada.getLiteral());
		
		// 4.- Borrar
		constantesService.borrarConstante(constante.getIdConstante());		
		assertNull(constantesService.getById(constante.getIdConstante()));

    	
    }
    
    @Test
    @Transactional
    @Rollback(true)
    void getConstanteByExample() throws Exception {
    	
		Constante constante = new Constante();		
		constante.setFamilia("Familia-ex");
		constante.setAtributo("Atributo-ex");
		constante.setLiteral("Literal-ex");
		constante.setValor(1L);
		constante.setActiva("1");
		
		// Creo la constante y luego la busco
		constantesService.insertarActualizarConstante(constante);				
		assertNotNull(constante.getIdConstante());

    	PaginacionBean paginacionBean = new PaginacionBean();
    	paginacionBean.setInicio(0);
    	
    	List<Constante> constantes = constantesService.findConstantesByExample(constante, paginacionBean);
    	assertEquals(1, constantes.size());
    	
    	int totalExample = constantesService.countConstantesByExample(constante);
    	assertEquals(1, totalExample);
		
    }


}

