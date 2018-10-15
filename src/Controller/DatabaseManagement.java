package Controller;

import Models.Dictionary;
import Models.SQLQuery;
import Models.Word;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement {
    private Connection dbConnection;
    private String path;
    private Statement statement;
    private String tableName = "";
    private String sqlPrefix = "jdbc:sqlite:";

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

    public DatabaseManagement() {

    }


    /*public void execute(String sqlCommand){
        try {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /*public void addColumn(String columnName, String datatype){
        String cmd = "ALTER TABLE " + tableName +" ADD "+ columnName + " " + datatype + ";";
        try {
            statement.execute(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void addNewWord(Word newWord, Dictionary.DictType dictType){
        String cmd = "INSERT INTO " + Dictionary.getTableName(dictType) + "(word,description) VALUES(?,?)";
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

    public ArrayList<Word> searchByWord(String wordSeatch, Dictionary.DictType dictType){
        String cmd = "SELECT * FROM " + Dictionary.getTableName(dictType) + " where word like \"" + wordSeatch +"%\" order by word ASC;";
        System.out.println(cmd);
        try {
            ResultSet resultSet = statement.executeQuery(cmd);
            return SQLQuery.getInstance().resultSetToWordConvert(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Word> showAllWords(Dictionary.DictType dictType){
        String cmd = "SELECT * FROM " + Dictionary.getTableName(dictType) + " ORDER BY word ASC;";
        try {
            ResultSet resultSet = statement.executeQuery(cmd);
            return SQLQuery.getInstance().resultSetToWordConvert(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String id, Dictionary.DictType dictType){
        String cmd = "DELETE FROM " + Dictionary.getTableName(dictType) + " WHERE id = " + id + ";";
        System.out.println(cmd);
        try {
            statement.execute(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
