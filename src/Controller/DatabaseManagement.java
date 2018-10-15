package Controller;

import Models.Word;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement {
    private static Connection dbConnection;
    private String path;
    private static Statement statement;
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
        System.out.println(cmd);
        try {
            ResultSet resultSet = statement.executeQuery(cmd);
            ArrayList<Word> result = new ArrayList<>();
            while(result.size() < MAX_ITEM && resultSet.next()){
                Word newWord = new Word();
                newWord.setId(resultSet.getString(1));
                newWord.setWord_target(resultSet.getString(2));
                newWord.setWord_explain("<p>" + resultSet.getString(3) + "</p><p>" + resultSet.getString(4) + "</p><p>" + resultSet.getString(5) + "</p>");
                result.add(newWord);
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
