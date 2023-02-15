package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ServletColtivazioni", value = "/ServletColtivazioni")
public class ServletColtivazioni extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getSession().getAttribute("currentUserSession") instanceof UtenteBean)) {
            response.sendError(401);
        }
        if (request.getParameter("coltivazione") != null) {
            request.getSession().setAttribute("coltivazioneID", request.getParameter("coltivazione"));
            response.sendRedirect("Coltivazione.jsp");
        }
    }

    /**
     * Metodo post.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getSession().getAttribute("currentUserSession") instanceof UtenteBean)) {
            response.sendError(401);
        } else if (request.getParameter("moduloInserimentoColtivazione") != null) {
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                if (request.getParameter("nomepianta") == null || request.getParameter("terreno") == null) {
                    String errore = "L'operazione non è stata eseguita: La pianta o il terreno sono vuoti.";
                    request.getSession().setAttribute("errore", errore);
                    response.sendRedirect("ListaColtivazioni.jsp");
                } else if (java.sql.Date.valueOf(request.getParameter("datainizio")).after(date)) {
                    String errore = "La data non è corretta.";
                    request.getSession().setAttribute("errore", errore);
                } else {
                    AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
                    String utente = aziendaBean.getEmail();
                    String nomepianta = request.getParameter("nomepianta"); //id
                    String[] sensorePh = request.getParameterValues("sensorePh"); //id
                    String[] sensoreTemperatura = request.getParameterValues("sensoreTemperatura"); //id
                    String[] sensoreUmidita = request.getParameterValues("sensoreUmidita"); //id
                    ArrayList<String> sensori = new ArrayList<>();
                    if (sensorePh != null) {
                        Collections.addAll(sensori, sensorePh);
                    }
                    if (sensoreTemperatura != null) {
                        Collections.addAll(sensori, sensoreTemperatura);
                    }
                    if (sensoreUmidita != null) {
                        Collections.addAll(sensori, sensoreUmidita);
                    }
                    String dataInizio = request.getParameter("datainizio");
                    java.sql.Date dataInizioDate = java.sql.Date.valueOf(dataInizio);
                    int terreno = Integer.parseInt(request.getParameter("terreno")); //id
                    ColtivazioneBean cb = new ColtivazioneBean();
                    cb.setPianta(Integer.valueOf(nomepianta));
                    cb.setStato_archiviazione(Byte.parseByte("0"));
                    cb.setTerreno(terreno);
                    SensoreManager sensoreManager = new SensoreManager();
                    ColtivazioneManager cm = new ColtivazioneManager();
                    cb.setData_inizio(dataInizioDate);
                    try {
                        ArrayList<SensoreBean> listaSensoriBean = new ArrayList<>();
                        for (String id_sensore : sensori) {
                            listaSensoriBean.add(sensoreManager.visualizzaSensore(Integer.parseInt(id_sensore)));
                        }
                        cm.avvioColtivazione(cb, utente, listaSensoriBean);
                        ArrayList<ColtivazioneBean> coltivazioneBeans = cm.visualizzaStatoColtivazioni(aziendaBean.getEmail());
                        Integer id_coltivazione = coltivazioneBeans.get(coltivazioneBeans.size() - 1).getId();
                        cb.setId(id_coltivazione);
                        SensoreBean sensoreBean;
                        for (String sensore : sensori) {
                            sensoreBean = sensoreManager.visualizzaSensore(Integer.parseInt(sensore));
                            sensoreManager.aggiungiAssociazioneSensore(cb.getId(), sensoreBean);
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }  else if (request.getParameter("sensoreDaRimuovere") != null) {
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
            } else if (request.getParameter("today") != null) {
                ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
                java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
                Integer coltivazioneId = Integer.valueOf(request.getSession().getAttribute("coltivazioneID").toString());
                String jsonColtivazioni = null;
                try {
                    jsonColtivazioni = costruisciJson(coltivazioneId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(jsonColtivazioni);
            } else {
            /* java.sql.Date todaydate = new java.sql.Date(System.currentTimeMillis());
            if (java.sql.Date.valueOf(request.getParameter("data_inizio_periodo")).after(todaydate)) {
                request.getSession().setAttribute("erroreDataPeriodo", "Periodo non valido");
                response.sendRedirect("Coltivazione.jsp");
            } else { */
                ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
                System.out.println("Data Inizio Periodo "  + request.getParameter("data_inizio_periodo"));
                System.out.println("Data Fine Periodo " + request.getParameter("data_inizio_periodo"));
                java.sql.Date inizioPeriodo = java.sql.Date.valueOf(request.getParameter("data_inizio_periodo"));
                java.sql.Date finePeriodo = java.sql.Date.valueOf(request.getParameter("data_fine_periodo"));
                Integer coltivazioneId = Integer.valueOf(request.getSession().getAttribute("coltivazioneID").toString());
                String tipo = request.getParameter("tipoSensore");
                System.out.println("Tipo di sensore arrivato : " + tipo);
                String jsonPeriodoColtivazioni = costruisciJsonPeriodo(inizioPeriodo, finePeriodo, coltivazioneId, tipo);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(jsonPeriodoColtivazioni);
            /*}*/
        }
    }

    /**
     * Questo metodo genera un json per mostrare l'andamento delle misurazioni di un tipo di sensore in un certo periodo.
     * @param inizioPeriodo
     * @param finePeriodo
     * @param coltivazioneId
     * @param tipo
     * @return string
     */
    public String costruisciJsonPeriodo(java.sql.Date inizioPeriodo, java.sql.Date finePeriodo, Integer coltivazioneId, String tipo) {
        ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
        List<MisurazioneSensoreBean> misurazioneSensoreBeanList = coltivazioneManager.restituisciMisurazioniPerPeriodo(inizioPeriodo, finePeriodo, coltivazioneId, tipo);
        String InizioJson = "{\"theme\":\"light1\",\"animationEnabled\":\"false\",\"title\":{\"text\":\"\"},\"data\":[{\"type\":\"spline\",\"dataPoints\":[";
        JSONObject jsonObject = new JSONObject();
        JSONObject title = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject dataContent = new JSONObject();
        JSONArray dataPoints = new JSONArray();
        jsonObject.put("theme", "light1");
        jsonObject.put("animationEnabled", "false");
        title.put("text", "");
        jsonObject.put("title", title);
        dataContent.put("type", "spline");

        for (int i = 0; i < misurazioneSensoreBeanList.size(); i++) {
            JSONObject dataPoint = new JSONObject();
            dataPoint.put("label", misurazioneSensoreBeanList.get(i).getData());
            dataPoint.put("y", misurazioneSensoreBeanList.get(i).getValore());
            dataPoints.add(dataPoint);
        }
        dataContent.put("dataPoints", dataPoints);
        data.add(dataContent);
        jsonObject.put("data", data);
        System.out.println("JSON : " + jsonObject);
        return jsonObject.toJSONString();
    }

    /**
     * Questo metodo genera un json per mostrare l'andamento delle misurazioni in una colivazione in un certo periodo.
     * @param coltivazioneId
     * @return string
     */
    public String costruisciJson(Integer coltivazioneId) throws SQLException {
        ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
        List<MisurazioneSensoreBean> misurazioneSensoreBeanListPH = coltivazioneManager.visualizzaMisurazioneColtivazione(coltivazioneId, "pH");
        List<MisurazioneSensoreBean> misurazioneSensoreBeanListTemperatura = coltivazioneManager.visualizzaMisurazioneColtivazione(coltivazioneId, "temperatura");
        List<MisurazioneSensoreBean> misurazioneSensoreBeanListUmidita = coltivazioneManager.visualizzaMisurazioneColtivazione(coltivazioneId, "umidita");
        JSONObject jsonObject = new JSONObject();
        JSONObject title = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject dataContentPH = new JSONObject();
        JSONObject dataContentTemperatura = new JSONObject();
        JSONObject dataContentUmidità = new JSONObject();
        JSONArray dataPoints = new JSONArray();
        jsonObject.put("theme", "light1");
        jsonObject.put("animationEnabled", "false");
        title.put("text", "");
        jsonObject.put("title", title);
        dataContentPH.put("type", "spline");
        dataContentTemperatura.put("type", "spline");
        dataContentUmidità.put("type", "spline");
        for (int i = 0; i < misurazioneSensoreBeanListPH.size(); i++) {
            JSONObject dataPoint = new JSONObject();
            dataPoint.put("label", misurazioneSensoreBeanListPH.get(i).getTipo());
            dataPoint.put("y", misurazioneSensoreBeanListPH.get(i).getValore());
            dataPoints.add(dataPoint);
        }
        dataContentPH.put("dataPoints", dataPoints);
        data.add(dataContentPH);
        dataPoints = new JSONArray();
        for (int i = 0; i < misurazioneSensoreBeanListTemperatura.size(); i++) {
            JSONObject dataPoint = new JSONObject();
            dataPoint.put("label", misurazioneSensoreBeanListTemperatura.get(i).getTipo());
            dataPoint.put("y", misurazioneSensoreBeanListTemperatura.get(i).getValore());
            dataPoints.add(dataPoint);
        }
        dataContentTemperatura.put("dataPoints", dataPoints);
        data.add(dataContentTemperatura);
        dataPoints = new JSONArray();
        for (int i = 0; i < misurazioneSensoreBeanListUmidita.size(); i++) {
            JSONObject dataPoint = new JSONObject();
            dataPoint.put("label", misurazioneSensoreBeanListUmidita.get(i).getTipo());
            dataPoint.put("y", misurazioneSensoreBeanListUmidita.get(i).getValore());
            dataPoints.add(dataPoint);
        }
        dataContentUmidità.put("dataPoints", dataPoints);
        data.add(dataContentUmidità);
        jsonObject.put("data", data);
        System.out.println("JSON : " + jsonObject);
        return jsonObject.toJSONString();
    }
}
