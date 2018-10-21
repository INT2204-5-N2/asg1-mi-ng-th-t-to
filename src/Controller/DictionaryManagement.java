package Controller;

import java.sql.SQLException;

public class DictionaryManagement {

    private static DictionaryManagement dictionaryManager;
    private static DatabaseManagement dbManager;
    private String dictType;
    public static final String evDict = "ENG-VIET";
    public static final String veDict = "VIET-ENG";

    private DictionaryManagement() throws SQLException {
        dictType = evDict;
        dbManager = new DatabaseManagement(getDictPath());
    }

    public String getDictType() {
        return dictType;
    }

    public static DictionaryManagement getInstance(){
        if(dictionaryManager == null){
            try {
                dictionaryManager = new DictionaryManagement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dictionaryManager;
    }

    public void setDictType(String dictType){
        this.dictType = dictType;
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
        return "Res/dict_hh.db";
    }
}
