package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Proyecto;
import es.golemdr.wittytool.repository.custom.ProyectosRepositoryCustom;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long>, ProyectosRepositoryCustom{

}
