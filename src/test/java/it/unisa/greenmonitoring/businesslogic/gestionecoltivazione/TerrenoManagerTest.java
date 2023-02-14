package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAO;
import net.bytebuddy.build.Plugin;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TerrenoManagerTest {

    private TerrenoBean terrenoBean;

    @Mock
    private TerrenoDAO terrenoDAO;

    @InjectMocks
    private TerrenoManager terrenoManager;

    @Before
    public void setUp() throws Exception {
        terrenoBean = new TerrenoBean();
        terrenoBean.setId(1234);
        terrenoBean.setLongitudine(3F);
        terrenoBean.setLatitudine(54F);
        terrenoBean.setAzienda("mariorui");
        terrenoBean.setSuperficie("40");
        terrenoBean.setNome("giggino");
        terrenoBean.setImmagine("imm.jpg");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTerrenoSuccess() throws SQLException{
        Mockito.when(terrenoDAO.retrieveAll()).thenReturn(new ArrayList<TerrenoBean>());
        TerrenoBean td = terrenoManager.inserisciTerreno(terrenoBean);
        Mockito.verify(terrenoDAO).createTerreno(terrenoBean);
        assertEquals(td,terrenoBean);
    }
    @Test
    public void createTerrenoSuperficieError() throws SQLException{
        terrenoBean.setSuperficie("pippo");
        TerrenoBean result = terrenoManager.inserisciTerreno(terrenoBean);
        assertNull(result);
    }

    @Test
    public void createTerrenoLongitudineError() throws SQLException{
        terrenoBean.setLongitudine(190F);
        TerrenoBean result = terrenoManager.inserisciTerreno(terrenoBean);
        assertNull(result);
    }

    @Test
    public void createTerrenoLatitudineError() throws SQLException{
        terrenoBean.setLatitudine(100F);
        TerrenoBean result = terrenoManager.inserisciTerreno(terrenoBean);
        assertNull(result);
    }

    @Test
    public void createTerrenoNomeError() throws SQLException{
        terrenoBean.setNome("a");
        TerrenoBean result = terrenoManager.inserisciTerreno(terrenoBean);
        assertNull(result);
    }
}

