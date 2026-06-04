package cl.innovatech.notification.controller;

import cl.innovatech.notification.model.Notificacion;
import cl.innovatech.notification.service.NotificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notificacion> listarTodas() {
        return service.listarTodas();
    }

    @PostMapping
    public Notificacion crear(@RequestBody Notificacion notificacion) {
        return service.crear(notificacion);
    }

    @PutMapping("/{id}/leer")
    public Notificacion marcarComoLeida(@PathVariable Long id) {
        return service.marcarComoLeida(id);
    }
}