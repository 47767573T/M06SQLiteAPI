import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class selectSQLite {

    static Connection c;
    static Statement stmt;
    static String archivoDB = themovieDBproject.ficheroDB;
    static String tablaPelis = themovieDBproject.nombreTablaPeliculas;
    static String tablaActores = themovieDBproject.nombreTablaActores;

    /**
     * método que muestra por pantalla las peliculas de la tabla "peliculas"
     */
    public static void movieList (){
        int numIndex = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ID, TITULO FROM "+tablaPelis);
            while ( rs.next() ) {
                numIndex++;
                int id = rs.getInt("ID");
                String titulo = rs.getString("TITULO");
                System.out.println(numIndex+"\t("+id+")"+titulo);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    /**
     * metodo que muestra por pantalla las características de la pelicula según id declarado
     * @param idSeleccionado id de la pelicula que se desea mostrar en pantalla
     */
    public static void query1 (int idSeleccionado){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            c.setAutoCommit(false);

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT TITULO, FECHA, PERSONAJE "
                    +"FROM "+tablaPelis+" as pelis, "+tablaActores+" as actores "
                    +"WHERE pelis.ID = actores.ID_PELICULA "
                    +"AND pelis.ID = "+idSeleccionado);

            String  titulo = rs.getString("TITULO");
            String  fecha = rs.getString("FECHA");
            System.out.println("::: "+titulo+" ("+fecha+") :::");

            while ( rs.next() ) {
                String  personaje = rs.getString("PERSONAJE");
                System.out.println("-"+personaje);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * método que muestra por pantalla los actores de la tabla "actores"
     */
    public static void actorList (){
        int numIndex = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT DISTINCT ID_ACTOR, NOMBRE FROM "+tablaActores);
            while ( rs.next() ) {
                numIndex++;
                int id = rs.getInt("ID_ACTOR");
                String nombre = rs.getString("NOMBRE");
                System.out.println(numIndex+"\t("+id+")"+nombre);
            }
            System.out.println("flag1");


            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("consulta realizada correctamente");

    }

    /**
     * metodo que muestra por pantalla las características del actor según id de actor declarada
     * @param idSeleccionado id de la pelicula que se desea mostrar en pantalla
     */
    public static void query2 (int idSeleccionado){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            c.setAutoCommit(false);

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT NOMBRE, TITULO "
                    +"FROM "+tablaPelis+" as pelis, "+tablaActores+" as actores "
                    +"WHERE pelis.ID = actores.ID_PELICULA "
                    +"AND actores.ID_ACTOR = "+idSeleccionado);

            while ( rs.next() ) {
                String  nombre = rs.getString("NOMBRE");
                String  titulo = rs.getString("TITULO");
                System.out.println(nombre+" - "+titulo);

            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("\nconsulta realizada correctamente");
    }
}


