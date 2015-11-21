

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




/**
 * Created by dremon on 09/11/15.
 */
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

    public static void main(String[] args){
        String s = "";
        String api_key = "e6f2c549601727fca2e90f4291bbe34d";

        for (int i = 0; i < 5; i++) {
            int peli = 2 +i;
            String film = String.valueOf(peli);
            String peticio = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key;
            try {
                s = getHTML(peticio);
                SJS(s);
            } catch (Exception e) {
                System.out.print("La peli " + film + " no existeix");
                System.out.println(s);
            }
        }


    }

    public static void SJS (String cadena){

        Object jsonCadena =JSONValue.parse(cadena);
        JSONObject jsonItem=(JSONObject)jsonCadena;
        System.out.print(jsonItem.get("original_title")+" ");
        System.out.print(jsonItem.get("release_date"));
        System.out.println(cadena);
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
