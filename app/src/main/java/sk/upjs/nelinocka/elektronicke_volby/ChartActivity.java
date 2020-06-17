package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Random;

public class ChartActivity extends AppCompatActivity {

    PieChart pieChart;
    Random rand = new Random();

    private String[] candidateNames = new String[]{
            "Albus Dumbledor", "Filius Flitwick", "Gilderoy Lockhart",
            "Horace Slughorn", "Minerva McGonagall", "Pomona Sprout", "Remus Lupin",
            "Rubeus Hagrid", "Severus Snape", "Sybill Trelawney"};
    int[] colors = new int[candidateNames.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        pieChart = findViewById(R.id.pieChart);

        PieDataSet pieDataSet = new PieDataSet(dataValues(), "");
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(35);
    }

    private ArrayList<PieEntry> dataValues() {
        //  VoteForCandidateRepository voteForCandidateRepository = new VoteForCandidateRepository(getApplicationContext());
        ArrayList<PieEntry> hodnoty = new ArrayList<PieEntry>();
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        for (int i = 0; i < candidateNames.length; i++) {
            //     VoteForCandidate voteForCandidate = voteForCandidateRepository.getVote(candidateNames[i]).getValue();
            int sum = sh.getInt(candidateNames[i], 0);
            System.out.println(candidateNames[i] + " " + sum);

            hodnoty.add(new PieEntry(sum, candidateNames[i]));
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colors[i] = Color.rgb(r, g, b);
        }
        return hodnoty;
    }
}
