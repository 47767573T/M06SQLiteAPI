

import java.sql.*;

public class insertSQLite {

    static String archivoDB = themovieDBproject.ficheroDB;

    public static void insertTablaPelis(int id, String titulo, String fecha) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(archivoDB);
                c.setAutoCommit(false);

                stmt = c.createStatement();
                String sql = "INSERT INTO "+themovieDBproject.nombreTablaPeliculas+" (ID,TITULO,FECHA) "
                            +"VALUES ("+id+",'"+titulo+"','"+fecha+"');";
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public static void insertTablaActores(int id, String nombre, long actor, String personaje, int idPeli) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(archivoDB);
                c.setAutoCommit(false);

                stmt = c.createStatement();
                String sql = "INSERT INTO "+themovieDBproject.nombreTablaActores+" (ID,NOMBRE,ID_ACTOR,PERSONAJE,ID_PELICULA) "
                        +"VALUES ("+id+",'"+nombre+"',"+actor+",'"+personaje+"',"+idPeli+");";
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName()+e.getClass().getMethods() + ": " + e.getMessage()+"prueba");
                System.exit(0);
            }
            System.out.println("Guardado ("+nombre+") en ("+themovieDBproject.nombreTablaActores+")");
        }
    }
}
/*
    public static void main(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:moviesXX.db");
                c.setAutoCommit(false);
                System.out.println("acceso correcto a Base de datos");

                stmt = c.createStatement();
                String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (5, 'Paul', 32, 'California', 20000.00 );";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (6, 'Allen', 25, 'Texas', 15000.00 );";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (7, 'Teddy', 23, 'Norway', 20000.00 );";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (8, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Guardado(s) registro(s) correctamente");
        }
    }*/
