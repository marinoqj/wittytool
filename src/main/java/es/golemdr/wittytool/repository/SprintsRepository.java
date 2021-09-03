package es.golemdr.wittytool.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.wittytool.domain.Sprint;
import es.golemdr.wittytool.repository.custom.SprintsRepositoryCustom;

@Repository
public interface SprintsRepository extends JpaRepository<Sprint, Long>, SprintsRepositoryCustom{

}
