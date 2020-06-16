package sk.upjs.nelinocka.elektronicke_volby;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class VoteForCandidateRepository {
    private String DB_NAME = "voteDB";
    private VoteForCandidateDatabase voteForCandidateDatabase;

    public VoteForCandidateRepository(Context context) {
        voteForCandidateDatabase = Room.databaseBuilder(context, VoteForCandidateDatabase.class, DB_NAME).build();
    }

    public void insertVote(String candidateName,
                           Integer sum) {

        insertVote(candidateName, sum);
    }

    public void insertVote(final VoteForCandidate voteForCandidate) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                voteForCandidateDatabase.daoAccess().insertVote(voteForCandidate);
                return null;
            }
        }.execute();
    }

    public void updateVote(final VoteForCandidate voteForCandidate) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                voteForCandidateDatabase.daoAccess().updateVote(voteForCandidate);
                return null;
            }
        }.execute();
    }

    public LiveData<VoteForCandidate> getVote(String candidateName) {
        return voteForCandidateDatabase.daoAccess().getVote(candidateName);
    }

    public LiveData<List<VoteForCandidate>> getVotes() {
        return voteForCandidateDatabase.daoAccess().fetchAllVote();
    }
}
