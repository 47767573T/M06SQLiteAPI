
import java.sql.*;

public class createSQLite {


    public static void createTabla() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(themovieDBproject.ficheroDB);
            System.out.println("acceso correcto a Base de datos");
            stmt = c.createStatement();
            String sqlPeli = "CREATE TABLE " + themovieDBproject.nombreTablaPeliculas
                    + " (ID INT PRIMARY KEY     NOT NULL,"
                    + " TITULO CHAR(100),"
                    + " FECHA CHAR(20))";

            String sqlActor = "CREATE TABLE " + themovieDBproject.nombreTablaActores
                    + "(ID INT PRIMARY KEY NOT NULL,"
                    + " NOMBRE         CHAR(50),"
                    + " ID_ACTOR       INT,"
                    + " PERSONAJE      CHAR(50),"
                    + " ID_PELICULA    INT)";

            stmt.executeUpdate(sqlPeli);
            stmt.executeUpdate(sqlActor);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabla de "+themovieDBproject.nombreTablaPeliculas+" y "+themovieDBproject.nombreTablaActores
                +" creada en "+ themovieDBproject.ficheroDB);

    }

}