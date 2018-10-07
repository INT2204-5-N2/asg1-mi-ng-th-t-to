package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLQuery {
    private static SQLQuery instance = null;

    private SQLQuery(){

    };

    public static SQLQuery getInstance(){
        if(instance == null){
            instance = new SQLQuery();
        }
        return instance;
    }

    public ArrayList<Word> resultSetToWordConvert(ResultSet resultSet){
        ArrayList<Word> result = new ArrayList<>();
        try {
            while(resultSet.next()){
                Word newWord = new Word();
                newWord.setWord_target(resultSet.getString(2));
                newWord.setWord_explain(resultSet.getString(4));
                result.add(newWord);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
