package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AutenticazioneManagerTest {

    private AziendaBean aziendaBean;

    private DipendenteBean dipendenteBean;
    @Mock
    private AziendaDAO aziendaDao;
    @Mock
    private DipendenteDAO dipendenteDAO;
    @InjectMocks
    private AutenticazioneManager autenticazioneManager;
    @Before
    public void setUp() throws Exception {
        aziendaBean = new AziendaBean();
        aziendaBean.setNome_azienda("SVN");
        aziendaBean.setPartita_iva("12345678901");
        aziendaBean.setEmail("SVN@gmail.com");
        aziendaBean.setCitta("Battipaglia");
        aziendaBean.setIndirizzo("via roma");
        aziendaBean.setPassword("SVNuduj156@");
        aziendaBean.setCodice_associazione("AZ456562");
        aziendaBean.setProvincia("SA");
        aziendaBean.setTelefono("23458246");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registraAzienda() throws SQLException {
        Mockito.when(aziendaDao.retrieveForKey(aziendaBean.getEmail())).thenReturn(new AziendaBean());
        AziendaBean az = autenticazioneManager.registraAzienda(aziendaBean);
        Mockito.verify(aziendaDao).create(aziendaBean);
        assertEquals(az,aziendaBean);
    }
    @Test
    public void registraAzienda2() throws SQLException {
        aziendaBean.setNome_azienda("55555555555555");
        AziendaBean az = autenticazioneManager.registraAzienda(aziendaBean);
        assertNull(az);
    }

    @Test
    public void registraDipendente() {
    }

}