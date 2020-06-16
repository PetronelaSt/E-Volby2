package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    private ArrayList<PieEntry> dataValues() {
        ArrayList<PieEntry> hodnoty = new ArrayList<PieEntry>();
        for (int i = 0; i < candidateNames.length; i++) {
            hodnoty.add(new PieEntry(10, candidateNames[i]));
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colors[i] = Color.rgb(r, g, b);
        }
        return hodnoty;
    }
}
