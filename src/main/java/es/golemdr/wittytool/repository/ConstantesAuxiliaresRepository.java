package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.ConstanteAuxiliar;
import es.golemdr.wittytool.repository.custom.ConstantesAuxiliaresRepositoryCustom;

@Repository
public interface ConstantesAuxiliaresRepository extends JpaRepository<ConstanteAuxiliar, Long>, ConstantesAuxiliaresRepositoryCustom{

}
