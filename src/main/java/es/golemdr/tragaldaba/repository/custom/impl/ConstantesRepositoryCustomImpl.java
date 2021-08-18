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

import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.repository.custom.ConstantesRepositoryCustom;




public class ConstantesRepositoryCustomImpl implements ConstantesRepositoryCustom{
	
    @PersistenceContext
    private EntityManager em;

	@Transactional
	public List<Constante> findConstantes(Constante filtro, PaginacionBean paginacion) {
		
		List<Constante> resultado = null;
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Constante> query = criteriaBuilder.createQuery(Constante.class);
		Root<Constante> constante = query.from(Constante.class);		

		List<Predicate> predicates = new ArrayList<>();
		if(filtro.getFamilia() != null){
		    Predicate condition = criteriaBuilder.like(constante.<String>get("familia"),"%"+filtro.getFamilia()+"%");
		    predicates.add(condition);
		}
		if(filtro.getAtributo() != null){
		    Predicate condition = criteriaBuilder.like(constante.<String>get("atributo"),"%"+filtro.getAtributo()+"%");
		    predicates.add(condition);
		}		
		if(filtro.getLiteral() != null){
		    Predicate condition = criteriaBuilder.like(constante.<String>get("literal"),"%"+filtro.getLiteral()+"%");
		    predicates.add(condition);
		}		
		
		
		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(constante).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
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
