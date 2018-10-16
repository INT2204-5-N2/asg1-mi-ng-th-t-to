package Controller;

import Helper.HttpConnection;

import java.io.InputStream;
import java.util.Scanner;

public class GoogleTranslation {
    public String translate(String wordToTranslate, String srcLang, String targetLang){
        wordToTranslate = wordToTranslate.replace(" ", "+");
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="+srcLang+"&tl=" + targetLang + "&dt=t&q=" + wordToTranslate;
        InputStream resultStream = HttpConnection.sendGet(url);
        Scanner scanner = new Scanner(resultStream);
        String result = scanner.nextLine();
        System.out.println(result);
        return result.substring(result.indexOf("[[[\"") + 4, result.indexOf("\",\""));
    }
}
//srcLang: En
//targerLang: Vi

