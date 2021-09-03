package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Evento;
import es.golemdr.wittytool.repository.EventosRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class EventosService {

		@Autowired
		private EventosRepository eventosRepository;


		public List<Evento> getEventos() {

			return eventosRepository.findAll();

		}


		public List<Evento> getEventos(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalEventos());

			return eventosRepository.findAll(paginacion).getContent();

		}


		public int getTotalEventos(){

			return eventosRepository.findAll().size();

		}


		public Evento insertarActualizarEvento(Evento evento) {

			return eventosRepository.save(evento);

		}


		public Evento getById(Long idEvento) {

			Evento resultado = null;

			Optional<Evento> evento = eventosRepository.findById(idEvento);

			if(evento.isPresent()) {
				resultado = evento.get();
			}

			return resultado;

		}


		public void borrarEvento(Long idEvento) {

			eventosRepository.deleteById(idEvento);

		}

		public List<Evento> findEventosByExample(Evento evento, PaginacionBean paginacion) {

			return eventosRepository.findEventos(evento, paginacion);

		}

		public int countEventosByExample(Evento evento) {

			return eventosRepository.findEventos(evento, null).size();

		}

}

