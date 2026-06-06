package cl.innovatech.notification.service;

import cl.innovatech.notification.model.Notificacion;
import cl.innovatech.notification.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notificacion> listarTodas() {
        return repository.findAll();
    }

    public Notificacion crear(Notificacion notificacion) {

        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeida(false);

        return repository.save(notificacion);
    }

    public Notificacion marcarComoLeida(Long id) {

        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notificacion.setLeida(true);

        return repository.save(notificacion);
    }
}