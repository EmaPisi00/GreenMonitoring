package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * Object that provides the methods to manage the Login.
     */
    private final AutenticazioneManager lm = new AutenticazioneManager();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sessione = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UtenteBean user = lm.Login(email, password);
            if (user instanceof AziendaBean) {
                sessione.setAttribute("currentUserSession", user);
                //ciclo for per la verifica del corretto retrieve delle informazioni dello user corrente
                for (String s : Arrays.asList(user.getEmail(), user.getPassword(), user.getTelefono(), user.getCitta(), user.getProvincia(), user.getIndirizzo(), ((AziendaBean) user).getPartita_iva(), ((AziendaBean) user).getNome_azienda())) {
                    System.out.println(s);
                }
                response.sendRedirect("ListaColtivazioni.jsp");
            } else if (user instanceof DipendenteBean)  {
                sessione.setAttribute("currentUserSession", user);
                if (((DipendenteBean) user).getAzienda() != null) {
                    //ciclo for per la verifica del corretto retrieve delle informazioni dello user corrente
                    for (String s : Arrays.asList(user.getEmail(), user.getPassword(), user.getTelefono(), user.getCitta(), user.getProvincia(), user.getIndirizzo(), ((DipendenteBean) user).getNome(), ((DipendenteBean) user).getCognome(), ((DipendenteBean) user).getAzienda())) {
                        System.out.println(s);
                    }
                    response.sendRedirect("ListaColtivazioni.jsp");
                } else {
                    response.sendRedirect("Associazione.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Le credenziali inserite non sono valide");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
