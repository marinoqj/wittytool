package es.golemdr.wittytool.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Sprint;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;


@Repository
public interface SprintsRepositoryCustom{

	List<Sprint> findSprints(Sprint sprint, PaginacionBean paginacion);

}
