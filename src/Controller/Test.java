package Controller;

public class Test {
    public static void main(String[] args){
        GoogleTranslation gg = new GoogleTranslation();
        System.out.println(gg.translate("just a test", GoogleTranslation.Language.en, GoogleTranslation.Language.vi));
        /*try {
            gg.playSound("just a test", "en");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
