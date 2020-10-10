package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Status {

    @Id
    private String id;

    private String name;

    private Boolean isDefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

}