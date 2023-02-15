package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneAziendaServletTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Mock
    private AutenticazioneManager autenticazioneManager;
    @InjectMocks
    private RegistrazioneAziendaServlet servlet;
    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test //email sbagliata
    public void registraAzienda1() throws Exception {
        request.setParameter("inputEmail", "azienda1@");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", ""); //nome azienda sbagliato
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //password sbagliata
    public void registraAzienda2() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "sss");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("2", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }
    @Test //conferma password sbagliata
    public void registraAzienda3() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("3", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //telefono sbagliato
    public void registraAzienda4() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "35625");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("4", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //città sbagliata
    public void registraAzienda5() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "@@@333-134|!");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("5", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //indirizzo sbagliato
    public void registraAzienda6() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "@!2656-");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("6", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //provincia sbagliata
    public void registraAzienda7() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "!|YTVFT!)(");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("7", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //nome azienda sbagliata
    public void registraAzienda8() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "@@@@@@@#à######");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        servlet.doPost(request, response);
        assertEquals("8", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //partita IVA sbagliata
    public void registraAzienda9() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "zzzzzz<xz<z@à");

        servlet.doPost(request, response);
        assertEquals("9", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test
    public void registraAziendaSuccess() throws Exception {
        request.setParameter("inputEmail", "azienda1@gmail.com");
        request.setParameter("inputPassword", "asd456JKL*");
        request.setParameter("confermaInputPassword", "asd456JKL*");
        request.setParameter("inputIndirizzo", "via fontanari");
        request.setParameter("inputTelefono", "3562548123");
        request.setParameter("inputCitta", "Grotta");
        request.setParameter("inputProvincia", "AV");
        request.setParameter("inputAzienda", "azienda");
        request.setParameter("codiceAzienda", "AC23LS23");
        request.setParameter("inputPartitaIva", "12324234721");

        Mockito.when(autenticazioneManager.registraAzienda(any(AziendaBean.class))).thenReturn(new AziendaBean());
        servlet.doPost(request, response);
        assertEquals("10", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }
}