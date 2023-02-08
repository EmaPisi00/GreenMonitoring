package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;

@WebServlet(name = "ServletTerreno", value = "/ServletTerreno")
@MultipartConfig
public class ServletTerreno extends HttpServlet {
    /**
     * Object that provides the methods to manage the Terreno.
     */
    private TerrenoManager tm = new TerrenoManager();
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
        if (request.getParameter("inserisciTerreno_submit") != null) {
            //setta i parametri
            String nome = request.getParameter("nome");
            String azienda = request.getParameter("azienda");

            String fileName = null;
            Part immagine = request.getPart("immagine");
            String contextPath = request.getServletContext().getRealPath("/");
            Path currentPath = Paths.get(contextPath);
            Path parentPath = currentPath.getParent();

            if (immagine.getSize() > 0) {
                fileName = Paths.get(immagine.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = immagine.getInputStream();
                // Salva l'immagine su disco
                String path = parentPath + "/immagini/terreni/" + fileName;
                Files.copy(fileContent, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
                fileContent.close();
            }

            Float latitudine = Float.parseFloat(request.getParameter("latitudine"));
            Float longitudine = Float.parseFloat(request.getParameter("longitudine"));
            String superfice = request.getParameter("superfice");
            //creo il bean da inserire
            TerrenoBean terreno = new TerrenoBean(nome, latitudine, longitudine, superfice, fileName, azienda);
            //errori ritorna il bean terreno con i parametri settati null se ci sono errori
            if (tm.createTerreno(terreno) == null) {
                request.setAttribute("erroriTerrenoBean", "errore nell'inserimento dei dati");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Terreni.jsp");
                dispatcher.forward(request, response);
            }
        }
        if (request.getParameter("pianta0") != null) {
            Enumeration<String> parameters = request.getParameterNames();
            while (parameters.hasMoreElements()) {
                int id = Integer.parseInt(request.getParameter(parameters.nextElement()));
                tm.rimuoviTerreno(id);
            }
            response.sendRedirect("Terreni.jsp");
        }

        response.sendRedirect("index.jsp");

    }

}
