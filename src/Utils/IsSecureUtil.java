package Utils;

import java.util.Random;

public class IsSecureUtil {
    public static int securityLevel(String password){
        //se la lunghezza è maggiore o uguale a 8 e possiede almeno un carattere speciale e almeno un numero
        if(password.matches("^(?=.{8,}$)(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[A-Z]).+$")){
            return 3;
        }
        if((password.matches("^(?=.{8,}$)(?=.*[^A-Za-z0-9]).+$")||password.matches("^(?=.{8,}$)(?=.*\\d).+$"))&&password.matches("^(?=.*[A-Za-z]).+$")){
            return 2;
        }
        if(password.matches("^(?=.{8,}$).+$")){
            return 1;
        }
        return 0;
    }

    public static boolean isSecure(String password){
        if(securityLevel(password)>=2){
            return true;
        }
        return false;
    }

    public static String generateSecurePassword(){
        String password = "";
        Random random = new Random();
        int len=(random.nextInt(8)+12);
        for (int i = 0; i <len; i++){
            char a = (char)(random.nextInt(92)+33);
            if(a==';'||a=='`'){
                if(random.nextBoolean()){
                    a= (char) (a+1);
                }
                else{
                    a= (char) (a-1);
                }
            }
            password += a;
        }
        if(securityLevel(password)==3){
            return password;
        }
        return generateSecurePassword();
    }
}
