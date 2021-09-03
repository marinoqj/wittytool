package es.golemdr.wittytool.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Rol;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;


@Repository
public interface RolesRepositoryCustom{

	List<Rol> findRoles(Rol rol, PaginacionBean paginacion);

}
