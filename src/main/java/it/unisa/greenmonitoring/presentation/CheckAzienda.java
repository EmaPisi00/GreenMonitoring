package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.GestioneUtente.UtenteManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import org.json.simple.JSONObject;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CheckAzienda", value = "/CheckAzienda")
public class CheckAzienda extends HttpServlet {

    /** Non lo so, l'ha messa intellij sta roba sotto.
    */
    public CheckAzienda() throws SQLException {
    }


    /**
     * Method that handle the GET requests.
     * @param request the request from the client.
     * @param response the response from the server.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Method that handle the POST requests.
     * @param request the request from the client.
     * @param response the response from the server.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        UtenteManager utenteManager = new UtenteManager();
        String codice = request.getParameter("codiceAzienda");
        System.out.println("Il codice subito dopo getParameter e' " + codice);
        JSONObject json = new JSONObject();
        if (codice != null && codice.matches("^\\w{8}$")) {
            try {
                AziendaBean aziendaBean = utenteManager.visualizzaAziendaPerCodiceAssociazione(codice);
                if (aziendaBean.getEmail() == null) {
                    return;
                }
                request.setAttribute("azienda", aziendaBean);
                response.setContentType("application/json");

                json.put("success", 1);
                json.put("nome", aziendaBean.getNome_azienda());
                json.put("indirizzo", aziendaBean.getIndirizzo());
                json.put("provincia", aziendaBean.getProvincia());
                response.getWriter().write(String.valueOf(json));
                System.out.println("finisce le cose e prende " + aziendaBean);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            json.put("success", 0);
            json.put("errore", "Il codice e' sbagliato");
            response.getWriter().write(String.valueOf(json));
            System.out.println("il codice è null");
        }
    }
}
