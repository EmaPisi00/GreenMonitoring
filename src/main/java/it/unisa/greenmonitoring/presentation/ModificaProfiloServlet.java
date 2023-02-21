package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.GestioneUtente.UtenteManager;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.sql.SQLException;


@WebServlet(name = "ModificaProfiloServlet", value = "/ModificaProfiloServlet")
@MultipartConfig
public class ModificaProfiloServlet extends HttpServlet {

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
        System.out.println(emailNuova + password + telefono + citta + provincia + indirizzo);
        //controlli utente generale
        if (!emailNuova.matches("^[\\w\\d]+@[\\w\\d]+\\.[\\w\\d]+$")) {
            System.out.println("Errore nell'email");
            request.setAttribute("errore", "1");
            request.setAttribute("descrizione", "Errore nell'inserimento dell'email");
            RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
            rd.forward(request, response);
        } else if (!(password.matches("^[a-zA-Z0-9!@#$%^&*]{8,16}$"))) {
            /*Inserisci una password di lunghezza max 15 caratteri e minimo 8 con almeno un carattere speciale,un numero e una maiuscola*/
            System.out.println("Errore nella password ");
            request.setAttribute("errore", "2");
            request.setAttribute("descrizione", "Errore nell'inserimento della password");
            RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
            rd.forward(request, response);
        } else if (!(telefono.matches("^((00|\\+)39[. ]??)??3\\d{2}[. ]??\\d{6,7}$"))) {
            System.out.println("Errore nel telefono");
            request.setAttribute("errore", "3");
            request.setAttribute("descrizione", "Errore nell'inserimento del numero telefonico");
            RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
            rd.forward(request, response);
        } else if (!(citta.matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome della città\n");
            request.setAttribute("errore", "4");
            request.setAttribute("descrizione", "Errore nell'inserimento della città");
            RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
            rd.forward(request, response);
        } else if (!(provincia.matches("^[a-zA-Z]+$"))) {
            System.out.println("Errore nel nome della Provincia");
            request.setAttribute("errore", "5");
            request.setAttribute("descrizione", "Errore nell'inserimento della provincia");
            RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
            rd.forward(request, response);
        } else if (!(indirizzo.matches("^[a-zA-Z0-9 ]{3,30}"))) {
            System.out.println("\nErrore nel nome dell'indirizzo \n");
            request.setAttribute("errore", "6");
            request.setAttribute("descrizione", "Errore nell'inserimento dell' indirizzo");
            RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
            rd.forward(request, response);
        } else {
            if (utente instanceof AziendaBean) {
                String nome_azienda = request.getParameter("Nome_azienda").isEmpty() ? ((AziendaBean) utente).getNome_azienda() : request.getParameter("Nome_azienda");
                String partita_iva = request.getParameter("partita_iva").isEmpty() ? ((AziendaBean) utente).getPartita_iva() : request.getParameter("partita_iva");

                if (!(nome_azienda.matches("^[a-zA-Z]+$"))) {
                    System.out.println("\nErrore nel nome dell'azienda\n");
                    request.setAttribute("errore", "7");
                    request.setAttribute("descrizione", "Errore nell'inserimento del nome azienda");
                    RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
                    rd.forward(request, response);
                } else if (!(partita_iva.matches("^[0-9]+$"))) {
                    System.out.println("\nErrore nella partita iva\n");
                    request.setAttribute("errore", "8");
                    request.setAttribute("descrizione", "Errore nell'inserimento della partita iva");
                    RequestDispatcher rd = request.getRequestDispatcher("/ModificaProfilo.jsp");
                    rd.forward(request, response);
                }

                AziendaBean utenteNuovo = new AziendaBean(emailNuova, password, telefono, citta, indirizzo, provincia);
                utenteNuovo.setNome_azienda(nome_azienda);
                utenteNuovo.setPartita_iva(partita_iva);
                try {
                    if (u.modificaDatiUtente(utenteNuovo, utente) != null) {
                        request.setAttribute("conferma", "11");
                        request.setAttribute("descrizione", "Salvataggio effettuato");
                        session.setAttribute("currentUserSession", utenteNuovo);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Profile.jsp");
                        dispatcher.forward(request, response);

                    } else {
                        request.setAttribute("errore", "12");
                        request.setAttribute("descrizione", "Non è stato possibile salvare nel db");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaProfilo.jsp");
                        dispatcher.forward(request, response);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else if (utente instanceof DipendenteBean) {
                String nome = request.getParameter("nome").isEmpty() ? ((DipendenteBean) utente).getNome() : request.getParameter("nome");
                String cognome = request.getParameter("cognome").isEmpty() ? ((DipendenteBean) utente).getNome() : request.getParameter("cognome");
                String Nome_aziendaDipendente = request.getParameter("Nome_aziendaDipendente").isEmpty() ? ((DipendenteBean) utente).getNome() : request.getParameter("Nome_aziendaDipendente");
                System.out.println("Dipendente " + nome + cognome + Nome_aziendaDipendente);

                if (!(nome.matches("^[a-zA-Z]+$"))) {
                    System.out.println("\nErrore nel nome");
                    request.setAttribute("errore", "9");
                    request.setAttribute("descrizione", "Errore nell'inserimento del nome dipendente");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Dipendente.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else if (!(cognome.matches("^[a-zA-Z]+$"))) {
                    System.out.println("\nErrore nel cognome");
                    request.setAttribute("errore", "10");
                    request.setAttribute("descrizione", "Errore nell'inserimento cognome Dipendente");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaProfilo.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                DipendenteBean utenteNuovo = new DipendenteBean(emailNuova, password, telefono, citta, indirizzo, provincia);
                utenteNuovo.setNome(nome);
                utenteNuovo.setCognome(cognome);
                utenteNuovo.setAzienda(Nome_aziendaDipendente);
                System.out.println(utenteNuovo);

                try {
                    if (u.modificaDatiUtente(utenteNuovo, utente) != null) {
                        request.setAttribute("conferma", "11");
                        request.setAttribute("descrizione", "Salvataggio effettuato");
                        session.setAttribute("currentUserSession", utenteNuovo);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Profile.jsp");
                        dispatcher.forward(request, response);

                    } else {
                        request.setAttribute("errore", "12");
                        request.setAttribute("descrizione", "Non è stato possibile salvare nel db");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaProfilo.jsp");
                        dispatcher.forward(request, response);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }

        }

    }
    }

