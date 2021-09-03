package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Funcionalidad;
import es.golemdr.wittytool.repository.custom.FuncionalidadesRepositoryCustom;

@Repository
public interface FuncionalidadesRepository extends JpaRepository<Funcionalidad, Long>, FuncionalidadesRepositoryCustom{

}
