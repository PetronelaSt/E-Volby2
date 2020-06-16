package sk.upjs.nelinocka.elektronicke_volby;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CandidateListViewFragment extends Fragment {
    ListView candidatesNamesListView;

    public CandidateListViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_master,
                container, false);
    }

    private Integer[] candidateImages = new Integer[]{
            R.drawable.albus_dumbledor, R.drawable.filius_flitwick,
            R.drawable.gilderoy_lockhart, R.drawable.horace_slughorn,
            R.drawable.minerva_mcgonagall, R.drawable.pomona_sprout, R.drawable.remus_lupin,
            R.drawable.rubeus_hagrid, R.drawable.severus_snape, R.drawable.sybill_trelawney};
    private String[] candidateNames = new String[]{
            "Albus Dumbledor", "Filius Flitwick", "Gilderoy Lockhart",
            "Horace Slughorn", "Minerva McGonagall", "Pomona Sprout", "Remus Lupin",
            "Rubeus Hagrid", "Severus Snape", "Sybill Trelawney"};
    private String[] candidatePoliticalParties = new String[]{"Gryffindor",
            "Ravenclaw", "Ravenclaw", "Slytherin", "Gryffindor", "Hufflepuff", "Gryffindor",
            "Gryffindor", "Slytherin", "Ravenclaw"};
    private ListView listView;
    private SearchView searchView;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final CandidateViewModel viewModel = new ViewModelProvider(requireActivity()).get(CandidateViewModel.class);

        @SuppressLint("WrongConstant") SharedPreferences sh
                = getActivity().getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        String date_end = sh.getString("endTimeForVoting", " ");
        String currentDateAndTime = sdf.format(new Date());

        if (currentDateAndTime.compareTo(date_end) >= 0) {
            AlertDialog ad = new AlertDialog.Builder(getContext())
                    .setTitle("Výsledky volieb")
                    .setMessage("Sú dostupné výsledky volieb, chcete si ich pozrieť?")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            Intent j = new Intent(getContext(), ChartActivity.class);
                            startActivity(j);

                        }
                    })

                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }

        candidatesNamesListView = view.findViewById(R.id.listView);
        candidatesNamesListView.setOnItemClickListener((parent, v, position, id) -> {
            String name = (String) candidatesNamesListView.getItemAtPosition(position);
            viewModel.setSelectedCandidate(name);
            viewModel.setSelectedCandidateID(position);
            //showNotification();
        });

        listView = view.findViewById(R.id.listView);
        CandidateListAdapter candidateListAdapter = new CandidateListAdapter(
                candidateImages, candidateNames, candidatePoliticalParties, this.getContext());
        listView.setAdapter(candidateListAdapter);

        // listView.setTextFilterEnabled(true);
        // searchView=view.findViewById(R.id.search_bar);
    }
}
