import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class themovieDBproject {

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

    private static String nombreTablaPeliculas = "Sin nombre";
    private static String nombreTablaActores = "Sin nombre";


    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);


        //:::::::::::::::::::::::::::::::::::::::PREPARAMOS Y CREAMOS LA TABLA ANTES DE INSERTAR REGISTROS
        String s = "";
        String api_key = "e6f2c549601727fca2e90f4291bbe34d";

        System.out.println("¿Nombre de la tabla de peliculas?");
        nombreTablaPeliculas = scn.next();

        System.out.println("Nombre de la tabla de actores?");
        nombreTablaActores = scn.next();

        createSQLite.createTabla(nombreTablaPeliculas, nombreTablaActores);

        /*
        //:::::::::::::::::::::::::::::::::::::::INTRODUCIMOS LOS REGISTROS
        for (int i = 0; i < 2; i++) {
            int peliculaIndex = 620+i;
            String peliculaID = String.valueOf(peliculaIndex);
            String actoresURL = "https://api.themoviedb.org/3/movie/"+peliculaID+"/credits?api_key="+api_key;
            String peliculasURL = "https://api.themoviedb.org/3/movie/"+peliculaID+"?api_key="+api_key;
            try {
                //s = getHTML(actoresURL);
                //SJC(s);
                s = getHTML(peliculasURL);
                SJS(s);
            } catch (Exception e) {
                System.out.println("La peli " + peliculaID + " no existeix" + e);
            }





        }*/
    }

    public static void SJS (String cadena){

        Object jsonCadena =JSONValue.parse(cadena);
        JSONObject jsonItem=(JSONObject)jsonCadena;
        String titulo = (String) jsonItem.get("original_title");
        String fecha = (String) jsonItem.get("release_date");

        System.out.print(titulo);
        System.out.println(fecha);
    }

    public static void SJC (String cadena){
        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        JSONArray arra03 = (JSONArray)arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb= (JSONObject)arra03.get(i);
            System.out.println(jb.get("character")+"<-->"+jb.get("name"));
        }
    }

}
