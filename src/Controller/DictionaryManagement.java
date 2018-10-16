package Controller;

public class DictionaryManagement {

    private static DictionaryManagement dictionaryManager;
    private static DatabaseManagement dbManager;
    private String dictType;
    public static final String evDict = "ENG-VIET";
    public static final String veDict = "VIET-ENG";

    private DictionaryManagement(){
        dictType = evDict;
        dbManager = new DatabaseManagement(getDictPath());
    }

    public String getDictType() {
        return dictType;
    }

    public static DictionaryManagement getInstance(){
        if(dictionaryManager == null){
            dictionaryManager = new DictionaryManagement();
        }
        return dictionaryManager;
    }

    public void setDictType(String dictType){
        this.dictType = dictType;
        dbManager = new DatabaseManagement(getDictPath());
    }
    public DatabaseManagement getDBManager(){
        return dbManager;
    }
    public String getTableName(){
        /*switch (dictType){
            case veDict:
                return "va";
            case evDict:
                return "av";
            default:
                return "";

        }*/
        return "word_btl";
    }

    public String getDictPath(){
        switch (dictType){
            case evDict:
                //return "/src/Res/envn.sqlite";
                return "/src/Res/database/av_all_v2.db";
            case veDict:
                return "/src/Res/database/va_all_v2.db";
            default:
                return "";

        }
    }
}
