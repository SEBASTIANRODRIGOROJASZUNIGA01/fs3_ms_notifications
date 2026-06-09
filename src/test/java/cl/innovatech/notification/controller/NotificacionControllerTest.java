package cl.innovatech.notification.controller;

import cl.innovatech.notification.model.Notificacion;
import cl.innovatech.notification.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionControllerTest {

    @Mock
    private NotificacionService service;

    @InjectMocks
    private NotificacionController controller;

    @Test
    void testListarTodas_DebeRetornarLista() {
        Notificacion notif = Notificacion.builder().id(1L).titulo("Test").mensaje("Msg").build();
        when(service.listarTodas()).thenReturn(Collections.singletonList(notif));

        List<Notificacion> result = controller.listarTodas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getTitulo());
        verify(service, times(1)).listarTodas();
    }

    @Test
    void testCrear_DebeRetornarNotificacionCreada() {
        Notificacion notif = Notificacion.builder().titulo("Nueva").mensaje("Msg").build();
        when(service.crear(any(Notificacion.class))).thenReturn(notif);

        Notificacion result = controller.crear(notif);

        assertNotNull(result);
        assertEquals("Nueva", result.getTitulo());
        verify(service, times(1)).crear(any(Notificacion.class));
    }

    @Test
    void testMarcarComoLeida_DebeRetornarNotificacionActualizada() {
        Notificacion notif = Notificacion.builder().id(1L).titulo("Test").leida(true).build();
        when(service.marcarComoLeida(1L)).thenReturn(notif);

        Notificacion result = controller.marcarComoLeida(1L);

        assertNotNull(result);
        assertTrue(result.getLeida());
        verify(service, times(1)).marcarComoLeida(1L);
    }
}
