
package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ColtivazioneManagerTest {

    private ColtivazioneBean coltivazioneBean;
    private SensoreBean sensoreBean;
    private PiantaBean piantaBean;
    private TerrenoBean terrenoBean;

    private String azienda;
    private ArrayList<SensoreBean> listaSensori;
    @Mock
    private ColtivazioneDAO coltivazioneDao;
    @Mock
    private PiantaDAO piantaDAO;
    @Mock
    private TerrenoDAO terrenoDAO;


    @InjectMocks
    private ColtivazioneManager coltivazioneManager;
    @Before
    public void setUp() throws Exception {

        sensoreBean = new SensoreBean();
        sensoreBean.setId(19);
        sensoreBean.setAzienda("SVN");
        sensoreBean.setTipo("Umidità");
        sensoreBean.setColtivazione(null);
        sensoreBean.setIdM("69mmt");

        coltivazioneBean = new ColtivazioneBean();
        coltivazioneBean.setId(1);
        coltivazioneBean.setPianta(1);
        coltivazioneBean.setTerreno(1);
        coltivazioneBean.setStato_archiviazione((byte) 0);
        coltivazioneBean.setData_inizio(Date.valueOf("2023-01-21"));
        coltivazioneBean.setData_fine(null);

        listaSensori = new ArrayList<SensoreBean>();
        listaSensori.add(sensoreBean);

        piantaBean = new PiantaBean();
        terrenoBean = new TerrenoBean();
        azienda = "nome azienda";
        piantaBean.setId(1);
        terrenoBean.setId(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void avvioColtivazioneSuccess() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(piantaBean);
        Mockito.when(terrenoDAO.retrieveByKey(coltivazioneBean.getTerreno())).thenReturn(terrenoBean);
        Mockito.when(coltivazioneDao.retrieveColtivazione(azienda)).thenReturn(new ArrayList<>());
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, listaSensori);
        System.out.println(actualColtivazione);
        Mockito.verify(coltivazioneDao).createColtivazione(coltivazioneBean);
        assertEquals(coltivazioneBean, actualColtivazione);
    }

    @Test
    public void avvioColtivazionePiantaNull() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(null);
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, listaSensori);
        assertNull(actualColtivazione);
    }
    @Test
    public void avvioColtivazioneTerrenoNull() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(piantaBean);
        Mockito.when(terrenoDAO.retrieveByKey(coltivazioneBean.getTerreno())).thenReturn(null);
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, listaSensori);
        assertNull(actualColtivazione);
    }

    @Test
    public void avvioColtivazioneTerrenoOccupato() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(piantaBean);
        Mockito.when(terrenoDAO.retrieveByKey(coltivazioneBean.getTerreno())).thenReturn(terrenoBean);
        ArrayList<ColtivazioneBean> coltivazioneBeans = new ArrayList<>();
        coltivazioneBeans.add(coltivazioneBean);
        Mockito.when(coltivazioneDao.retrieveColtivazione(azienda)).thenReturn(coltivazioneBeans);
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, listaSensori);
        assertNull(actualColtivazione);
    }

    @Test
    public void avvioColtivazioneDataFutura() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(piantaBean);
        Mockito.when(terrenoDAO.retrieveByKey(coltivazioneBean.getTerreno())).thenReturn(terrenoBean);
        Mockito.when(coltivazioneDao.retrieveColtivazione(azienda)).thenReturn(new ArrayList<>());
        coltivazioneBean.setData_inizio(Date.valueOf("2024-01-01"));
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, listaSensori);
        assertNull(actualColtivazione);
    }

    @Test
    public void avvioColtivazioneListaSensoriVuota() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(piantaBean);
        Mockito.when(terrenoDAO.retrieveByKey(coltivazioneBean.getTerreno())).thenReturn(terrenoBean);
        Mockito.when(coltivazioneDao.retrieveColtivazione(azienda)).thenReturn(new ArrayList<>());
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, new ArrayList<SensoreBean>());
        assertNull(actualColtivazione);
    }

    @Test
    public void avvioColtivazioneListaSensoriOccupati() throws Exception {
        Mockito.when(piantaDAO.retrieveByKey(coltivazioneBean.getPianta())).thenReturn(piantaBean);
        Mockito.when(terrenoDAO.retrieveByKey(coltivazioneBean.getTerreno())).thenReturn(terrenoBean);
        Mockito.when(coltivazioneDao.retrieveColtivazione(azienda)).thenReturn(new ArrayList<>());
        sensoreBean.setColtivazione(4);
        ArrayList<SensoreBean> listaSensoriOccupati = new ArrayList<>();
        listaSensoriOccupati.add(sensoreBean);
        ColtivazioneBean actualColtivazione = coltivazioneManager.avvioColtivazione(coltivazioneBean, azienda, listaSensoriOccupati);
        assertNull(actualColtivazione);
    }

}
