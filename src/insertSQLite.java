

import java.sql.*;

public class insertSQLite {

    static String archivoDB = themovieDBproject.ficheroDB;
    static String tablaPelis = themovieDBproject.nombreTablaPeliculas;
    static String tablaActores = themovieDBproject.nombreTablaActores;
    static Connection c;
    static Statement stmt;

    /**
     * Método para insertar registros de peliculas en la tabla de peliculas
     * @param id campo ID de la peliculas para introducir en la BBDD
     * @param titulo campo titulo de la pelicula para introducir en la BBDD
     * @param fecha campo fecha de lanzamiento de la peliculas para introducir en la BBDD
     */
    public static void insertTablaPelis(int id, String titulo, String fecha) {
        {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(archivoDB);
                c.setAutoCommit(false);

                stmt = c.createStatement();

                //Formar el estamento para insertar en las tablas por cada pelicula
                String sql = "INSERT INTO "+tablaPelis+" (ID,TITULO,FECHA) "
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

    /**
     * Método para insertar registros de peliculas en la tabla de peliculas
     * @param id campo ID de la relacion entre pelicula y actor para introducir en la BBDD
     * @param nombre campo nombre de la pelicula para introducir en la BBDD
     * @param actor campo fecha de lanzamiento de la peliculas para introducir en la BBDD
     * @param personaje campo persona de la peliculas para introducir en la BBDD
     * @param idPeli campo ID de la pelicula para introducir en la BBDD
     */
    public static void insertTablaActores(int id, String nombre, long actor, String personaje, int idPeli) {
        {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(archivoDB);
                c.setAutoCommit(false);

                //Formar el estamento para insertar en las tablas de actor por cada pelicula
                stmt = c.createStatement();
                String sql = "INSERT INTO "+tablaActores+" (ID,NOMBRE,ID_ACTOR,PERSONAJE,ID_PELICULA) "
                        +"VALUES ("+id+",'"+nombre+"',"+actor+",'"+personaje+"',"+idPeli+");";
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();

            } catch (Exception e) {
                System.err.println(e.getClass().getName()+e.getClass().getMethods() + ": " + e.getMessage());
                System.exit(0);
            }
            //System.out.println("Guardado ("+nombre+") en ("+themovieDBproject.nombreTablaActores+")");        FLAG
        }
    }
}
