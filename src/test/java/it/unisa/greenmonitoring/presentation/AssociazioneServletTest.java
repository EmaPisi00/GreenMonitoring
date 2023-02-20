package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.UtenteManager;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class AssociazioneServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Mock
    UtenteManager utenteManager;
    @InjectMocks
    AssociazioneServlet associazioneServlet;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown() {
    }

    //Il codice rispetta il formato e ha successo in quanto è presente nel db.
    @Test
    public void associazioneDipendenteSuccess() throws Exception {
        DipendenteBean dipendenteBean = new DipendenteBean();
        request.getSession().setAttribute("currentUserSession", dipendenteBean);
        request.setParameter("codiceAzienda", "ASDdd234");
        Mockito.when(utenteManager.associazioneDipendente(eq(dipendenteBean), any(String.class))).thenReturn(true);
        associazioneServlet.doPost(request, response);
        Assert.assertEquals("4", request.getAttribute("errore"));
        Assert.assertEquals(200, response.getStatus());
    }
    // TC_1.1_1 Il dipendente inserisce un codice azienda che non rispetta il formato.
    @Test
    public void associazioneDipendente2() throws Exception {
        DipendenteBean dipendenteBean = new DipendenteBean();
        request.getSession().setAttribute("currentUserSession", dipendenteBean);
        request.setParameter("codiceAzienda", "");
        associazioneServlet.doPost(request, response);
        Assert.assertEquals("2", request.getAttribute("errore"));
        Assert.assertEquals(200, response.getStatus());
    }
    // TC_1.1_3 Il dipendente inserisce un codice azienda non presente sul db.
    @Test
    public void associazioneDipendente3() throws Exception {
        DipendenteBean dipendenteBean = new DipendenteBean();
        request.getSession().setAttribute("currentUserSession", dipendenteBean);
        request.setParameter("codiceAzienda", "ASDdd234");
        Mockito.when(utenteManager.associazioneDipendente(eq(dipendenteBean), any(String.class))).thenReturn(false);
        associazioneServlet.doPost(request, response);
        Assert.assertEquals("3", request.getAttribute("errore"));
        Assert.assertEquals(200, response.getStatus());
    }

    // TC_1.1_2 Il dipendente inserisce un codice azienda non presente sul db.
    @Test
    public void associazioneDipendente1() throws Exception {
        DipendenteBean dipendenteBean = new DipendenteBean();
        dipendenteBean.setAzienda("impresa@host.dominio");
        request.getSession().setAttribute("currentUserSession", dipendenteBean);
        request.setParameter("codiceAzienda", "ASDdd234");
        associazioneServlet.doPost(request, response);
        Assert.assertEquals("1", request.getAttribute("errore"));
        Assert.assertEquals(200, response.getStatus());
    }
}
