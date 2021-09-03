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

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import es.golemdr.wittytool.domain.Usuario;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.repository.custom.UsuariosRepositoryCustom;




public class UsuariosRepositoryCustomImpl implements UsuariosRepositoryCustom{
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional
	public Usuario recuperarUsuarioLogin(String login) {
		
		Usuario usuarioResult = null;
		
		try {
			
			Session session = em.unwrap(Session.class);
			
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT LOGIN, PASSWORD, NOMBRE, APELLIDO1, APELLIDO2 FROM USUARIOS WHERE LOGIN = ?");
			
			NativeQuery query = session.createNativeQuery(queryString.toString());
			
			query.setParameter(1, login);
			
			Object[] usuario = (Object[]) query.getSingleResult();
			
			usuarioResult = new Usuario();
			
			usuarioResult.setLogin(usuario[0].toString());
			usuarioResult.setPassword(usuario[1].toString());
			usuarioResult.setNombre(usuario[2].toString());
			usuarioResult.setApellido1(usuario[3].toString());
			usuarioResult.setApellido2(usuario[4].toString());			
					
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return usuarioResult;
	}
	
	@Transactional
	public List<Usuario> findUsuarios(Usuario filtro, PaginacionBean paginacion) {

		List<Usuario> resultado = null;

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuario = query.from(Usuario.class);



		List<Predicate> predicates = new ArrayList<>();

        // TODO - Implementar campos de busqueda




		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(usuario).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

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
