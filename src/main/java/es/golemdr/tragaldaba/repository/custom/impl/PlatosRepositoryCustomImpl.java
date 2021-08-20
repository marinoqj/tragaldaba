package es.golemdr.tragaldaba.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import es.golemdr.tragaldaba.domain.Plato;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.repository.custom.PlatosRepositoryCustom;




public class PlatosRepositoryCustomImpl implements PlatosRepositoryCustom{
	
    @PersistenceContext
    private EntityManager em;

	@Transactional
	public List<Plato> findPlatos(Plato filtro, PaginacionBean paginacion) {
		
		List<Plato> resultado = null;
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Plato> query = criteriaBuilder.createQuery(Plato.class);
		Root<Plato> plato = query.from(Plato.class);		

		List<Predicate> predicates = new ArrayList<>();
		if(filtro.getNombre() != null){
		    Predicate condition = criteriaBuilder.like(plato.<String>get("nombre"),"%"+filtro.getNombre()+"%");
		    predicates.add(condition);
		}
		if(filtro.getTipo() != null && filtro.getTipo() > 0){
		    Predicate condition = criteriaBuilder.equal(plato.<String>get("tipo"),filtro.getTipo());
		    predicates.add(condition);
		}		
		
		
		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(plato).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		/**
		 * Devuelve solo los resultados que concidan (parcialmente - por los comodines) con ALGUNO los elementos del filtro
		 */
		//query.select(constante).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		
		if(paginacion != null) {
			
			//resultado = em.createQuery(query).getResultList().subList(paginacion.getInicio(), (paginacion.getInicio() + paginacion.getElementosXpagina()));
			resultado = em.createQuery(query).setFirstResult(paginacion.getInicio()).setMaxResults(paginacion.getElementosXpagina()).getResultList();
			
		}else {
			
			resultado = em.createQuery(query).getResultList();
		}
		
		return resultado; 
	}

}
