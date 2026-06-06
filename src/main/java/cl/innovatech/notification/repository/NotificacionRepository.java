package cl.innovatech.notification.repository;

import cl.innovatech.notification.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository
        extends JpaRepository<Notificacion, Long> {
}