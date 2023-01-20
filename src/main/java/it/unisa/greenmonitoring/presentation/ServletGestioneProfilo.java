package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.autenticazione.UtenteManager;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.sql.SQLException;


@WebServlet(name = "ServletGestioneProfilo", value = "/ServletGestioneProfilo")
@MultipartConfig
public class ServletGestioneProfilo extends HttpServlet {

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
        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("currentUserSession");

        UtenteManager u = new UtenteManager();
        String emailNuova = request.getParameter("email").isEmpty() ? utente.getEmail() : request.getParameter("email");
        String password = request.getParameter("password").isEmpty() ? utente.getPassword() : request.getParameter("password");
        String telefono = request.getParameter("telefono").isEmpty() ? utente.getTelefono() : request.getParameter("telefono");
        String citta = request.getParameter("citta").isEmpty() ? utente.getCitta() : request.getParameter("citta");
        String provincia = request.getParameter("provincia").isEmpty() ? utente.getProvincia() : request.getParameter("provincia");
        String indirizzo = request.getParameter("indirizzo").isEmpty() ? utente.getIndirizzo() : request.getParameter("indirizzo");
        System.out.println("sono in servlet");
        System.out.println(utente + " " + utente.getClass());
        if (utente instanceof AziendaBean) {
            String nome_azienda = request.getParameter("Nome_azienda").isEmpty() ? ((AziendaBean) utente).getNome_azienda() : request.getParameter("Nome_azienda");
            String partita_iva = request.getParameter("partita_iva").isEmpty() ? ((AziendaBean) utente).getPartita_iva() : request.getParameter("partita_iva");
            AziendaBean utenteNuovo = new AziendaBean(emailNuova, password, telefono, citta, indirizzo, provincia);
            utenteNuovo.setNome_azienda(nome_azienda);
            utenteNuovo.setPartita_iva(partita_iva);
            try {
                u.controllaDatiAggiorna(utenteNuovo, utente);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if (utente instanceof DipendenteBean) {
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String Nome_aziendaDipendente = request.getParameter("Nome_aziendaDipendente");
            DipendenteBean utenteNuovo = new DipendenteBean(emailNuova, password, telefono, citta, indirizzo, provincia);
            utenteNuovo.setNome(nome);
            utenteNuovo.setCognome(cognome);
            utenteNuovo.setAzienda(Nome_aziendaDipendente);
            try {
                u.controllaDatiAggiorna(utenteNuovo, utente);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
