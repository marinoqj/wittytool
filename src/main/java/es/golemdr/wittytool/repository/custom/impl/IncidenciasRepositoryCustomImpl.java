package es.golemdr.wittytool.repository.custom.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import es.golemdr.wittytool.domain.Incidencia;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.repository.custom.IncidenciasRepositoryCustom;



public class IncidenciasRepositoryCustomImpl implements IncidenciasRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

	@Transactional
	public List<Incidencia> findIncidencias(Incidencia filtro, PaginacionBean paginacion) {

		List<Incidencia> resultado = null;

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Incidencia> query = criteriaBuilder.createQuery(Incidencia.class);
		Root<Incidencia> incidencia = query.from(Incidencia.class);



		List<Predicate> predicates = new ArrayList<>();

        // TODO - Implementar campos de busqueda




		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(incidencia).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

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
