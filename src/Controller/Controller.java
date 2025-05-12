package Controller;

import Exceptions.PasswordIsWrong;
import Model.Credential;
import Model.SetOfCredential;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private SetOfCredential credentials;

    public Controller(){
        credentials = new SetOfCredential();
    }

    public List<Credential> getData() throws PasswordIsWrong {
        return credentials.getCredentials();
    }

    public boolean neverStarted(){
        return !credentials.getIsSetupped();
    }

    public void addCredential(Credential credential) throws PasswordIsWrong {
        credentials.add(credential);
    }

    public void setPassword(String password) throws PasswordIsWrong {
        credentials.setPassword(password);
    }

    public List<Credential> findType(String type,String search) throws PasswordIsWrong {
        List<Credential> creds = new ArrayList<>();
        //Intellj mi consigliava questa sintassi e la trovavo molto carina
        creds = switch (type) {
            case "Sito" -> credentials.find(0, search);
            case "Username" -> credentials.find(1, search);
            case "Email" -> credentials.find(2, search);
            case "Telefono" -> credentials.find(3, search);
            case "Nessun Filtro" -> credentials.find(4, search);
            default -> creds;
        };
        return creds;
    }

    public Credential find(String site,String username) {
        return credentials.find(site,username);
    }

    public void removeCredential(String site,String username) throws PasswordIsWrong {
        credentials.remove(site,username);
    }
}
