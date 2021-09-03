package es.golemdr.wittytool.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Usuario;
import es.golemdr.wittytool.repository.UsuariosRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		Usuario datosUsuario = null;
		String[] rolesUsuario = null;

		Usuario usuarioLogin = new Usuario();
		usuarioLogin.setLogin(login);

		Example<Usuario> example = Example.of(usuarioLogin);

		// TODO -Hacer que funcione con JPA
		
		//datosUsuario = usuariosRepository.find(example).get();
		
		datosUsuario = usuariosRepository.recuperarUsuarioLogin(login);

		if (datosUsuario == null) {

			throw new UsernameNotFoundException("Usuario no encontrado");
		}

//		if (datosUsuario.getRoles().size() > 0) {
//
//			rolesUsuario = new String[datosUsuario.getRoles().size()];
//
//			for (int i = 0; i < datosUsuario.getRoles().size(); i++) {
//
//				rolesUsuario[i] = datosUsuario.getRoles().get(i).getNombreRol();
//
//			}
//
//		}
		
		rolesUsuario = new String[1];
		rolesUsuario[0] = "ADMIN";
		

		UserDetails securityUser = User.withUsername(login).password(new BCryptPasswordEncoder().encode(datosUsuario.getPassword())).roles(rolesUsuario).build();

		return securityUser;

	}
		

}

