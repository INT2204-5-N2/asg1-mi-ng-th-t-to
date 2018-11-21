package bomberman.Entity;

import java.util.Random;

public class Balloom extends Enemy {
    @Override
    public Status generateMove() {
        Random random=new Random();
        int move=random.nextInt(4);
        switch (move)
        {
            case 0:
                return Status.GO_LEFT;
            case 1:
                return Status.GO_RIGHT;
            case 2:
                return Status.GO_DOWN;
            case 3:
                return Status.GO_UP;
        }
        return Status.GO_DOWN;
    }

//    public static void main(String[] args) {
//        Balloom a=new Balloom();
//        if(a.generateMove()==Status.GO_LEFT)
//        {
//            System.out.println("GO Left");
//        }
//        if(a.generateMove()==Status.GO_RIGHT)
//        {
//            System.out.println("GO right");
//        }
//        if(a.generateMove()==Status.GO_DOWN)
//        {
//            System.out.println("GO down");
//        }
//        if(a.generateMove()==Status.GO_UP)
//        {
//            System.out.println("GO UP");
//        }
//
//    }
}
