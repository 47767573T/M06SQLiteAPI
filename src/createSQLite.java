
import java.sql.*;

public class createSQLite {


    static String archivoDB = themovieDBproject.ficheroDB;

    public static void createTabla(String nombreTablaPelis, String nombreTablaActores) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            System.out.println("acceso correcto a Base de datos");

            stmt = c.createStatement();
            String sqlPeli = "CREATE TABLE " + nombreTablaPelis
                    + " (ID INT PRIMARY KEY     NOT NULL,"
                    + " TITULO CHAR(100),"
                    + " FECHA CHAR(20))";

            String sqlActor = "CREATE TABLE " + nombreTablaActores
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
        System.out.println("Tabla de MoviesDB creada");
    }

}



/*
    public static void main(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;

            try {

                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:nombre_de_archivo.db");
                System.out.println("acceso correcto a Base de datos");

                stmt = c.createStatement();
                String sql = "CREATE TABLE PELICULAS " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " AGE            INT     NOT NULL, " +
                        " ADDRESS        CHAR(50), " +
                        " SALARY         REAL)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Tabla de peliculas creada");

            try {

                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:nombre_de_archivo.db");
                System.out.println("acceso correcto a Base de datos");

                stmt = c.createStatement();
                String sql = "CREATE TABLE ACTORES " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " AGE            INT     NOT NULL, " +
                        " ADDRESS        CHAR(50), " +
                        " SALARY         REAL)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Tabla de actores creada");


        }

    }


}*/
