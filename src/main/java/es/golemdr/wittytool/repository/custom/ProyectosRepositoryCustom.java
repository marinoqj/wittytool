package es.golemdr.wittytool.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Proyecto;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;


@Repository
public interface ProyectosRepositoryCustom{

	List<Proyecto> findProyectos(Proyecto proyecto, PaginacionBean paginacion);

}
