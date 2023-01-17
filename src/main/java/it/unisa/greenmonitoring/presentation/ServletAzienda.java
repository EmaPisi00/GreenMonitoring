package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.autenticazione.AutenticazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAzienda", value = "/ServletAzienda")
public class ServletAzienda extends HttpServlet {

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
        String indirizzo = request.getParameter("inputIndirizzo");
        String telefono = request.getParameter("inputTelefono");
        String citta = request.getParameter("inputCitta");
        String provincia = request.getParameter("inputProvincia");
        String nome_azienda = request.getParameter("inputAzienda");
        String partita_iva = request.getParameter("inputPartitaIva");

        if (!(password.equals(confermPassword)) || !(email.equals(confermEmail))) {
            System.out.println("\nErrore email o password differenti\n");
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
            aziendaBean.setPartita_iva(partita_iva);

            try {
                aziendaManager.registraAzienda(aziendaBean);
                response.sendRedirect("index.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
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
