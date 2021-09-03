package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Incidencia;
import es.golemdr.wittytool.repository.custom.IncidenciasRepositoryCustom;

@Repository
public interface IncidenciasRepository extends JpaRepository<Incidencia, Long>, IncidenciasRepositoryCustom{

}
