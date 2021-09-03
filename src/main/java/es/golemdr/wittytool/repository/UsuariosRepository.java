package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import es.golemdr.wittytool.domain.Usuario;
import es.golemdr.wittytool.repository.custom.UsuariosRepositoryCustom;





public interface UsuariosRepository extends JpaRepository<Usuario, Long>, UsuariosRepositoryCustom{

}
