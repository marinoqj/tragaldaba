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

import es.golemdr.tragaldaba.domain.Proveedor;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.repository.custom.ProveedoresRepositoryCustom;



public class ProveedoresRepositoryCustomImpl implements ProveedoresRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

	@Transactional
	public List<Proveedor> findProveedores(Proveedor filtro, PaginacionBean paginacion) {

		List<Proveedor> resultado = null;
		Predicate condition = null;

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Proveedor> query = criteriaBuilder.createQuery(Proveedor.class);
		Root<Proveedor> proveedor = query.from(Proveedor.class);



		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getRazonSocial() != null){
		    condition = criteriaBuilder.like(proveedor.<String>get("razonSocial"),"%"+filtro.getRazonSocial()+"%");
		    predicates.add(condition);
		}
		
		if(filtro.getCodPostal() != null && filtro.getCodPostal() > 0){
		    condition = criteriaBuilder.equal(proveedor.<Long>get("codPostal"),filtro.getCodPostal());
		    predicates.add(condition);
		}	
		
		if(filtro.getEmail() != null){
		    condition = criteriaBuilder.like(proveedor.<String>get("email"),"%"+filtro.getEmail()+"%");
		    predicates.add(condition);
		}




		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(proveedor).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

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
