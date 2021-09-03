package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Rol;
import es.golemdr.wittytool.repository.RolesRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class RolesService {

		@Autowired
		private RolesRepository rolesRepository;


		public List<Rol> getRoles() {

			return rolesRepository.findAll();

		}


		public List<Rol> getRoles(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalRoles());

			return rolesRepository.findAll(paginacion).getContent();

		}


		public int getTotalRoles(){

			return rolesRepository.findAll().size();

		}


		public Rol insertarActualizarRol(Rol rol) {

			return rolesRepository.save(rol);

		}


		public Rol getById(Long idRol) {

			Rol resultado = null;

			Optional<Rol> rol = rolesRepository.findById(idRol);

			if(rol.isPresent()) {
				resultado = rol.get();
			}

			return resultado;

		}


		public void borrarRol(Long idRol) {

			rolesRepository.deleteById(idRol);

		}

		public List<Rol> findRolesByExample(Rol rol, PaginacionBean paginacion) {

			return rolesRepository.findRoles(rol, paginacion);

		}

		public int countRolesByExample(Rol rol) {

			return rolesRepository.findRoles(rol, null).size();

		}

}

