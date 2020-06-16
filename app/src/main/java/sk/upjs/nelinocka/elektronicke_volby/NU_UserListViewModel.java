package sk.upjs.nelinocka.elektronicke_volby;

import java.util.List;

import androidx.lifecycle.*;
import retrofit2.*;

public class NU_UserListViewModel extends ViewModel {
    private NU_VoteApi voteApi = NU_VoteApi.API;
    private MutableLiveData<List<NU_User>> users;

    public LiveData<List<NU_User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            refresh();
        }

        return users;
    }

    public void refresh() {
        Call<List<NU_User>> call = voteApi.getUsers();
        call.enqueue(new Callback<List<NU_User>>() {
            @Override
            public void onResponse(Call<List<NU_User>> call,
                                   Response<List<NU_User>> response) {
                if (response.isSuccessful()) {
                    NU_UserListViewModel.this.users.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<NU_User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
