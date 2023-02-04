
package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.ColtivazioneDAO;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAO;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ColtivazioneManagerTest {

    private ColtivazioneBean coltivazioneBean;
    private ColtivazioneBean coltivazioneBeanPerLista;
    private SensoreBean sensoreBean;

    private PiantaBean piantaBean;
    private TerrenoBean terrenoBean;
    private ArrayList<PiantaBean> listaPiante;
    private ArrayList<TerrenoBean> terrenoBeann;
    private ArrayList<ColtivazioneBean> coltivazioneBeans;
    @Mock
    private ColtivazioneDAO coltivazioneDao;
    @Mock
    private PiantaDAO piantaDAO;
    @Mock
    private TerrenoDAO terrenoDAO;
    @InjectMocks
    private ColtivazioneManager avvioColtivazioneManager;
    @Before
    public void setUp() throws Exception {
    sensoreBean = new SensoreBean();

        sensoreBean.setId(19);
        sensoreBean.setAzienda("SVN");
        sensoreBean.setTipo("Umidità");
        sensoreBean.setColtivazione(1);
        sensoreBean.setIdM("69mmt");
        coltivazioneBean = new ColtivazioneBean();
        coltivazioneBean.setPianta(1);
        coltivazioneBean.setTerreno(1);
        coltivazioneBean.setStato_archiviazione((byte) 0);
        coltivazioneBean.setData_inizio(Date.valueOf("2023-01-21"));
        coltivazioneBean.setData_fine(null);
        coltivazioneBean.setInListaSensori(sensoreBean);

        piantaBean = new PiantaBean();
        listaPiante = new ArrayList<>();
        terrenoBean = new TerrenoBean();
        terrenoBeann = new ArrayList<>();
        coltivazioneBeans = new ArrayList<>();
        coltivazioneBeanPerLista = new ColtivazioneBean();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void avvioColtivazione() throws Exception {
        piantaBean.setId(1);
        listaPiante.add(piantaBean);
        terrenoBean.setId(1);
        terrenoBeann.add(terrenoBean);
        coltivazioneBeanPerLista.setTerreno(2);
        coltivazioneBeans.add(coltivazioneBeanPerLista);
        Mockito.when(piantaDAO.RetriveAllPiantaDefault()).thenReturn(listaPiante);
        Mockito.when(terrenoDAO.retrieveTerreno()).thenReturn(terrenoBeann);
        Mockito.when(coltivazioneDao.retrieveColtivazione("1")).thenReturn(coltivazioneBeans);
        ColtivazioneBean actualColtivazione = avvioColtivazioneManager.avvioColtivazione(coltivazioneBean,"1");
        Mockito.verify(coltivazioneDao).createColtivazione(coltivazioneBean);
        assertEquals(coltivazioneBean, actualColtivazione);
    }

}

