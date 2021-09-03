package es.golemdr.wittytool.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Anotacion;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;


@Repository
public interface AnotacionesRepositoryCustom{

	List<Anotacion> findAnotaciones(Anotacion anotacion, PaginacionBean paginacion);

}