package Controller;

import Helper.HttpConnection;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class GoogleTranslator {
    public enum Language{
        en, vi
    }
    public String translate(String wordToTranslate, Language srcLang, Language targetLang){
        wordToTranslate = wordToTranslate.replace(" ", "+");
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="+srcLang+"&tl=" + targetLang + "&dt=t&q=" + wordToTranslate;
        InputStream resultStream = HttpConnection.sendGet(url);
        Scanner scanner = new Scanner(resultStream);
        String result = scanner.nextLine();
        System.out.println(result);
        return result.substring(result.indexOf("[[[\"") + 4, result.indexOf("\",\""));
    }

    public File getSoundFile(String wordToRead, Language srcLang){
        wordToRead = wordToRead.replace(" ", "+");
        String url = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q="+ wordToRead +"&tl=" + srcLang + "&client=tw-ob ";
        InputStream soundStream = HttpConnection.sendGet(url);
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

