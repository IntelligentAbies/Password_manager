package Utils;

import Exceptions.CredentialNotValid;
import Model.Credential;

import java.util.List;

public class IsAValidCredentialUtils {
    public static void isValid(Credential c, List<Credential> credentials) {
        isUnique(c, credentials);
        notSemicolonInCredential(c);
        siteAndPasswordArePresent(c);
    }
    public static void notSemicolonInCredential(Credential c) {
        if(c.getEmail().matches(".*;.*")||c.getPassword().matches(".*;.*")||c.getSite().matches(".*;.*")||c.getUsername().matches(".*;.*")||c.getPhoneNumber().matches(".*;.*")){
            throw new CredentialNotValid("Non puoi usare un \";\" nelle credenziali : (");
        }
    }
    public static void siteAndPasswordArePresent(Credential c) {
        if(c.getSite().isEmpty() || c.getPassword().isEmpty()){
            throw new CredentialNotValid("Il Campo del sito e della password non devono essere vuoti");
        }
    }
    public static void isUnique(Credential c, List<Credential> credentials) {
        for (Credential c1 : credentials) {
            if(c.getSite().equals(c1.getSite()) && c.getUsername().equals(c1.getUsername())){
                throw new CredentialNotValid("Esiste già una credenziale con il Sito: \"" + c.getSite()+"\" e Username: \""+c.getUsername()+"\"");
            }
        }
    }
}
