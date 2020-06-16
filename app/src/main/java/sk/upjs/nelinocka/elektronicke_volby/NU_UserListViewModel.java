package sk.upjs.nelinocka.elektronicke_volby;

import java.util.List;

import androidx.lifecycle.*;
import retrofit2.*;

public class NU_UserListViewModel extends ViewModel {
    private NU_VoteApi voteApi = NU_VoteApi.API;
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            refresh();
        }

        return users;
    }

    public void refresh() {
        Call<List<User>> call = voteApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call,
                                   Response<List<User>> response) {
                if (response.isSuccessful()) {
                    NU_UserListViewModel.this.users.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
