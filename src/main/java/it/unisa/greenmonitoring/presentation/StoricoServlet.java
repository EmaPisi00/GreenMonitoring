package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "StoricoServlet", value = "/StoricoServlet")
public class StoricoServlet extends HttpServlet {

    /**
     * Coltivazione manager.
     */
    private ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            java.sql.Date inizioPeriodo = java.sql.Date.valueOf(request.getParameter("data_inizio_periodo"));
            java.sql.Date finePeriodo = java.sql.Date.valueOf(request.getParameter("data_fine_periodo"));
            Integer coltivazioneId = Integer.valueOf(request.getSession().getAttribute("coltivazioneID").toString());
            String tipo = request.getParameter("tipoSensore");
            String jsonPeriodoColtivazioni = costruisciJsonPeriodo(inizioPeriodo, finePeriodo, coltivazioneId, tipo);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(jsonPeriodoColtivazioni);
        }
    }

    /**
     * Questo metodo genera un json per mostrare l'andamento delle misurazioni di un tipo di sensore e / o un certo periodo.
     *
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
}
