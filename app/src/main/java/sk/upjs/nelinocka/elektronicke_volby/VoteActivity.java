package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

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

    TextView voteForCandidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        voteForCandidate = findViewById(R.id.voteForCandidate);

        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("SharedPreferences", MODE_APPEND);
        String candidateName = sh.getString("candidateName", " ");

        voteForCandidate.setText("Hlasujete za " + candidateName.toString());

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

                if (!personOP.toString().isEmpty() && !personPIN.toString().isEmpty()) {
                    overeniePrihlasenia(binding.personOP, binding.personPIN, candidateName);
                }

                binding.personOP.setError("");
                binding.personPIN.setError("");

                binding.personOP.getEditText().setText("");
                binding.personPIN.getEditText().setText("");
            }
        });
    }

    private void overeniePrihlasenia(TextInputLayout personOP, TextInputLayout personPIN, String candidateName) {
        if (personOP.getEditText().getText().toString().equals("AH315681") && personPIN.getEditText().getText().toString().equals("9811")) {
            binding.personOP.getEditText().setText("");
            binding.personPIN.getEditText().setText("");

            String currentDateAndTime = sdf.format(new Date());
            if (currentDateAndTime.compareTo(date_end) >= 0) {
                Toast toast = Toast.makeText(getBaseContext(), "Máme výsledky", Toast.LENGTH_LONG);
                toast.show();

                Intent i = new Intent(getBaseContext(), ChartActivity.class);
                startActivity(i);
            } else {
                odoslalHlas(personOP, personPIN);
                pripocitajHlas(candidateName);
                Toast toast = Toast.makeText(getBaseContext(), "Váš hlas bol odoslaný", Toast.LENGTH_LONG);
                toast.show();


                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        } else {
            Toast toast = Toast.makeText(getBaseContext(), "Nesprávne prihlasovacie údaje", Toast.LENGTH_LONG);
            toast.show();

            binding.personOP.getEditText().setText("");
            binding.personPIN.getEditText().setText("");
        }
    }

    private void odoslalHlas(TextInputLayout personOP, TextInputLayout personPIN) {

    }

    private void pripocitajHlas(String candidateName) {

    }
}
