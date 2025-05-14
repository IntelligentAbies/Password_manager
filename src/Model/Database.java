package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {
    public static void init(){
        try {
            File file = new File("credential.db");
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(String ct){
        try {
            FileWriter myWriter = new FileWriter("credential.db");
            myWriter.write(ct);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(){
        try {
            File myObj = new File("credential.db");
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data+= myReader.nextLine();
            }
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
