package Controller;

public class Test {
    public static void main(String[] args){
        GoogleTranslator gg = new GoogleTranslator();
        System.out.println(gg.translate("just a test", GoogleTranslator.Language.en, GoogleTranslator.Language.vi));
        /*try {
            gg.playSound("just a test", "en");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
