package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Anotacion;
import es.golemdr.wittytool.repository.custom.AnotacionesRepositoryCustom;

@Repository
public interface AnotacionesRepository extends JpaRepository<Anotacion, Long>, AnotacionesRepositoryCustom{

}
