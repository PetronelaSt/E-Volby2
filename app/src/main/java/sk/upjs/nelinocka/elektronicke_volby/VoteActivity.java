package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sk.upjs.nelinocka.elektronicke_volby.databinding.ActivityVoteBinding;

public class VoteActivity extends AppCompatActivity {
    /*
        private NU_UserListViewModel nu_userListViewModel;
        private NU_VoteApi voteApi = NU_VoteApi.API;
    */
    ActivityVoteBinding binding;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
    String date_end = "20200503203400";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence personOP = binding.personOP.getEditText().getText();
                CharSequence personPIN = binding.personPIN.getEditText().getText();

                if (personOP.toString().isEmpty()) {
                    binding.personOP.setError("Vyplňte OP!");
                    return;
                }
                if (personPIN.toString().isEmpty()) {
                    binding.personPIN.setError("Vyplňte PIN!");
                    return;
                }
                binding.personOP.setError("");
                binding.personPIN.setError("");

                binding.personOP.getEditText().setText("");
                binding.personPIN.getEditText().setText("");

                String currentDateAndTime = sdf.format(new Date());
                if (currentDateAndTime.compareTo(date_end) >= 0) {
                    Toast toast = Toast.makeText(getBaseContext(), "Máme výsledky", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getBaseContext(), "Váš hlas bol odoslaný", Toast.LENGTH_LONG);
                    toast.show();


                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
