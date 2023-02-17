package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletRimozioneSensore", value = "/ServletRimozioneSensore")
public class ServletRimozioneSensore extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("sensoreDaRimuovere") != null) {
            String sensoreDaRimuovere = request.getParameter("sensoreDaRimuovere");
            AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
            SensoreManager sm = new SensoreManager();
            List<SensoreBean> sensoreBeanList = sm.visualizzaListaSensori(aziendaBean.getEmail());
            SensoreBean SensoreDaRimuovere = null;
            for (int i = 0; i < sensoreBeanList.size(); i++) {
                if (sensoreBeanList.get(i).getId() == Integer.valueOf(sensoreDaRimuovere)) {
                    SensoreDaRimuovere = sensoreBeanList.get(i);
                    break;
                }
            }
            try {
                SensoreDaRimuovere.setColtivazione(null);
                sm.cancellaAssociazioneSensore(SensoreDaRimuovere);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("Coltivazione.jsp");
        }
    }
}
