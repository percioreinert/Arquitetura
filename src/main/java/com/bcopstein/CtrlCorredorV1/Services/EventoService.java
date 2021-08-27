package com.bcopstein.CtrlCorredorV1.Services;

import com.bcopstein.CtrlCorredorV1.Entities.EstatísticasDTO;
import com.bcopstein.CtrlCorredorV1.Entities.Evento;
import com.bcopstein.CtrlCorredorV1.Repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EstatísticasDTO estatisticas(int distancia) {
        List<Evento> eventos = eventoRepository.findByDistancia(distancia);

        LocalTime media = media(eventos);
        double mediana = mediana(eventos);
        double desvioPadrao = desvioPadrao(eventos);

        return new EstatísticasDTO(media, mediana, desvioPadrao, eventos);
    }

    private LocalTime media(List<Evento> eventos) {
        long segundos = 0;
        for (Evento e : eventos) {
            segundos += e.getSecondsFromTime();
        }
        segundos /= eventos.size();
        return LocalTime.ofSecondOfDay(segundos);
    }

    private double mediana(List<Evento> eventos) {

        return 0.0;
    }

    private double desvioPadrao(List<Evento> eventos) {

        return 0.0;
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public boolean addEvento(Evento evento) {
        return eventoRepository.addEvento(evento);
    }
}