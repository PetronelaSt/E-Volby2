package sk.upjs.nelinocka.elektronicke_volby;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {VoteForCandidate.class}, version = 1, exportSchema = false)
public abstract class VoteForCandidateDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
