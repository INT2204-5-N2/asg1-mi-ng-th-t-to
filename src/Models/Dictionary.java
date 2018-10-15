package Models;

public class Dictionary {

    public enum DictType{
        ENG2VIET, VIET2ENG
    }

    public static final String DICT_PATH = "/src/Res/database/av_all_v2.db";

    public static String getTableName(DictType dictType){
        switch (dictType){
            case VIET2ENG:
                return "va";
            case ENG2VIET:
                return "word_tbl";
            default:
                return "";

        }
    }

}
