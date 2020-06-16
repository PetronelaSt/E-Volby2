package sk.upjs.nelinocka.elektronicke_volby;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// https://android.jlelse.eu/5-steps-to-implement-room-persistence-library-in-android-47b10cd47b24

@Entity
public class VoteForCandidate implements Serializable {
    @PrimaryKey
    @NotNull
    private String name;

    private Integer sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
