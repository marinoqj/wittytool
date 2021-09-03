package es.golemdr.wittytool.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.ConstanteAuxiliar;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;


@Repository
public interface ConstantesAuxiliaresRepositoryCustom{

	List<ConstanteAuxiliar> findConstantesAuxiliares(ConstanteAuxiliar constanteauxiliar, PaginacionBean paginacion);

}
