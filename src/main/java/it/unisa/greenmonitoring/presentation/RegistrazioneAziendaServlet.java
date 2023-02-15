package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrazioneAziendaServlet", value = "/RegistrazioneAziendaServlet")
public class RegistrazioneAziendaServlet extends HttpServlet {

    /**
     * Dichiaro la variabile privata AziendaManager.
     */
    private AutenticazioneManager aziendaManager = new AutenticazioneManager();

    /**
     * Metodo doGet.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    /**
     * Metodo doPost.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        String confermPassword = request.getParameter("confermaInputPassword");
        String telefono = request.getParameter("inputTelefono");
        String citta = request.getParameter("inputCitta");
        String indirizzo = request.getParameter("inputIndirizzo");
        String provincia = request.getParameter("inputProvincia");
        String nome_azienda = request.getParameter("inputAzienda");
        String partita_iva = request.getParameter("inputPartitaIva");


        AziendaBean aziendaBean = new AziendaBean();

        aziendaBean.setEmail(email);
        aziendaBean.setPassword(password);
        aziendaBean.setIndirizzo(indirizzo);
        aziendaBean.setTelefono(telefono);
        aziendaBean.setProvincia(provincia);
        aziendaBean.setCitta(citta);
        aziendaBean.setNome_azienda(nome_azienda);
        aziendaBean.setPartita_iva(partita_iva);


        if (!aziendaBean.getEmail().matches("^[\\w\\d]+@[\\w\\d]+\\.[\\w\\d]+$")) {
                System.out.println("\nErrore nell'email dell'azienda\n");
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getPassword().matches("^[a-zA-Z0-9!@#$%^&*]{8,16}$"))) {
                /*Inserisci una password di lunghezza max 15 caratteri e minimo 8 con almeno un carattere speciale,un numero e una maiuscola*/
                System.out.println("\nErrore nella password \n");
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getPassword().equals(confermPassword))) {
                /*Inserisci una password di lunghezza max 15 caratteri e minimo 8 con almeno un carattere speciale,un numero e una maiuscola*/
                System.out.println("\nErrore nella conferma password \n");
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getTelefono().matches("^((00|\\+)39[. ]??)??3\\d{2}[. ]??\\d{6,7}$"))) {
                System.out.println("\nErrore nel telefono\n");
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getCitta().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome della città\n");
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getIndirizzo().matches("^[a-zA-Z0-9 ]{3,30}"))) {
                System.out.println("\nErrore nel nome dell'indirizzo \n");
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getProvincia().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome della Provincia\n");
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getNome_azienda().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome dell'azienda\n");
                request.setAttribute("errore", "8");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else if (!(aziendaBean.getPartita_iva().matches("^[0-9]+$"))) {
                System.out.println("\nErrore nella partita iva\n");
                request.setAttribute("errore", "9");
                request.setAttribute("descrizione", "descrizione...");
            RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
            rd.forward(request, response);
            } else {
                try {
                    aziendaManager.registraAzienda(aziendaBean);
                    request.setAttribute("errore", "10");
                    RequestDispatcher rd = request.getRequestDispatcher("/RegistrazioneAzienda.jsp");
                    rd.forward(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
