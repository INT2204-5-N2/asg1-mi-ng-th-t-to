package Controller;

import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleTranslation {
    public String translate(String input, String srcLang, String targetLang){
        input = URLEncoder.encode((input));
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="+srcLang+"&tl=" + targetLang + "&dt=t&q=" + input;
        String result = Helper.HttpConnection.sendGet(url);
        System.out.println(result);
        return result.substring(result.indexOf("[[[\"") + 4, result.indexOf("\",\""));
    }
}
