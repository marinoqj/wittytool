package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Evento;
import es.golemdr.wittytool.repository.custom.EventosRepositoryCustom;

@Repository
public interface EventosRepository extends JpaRepository<Evento, Long>, EventosRepositoryCustom{

}
