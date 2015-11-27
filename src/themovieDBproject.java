import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;


public class themovieDBproject {


    static Random rnd = new Random();   //Crear nombres de base de datos aleatorias (para realizar pruebas)

    public static String ficheroDB = "jdbc:sqlite:moviesDB"+rnd.nextInt(5000)+".db";
    public static String nombreTablaPeliculas = "PELICULAS";
    public static String nombreTablaActores = "ACTORES";
    public static int lastIdCast = 1;
    public static int idIniPelicula = 600;  //ID desde el cual leeremos las peliculas que guardaremos
    public static int numPeliculas = 100;   //número de peliculas que queremos guardar


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        //:::::::::::::::::::::::::::::::::::::::PREPARAMOS Y CREAMOS LA TABLA ANTES DE INSERTAR REGISTROS
        String s = "";
        String api_key = "e6f2c549601727fca2e90f4291bbe34d";

        createSQLite.createTabla();


        //:::::::::::::::::::::::::::::::::::::::INTRODUCIMOS LOS REGISTROS
        for (int i = 0; i < numPeliculas; i++) {
            int peliculaIndex = idIniPelicula + i;
            String peliculaID = String.valueOf(peliculaIndex);
            String actoresURL = "https://api.themoviedb.org/3/movie/" + peliculaID + "/credits?api_key=" + api_key;
            String peliculasURL = "https://api.themoviedb.org/3/movie/" + peliculaID + "?api_key=" + api_key;
            try {

                s = getHTML(peliculasURL);              //define el json de la pelicula a guardar según url introducida
                jsonToTablaPelis(s, peliculaIndex);     //guarda la pelicula en la base de datos
                s = getHTML(actoresURL);                //define el json de la pelicula a guardar según url introducida
                jsonToTablaActores(s, peliculaIndex);   //guarda el actor en la base de datos


            } catch (Exception e) {
                System.out.println("La peli " + peliculaID + " no existeix ");
            }
        }
                //::::::::::::::::::::::::::::::::::::::::GENERAMOS LOS 2 MODOS DE CONSULTA
                //MODO 1:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                System.out.println("\nQUERY MODO 1:\n");

                System.out.println("Listado de peliculas: ");
                selectSQLite.movieList();       //Muestra el listado de peliculas guardadas en la BBDD

                System.out.println("Elija un ID de la pelicula del listado");
                int idPeli = scn.nextInt();
                selectSQLite.query1(idPeli);    //Muestra info de la pelicula según ID decidido por usuario


                //MODO 2:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                System.out.println("\nQUERY MODO 1:\n");
                System.out.println("");
                selectSQLite.actorList();       //Muestra el listado de actores guardados en la BBDD

                System.out.println("Elija un ID del actor del listado");
                int idActor = scn.nextInt();
                selectSQLite.query2(idActor);   //Muestra info del actor según ID decidido por usuario
    }

    /**
     * Recoge el JSON, define las variables requeridas en String y llama la clase de inserción en la tabla de Peli que se encargará de guardar la informacion
     * @param cadena cadena Json de donde extraer la información para la inserción en la tabla.
     * @param idPeli Integer de la pelicula que introduciremos en la tabla
     */
    public static void jsonToTablaPelis (String cadena, int idPeli){

        Object jsonObj =JSONValue.parse(cadena);
        JSONObject jsonItem=(JSONObject)jsonObj;
        String titulo = (String) jsonItem.get("original_title");
        String tituloCorregido = correctComillas(titulo);

        String fecha = (String) jsonItem.get("release_date");

        //Llamamos a la clase que gestiona la inserción de peliculas y el método para inserción en la BBDD de peliculas
        insertSQLite.insertTablaPelis(idPeli, tituloCorregido, fecha);
    }


    /**
     * Recoge el JSON, define las variables requeridas en String y llama la clase de inserción en la tabla de Actores que se encargará de guardar la informacion
     * @param cadena cadena Json de donde extraer la información para la inserción en la tabla.
     * @param idPeli Integer de la pelicula que introduciremos en la tabla
     */
    public static void jsonToTablaActores (String cadena, int idPeli){
        Object jsonObj =JSONValue.parse(cadena);
        JSONObject jsonItem=(JSONObject)jsonObj;
        JSONArray casting = (JSONArray)jsonItem.get("cast");


        //Recorre la pelicula extrayendo los actores y personajes del JSON de peliculas y llama la clase de inserción en BBDD para introducir los datos
        for (int i = 0; i < casting.size(); i++) {

            JSONObject jo= (JSONObject)casting.get(i);
            String nombre = (String) jo.get("name");
            String nombreCorregido = correctComillas(nombre);

            long actor = (long) jo.get("id");
            String personaje = (String) jo.get("character");
            String personajeCorregido = correctComillas(personaje);

            //Metodo de inserción en la tabla de actores de la clase de inserción de datos.
            insertSQLite.insertTablaActores(lastIdCast, nombreCorregido, actor, personajeCorregido, idPeli);
            lastIdCast++;
        }
    }

    /**
     * Metodo para extraer el String JSON de una llamada "GET" a TheMovieDB.
     * @param urlToRead web definida de la que se quiere conseguir el JSON.
     * @return JSON de la url pedida pasada a String
     * @throws Exception errores de conexión
     */
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /**
     * Método para comprobar y corregir el uso de comilla simple en algunos de los parametros a introducir en la BBDD de peliculas y actores
     * @param fraseConComillas la cadena que puede llevar comilla simple inscrito.
     * @return el String corregido si se encuentra comillas simple en el codigo, en caso contrario, devuelve la misma cadena introducida
     */
    public static String correctComillas (String fraseConComillas){
        String comillas = "\'";

        if (fraseConComillas.contains(comillas)) {
        String fraseSinComillas = fraseConComillas.replace(comillas,"\"");
            return fraseSinComillas;
        }

        return fraseConComillas;
    }

}
