package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import sk.upjs.nelinocka.elektronicke_volby.databinding.ActivityChartBinding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Random;

public class ChartActivity extends AppCompatActivity {

    PieChart pieChart;
    Random rand = new Random();
    ActivityChartBinding binding;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        binding = ActivityChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pieChart = binding.pieChart;

        PieDataSet pieDataSet = new PieDataSet(dataValues(), "");
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);

        pieData.setValueTextSize(12);

        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(35);

        Legend legend = pieChart.getLegend();
        legend.setTextSize(12);
        legend.setWordWrapEnabled(true);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int i = pieChart.getData().getDataSetForEntry(e).getEntryIndex((PieEntry) e);

                View view = getLayoutInflater().from(ChartActivity.this).inflate(R.layout.listview_row, null);
                ImageView img = view.findViewById(R.id.candidateImg);
                TextView name = view.findViewById(R.id.candidateName);
                TextView pp = view.findViewById(R.id.candidatePoliticalParty);

                img.setImageResource(candidateImages[i]);
                name.setText(candidateNames[i]);
                pp.setText(candidatePoliticalParties[i]);

                AlertDialog.Builder adb = new AlertDialog.Builder(ChartActivity.this);
                adb.setCancelable(true);
                adb.setView(view);
                alertDialog = adb.create();
                alertDialog.show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private ArrayList<PieEntry> dataValues() {
        //  VoteForCandidateRepository voteForCandidateRepository = new VoteForCandidateRepository(getApplicationContext());
        ArrayList<PieEntry> hodnoty = new ArrayList<PieEntry>();
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        for (int i = 0; i < candidateNames.length; i++) {
            //     VoteForCandidate voteForCandidate = voteForCandidateRepository.getVote(candidateNames[i]).getValue();
            int sum = sh.getInt(candidateNames[i], 0);
            // System.out.println(candidateNames[i] + " " + sum);
            hodnoty.add(new PieEntry(sum, candidateNames[i]));
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colors[i] = Color.rgb(r, g, b);
        }
        return hodnoty;
    }


    private String[] candidateNames = new String[]{
            "Albus Dumbledor", "Filius Flitwick", "Gilderoy Lockhart",
            "Horace Slughorn", "Minerva McGonagall", "Pomona Sprout", "Remus Lupin",
            "Rubeus Hagrid", "Severus Snape", "Sybill Trelawney"};
    private String[] candidatePoliticalParties = new String[]{"Gryffindor",
            "Ravenclaw", "Ravenclaw", "Slytherin", "Gryffindor", "Hufflepuff", "Gryffindor",
            "Gryffindor", "Slytherin", "Ravenclaw"};
    private Integer[] candidateImages = new Integer[]{
            R.drawable.albus_dumbledor, R.drawable.filius_flitwick,
            R.drawable.gilderoy_lockhart, R.drawable.horace_slughorn,
            R.drawable.minerva_mcgonagall, R.drawable.pomona_sprout, R.drawable.remus_lupin,
            R.drawable.rubeus_hagrid, R.drawable.severus_snape, R.drawable.sybill_trelawney};
    int[] colors = new int[candidateNames.length];
}
