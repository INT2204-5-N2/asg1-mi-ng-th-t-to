package Models;

public class Dictionary {

    public enum DictType{
        ENG2VIET, VIET2ENG
    }

    private static final String DICT_PATH = "/src/Res/dict_hh.db";

    public static String getTableName(DictType dictType){
        switch (dictType){
            case VIET2ENG:
                return "va";
            case ENG2VIET:
                return "av";
            default:
                return "";

        }
    }

}
