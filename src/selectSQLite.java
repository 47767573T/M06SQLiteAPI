
import java.sql.*;


public class selectSQLite {

    static String archivoDB = themovieDBproject.ficheroDB;

    public static void movieList (){
        int numIndex = 0;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            c.setAutoCommit(false);
            System.out.println("Acceso correcto a Base de datos");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ID, TITULO FROM "+themovieDBproject.nombreTablaPeliculas+";");
            while ( rs.next() ) {
                numIndex++;
                int id = rs.getInt("ID");
                String titulo = rs.getString("TITULO");

                System.out.println(numIndex+"\t("+id+")"+titulo);

            }

            System.out.println(rs.findColumn("ID"));

            //rs.close();
            //stmt.close();
            //c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage()+"prueba");
            System.exit(0);
        }
        System.out.println("consulta realizada correctamente");

    }


    public static void query1 (){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(archivoDB);
            c.setAutoCommit(false);
            System.out.println("Acceso correcto a Base de datos");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT @rownum:=@rownum+1 'numero', id, name, age FROM COMPANY, (SELECT @rownum:=0)" );
            while ( rs.next() ) {

                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("consulta realizada correctamente");
    }


   /* public static void main(String[] args)
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:moviesXX.db");
                c.setAutoCommit(false);
                System.out.println("acceso correcto a Base de datos");

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT id, name, age FROM COMPANY;" );
                while ( rs.next() ) {

                    int id = rs.getInt("id");
                    String  name = rs.getString("name");
                    int age  = rs.getInt("age");
                    String  address = rs.getString("address");
                    float salary = rs.getFloat("salary");
                    System.out.println( "ID = " + id );
                    System.out.println( "NAME = " + name );
                    System.out.println( "AGE = " + age );
                    System.out.println( "ADDRESS = " + address );
                    System.out.println( "SALARY = " + salary );
                    System.out.println();
                }
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("consulta realizada correctamente");
        }
*/
}


