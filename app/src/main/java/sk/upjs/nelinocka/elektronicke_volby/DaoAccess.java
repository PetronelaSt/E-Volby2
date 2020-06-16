package sk.upjs.nelinocka.elektronicke_volby;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoAccess {
    @Insert
    Long insertVote(VoteForCandidate voteForCandidate);

    @Query("SELECT * FROM VoteForCandidate")
    LiveData<List<VoteForCandidate>> fetchAllVote();


    @Query("SELECT * FROM VoteForCandidate WHERE name =:candidateName")
    LiveData<VoteForCandidate> getVote(String candidateName);


    @Update
    void updateVote(VoteForCandidate voteForCandidate);

}
