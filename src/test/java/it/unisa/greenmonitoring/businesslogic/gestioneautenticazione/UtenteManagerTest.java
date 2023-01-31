package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UtenteManagerTest {

    private DipendenteBean dipendenteBean;

    private String codiceAzienda;

    @Mock
    private AziendaDAO aziendaDAO;

    @Mock
    private DipendenteDAO dipendenteDAO;

    @InjectMocks
    private UtenteManager utenteManager;

    @Before
    public void setUp() throws Exception {
        dipendenteBean = new DipendenteBean();
        dipendenteBean.setNome("Giacomo");
        dipendenteBean.setCognome("Segantini");
        dipendenteBean.setCitta("Benevento");
        dipendenteBean.setEmail("utente@email.com");
        dipendenteBean.setIndirizzo("Via Garibaldi");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void associazioneDipendente1() throws SQLException {
    }

    @Test
    public void associazioneDipendente2() throws SQLException {
        codiceAzienda = "5";
        Mockito.when(aziendaDAO.retrieveByCode(codiceAzienda)).thenReturn(null);
        boolean result = utenteManager.associazioneDipendente(dipendenteBean, codiceAzienda);
        Assert.assertFalse(result);
    }

    @Test
    public void associazioneDipendente3() throws SQLException {
    }

    @Test
    public void associazioneDipendente4() throws SQLException {
        codiceAzienda = "6";
        Mockito.when(aziendaDAO.retrieveByCode(codiceAzienda)).thenReturn(new AziendaBean());
        boolean result = utenteManager.associazioneDipendente(dipendenteBean, codiceAzienda);
        Mockito.verify(dipendenteDAO).doUpdate(dipendenteBean);
        Assert.assertTrue(result);
    }
}