package Controller;

public class Test {
    public static void main(String[] args){
        GoogleTranslator gg = new GoogleTranslator();
        String s = "ghét";
        System.out.println(gg.translate(s, GoogleTranslator.Language.vi, GoogleTranslator.Language.en));
        /*try {
            gg.playSound("just a test", "en");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
