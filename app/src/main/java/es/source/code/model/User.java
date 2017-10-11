package es.source.code.model;

import java.io.Serializable;

/**
 * Created by zhoutao on 2017/10/10.
 */

public class User implements Serializable{
    String userName;
    String password;
    Boolean oldUser;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getOldUser() {
        return oldUser;
    }

    public void setOldUser(Boolean oldUser) {
        this.oldUser = oldUser;
    }
}
