package Models;

import java.util.Scanner;

public class Dictionary {
    private static final String EV_PATH = "/src/Res/dict_hh.db";
    private DatabaseConnection EV_db = new DatabaseConnection(EV_PATH);

    public static void main(String[] args){
        Dictionary dictionary = new Dictionary();
        String search;
        Scanner scanner = new Scanner(System.in);
        search = scanner.next();
        /*Word test =*/ dictionary.EV_db.getWord(search, "av");
        /*System.out.println(test.getWord_target() + "\n" + test.getWord_explain());*/
    }
}
