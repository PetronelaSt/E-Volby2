package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sk.upjs.nelinocka.elektronicke_volby.databinding.ActivityVoteBinding;

public class VoteActivity extends AppCompatActivity {
    ActivityVoteBinding binding;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

    TextView voteForCandidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        voteForCandidate = binding.voteForCandidate;

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
        if (overHeslo(personOP, personPIN) == true) {
            AlertDialog ad = new AlertDialog.Builder(this)
                    .setTitle("Potvrdenie o odoslaní hlasu")
                    .setMessage("Naozaj chcete odoslať hlas kandidátovi " + candidateName + "?")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            @SuppressLint("WrongConstant") SharedPreferences sh
                                    = getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
                            String date_end = sh.getString("endTimeForVoting", " ");
                            String currentDateAndTime = sdf.format(new Date());
                            if (currentDateAndTime.compareTo(date_end) >= 0) {
                                Toast toast = Toast.makeText(getBaseContext(), "Máme výsledky", Toast.LENGTH_LONG);
                                toast.show();

                                Intent j = new Intent(getBaseContext(), ChartActivity.class);
                                startActivity(j);
                            } else {
                                if (odoslalHlas(personPIN) == true) {
                                    Toast toast = Toast.makeText(getBaseContext(), "Už ste hlasovali", Toast.LENGTH_LONG);
                                    toast.show();

                                    Intent j = new Intent(getBaseContext(), MainActivity.class);
                                    startActivity(j);
                                } else if (odoslalHlas(personPIN) == false) {
                                    pripocitajHlas(candidateName);
                                    odosliHlas(personPIN);
                                    Toast toast = Toast.makeText(getBaseContext(), "Váš hlas bol odoslaný", Toast.LENGTH_LONG);
                                    toast.show();

                                    Intent j = new Intent(getBaseContext(), MainActivity.class);
                                    startActivity(j);
                                }
                            }
                        }
                    })

                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            Toast toast = Toast.makeText(getBaseContext(), "Nesprávne prihlasovacie údaje", Toast.LENGTH_LONG);
            toast.show();

            binding.personOP.getEditText().setText("");
            binding.personPIN.getEditText().setText("");
        }
    }

    private boolean odoslalHlas(TextInputLayout personPIN) {
        boolean hlasoval;
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        hlasoval = sh.getBoolean(personPIN.getEditText().getText().toString(), false);
        return hlasoval;
    }

    private void odosliHlas(TextInputLayout personPIN) {
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(personPIN.getEditText().getText().toString(), true);
        editor.commit();
    }

    private boolean overHeslo(TextInputLayout personOP, TextInputLayout personPIN) {
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        String heslo = sh.getString(personOP.getEditText().getText().toString(), "");
        return heslo.equals(personPIN.getEditText().getText().toString());
    }

    private void pripocitajHlas(String candidateName) {
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        int sum = sh.getInt(candidateName, 0);
        sum = sum + 1;
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(candidateName, sum);
        editor.commit();
        /*
        VoteForCandidateRepository voteForCandidateRepository = new VoteForCandidateRepository(getApplicationContext());
        VoteForCandidate voteForCandidate = voteForCandidateRepository.getVote(candidateName).getValue();
        voteForCandidate.setName(candidateName);
        int povodne = voteForCandidate.getSum();
        voteForCandidate.setSum(povodne + 1);

        voteForCandidateRepository.updateVote(voteForCandidate);*/
    }
}
