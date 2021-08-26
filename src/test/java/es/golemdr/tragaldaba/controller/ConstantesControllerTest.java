package es.golemdr.tragaldaba.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import es.golemdr.tragaldaba.config.AppInitializer;
import es.golemdr.tragaldaba.config.WebMvcConfig;
import es.golemdr.tragaldaba.controller.constantes.ForwardConstants;
import es.golemdr.tragaldaba.controller.constantes.UrlConstants;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = { AppInitializer.class, WebMvcConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConstantesControllerTest {
	
	   @Autowired
	   private WebApplicationContext wac;

	   private MockMvc mockMvc;

	   @BeforeEach
	   void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	   }
	   
		@Test
		@Order(1)
		@Transactional
		//@Rollback(false)
		void testProcessCreationFormSuccess() throws Exception {
			
			mockMvc.perform(post(UrlConstants.URL_INSERTAR_CONSTANTE)
					.param("familia", "Familia")
					.param("atributo", "Atributo")
					.param("literal", "Literal")
					.param("valor", "1")
					.param("activa", "1"))
					.andExpect(view().name(ForwardConstants.RED_LISTADO_CONSTANTES));
		}
		
		@Test
		@Order(2)
		void testInitUpdateConstanteForm() throws Exception {
			
			mockMvc.perform(post(UrlConstants.URL_EDITAR_CONSTANTE).param("idConstante", "3")).andExpect(status().isOk())
					.andExpect(model().attributeExists("constante"))
					.andExpect(model().attribute("constante", hasProperty("familia", is("Desplegable"))))
					.andExpect(model().attribute("constante", hasProperty("atributo", is("Entrega"))))
					.andExpect(model().attribute("constante", hasProperty("literal", is("Entrega a domicilio"))))
					.andExpect(model().attribute("constante", hasProperty("valor", is(1L))))
					.andExpect(model().attribute("constante", hasProperty("activa", is("1"))))
					.andExpect(view().name(ForwardConstants.FWD_CONSTANTE_FORM));
		}
		
		@Test
		@Order(3)
		@Transactional
		//@Rollback(false)
		void testProcessUpdateConstanteFormSuccess() throws Exception {	
			
			mockMvc.perform(post(UrlConstants.URL_ACTUALIZAR_CONSTANTE)
					.param("idConstante", "3")
					.param("familia", "Desplegablezz")
					.param("atributo", "Entrega")
					.param("literal", "Entrega a domicilio")
					.param("valor", "1")
					.param("activa", "1"))
					.andExpect(status().is3xxRedirection()).andExpect(view().name(ForwardConstants.RED_LISTADO_CONSTANTES));
		}		
		
		@Test
		@Order(4)
		@Transactional
		void testDeleteConstanteForm() throws Exception {
			
			mockMvc.perform(post(UrlConstants.URL_BORRAR_CONSTANTE).param("idConstante", "3")).andExpect(status().is3xxRedirection())
					.andExpect(view().name(ForwardConstants.RED_LISTADO_CONSTANTES));
		}
		
		@Test
		@Order(5)
		void testListConstantes() throws Exception {
			
			mockMvc.perform(get(UrlConstants.URL_LISTADO_CONSTANTES, "1")).andExpect(status().isOk())
					.andExpect(model().attributeExists("constantes"))
					.andExpect(model().attributeExists("constante"))
					.andExpect(model().attributeExists("paginacion"))
					.andExpect(model().attributeExists("hayFiltro"))
					.andExpect(view().name(ForwardConstants.FWD_LISTADO_CONSTANTES));
		}

}
