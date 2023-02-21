package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
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

import java.io.InputStream;

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
    @Mock
    private InputStream input;

    @InjectMocks
    private TerrenoServlet servlet;

    @Before
    public void setUp() throws Exception {
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test //latitudine sbagliata per formato
    public void InserisciTerreno1() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "jjjjdfff");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //longitudine sbagliata per formato
    public void InserisciTerreno2() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "ssssssss");
        request.setParameter("superfice", "50");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("2", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //superfice sbagliata
    public void InserisciTerreno3() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "dddssd");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("3", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //nome sbagliato
    public void InserisciTerreno4() throws Exception {
        request.setParameter("nome", "w");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");
        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("4", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //latitudine sbagliata per range
    public void InserisciTerreno5() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "100.0000");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("5", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //longitudine sbagliata per valore
    public void InserisciTerreno6() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "190");
        request.setParameter("superfice", "50");

        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("6", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //immagine sbagliata
    public void InserisciTerreno7() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");
        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(part.getSize()).thenReturn(0L);
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("7", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //immagine sbagliata
    public void InserisciTerreno8() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");
        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(part.getSize()).thenReturn(2L);
        Mockito.when(part.getContentType()).thenReturn("pdf");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("8", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }

    @Test //
    public void InserisciTerreno9() throws Exception {
        request.setParameter("nome", "terreno 1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");
        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(part.getSize()).thenReturn(1L);
        Mockito.when(part.getContentType()).thenReturn("image");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(terrenoManager.inserisciTerreno(any(TerrenoBean.class))).thenReturn(null);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("9", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }



    @Test //
    public void InserisciTerrenoSuccess() throws Exception {
        request.setParameter("nome", "terreno1");
        request.setParameter("azienda", "techenegative@gmail.com");
        request.setParameter("latitudine", "40.770");
        request.setParameter("longitudine", "14.7972");
        request.setParameter("superfice", "50");
        request.setParameter("inserisciTerreno_submit","1");
        Mockito.when(part.getSize()).thenReturn(2L);
        Mockito.when(part.getContentType()).thenReturn("image");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(terrenoManager.inserisciTerreno(any(TerrenoBean.class))).thenReturn(new TerrenoBean());
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("10", request.getAttribute("conferma"));
        assertEquals(200, response.getStatus());
    }
}