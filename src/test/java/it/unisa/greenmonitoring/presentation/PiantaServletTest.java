package it.unisa.greenmonitoring.presentation;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
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
public class PiantaServletTest {
    @Spy
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Mock
    private PiantaManager piantaManager;
    @Mock
    private Part part;
    @Mock
    private InputStream input;
    @InjectMocks
    private PiantaServlet servlet;


    @Before
    public void setUp() throws Exception {
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test //ph minimo Formato
    public void  inserisciPiantaTest1() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "cc");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //ph max Formato
    public void  inserisciPiantaTest2() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "12");
        request.setParameter("ph_max" , "cc");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("2", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //temp min Formato
    public void  inserisciPiantaTest3() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "cc");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("3", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //temp max Formato
    public void  inserisciPiantaTest4() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "cc");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("4", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //umidità min Formato
    public void  inserisciPiantaTest5() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "cc");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("5", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //umidita max Formato
    public void  inserisciPiantaTest6() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "cc");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("6", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //nome lunghezza Formato
    public void  inserisciPiantaTest7() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "1");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("7", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //ph max range
    public void  inserisciPiantaTest8() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "1111");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("8", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //temp max range
    public void  inserisciPiantaTest9() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "1122");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("9", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //umidita max range
    public void  inserisciPiantaTest10() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "carina");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "1231");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("10", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //descrizione lunghezza
    public void  inserisciPiantaTest11() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("11", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //immagine dim error
    public void  inserisciPiantaTest12() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "descriz");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(part.getSize()).thenReturn(0L);
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("12", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //immagine tipo error
    public void  inserisciPiantaTest13() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "descriz");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(part.getSize()).thenReturn(2L);
        Mockito.when(part.getContentType()).thenReturn("pdf");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("13", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //piantaManager= null
    public void  inserisciPiantaTest14() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "descriz");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(part.getSize()).thenReturn(2L);
        Mockito.when(part.getContentType()).thenReturn("image");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(piantaManager.inserisciPianta(any(PiantaBean.class))).thenReturn(null);
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("14", request.getAttribute("errore"));
        assertEquals(200, response.getStatus());
    }
    @Test //tutto ok
    public void  inserisciPiantaTest15() throws Exception{
        request.getSession().setAttribute("currentUserSession",new UtenteBean());
        request.setParameter("nome" , "rosa");
        request.setParameter("descrizione" , "descriz");
        request.setParameter("ph_min" , "11");
        request.setParameter("ph_max" , "12");
        request.setParameter("temperatura_min" , "11");
        request.setParameter("temperatura_max" , "12");
        request.setParameter("umidita_min" , "11");
        request.setParameter("umidita_max" , "12");
        request.setParameter("inserisciPianta_submit" , "1");

        Mockito.when(part.getSize()).thenReturn(2L);
        Mockito.when(part.getContentType()).thenReturn("image");
        Mockito.when(request.getPart("immagine")).thenReturn(part);
        Mockito.when(piantaManager.inserisciPianta(any(PiantaBean.class))).thenReturn(new PiantaBean());
        Mockito.when(input.readAllBytes()).thenReturn(new byte[5]);
        Mockito.when(part.getInputStream()).thenReturn(input);
        servlet.doPost(request, response);
        assertEquals("15", request.getAttribute("conferma"));
        assertEquals(200, response.getStatus());
    }



}


