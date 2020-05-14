import db.DataBase;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        System.out.println(db.getAllTracks());
    }
}
