package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Usuario;
import es.golemdr.wittytool.repository.UsuariosRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class UsuariosService {

		@Autowired
		private UsuariosRepository usuariosRepository;


		public List<Usuario> getUsuarios() {

			return usuariosRepository.findAll();

		}


		public List<Usuario> getUsuarios(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalUsuarios());

			return usuariosRepository.findAll(paginacion).getContent();

		}


		public int getTotalUsuarios(){

			return usuariosRepository.findAll().size();

		}


		public Usuario insertarActualizarUsuario(Usuario usuario) {

			return usuariosRepository.save(usuario);

		}


		public Usuario getById(Long idUsuario) {

			Usuario resultado = null;

			Optional<Usuario> usuario = usuariosRepository.findById(idUsuario);

			if(usuario.isPresent()) {
				resultado = usuario.get();
			}

			return resultado;

		}


		public void borrarUsuario(Long idUsuario) {

			usuariosRepository.deleteById(idUsuario);

		}

		public List<Usuario> findUsuariosByExample(Usuario usuario, PaginacionBean paginacion) {

			return usuariosRepository.findUsuarios(usuario, paginacion);

		}

		public int countUsuariosByExample(Usuario usuario) {

			return usuariosRepository.findUsuarios(usuario, null).size();

		}

}

