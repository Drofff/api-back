package ua.ozzy.apiback.model;

import ua.ozzy.apiback.enums.Role;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends SystemUser {

    @Id
    private String id;

    private String username;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Role getRole() {
        return Role.ADMIN;
    }

    public static class Builder {

        private final Admin admin = new Admin();

        public Builder id(String id) {
            admin.setId(id);
            return this;
        }

        public Builder username(String username) {
            admin.setUsername(username);
            return this;
        }

        public Builder password(String password) {
            admin.setPassword(password);
            return this;
        }

        public Admin build() {
            return admin;
        }

    }

}
