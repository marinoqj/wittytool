package es.golemdr.wittytool.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Incidencia;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;


@Repository
public interface IncidenciasRepositoryCustom{

	List<Incidencia> findIncidencias(Incidencia incidencia, PaginacionBean paginacion);

}
