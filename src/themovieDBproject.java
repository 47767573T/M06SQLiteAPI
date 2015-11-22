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


    static Random rnd = new Random();

    private static String nombreTablaPeliculas = "Sin nombre";
    private static String nombreTablaActores = "Sin nombre";
    public static String ficheroDB = "jdbc:sqlite:moviesDB"+rnd.nextInt(5000)+".db";
    public static int lastIdCast = 1;


    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);


        //:::::::::::::::::::::::::::::::::::::::PREPARAMOS Y CREAMOS LA TABLA ANTES DE INSERTAR REGISTROS
        String s = "";
        String api_key = "e6f2c549601727fca2e90f4291bbe34d";

        //System.out.println("¿Nombre de la tabla de peliculas?");
        //nombreTablaPeliculas = scn.next();
        nombreTablaPeliculas = "pelis";

        //System.out.println("Nombre de la tabla de actores?");
        //nombreTablaActores = scn.next();
        nombreTablaActores = "actores";

        createSQLite.createTabla(nombreTablaPeliculas, nombreTablaActores);


        //:::::::::::::::::::::::::::::::::::::::INTRODUCIMOS LOS REGISTROS
        for (int i = 0; i < 2; i++) {
            int peliculaIndex = 851+i;
            String peliculaID = String.valueOf(peliculaIndex);
            String actoresURL = "https://api.themoviedb.org/3/movie/"+peliculaID+"/credits?api_key="+api_key;
            String peliculasURL = "https://api.themoviedb.org/3/movie/"+peliculaID+"?api_key="+api_key;
            try {

                s = getHTML(peliculasURL);
                jsonToTablaPelis(s, peliculaIndex);
                s = getHTML(actoresURL);
                jsonToTablaActores(s, peliculaIndex);

            } catch (Exception e) {
                System.out.println("La peli " + peliculaID + " no existeix " + e);
            }
        }
    }

    public static void jsonToTablaPelis (String cadena, int id){

        Object jsonObj =JSONValue.parse(cadena);
        JSONObject jsonItem=(JSONObject)jsonObj;
        String titulo = (String) jsonItem.get("original_title");
        String fecha = (String) jsonItem.get("release_date");

        insertSQLite.insertTablaPelis(nombreTablaPeliculas, id, titulo, fecha);
    }

    public static void jsonToTablaActores (String cadena, int idPeli){
        Object jsonObj =JSONValue.parse(cadena);
        JSONObject jsonItem=(JSONObject)jsonObj;
        JSONArray casting = (JSONArray)jsonItem.get("cast");

        for (int i = 0; i < casting.size(); i++) {

            JSONObject jo= (JSONObject)casting.get(i);
            String nombre = (String) jo.get("name");
            long actor = (long) jo.get("id");
            String personaje = (String) jo.get("character");
            insertSQLite.insertTablaActores(nombreTablaActores, lastIdCast, nombre, actor, personaje, idPeli);
            lastIdCast++;
        }
    }

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
}
