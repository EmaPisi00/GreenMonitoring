package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ColtivazioniServlet", value = "/ColtivazioniServlet")
public class ColtivazioniServlet extends HttpServlet {
    /**
     * Sensore manager.
     */
    private SensoreManager sensoreManager = new SensoreManager();
    /**
     * Pianta manager.
     */
    private PiantaManager piantaManager = new PiantaManager();
    /**
     * Terreno manager.
     */
    private TerrenoManager terrenoManager = new TerrenoManager();
    /**
     * Coltivazione manager.
     */
    private ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
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
            Date date = new java.sql.Date(System.currentTimeMillis());
            AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
            String utente = aziendaBean.getEmail();
            String[] sensorePh = request.getParameterValues("sensorePh"); //id
            String[] sensoreTemperatura = request.getParameterValues("sensoreTemperatura"); //id
            String[] sensoreUmidita = request.getParameterValues("sensoreUmidita"); //id
            ArrayList<String> sensori = new ArrayList<>();

            int idPianta;
            int idterreno;
            Date dataInizio;
            try {
                idPianta = Integer.parseInt(request.getParameter("nomepianta"));
            } catch (NumberFormatException | NullPointerException numberFormatException) {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", " Formato id pianta errato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                idterreno = Integer.parseInt(request.getParameter("terreno"));
            } catch (NumberFormatException | NullPointerException numberFormatException) {
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", " Formato terreno errato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                dataInizio = Date.valueOf(request.getParameter("datainizio"));
            } catch (IllegalArgumentException | NullPointerException numberFormatException) {
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", " Formato data errato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if (piantaManager.visualizzaPianta(idPianta) == null) {
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", " Pianta inesistente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (terrenoManager.restituisciTerrenoDaInt(idterreno) == null) {
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", " Terreno inesistente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (dataInizio.after(new Date(System.currentTimeMillis()))) {
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", " Data non valida");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if ((sensorePh == null || sensorePh.length == 0) && (sensoreTemperatura == null || sensoreTemperatura.length == 0) && (sensoreUmidita == null || sensoreUmidita.length == 0)) {
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", " Nessun sensore selezionato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (coltivazioneManager.visualizzaStatoColtivazioni(aziendaBean.getEmail()).stream().anyMatch(o -> o.getTerreno().equals(idterreno))) {
                request.setAttribute("errore", "8");
                request.setAttribute("descrizione", " Il terreno è già occupato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else {
                if (sensorePh != null) {
                    Collections.addAll(sensori, sensorePh);
                }
                if (sensoreTemperatura != null) {
                    Collections.addAll(sensori, sensoreTemperatura);
                }
                if (sensoreUmidita != null) {
                    Collections.addAll(sensori, sensoreUmidita);
                }
            }
            ColtivazioneBean cb = new ColtivazioneBean();
            cb.setPianta(idPianta);
            cb.setStato_archiviazione(Byte.parseByte("0"));
            cb.setTerreno(idterreno);
            cb.setData_inizio(dataInizio);
            try {
                ArrayList<SensoreBean> listaSensoriBean = new ArrayList<>();
                for (String id_sensore : sensori) {
                    listaSensoriBean.add(sensoreManager.visualizzaSensore(Integer.parseInt(id_sensore)));
                }
                if (coltivazioneManager.avvioColtivazione(cb, utente, listaSensoriBean) == null) {
                    request.setAttribute("errore", "8");
                    request.setAttribute("descrizione", " Errore nel salvataggio della coltivazione");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("errore", "9");
                    request.setAttribute("descrizione", " Coltivazione avviata. ");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (request.getParameter("today") != null) {
            Integer coltivazioneId = Integer.valueOf(request.getSession().getAttribute("coltivazioneID").toString());
            String tipo = request.getParameter("tipoSensore");
            String jsonColtivazioni = null;
            jsonColtivazioni = costruisciJsonPeriodo(null, null, coltivazioneId, tipo);
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
     * Questo metodo genera un json per mostrare l'andamento delle misurazioni di un tipo di sensore e / o un certo periodo.
     * @param inizioPeriodo
     * @param finePeriodo
     * @param coltivazioneId
     * @param tipo
     * @return string
     */
    public String costruisciJsonPeriodo(java.sql.Date inizioPeriodo, java.sql.Date finePeriodo, Integer coltivazioneId, String tipo) {
        List<MisurazioneSensoreBean> misurazioneSensoreBeanList = new ArrayList<>();
        if (inizioPeriodo == null && finePeriodo == null) {
            misurazioneSensoreBeanList = coltivazioneManager.visualizzaMisurazioneColtivazione(coltivazioneId, tipo);
        } else {
            misurazioneSensoreBeanList = coltivazioneManager.restituisciMisurazioniPerPeriodo(inizioPeriodo, finePeriodo, coltivazioneId, tipo);
        }
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
            dataPoint.put("label", "\"" + misurazioneSensoreBeanList.get(i).getData().toString() + "\"");
            dataPoint.put("y", misurazioneSensoreBeanList.get(i).getValore());
            dataPoints.add(dataPoint);
        }
        dataContent.put("dataPoints", dataPoints);
        data.add(dataContent);
        jsonObject.put("data", data);
        System.out.println("JSON : " + jsonObject);
        return jsonObject.toJSONString();
    }
/*
    /**
     * Questo metodo genera un json per mostrare l'andamento delle misurazioni in una colivazione in un certo periodo.
     * @param coltivazioneId
     * @return string
     */
  /*  public String costruisciJson(Integer coltivazioneId, String tipo) {
        List<MisurazioneSensoreBean> misurazioneSensoreBeanListPH = coltivazioneManager.visualizzaMisurazioneColtivazione(coltivazioneId, tipo);
        JSONObject jsonObject = new JSONObject();
        JSONObject title = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject dataContent = new JSONObject();
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
    } */
}
