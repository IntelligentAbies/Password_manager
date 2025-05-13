package Model;

import java.util.ArrayList;
import java.util.List;

public class Credential {
    private String site;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public Credential(String site, String username, String email, String phoneNumber, String password) {
        setSite(site);
        setUsername(username);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
    }

    public Credential(){
        setSite("");
        setUsername("");
        setEmail("");
        setPhoneNumber("");
        setPassword("");
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void deformat(String credential){
        String[] a = credential.split(";");
        setSite(a[0]);
        setUsername(a[1]);
        setEmail(a[2]);
        setPhoneNumber(a[3]);
        setPassword(a[4]);
    }

    public String toString() {
        return site + ";" + username + ";" + email + ";" + phoneNumber + ";" + password;
    }
}
