package Models;

import Controller.DatabaseManagement;

import java.util.Scanner;

public class Dictionary {
    public enum DictType{
        ENG2VIET, VIET2ENG
    }
    private static final String DICT_PATH = "/src/Res/dict_hh.db";
    private DatabaseManagement DICT_db;
    Dictionary(){
        DICT_db = new DatabaseManagement(DICT_PATH);
    }
    public static void main(String[] args){
        Dictionary dictionary = new Dictionary();
        String search;
        Scanner scanner = new Scanner(System.in);
        search = scanner.next();

    }
}
