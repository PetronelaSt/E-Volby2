package sk.upjs.nelinocka.elektronicke_volby;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.*;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DetailCandidateFragment extends Fragment {

    private TextView detailAboutCandidate;
    private TextView candidateName;
    private Button pickedCandidate;
    private ImageView candidateImage;
    private TextView candidateAge;
    private TextView candidatePoliticalParty;

    public DetailCandidateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,
                container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        detailAboutCandidate =
                view.findViewById(R.id.detailsAboutCandidate);
        candidateName = view.findViewById(R.id.candidateName);
        candidateAge = view.findViewById(R.id.candidateAge);
        candidateImage = view.findViewById(R.id.candidateImg);
        candidatePoliticalParty = view.findViewById(R.id.candidatePoliticalParty);
        pickedCandidate = view.findViewById(R.id.pickedCandidate);
        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        CandidateViewModel candidateViewModel = viewModelProvider.get(CandidateViewModel.class);

        //candidateViewModel.getSelectedCandidate().observe(this, this::setDetailsAboutCandidate);
        candidateViewModel.getSelectedCandidateID().observe(this, this::setDetailsAboutCandidate);

        Button button = view.findViewById(R.id.pickedCandidate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), VoteActivity.class);
                startActivity(i);
            }
        });
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
    private String[] candidateAges = new String[]{"139",
            "62", "56", "120", "110", "87", "60",
            "92", "60", "57"};

    private void setDetailsAboutCandidate(@org.jetbrains.annotations.NotNull Integer candidateID) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("candidateName", candidateNames[candidateID]);
        editor.commit();

        candidateName.setText(candidateNames[candidateID]);
        candidateImage.setImageResource(candidateImages[candidateID]);
        candidatePoliticalParty.setText(candidatePoliticalParties[candidateID]);
        candidateAge.setText(candidateAges[candidateID]);
        detailAboutCandidate.setText("   " + detailsAboutCandidates[candidateID]);
    }

    private String[] detailsAboutCandidates = new String[]{"Profesor Albus Percival Wulfric Brian Brumbál, O.M. (First Class), Grand Sorc., D. Wiz., X.J. (sorc.), S. z Mag.Q. (c. Leto 1881 - 30. júna 1997 ) bol anglický čarodejník v krvi, ktorý bol profesorom obrany proti temnému umeniu, neskôr profesorom transfigurácie a neskôr riaditeľom Bradavickej školy Čarodejníctvo a čarodejníctvo. Profesor Dumbledore tiež pôsobil ako Najvyšší mugwump Medzinárodnej konfederácie čarodejníkov (? -1995) a hlavný Warlock vo Wizengamote (? -1995; 1996-1997). Bol to polokrvný čarodejnícky čarodejník, ktorý sa považoval za najväčšieho čarodejníka modernej doby, možno všetkých čias. Syn Percival a Kendra Dumbledore a starší brat Aberforth a Ariana. Jeho otec zomrel v Azkabane, keď bol Dumbledore mladý, zatiaľ čo jeho matka a sestra boli neskôr náhodou zabití. Jeho predčasné straty ho výrazne ovplyvnili už na začiatku jeho smrti, ale zase ho urobili lepším človekom.Zatiaľ čo spočiatku nemohol priamo konať proti Gellertovi Grindelwaldovi, kvôli ich minulému paktu krvi, ministerskému dozoru nad ním, jeho pretrvávajúcim pocitom lásky k Grindelwaldu a jeho pretrvávajúcej vine nad Arianinou smrťou, Dumbledore poslal mloka Newt Scamandera do New Yorku, aby prepustil Thunderbirda. Frank a neskôr poslal Newta do Paríža, aby zachránil Credence Barebone. Po tragickej smrti Leta Lestrange v Paríži sa Brumbál rozhodol odhodlať prispievať užitočnou radou k odporu proti Grindelwaldu, najmä proti Mlokovi, Theseovi, Tine, Jacobovi, Yusufovi a Naginimu v globálnej čarodejníckej vojne. Grindelwald však spočiatku nevedel o Dumbledorovi v úmysle použiť proti nemu údajný Obscurial príbuzný Aurelius Dumbledore.Po globálnej čarodejníckej vojne a po skončení revolúcie Za väčšiu dobrú povesť sa Brumbál stal najznámejšou pre svoju porážku Gellert Grindelwald, objav dvanástich použití dračej krvi a prácu na alchýmii s Nicolasom Flamelom.Práve prostredníctvom Dumbledora sa formoval odpor voči vzostupu lorda Voldemorta, pretože to bol on, kto založil a viedol Fénixov poriadok proti Voldemortovi počas prvej čarodejníckej vojny. Po náhlom návrate Voldemorta a začiatku druhej čarodejníckej vojny po rezignácii Corneliusa Fudgea sa Brumbál rýchlo utvoril a viedol druhý Phoenixský rád. Podporil by tiež Jacobov súrodenec v pátraní Cursed Vaults a neskôr by dôsledne podporoval trojicu Harryho, Rona a Hermiony počas ich prvých šiestich rokov v Rokforte, a pomenovali by podľa nich Brumbálovu armádu.Vďaka tomu, že mal horlivú myseľ a legendárnu moc, sa Dumbledore stal jediným čarodejníkom, ktorého sa Voldemort kedy obával. Od roku 1945 do roku 1997 bol čarodejníkom a majstrom Starej prútika a mnohými ich považoval za najväčšieho riaditeľa Rokfortu. Keď sa chystal zomrieť prekliatým prsteňom, Dumbledore naplánoval svoju vlastnú smrť so Severusom Snapom. Podľa plánu bol Dumbledora zabitý Snapom počas bitky o Astronomickú vežu.Aj keď v tom čase už nebol nažive, bol vďaka Brumbálovým manipuláciám nakoniec lord Voldemort nakoniec porazený a trvalý mier sa konečne obnovil do čarodejníckeho sveta. Dumbledore je jediný riaditeľ, ktorý bol položený na odpočinok v Rokforte. Jeho portrét stále zostáva v Rokforte. Harry Potter neskôr po ňom vymenoval svojho druhého syna Albusa Severusa Pottera.", "Filius Flitwick", "Gilderoy Lockhart",
            "Profesor Horace Eugene Flaccus Slughorn (28. apríla, v rokoch 1882 až 1913) bol britským čarodejníkom s čistou krvou alebo krvou. Predtým, ako sa v roku 1931 vrátil ako majster elixírov, navštevoval Bradavickú školu čarodejníctva a čarodejníctva ako člen Slizolinčana. Pred odchodom do dôchodku v roku 1981 tiež pôsobil ako vedúci Slizolinských domov kvôli strachu, že Albus Dumbledore zistí, že povedal Tomovi Riddlovi o horcruxoch.Po návrate Voldemorta a Smrťožrútov, ktorí začali hľadať Slughorna, profesor na dôchodku žil život na úteku, neistý, či ho Voldemort chcel prijať, alebo ho zabiť, aby jeho horcruxovia zostali v tajnosti. V roku 1996 Dumbledore presvedčil Slughorna, aby sa vrátil do Rokfortu tým, že ho zviedol k odchodu do dôchodku s perspektívou „zhromaždenia“ Harryho Pottera, ako mal toľko iných študentov, pričom jeho skutočným cieľom bolo zistiť, čo Slughorn povedal Riddlovi. Slughorn nakoniec prezradil svoju skutočnú pamäť. Ďalej pokračoval vo vyučovaní v školskom roku 1997 - 1998 za Voldemortovho režimu a bol prekvapený, keď sa dozvedel, že Voldemort ho chcel prijať len preto, aby učil mladých čarodejníkov, ktorých by Voldemort považoval za vhodný na účasť v Rokforte.Počas bitky v Bradaviciach sa Slughorn postavil na bok s obhajcami Rokfortu, zbieral posily z Bradavíc a zapojil svojho bývalého žiaka do súboja s Minervou McGonagallovou a Kingsley Shackleboltom. Prežil bitku a do roku 2016 druhýkrát odišiel do dôchodku s portrétom zaveseným na Slizolinskom žalári.", "Minerva McGonagall", "Pomona Sprout", "Remus Lupin",
            "Rubeus Hagrid", "Severus Snape", "Sybill Trelawney"};
}
