package Controller;

public class DictionaryManagement {

    private static DictionaryManagement dictionaryManager;
    private static DatabaseManagement dbManager;
    private String dictType;
    private static final String evDict = "ENG-VIET";
    private static final String veDict = "VIET-ENG";

    private DictionaryManagement(){
        dictType = evDict;
        dbManager = new DatabaseManagement(getDictPath());
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
        switch (dictType){
            case veDict:
                return "va";
            case evDict:
                return "av";
            default:
                return "";

        }
    }

    public String getDictPath(){
        switch (dictType){
            case evDict:
                return "/src/Res/envn.sqlite";
            case veDict:
                return "/src/Res/database/va_all_v2.db";
            default:
                return "";

        }
    }
}
