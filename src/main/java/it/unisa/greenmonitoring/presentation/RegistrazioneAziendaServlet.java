package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAzienda", value = "/ServletAzienda")
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

        String email = request.getParameter("inputEmail");
        String confermEmail = request.getParameter("confermaInputEmail");
        String password = request.getParameter("inputPassword");
        String confermPassword = request.getParameter("confermaInputPassword");
        String telefono = request.getParameter("inputTelefono");
        String citta = request.getParameter("inputCitta");
        String indirizzo = request.getParameter("inputIndirizzo");
        String provincia = request.getParameter("inputProvincia");
        String nome_azienda = request.getParameter("inputAzienda");
        String partita_iva = request.getParameter("inputPartitaIva");
        String codice_associazione = request.getParameter("codiceAzienda");


        if (!(password.equals(confermPassword)) || !(email.equals(confermEmail))) {
            System.out.println("\nErrore ");
            response.sendRedirect("RegistrazioneAzienda.jsp");
        } else {

            AziendaBean aziendaBean = new AziendaBean();

            aziendaBean.setEmail(email);
            aziendaBean.setPassword(password);
            aziendaBean.setIndirizzo(indirizzo);
            aziendaBean.setTelefono(telefono);
            aziendaBean.setProvincia(provincia);
            aziendaBean.setCitta(citta);
            aziendaBean.setNome_azienda(nome_azienda);
            aziendaBean.setCodice_associazione(codice_associazione);
            aziendaBean.setPartita_iva(partita_iva);


            if (!aziendaBean.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)(\\.[A-Za-z]{2,})$")) {
                System.out.println("\nErrore nel nome dell'azienda\n");
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getPassword().matches("^[a-zA-Z0-9!@#$%^&*]+$"))) {
                /*Inserisci una password di lunghezza max 15 caratteri e minimo 8 con almeno un carattere speciale,un numero e una maiuscola*/
                System.out.println("\nErrore nella password \n");
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getTelefono().matches("^((00|\\+)39[. ]??)??3\\d{2}[. ]??\\d{6,7}$"))) {
                System.out.println("\nErrore nel nome della città\n");
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getCitta().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome della città\n");
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getIndirizzo().matches("/^\\s*\\S+(?:\\s+\\S+){2}/"))) {
                System.out.println("\nErrore nel nome della città\n");
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getProvincia().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome della Provincia\n");
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getNome_azienda().matches("^[a-zA-Z]+$"))) {
                System.out.println("\nErrore nel nome dell'azienda\n");
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else if (!(aziendaBean.getPartita_iva().matches("^[0-9]+$"))) {
                System.out.println("\nErrore nella partita iva\n");
                request.setAttribute("errore", "8");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrazioneAzienda.jsp");
                dispatcher.forward(request, response);
            } else {
                try {
                    aziendaManager.registraAzienda(aziendaBean);
                    //code 200
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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

    }
}
