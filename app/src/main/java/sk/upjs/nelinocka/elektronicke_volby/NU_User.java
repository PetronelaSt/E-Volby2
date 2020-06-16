package sk.upjs.nelinocka.elektronicke_volby;

import java.io.Serializable;
import java.util.Objects;

public class NU_User implements Serializable {
    private String OP;
    private String PIN;

    public NU_User() {
        // empty constructor
    }

    public NU_User(String OP, String PIN) {
        this.OP = OP;
        this.PIN = PIN;
    }

    @Override
    public String toString() {
        return this.OP + " " + this.PIN;
    }
    // gettery a settery, hashCode() a equals()
}
