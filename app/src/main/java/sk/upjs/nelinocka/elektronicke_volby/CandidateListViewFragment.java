package sk.upjs.nelinocka.elektronicke_volby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final CandidateViewModel viewModel = new ViewModelProvider(requireActivity()).get(CandidateViewModel.class);

        candidatesNamesListView = view.findViewById(R.id.listView);
        //candidatesNamesListView = view.findViewById(R.id.candidateName);
        candidatesNamesListView.setOnItemClickListener((parent, v, position, id) -> {
            String name = (String) candidatesNamesListView.getItemAtPosition(position);
            viewModel.setSelectedCandidate(name);
            viewModel.setSelectedCandidateID(position);
        });

        listView = view.findViewById(R.id.listView);
        CandidateListAdapter candidateListAdapter = new CandidateListAdapter(
                candidateImages, candidateNames, candidatePoliticalParties, this.getContext());
        listView.setAdapter(candidateListAdapter);
    }

}
