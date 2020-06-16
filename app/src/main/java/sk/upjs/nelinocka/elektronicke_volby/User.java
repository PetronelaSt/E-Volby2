package sk.upjs.nelinocka.elektronicke_volby;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String OP;
    private String PIN;
    private Boolean hlasoval;

    public User() {
        // empty constructor
    }

    public User(String OP, String PIN, Boolean hlasoval) {
        this.OP = OP;
        this.PIN = PIN;
        this.hlasoval = hlasoval;
    }

    @Override
    public String toString() {
        return this.OP + " " + this.PIN + " " + this.hlasoval;
    }
    // gettery a settery, hashCode() a equals()

    public String getOP() {
        return OP;
    }

    public String getPIN() {
        return PIN;
    }

    public Boolean getHlasoval() {
        return hlasoval;
    }

    public void setHlasoval(Boolean hlasoval) {
        this.hlasoval = hlasoval;
    }
}
