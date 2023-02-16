/* package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Part;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TerrenoServletTest {

    @Spy
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Mock
    private TerrenoManager terrenoManager;
    @Mock
    private Part part;
    @InjectMocks
    private TerrenoServlet servlet;

    @Before
    public void setUp() throws Exception {
        //request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test //immagine sbagliata
    public void InserisciTerreno1() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(part.getSize()).thenReturn(0L);
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //nome sbagliato
    public void InserisciTerreno2() throws Exception {
        request.setParameter("nome", "w");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(part.getSize()).thenReturn(2L);
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        servlet.doPost(request, response);
        assertEquals("2", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //latitudine sbagliata per formato
    public void InserisciTerreno4() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("immagine", String.valueOf(part));
        request.setParameter("latitudine", "@@@@@dfff");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //latitudine sbagliata per range
    public void InserisciTerreno5() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("immagine", String.valueOf(part));
        request.setParameter("latitudine", "100.0000");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //longitudine sbagliata per formato
    public void InserisciTerreno6() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("immagine", String.valueOf(part));
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "ssssssss");
        request.setParameter("superfice", "50");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //longitudine sbagliata per formato
    public void InserisciTerreno7() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("immagine", String.valueOf(part));
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "190");
        request.setParameter("superfice", "50");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //superficie sbagliata
    public void InserisciTerreno8() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("immagine", String.valueOf(part));
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "dddssd");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
}*/