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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleTranslator {
    public enum Language{
        en, vi
    }

    private InputStream sendGet(String url){
        try {
            URL urlCon = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0");
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
        String encodedWord = wordToTranslate;
        try {
            encodedWord = URLEncoder.encode(wordToTranslate, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://translate.googleapis.com/translate_a/single?client=webapp&hl=vi&sl="
                    +srcLang+"&tl=" + targetLang + "&q=" + encodedWord +
                    "&multires=1&otf=0&pc=0&trs=1&ssel=0&tsel=0&kc=1&dt=t&ie=UTF-8&oe=UTF-8&tk="
                    + generateToken(wordToTranslate);

        InputStream resultStream = sendGet(url);
        Scanner scanner = new Scanner(resultStream, "UTF-8");

        String rawData = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\[(\\[\".*?\\])\\]");
        Matcher matcher = pattern.matcher(rawData);
        matcher.find();
        rawData = matcher.group(1);
        pattern = Pattern.compile("\\[\"(.*?)\"");
        matcher = pattern.matcher(rawData);
        String result = "";
        while (matcher.find()){
            result = result + matcher.group(1);
        }
        result = result.replace("\\n", "\n");
        return result;
    }

    public File getSoundFile(String wordToRead, Language srcLang){
        wordToRead = wordToRead.replace(" ", "+");
        String url = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q="+ wordToRead +"&tl=" + srcLang + "&client=tw-ob ";
        InputStream soundStream = sendGet(url);
        try {
            File temp = new File("tts.mp3");
            Files.write(temp.toPath(), soundStream.readAllBytes());
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /* Code to generate google translate token
       @author Aaron Gokaslan and Dean1510
       Source: https://github.com/lkuza2/java-speech-api
     */
    private static int[] TKK() {
        return new int[]{ 0x6337E , 0x217A58DC + 0x5AF91132 };
    }

    private static int shr32(int x , int bits) {
        if (x < 0) {
            long x_l = 0xffffffffl + x + 1;
            return (int) ( x_l >> bits );
        }
        return x >> bits;
    }

    private static int RL(int a , String b) {//I am not entirely sure what this magic does.
        for (int c = 0; c < b.length() - 2; c += 3) {
            int d = b.charAt(c + 2);
            d = d >= 65 ? d - 87 : d - 48;
            d = b.charAt(c + 1) == '+' ? shr32(a, d) : ( a << d );
            a = b.charAt(c) == '+' ? ( a + ( d & 0xFFFFFFFF ) ) : a ^ d;
        }
        return a;
    }

    private static String generateToken(String text) {
        int tkk[] = TKK();
        int b = tkk[0];
        int e = 0;
        int f = 0;
        List<Integer> d = new ArrayList<Integer>();
        for (; f < text.length(); f++) {
            int g = text.charAt(f);
            if (0x80 > g) {
                d.add(e++, g);
            } else {
                if (0x800 > g) {
                    d.add(e++, g >> 6 | 0xC0);
                } else {
                    if (0xD800 == ( g & 0xFC00 ) && f + 1 < text.length() && 0xDC00 == ( text.charAt(f + 1) & 0xFC00 )) {
                        g = 0x10000 + ( ( g & 0x3FF ) << 10 ) + ( text.charAt(++f) & 0x3FF );
                        d.add(e++, g >> 18 | 0xF0);
                        d.add(e++, g >> 12 & 0x3F | 0x80);
                    } else {
                        d.add(e++, g >> 12 | 0xE0);
                        d.add(e++, g >> 6 & 0x3F | 0x80);
                    }
                }
                d.add(e++, g & 63 | 128);
            }
        }

        int a_i = b;
        for (e = 0; e < d.size(); e++) {
            a_i += d.get(e);
            a_i = RL(a_i, "+-a^+6");
        }
        a_i = RL(a_i, "+-3^+b+-f");
        a_i ^= tkk[1];
        long a_l;
        if (0 > a_i) {
            a_l = 0x80000000l + ( a_i & 0x7FFFFFFF );
        } else {
            a_l = a_i;
        }
        a_l %= Math.pow(10, 6);
        return String.format(Locale.US, "%d.%d", a_l, a_l ^ b);
    }
}

