package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Bitacora;
import es.golemdr.wittytool.repository.custom.BitacorasRepositoryCustom;

@Repository
public interface BitacorasRepository extends JpaRepository<Bitacora, Long>, BitacorasRepositoryCustom{

}
