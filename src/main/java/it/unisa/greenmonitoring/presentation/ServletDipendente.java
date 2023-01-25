package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.AutenticazioneManager;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDipendente", value = "/ServletDipendente")
public class ServletDipendente extends HttpServlet {


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

        String nome = request.getParameter("inputNome");
        String cognome = request.getParameter("inputCognome");
        String email = request.getParameter("inputEmail");
        String confermEmail = request.getParameter("confermaInputEmail");
        String password = request.getParameter("inputPassword");
        String confermPassword = request.getParameter("confermaInputPassword");
        String indirizzo = request.getParameter("inputIndirizzo");
        String citta = request.getParameter("inputCitta");
        String provincia = request.getParameter("inputProvincia");
        String telefono = request.getParameter("inputTelefono");
        String azienda = request.getParameter("inputAzienda");

        if (!(password.equals(confermPassword)) || !(email.equals(confermEmail))) {
            System.out.println("\nErrore email o password diverse ");
            response.sendRedirect("Dipendente.jsp");
        } else {

            DipendenteBean dipendenteBean = new DipendenteBean();

            dipendenteBean.setAzienda(null);
            dipendenteBean.setCognome(cognome);
            dipendenteBean.setNome(nome);
            dipendenteBean.setCitta(citta);
            dipendenteBean.setPassword(password);
            dipendenteBean.setEmail(email);
            dipendenteBean.setTelefono(telefono);
            dipendenteBean.setProvincia(provincia);
            dipendenteBean.setIndirizzo(indirizzo);

            try {
                aziendaManager.registraDipendente(dipendenteBean);
                response.sendRedirect("index.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

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
        String[] idDipendente = request.getParameterValues("idDipendente");
        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");

        if (user instanceof AziendaBean) {
            String emailAzienda = user.getEmail();

            if (idDipendente != null) {
                for (String id : idDipendente) {
                    AziendaDAO aziendaDAO = null;
                    aziendaDAO = new AziendaDAOImpl();
                    try {
                        aziendaDAO.removeAssociation(emailAzienda, id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                response.sendRedirect("RimuoviDipendente.jsp");
            } else {
                response.sendRedirect("RimuoviDipendente.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}

