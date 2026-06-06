package cl.innovatech.notification.service;

import cl.innovatech.notification.model.Notificacion;
import cl.innovatech.notification.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService service;

    private Notificacion notifPrueba;

    @BeforeEach
    void setUp() {
        notifPrueba = Notificacion.builder()
                .id(1L)
                .titulo("Test Notification")
                .mensaje("This is a test notification")
                .tipo("SOBRECARGA")
                .leida(false)
                .build();
    }

    @Test
    void testListarTodas_DebeRetornarLista() {
        when(repository.findAll()).thenReturn(Collections.singletonList(notifPrueba));

        List<Notificacion> result = service.listarTodas();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Notification", result.get(0).getTitulo());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testCrear_DebeAsignarFechaYGuardar() {
        when(repository.save(any(Notificacion.class))).thenReturn(notifPrueba);

        Notificacion result = service.crear(notifPrueba);
        assertNotNull(result);
        assertNotNull(result.getFecha());
        assertFalse(result.getLeida());
        verify(repository, times(1)).save(notifPrueba);
    }

    @Test
    void testMarcarComoLeida_DebeCambiarEstadoYGuardar() {
        when(repository.findById(1L)).thenReturn(Optional.of(notifPrueba));
        when(repository.save(any(Notificacion.class))).thenReturn(notifPrueba);

        Notificacion result = service.marcarComoLeida(1L);
        assertNotNull(result);
        assertTrue(result.getLeida());
        verify(repository, times(1)).save(any(Notificacion.class));
    }


}
