package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;
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

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ColtivazioniServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Mock
    private ColtivazioneManager coltivazioneManager;
    @Mock
    private PiantaManager piantaManager;
    @Mock
    private TerrenoManager terrenoManager;
    @Mock
    private SensoreManager sensoreManager;
    @InjectMocks
    private ColtivazioniServlet servlet;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown() {
    }

    @Test //formato id pianta sbagliato
    public void InserisciColtivazione1() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "a");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "2023-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        servlet.doPost(request, response);
        assertEquals("1", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //formato id terreno sbagliato
    public void InserisciColtivazione2() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "b");
        request.setParameter("datainizio", "2023-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        servlet.doPost(request, response);
        assertEquals("2", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //formato data inizio sbagliato (la data spostata alla fine anziché all'inizio)
    public void InserisciColtivazione3() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "25-01-2023");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        servlet.doPost(request, response);
        assertEquals("3", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //pianta inesistente
    public void InserisciColtivazione4() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "2023-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        Mockito.when(piantaManager.visualizzaPianta(anyInt())).thenReturn(null);
        servlet.doPost(request, response);
        assertEquals("4", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //terreno inesistente
    public void InserisciColtivazione5() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2333");
        request.setParameter("datainizio", "2023-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        Mockito.when(piantaManager.visualizzaPianta(anyInt())).thenReturn(new PiantaBean());
        Mockito.when(terrenoManager.restituisciTerrenoDaInt(anyInt())).thenReturn(null);

        servlet.doPost(request, response);
        assertEquals("5", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //data sbagliata, futura al giorno attuale
    public void InserisciColtivazione6() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "2024-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        Mockito.when(piantaManager.visualizzaPianta(anyInt())).thenReturn(new PiantaBean());
        Mockito.when(terrenoManager.restituisciTerrenoDaInt(anyInt())).thenReturn(new TerrenoBean());

        servlet.doPost(request, response);
        assertEquals("6", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //sensori non selezionati
    public void InserisciColtivazione7() throws Exception {
        request.getSession().setAttribute("currentUserSession",new AziendaBean());
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "2023-01-25");

        Mockito.when(piantaManager.visualizzaPianta(anyInt())).thenReturn(new PiantaBean());
        Mockito.when(terrenoManager.restituisciTerrenoDaInt(anyInt())).thenReturn(new TerrenoBean());

        servlet.doPost(request, response);
        assertEquals("7", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }

    @Test //avvioColtivazione non parte (es. lista sensori vuota)
    public void InserisciColtivazione8() throws Exception {
        AziendaBean aziendaBean = new AziendaBean();
        aziendaBean.setEmail("eee@ttt");
        request.getSession().setAttribute("currentUserSession", aziendaBean);
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "2023-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        Mockito.when(sensoreManager.visualizzaSensore(anyInt())).thenReturn(new SensoreBean());
        Mockito.when(piantaManager.visualizzaPianta(anyInt())).thenReturn(new PiantaBean());
        Mockito.when(terrenoManager.restituisciTerrenoDaInt(anyInt())).thenReturn(new TerrenoBean());
        Mockito.when(coltivazioneManager.avvioColtivazione(any(ColtivazioneBean.class), eq(aziendaBean.getEmail()), any(ArrayList.class))).thenReturn(null);
        servlet.doPost(request, response);
        assertEquals("8", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }


    @Test
    public void InserisciColtivazioneSuccess() throws Exception {
        AziendaBean aziendaBean = new AziendaBean();
        aziendaBean.setEmail("eee@ttt");
        request.getSession().setAttribute("currentUserSession", aziendaBean);
        request.setParameter("moduloInserimentoColtivazione", "1");
        request.setParameter("nomepianta", "2");
        request.setParameter("terreno", "2");
        request.setParameter("datainizio", "2023-01-25");
        request.setParameter("sensorePh", "15");
        request.setParameter("sensoreTemperatura", "16");
        request.setParameter("sensoreUmidita", "17");

        Mockito.when(sensoreManager.visualizzaSensore(anyInt())).thenReturn(new SensoreBean());
        Mockito.when(piantaManager.visualizzaPianta(anyInt())).thenReturn(new PiantaBean());
        Mockito.when(terrenoManager.restituisciTerrenoDaInt(anyInt())).thenReturn(new TerrenoBean());
        Mockito.when(coltivazioneManager.avvioColtivazione(any(ColtivazioneBean.class), eq(aziendaBean.getEmail()), any(ArrayList.class))).thenReturn(new ColtivazioneBean());
        servlet.doPost(request, response);
        assertEquals("9", request.getAttribute("errore"));
        assertEquals(200 , response.getStatus());
    }
}