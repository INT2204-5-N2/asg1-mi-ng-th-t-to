package Models;

import java.sql.*;

public class DatabaseConnection {
    private Connection dbConnection;
    private String path;
    private Statement statement;
    private final static String tableName = "";
    private static final String sqlPrefix = "jdbc:sqlite:";
    private int totalColumn = 3;
    DatabaseConnection(String path){
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
        totalColumn++;
    }

    public void addNewRow(String... args){
        if(args.length == totalColumn){
            String cmd = "INSERT INTO " + tableName + " VALUES (";
            for (int i = 0; i < args.length - 1; i++){
                cmd += args[i] + ", ";
            }
            cmd += args[args.length - 1] + ");";
            System.out.println(cmd);
            execute(cmd);
        }
        else{
            System.out.println("thieu tham so");
        }
    }

    public Word getWord(String str, String dictType){
        String cmd = "SELECT * FROM " + dictType + " where word like \"" + str +"%\";";
        System.out.println(cmd);
        Word result = new Word();
        ResultSet resultSet = null;
        try {
            //Statement stm = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(cmd);
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(4));
                //return result;
            }
            //return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
