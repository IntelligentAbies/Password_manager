package Utils;

public class IsSecureUtil {
    public static int securityLevel(String password){
        //se la lunghezza è maggiore o uguale a 8 e possiede almeno un carattere speciale e almeno un numero
        if(password.matches("^(?=.{8,}$)(?=.*\\d)(?=.*[^A-Za-z0-9]).+$")){
            return 3;
        }
        if(password.matches("^(?=.{8,}$)(?=.*[^A-Za-z0-9]).+$")||password.matches("^(?=.{8,}$)(?=.*\\d).+$")){
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
}
