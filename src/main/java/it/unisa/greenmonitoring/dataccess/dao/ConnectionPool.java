package it.unisa.greenmonitoring.dataccess.dao;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Connessione DB.
 */
public final class ConnectionPool {
    /**
     * Dichiaro List<Connection>.
     */
    private static List<Connection> freecon;

    /**
     * Dichiaro user.
     */
    private static String user = Const.name;

    /**
     * Dichiaro pwd.
     */
    private static String pwd = Const.pwd;

    /**
     * Dichiaro db.
     */
    private static String db = Const.nomeDB;


    /**
     * Costruttore.
     */
    private ConnectionPool() {

    }

    static {
        freecon = new LinkedList<Connection>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creazione metodo DB Connection.
     * @return connection
     * @throws SQLException
     */
   private static synchronized Connection createDBConnection() throws SQLException {


                // non serve  Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/" + db;
                Connection con = DriverManager.getConnection(url, user, pwd);

                con.setAutoCommit(false);
                System.out.println("Connessione Eseguita!");
                return con;
        }

    /**
     * Metodo che richiama la connessione al DB.
      * @return connection
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {

        Connection con;

        if (!freecon.isEmpty()) {
            con = (Connection) freecon.get(0);
            freecon.remove(0);

            try {
                if (con.isClosed()) {
                    con = getConnection();
                }
            } catch (SQLException e) {
                con.close();
                con = getConnection();
            }
        } else {
            con = createDBConnection();
        }
        return con;

    }

    /**
     * Connessione.
     * @param c
     * @throws SQLException
     */
    public static synchronized void relaseConnection(Connection c) throws SQLException {
        if (c != null) {
            freecon.add(c);
        }
    }


}
