package Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Scanner;

public class GoogleTranslator {
    public enum Language{
        en, vi
    }

    private InputStream sendGet(String url){
        try {
            URL urlCon = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Rigor API Tester");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            return connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String translate(String wordToTranslate, Language srcLang, Language targetLang){
        //wordToTranslate = wordToTranslate.replace(" ", "+");
        try {
            wordToTranslate = URLEncoder.encode(wordToTranslate, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="+srcLang+"&tl=" + targetLang + "&dt=t&q=" + wordToTranslate + "&ie=\"UTF-8\"&oe=\"UTF-8\"";

        InputStream resultStream = sendGet(url);
        Scanner scanner = new Scanner(resultStream);
        String result = scanner.nextLine();
        System.out.println(result);
        return result.substring(result.indexOf("[[[\"") + 4, result.indexOf("\",\""));
    }

    public File getSoundFile(String wordToRead, Language srcLang){
        wordToRead = wordToRead.replace(" ", "+");
        String url = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q="+ wordToRead +"&tl=" + srcLang + "&client=tw-ob ";
        InputStream soundStream = sendGet(url);
        try {
            File temp = File.createTempFile("tts", ".mp3");
            Files.write(temp.toPath(), soundStream.readAllBytes());
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

