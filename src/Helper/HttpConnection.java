package Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection {

    public static String sendGet(String url){
        try {
            URL urlCon = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Rigor API Tester");
            int responseCode = connection.getResponseCode();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inputStream.readLine()) != null) {
                response.append(inputLine);
            }
            inputStream.close();
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
