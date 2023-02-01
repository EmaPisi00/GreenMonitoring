package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.ColtivazioneDAO;
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

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ColtivazioneManagerTest {

    private ColtivazioneBean coltivazioneBean;

    @Mock
    private ColtivazioneDAO coltivazioneDao;
    @InjectMocks
    private ColtivazioneManager avvioColtivazioneManager;
    @Before
    public void setUp() throws Exception {
        coltivazioneBean = new ColtivazioneBean();
        coltivazioneBean.setPianta(1);
        coltivazioneBean.setTerreno(1);
        coltivazioneBean.setStato_archiviazione((byte) 0);
        coltivazioneBean.setData_inizio(Date.valueOf("2023-01-21"));
        coltivazioneBean.setData_fine(null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void avvioColtivazione() throws SQLException {
        ColtivazioneBean actualColtivazione = avvioColtivazioneManager.avvioColtivazione(coltivazioneBean);
        Mockito.verify(coltivazioneDao).createColtivazione(coltivazioneBean);
        assertEquals(coltivazioneBean, actualColtivazione);
    }
}




/*


        sensoreBean = new SensoreBean();

        sensoreBean.setId(19);
        sensoreBean.setAzienda("SVN");
        sensoreBean.setTipo("Umidità");
        sensoreBean.setColtivazione(1);
        sensoreBean.setIdM("69mmt");
 */