package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class AccessKey {

    @Id
    private String keyHash;

    @Transient
    private String rawKey;

    public AccessKey() {
    }

    public AccessKey(String keyHash) {
        this.keyHash = keyHash;
    }

    public String getKeyHash() {
        return keyHash;
    }

    public void setKeyHash(String keyHash) {
        this.keyHash = keyHash;
    }

    public String getRawKey() {
        return rawKey;
    }

    public void setRawKey(String rawKey) {
        this.rawKey = rawKey;
    }

}
