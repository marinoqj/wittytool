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

import es.golemdr.wittytool.domain.Bitacora;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.repository.custom.BitacorasRepositoryCustom;



public class BitacorasRepositoryCustomImpl implements BitacorasRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

	@Transactional
	public List<Bitacora> findBitacoras(Bitacora filtro, PaginacionBean paginacion) {

		List<Bitacora> resultado = null;

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Bitacora> query = criteriaBuilder.createQuery(Bitacora.class);
		Root<Bitacora> bitacora = query.from(Bitacora.class);



		List<Predicate> predicates = new ArrayList<>();

        // TODO - Implementar campos de busqueda




		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(bitacora).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

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
