package sk.upjs.nelinocka.elektronicke_volby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sk.upjs.nelinocka.elektronicke_volby.R;

public class CandidateListAdapter extends BaseAdapter {
    private Integer[] candidateImages;
    private String[] candidateNames, candidatePoliticalParties;
    private LayoutInflater inflter;
    private Context context;
    private ImageView candidateImg;
    private TextView candidateName, candidatePoliticalParty;

    public CandidateListAdapter(Integer[] candidateImages, String[] candidateNames,
                                String[] candidatePoliticalParties, Context context) {
        this.candidateImages = candidateImages;
        this.candidateNames = candidateNames;
        this.candidatePoliticalParties = candidatePoliticalParties;
        this.context = context;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return candidateNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.listview_row, null);
        candidateImg = view.findViewById(R.id.candidateImg);
        candidateName = view.findViewById(R.id.candidateName);
        candidatePoliticalParty = view.findViewById(R.id.candidatePoliticalParty);
        candidateImg.setImageResource(candidateImages[position]);
        candidateName.setText(candidateNames[position]);
        candidatePoliticalParty.setText(candidatePoliticalParties[position]);
        return view;
    }
}
