package Model;

import Exceptions.CredentialDoesntExist;
import Exceptions.DatabaseNotSetupped;
import Exceptions.PasswordIsWrong;
import Utils.AesEncryptionUtil;
import Utils.IsAValidCredentialUtils;

import java.util.ArrayList;
import java.util.List;

public class SetOfCredential {
    private static List<Credential> credentials;
    private static String password;
    private static boolean isSetupped;

    public SetOfCredential() {
        Database.init();
        if(Database.read().isEmpty()){
            isSetupped = false;
            credentials = new ArrayList<Credential>();
        }
        else{
            isSetupped = true;
        }
    }

    public void add(Credential c) throws PasswordIsWrong {
        IsAValidCredentialUtils.isValid(c, credentials);
        credentials.add(c);
        String cleartext=toString();
        String ciphertext = AesEncryptionUtil.encrypt(cleartext,password);
        Database.update(ciphertext);
        if(!isSetupped){
            isSetupped = true;
        }
    }

    public void add(String s) throws PasswordIsWrong {
        String ciphertext = AesEncryptionUtil.encrypt(s,password);
        Database.update(ciphertext);
        if(!isSetupped){
            isSetupped = true;
        }
    }

    public List<Credential> getCredentials() throws PasswordIsWrong {
        return getCredentials(password);
    }

    public List<Credential> getCredentials(String password) throws PasswordIsWrong {
        if(!isSetupped){
            throw new DatabaseNotSetupped("The database has not been setupped yet");
        }
        String ciphertext = Database.read();
        String cleartext = AesEncryptionUtil.decrypt(ciphertext,password);
        String[] tmp = cleartext.split("\n");
        credentials=new ArrayList<>();
        if(!tmp[0].isEmpty()) {
            for (String formatted_credential : tmp) {
                Credential c = new Credential();
                c.deformat(formatted_credential);
                credentials.add(c);
            }
        }
        return credentials;
    }

    public boolean getIsSetupped(){
        return isSetupped;
    }

    public void setPassword(String password) throws PasswordIsWrong {
        if(isSetupped){
            credentials=getCredentials(password);
        }
        this.password = password;
    }

    public List<Credential> find(int type,String search) {
        List<Credential> creds = new ArrayList<>();
        String regex = ".*"+(search.toLowerCase())+".*";
        switch(type){
            case 0:
                for(Credential credential : credentials){
                    if(credential.getSite().toLowerCase().matches(regex)){
                        creds.add(credential);
                    }
                }
                break;
            case 1:
                for(Credential credential : credentials){
                    if(credential.getUsername().toLowerCase().matches(regex)){
                        creds.add(credential);
                    }
                }
                break;
            case 2:
                for(Credential credential : credentials){
                    if(credential.getEmail().toLowerCase().matches(regex)){
                        creds.add(credential);
                    }
                }
                break;
            case 3:
                for(Credential credential : credentials){
                    if(credential.getPhoneNumber().toLowerCase().matches(regex)){
                        creds.add(credential);
                    }
                }
                break;
            case 4:
                for(Credential credential : credentials){
                    creds.add(credential);
                }
                break;
        }

        return creds;
    }

    public Credential find(String site,String username){
        int i=0;
        boolean found=false;
        while(i<credentials.size()&&!found){
            if(credentials.get(i).getSite().equals(site) && credentials.get(i).getUsername().equals(username)){
                found=true;
                i--;
            }
            i++;
        }
        if(!found){
            throw new CredentialDoesntExist("Non esiste NESSUNA credenziale con Site: \""+site+"\" e Username: \""+username+"\"");
        }
        else{
            return credentials.get(i);
        }
    }

    public void remove(String site, String username) throws PasswordIsWrong {
        int i=0;
        boolean found = false;
        while(i<credentials.size()&&!found){
            Credential cred = credentials.get(i);
            if(cred.getUsername().equals(username)&&cred.getSite().equals(site)){
                credentials.remove(i);
                found = true;
            }
            i++;
        }
        if(!found){
            throw new CredentialDoesntExist("Non esiste NESSUNA credenziale con Site: \""+site+"\" e Username: \""+username+"\"");
        }else{
            String cleartext= toString();
            String ciphertext = AesEncryptionUtil.encrypt(cleartext,password);
            Database.update(ciphertext);
        }
    }

    public String toString(){
        String s="";
        for(Credential credential : credentials) {
            s += credential.toString() + "\n";
        }
        return s;
    }
}
