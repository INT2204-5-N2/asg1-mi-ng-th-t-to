package Controller;

import Models.Word;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement {
    private Connection dbConnection;
    private String path;
    private Statement statement;
    private String tableName = "";
    private String sqlPrefix = "jdbc:sqlite:";
    private static final int MAX_ITEM = 30;

    public DatabaseManagement(String path){
        this.path = path;
        if(dbConnection == null){
            try {
                dbConnection = DriverManager.getConnection(sqlPrefix + "." + path);
                statement = dbConnection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addNewWord(Word newWord){
        String cmd = "INSERT INTO " + DictionaryManagement.getInstance().getTableName() + "(word,description) VALUES(?,?)";
        try
        {
            PreparedStatement pstmt = dbConnection.prepareStatement(cmd);
            pstmt.setString(1, newWord.getWord_target());
            pstmt.setString(2, newWord.getWord_explain());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Word> searchByWord(String wordSeatch){
        String cmd = "SELECT * FROM " + DictionaryManagement.getInstance().getTableName() + " where word like \"" + wordSeatch +"%\" order by word ASC;";
        try {
            ResultSet resultSet = statement.executeQuery(cmd);
            ArrayList<Word> result = new ArrayList<>();
            if(DictionaryManagement.getInstance().getDictType().equals(DictionaryManagement.evDict)){
                while(result.size() < MAX_ITEM && resultSet.next()){
                    Word newWord = new Word();
                    newWord.setWord_target(resultSet.getString("word"));
                    newWord.setWord_explain(new String(resultSet.getBytes("av")));
                    newWord.setTechnical(new String(resultSet.getBytes("cnav")));
                    newWord.setSynonym(new String(resultSet.getBytes("dnpn")));
                    newWord.setEng2Eng(new String(resultSet.getBytes("aa")));
                    result.add(newWord);
                }
            }
            else {
                while (result.size() < MAX_ITEM && resultSet.next()){
                    Word newWord = new Word();
                    newWord.setWord_target(resultSet.getString("word"));
                    newWord.setWord_explain(new String(resultSet.getBytes(3)));
                    result.add(newWord);
                }
            }
            resultSet.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String id){
        String cmd = "DELETE FROM " + DictionaryManagement.getInstance().getTableName() + " WHERE id = " + id + ";";
        System.out.println(cmd);
        try {
            statement.execute(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
