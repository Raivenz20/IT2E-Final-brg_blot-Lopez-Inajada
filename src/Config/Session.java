/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

/**
 *
 * @author Raven
 */
public class Session {
    
    private static Session instance;
    private int uid;
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String contact;
    private String username;
    private String type;
    private String status;
    private String fcolor;
    private String fanimal;
    
    public Session(){
        
    }
    
    public static synchronized Session getInstance() {
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public static boolean isIntanceEmpty() {
        return instance == null;
    }
    
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getFcolor() {
        return fcolor;
    }
    
    public void setFcolor(int uid) {
        this.fcolor = fcolor;
    }
    
    public String getFanimal() {
        return fanimal;
    }
    
    public void setFanimal(int uid) {
        this.fanimal = fanimal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

