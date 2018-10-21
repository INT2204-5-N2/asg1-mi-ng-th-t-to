package Controller;

import Models.Word;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement {
    private static Connection dbConnection;
    //private String path;
    private static Statement statement;
    private String sqlPrefix = "jdbc:sqlite:";

    public DatabaseManagement(String path) {
        File dbFile = new File(path);
        try {
            if(!dbFile.exists()){
                Files.copy(getClass().getClassLoader().getResourceAsStream(path),dbFile.toPath());
            }
            dbConnection = DriverManager.getConnection(sqlPrefix + path);
            statement = dbConnection.createStatement();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewWord(Word newWord){
        String cmd = "INSERT INTO " + DictionaryManagement.getInstance().getTableName() + "(word,pronounce,description) VALUES(?,?,?)";
        try
        {
            PreparedStatement pstmt = dbConnection.prepareStatement(cmd);
            pstmt.setString(1, newWord.getWord_target());
            pstmt.setString(2, newWord.getPronounce());
            pstmt.setString(3, newWord.getWord_explain());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Word> searchByWord(String wordSeatch){
        String cmd = "SELECT * FROM " + DictionaryManagement.getInstance().getTableName() + " where word like \"" + wordSeatch +"%\" order by word ASC;";
        try {
            //statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(cmd);
            ArrayList<Word> result = new ArrayList<>();
            while (resultSet.next())
            {
                Word newWord = new Word();
                newWord.setWord_target(resultSet.getString("word"));
                newWord.setWord_explain(resultSet.getString("description"));
                newWord.setPronounce(resultSet.getString("pronounce"));
                result.add(newWord);
            }
            resultSet.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void edit(Word word){
        String cmd = "UPDATE " + DictionaryManagement.getInstance().getTableName() +
                     " SET pronounce = ?, description = ? WHERE word = ?";
        try {
            PreparedStatement ppsm = dbConnection.prepareStatement(cmd);
            ppsm.setString(1, word.getPronounce());
            ppsm.setString(2, word.getWord_explain());
            ppsm.setString(3, word.getWord_target());
            ppsm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(String word){
        String cmd = "DELETE FROM " + DictionaryManagement.getInstance().getTableName() + " WHERE word = '" + word + "';";
        try {
            statement.execute(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
