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
    public DatabaseManagement(String path){
        this.path = path;
        try {
            dbConnection = DriverManager.getConnection(sqlPrefix + "." + path);
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void execute(String sqlCommand){
        try {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTable(String tableName, String columnName, String datatype) {
        //this.tableName = tableName;
        String statement = "CREATE TABLE " + tableName + "("+ columnName + " " + datatype +");";
        execute(statement);
    }

    public void addColumn(String columnName, String datatype){
        String cmd = "ALTER TABLE " + tableName +" ADD "+ columnName + " " + datatype + ";";
        execute(cmd);
    }


    public ArrayList<Word> searchByWord(String wordSeatch, String dictType){
        String cmd = "SELECT * FROM " + dictType + " where word like \"" + wordSeatch +"%\";";
        System.out.println(cmd);
        ArrayList<Word> result = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(cmd);
            while(resultSet.next()){
                Word newWord = new Word();
                newWord.setWord_target(resultSet.getString(2));
                newWord.setWord_explain(resultSet.getString(4));
                result.add(newWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}