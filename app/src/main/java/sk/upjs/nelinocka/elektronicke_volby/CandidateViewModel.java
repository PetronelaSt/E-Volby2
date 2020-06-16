package sk.upjs.nelinocka.elektronicke_volby;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CandidateViewModel extends ViewModel {
    private MutableLiveData<String> selectedCandidate = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedCandidateID = new MutableLiveData<>();


    public LiveData<String> getSelectedCandidate() {
        return selectedCandidate;
    }

    public LiveData<Integer> getSelectedCandidateID() {
        return selectedCandidateID;
    }

    public void setSelectedCandidate(String selectedCandidate) {
        this.selectedCandidate.postValue(selectedCandidate);
    }

    public void setSelectedCandidateID(int position) {
        this.selectedCandidateID.postValue(position);
    }
}
