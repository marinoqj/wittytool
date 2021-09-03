package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Sprint;
import es.golemdr.wittytool.repository.SprintsRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class SprintsService {

		@Autowired
		private SprintsRepository sprintsRepository;


		public List<Sprint> getSprints() {

			return sprintsRepository.findAll();

		}


		public List<Sprint> getSprints(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalSprints());

			return sprintsRepository.findAll(paginacion).getContent();

		}


		public int getTotalSprints(){

			return sprintsRepository.findAll().size();

		}


		public Sprint insertarActualizarSprint(Sprint sprint) {

			return sprintsRepository.save(sprint);

		}


		public Sprint getById(Long idSprint) {

			Sprint resultado = null;

			Optional<Sprint> sprint = sprintsRepository.findById(idSprint);

			if(sprint.isPresent()) {
				resultado = sprint.get();
			}

			return resultado;

		}


		public void borrarSprint(Long idSprint) {

			sprintsRepository.deleteById(idSprint);

		}

		public List<Sprint> findSprintsByExample(Sprint sprint, PaginacionBean paginacion) {

			return sprintsRepository.findSprints(sprint, paginacion);

		}

		public int countSprintsByExample(Sprint sprint) {

			return sprintsRepository.findSprints(sprint, null).size();

		}

}

