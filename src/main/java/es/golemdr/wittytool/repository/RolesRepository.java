package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Rol;
import es.golemdr.wittytool.repository.custom.RolesRepositoryCustom;

@Repository
public interface RolesRepository extends JpaRepository<Rol, Long>, RolesRepositoryCustom{

}
