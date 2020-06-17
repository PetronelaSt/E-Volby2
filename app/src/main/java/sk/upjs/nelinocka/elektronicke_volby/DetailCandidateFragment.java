package sk.upjs.nelinocka.elektronicke_volby;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DetailCandidateFragment extends Fragment {

    private TextView detailAboutCandidate;
    private TextView candidateName;
    private Button pickedCandidate;
    private ImageView candidateImage;
    private TextView candidateAge;
    private TextView candidatePoliticalParty;
    private String currentDateAndTime;

    public DetailCandidateFragment() {
        // Required empty public constructor
    }

    DetailCandidateFragment binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,
                container,
                false);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

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

        candidateViewModel.getSelectedCandidateID().observe(this, this::setDetailsAboutCandidate);

        @SuppressLint("WrongConstant") SharedPreferences sh
                = getActivity().getSharedPreferences("SharedPreferences", Context.MODE_APPEND);
        String date_start = sh.getString("startTimeForVoting", " ");
        String date_end = sh.getString("endTimeForVoting", " ");
        String currentDateAndTime = sdf.format(new Date());

        if (currentDateAndTime.compareTo(date_start) < 0) {
            pickedCandidate.setEnabled(false);
        } else if (currentDateAndTime.compareTo(date_end) >= 0) {
            pickedCandidate.setText("Výsledky volieb");
            pickedCandidate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), ChartActivity.class);
                    startActivity(i);
                }
            });
        } else if (currentDateAndTime.compareTo(date_start) >= 0 && currentDateAndTime.compareTo(date_end) < 0) {
            pickedCandidate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), VoteActivity.class);
                    startActivity(i);
                }
            });
        }
    }

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
    private String[] detailsAboutCandidates = new String[]{
            "Profesor Albus Percival Wulfric Brian Brumbál, O.M. (First Class), Grand Sorc., D. Wiz., X.J. (sorc.), S. z Mag.Q. (c. Leto 1881 - 30. júna 1997 ) bol anglický čarodejník v krvi, ktorý bol profesorom obrany proti temnému umeniu, neskôr profesorom transfigurácie a neskôr riaditeľom Bradavickej školy Čarodejníctvo a čarodejníctvo. Profesor Dumbledore tiež pôsobil ako Najvyšší mugwump Medzinárodnej konfederácie čarodejníkov (? -1995) a hlavný Warlock vo Wizengamote (? -1995; 1996-1997). Bol to polokrvný čarodejnícky čarodejník, ktorý sa považoval za najväčšieho čarodejníka modernej doby, možno všetkých čias. Syn Percival a Kendra Dumbledore a starší brat Aberforth a Ariana. Jeho otec zomrel v Azkabane, keď bol Dumbledore mladý, zatiaľ čo jeho matka a sestra boli neskôr náhodou zabití. Jeho predčasné straty ho výrazne ovplyvnili už na začiatku jeho smrti, ale zase ho urobili lepším človekom.Zatiaľ čo spočiatku nemohol priamo konať proti Gellertovi Grindelwaldovi, kvôli ich minulému paktu krvi, ministerskému dozoru nad ním, jeho pretrvávajúcim pocitom lásky k Grindelwaldu a jeho pretrvávajúcej vine nad Arianinou smrťou, Dumbledore poslal mloka Newt Scamandera do New Yorku, aby prepustil Thunderbirda. Frank a neskôr poslal Newta do Paríža, aby zachránil Credence Barebone. Po tragickej smrti Leta Lestrange v Paríži sa Brumbál rozhodol odhodlať prispievať užitočnou radou k odporu proti Grindelwaldu, najmä proti Mlokovi, Theseovi, Tine, Jacobovi, Yusufovi a Naginimu v globálnej čarodejníckej vojne. Grindelwald však spočiatku nevedel o Dumbledorovi v úmysle použiť proti nemu údajný Obscurial príbuzný Aurelius Dumbledore.Po globálnej čarodejníckej vojne a po skončení revolúcie Za väčšiu dobrú povesť sa Brumbál stal najznámejšou pre svoju porážku Gellert Grindelwald, objav dvanástich použití dračej krvi a prácu na alchýmii s Nicolasom Flamelom.Práve prostredníctvom Dumbledora sa formoval odpor voči vzostupu lorda Voldemorta, pretože to bol on, kto založil a viedol Fénixov poriadok proti Voldemortovi počas prvej čarodejníckej vojny. Po náhlom návrate Voldemorta a začiatku druhej čarodejníckej vojny po rezignácii Corneliusa Fudgea sa Brumbál rýchlo utvoril a viedol druhý Phoenixský rád. Podporil by tiež Jacobov súrodenec v pátraní Cursed Vaults a neskôr by dôsledne podporoval trojicu Harryho, Rona a Hermiony počas ich prvých šiestich rokov v Rokforte, a pomenovali by podľa nich Brumbálovu armádu.Vďaka tomu, že mal horlivú myseľ a legendárnu moc, sa Dumbledore stal jediným čarodejníkom, ktorého sa Voldemort kedy obával. Od roku 1945 do roku 1997 bol čarodejníkom a majstrom Starej prútika a mnohými ich považoval za najväčšieho riaditeľa Rokfortu. Keď sa chystal zomrieť prekliatým prsteňom, Dumbledore naplánoval svoju vlastnú smrť so Severusom Snapom. Podľa plánu bol Dumbledora zabitý Snapom počas bitky o Astronomickú vežu.Aj keď v tom čase už nebol nažive, bol vďaka Brumbálovým manipuláciám nakoniec lord Voldemort nakoniec porazený a trvalý mier sa konečne obnovil do čarodejníckeho sveta. Dumbledore je jediný riaditeľ, ktorý bol položený na odpočinok v Rokforte. Jeho portrét stále zostáva v Rokforte. Harry Potter neskôr po ňom vymenoval svojho druhého syna Albusa Severusa Pottera.",
            "Profesor Filius Flitwick (b. 17. októbra 1958 alebo skôr) bol britským čarodejníkom z polovice škriatka, ktorý navštevoval školu čarodejníctva a čarodejníctva v Bradaviciach a bol zaradený do Ravenclawovho domu, bol inteligentný mladý muž a vzorový študent.Po ukončení štúdia sa Flitwick vrátil do Rokfortu a stal sa majstrom kúziel, ako aj vedúcim Ravenclawovho domu.Počas prvej a druhej čarodejníckej vojny sa Flitwick postavil proti lordovi Voldemortovi. V roku 1995 sa tiež postavil proti Doloresovi Umbridgeovi, vysokému inkvizítorovi v Bradaviciach, av roku 1997 bojoval v bitke pri Astronomickej veži, po ktorej sa zúčastnil Pohrebu Albusa Dumbledora. V školskom roku 1997 - 1998 tiež chránil študentov pred Carrowom. 2. mája 1998 sa zúčastnil bitky v Bradaviciach a porazil mnoho smrťožrútov.Prežil vojnu a pravdepodobne pokračoval vo výučbe kúziel v Rokforte budúcim generáciám študentov.",
            "Profesor Gilderoy Lockhart, O.M. (Tretia trieda), (26. januára, 1964 ) bol čarodejník s polokrvou, študent Ravenclaw na Bradavickej škole čarodejníctva a čarodejníctva a neskôr slávna čarodejnícka osobnosť, ktorá napísala mnoho kníh na temných tvoroch a ich stretnutiach s nimi.Pred nástupom do funkcie profesora obrany proti tmavému umeniu na Rokforte čarodejníctva a čarodejníctva v Bradaviciach v školskom roku 1992 - 1993 získal mnoho prestížnych ocenení, napríklad Merlinský tretí ročník; Čestný člen Ligy obranných síl Temných síl; a päťnásobný víťaz ceny čarodejníckeho týždňa za najkrajší úsmev. Vymyslel šampón Occamy vaječný žĺtok, ktorý bol pre otvorený trh príliš nebezpečný a drahý; následne sa stal jeho snom predávať tieto výrobky.Jeho obľúbená farba je fialová.V skutočnosti nikdy neurobil žiadne hrdinské činy, ktoré tvrdil, že urobil, ale namiesto toho využil svoj značný talent v Memory Charms, aby prinútil skutočných ľudí, ktorí ich urobili, aby zabudli, čo urobili. Je ironické, že Lockhart stratil celú svoju pamäť 29. mája 1993 v dôsledku zlyhania pamäťového kúzla obsadeného poškodenou prútikom Rona Weasleyho a stal sa trvalým obyvateľom Nemocnice pre magické choroby a zranenia St Mungo. Po hospitalizácii diktoval svoju poslednú knihu s názvom Kto som ?.",
            "Profesor Horace Eugene Flaccus Slughorn (28. apríla, v rokoch 1882 až 1913) bol britským čarodejníkom s čistou krvou alebo krvou. Predtým, ako sa v roku 1931 vrátil ako majster elixírov, navštevoval Bradavickú školu čarodejníctva a čarodejníctva ako člen Slizolinčana. Pred odchodom do dôchodku v roku 1981 tiež pôsobil ako vedúci Slizolinských domov kvôli strachu, že Albus Dumbledore zistí, že povedal Tomovi Riddlovi o horcruxoch.Po návrate Voldemorta a Smrťožrútov, ktorí začali hľadať Slughorna, profesor na dôchodku žil život na úteku, neistý, či ho Voldemort chcel prijať, alebo ho zabiť, aby jeho horcruxovia zostali v tajnosti. V roku 1996 Dumbledore presvedčil Slughorna, aby sa vrátil do Rokfortu tým, že ho zviedol k odchodu do dôchodku s perspektívou „zhromaždenia“ Harryho Pottera, ako mal toľko iných študentov, pričom jeho skutočným cieľom bolo zistiť, čo Slughorn povedal Riddlovi. Slughorn nakoniec prezradil svoju skutočnú pamäť. Ďalej pokračoval vo vyučovaní v školskom roku 1997 - 1998 za Voldemortovho režimu a bol prekvapený, keď sa dozvedel, že Voldemort ho chcel prijať len preto, aby učil mladých čarodejníkov, ktorých by Voldemort považoval za vhodný na účasť v Rokforte.Počas bitky v Bradaviciach sa Slughorn postavil na bok s obhajcami Rokfortu, zbieral posily z Bradavíc a zapojil svojho bývalého žiaka do súboja s Minervou McGonagallovou a Kingsley Shackleboltom. Prežil bitku a do roku 2016 druhýkrát odišiel do dôchodku s portrétom zaveseným na Slizolinskom žalári.",
            "Profesorka Minerva McGonagallová, O.M. (First Class), bola škótska polokrvná čarodejnica, jediná dcéra mudla Roberta McGonagalla a čarodejnice Isobel Ross. Bola tiež staršou sestrou Malcolma a Roberta Jr. Minervy navštevovala Bradavickú školu čarodejníctva a čarodejníctva a bola zaradená do Chrabromilského domu, ale rozhodnutie o tom, či je Chrabromilčanka alebo Ravenclaw, trvalo päť a pol minúty. Hatstall. Počas siedmeho roku bola menovaná za Head Girl.Po svojom vzdelaní Minerva pracovala dva roky na ministerstve mágie a neskôr sa vrátila do Rokfortu, kde sa stala vedúcou chrabromilského domu a profesorkou premeny. Aj keď počas prvej čarodejníckej vojny nebola členom Fénixovho rádu, Minerva veľmi pomohla odporu ministerstva mágie špehovaním Smrťožrútov a poskytnutím aurorom zásadné informácie o ich činnosti.Nakoniec sa Minerva stala v rôznom čase súčasne zástupkyňou riaditeľa a riaditeľkou Rokfortu. V roku 1995 sa postavila proti Doloresovi Umbridgeovi, vysokému inkvizítorovi v Rokforte. Chránila študentov pred Alecto a Amycus Carrow od roku 1997 do roku 1998. V rámci nového hnutia odporu od Phoenixu bojovala Minerva v niekoľkých bitkách oboch vojen, vrátane bitky o Astronomickú vežu a bitky v Bradaviciach, kde bojovala viedol odpor proti Lordovi Voldemortovi.Minerva prežila druhú čarodejnícku vojnu a pokračovala vo svojej funkcii riaditeľky, čo je titul, ktorý mala stále v čase Albusa Pottera v Rokforte.",
            "Profesorka Pomona Sprout (b. 15. mája) bola britská čarodejnica, ktorá pracovala ako vedúci Hufflepuffovho domu a vedúci oddelenia herbológie na Bradavskej škole čarodejníctva a čarodejníctva. Navštevovala školu v mladosti, kde bola zaradená do Hufflepuffa a vynikala v Herbology. Nejaký čas po ukončení štúdia sa Sprout vrátil do Rokfortu, aby učil.Profesor Sprout sa postavil proti Lordovi Voldemortovi počas druhej čarodejníckej vojny. V roku 1993 vyrastala Mandrakes, ktorá by sa neskôr použila v Mandrakeovom restoratívnom návrhu na vyliečenie tých, ktorí boli skamenení Slizolinským monštrom. V školskom roku 1995 - 1996, proti ktorému sa ostatní učitelia radi pripojili, tiež oponovala Dolores Umbridgeovej a jej tyranii v škole, hlasovala za to, aby zostala škola otvorená aj po bitke pri Astronomickej veži a pomohla v roku 1998, keď Severusa Snapea vyhnali. Sprout vzal skupinu študentov na cimburie počas bitky v Bradaviciach a hodil diabolského Snare a rôzne ďalšie nebezpečné rastliny na útočiacich smrťožrútov.Nakoniec prežila vojnu. Do roku 2014 Neville Longbottom vyučoval herbológiu, buď vedľa nej, alebo ako náhradu.",
            "Profesor Remus John Lupin, O.M. (First Class), (10. marca 1960 - 2. mája 1998), tiež známy ako Moony, bol čarodejníkom krvi a jediným synom Lyalla a Hope Lupina (rodená Howell). Počas detstva bol postihnutý lycanthropiou v dôsledku pomsty Fenrira Greybacka proti Lyallovi. V rokoch 1971-1978 navštevoval Rokfortskú a čarodejnícku školu v Bradaviciach a bol zaradený do Chrabromilského domu. Počas školských rokov bol jedným z Marauderov, najlepších priateľov so Siriusom Blackom, Jamesom Potterom a Petrom Pettigrewom. Spoločne vytvorili Marauderovu mapu.Po Rokforte sa Remus a jeho priatelia pripojili k pôvodnému Fénixskému rádu a bojovali v prvej čarodejníckej vojne. Pettigrew však zradil Jamesa a Lily Potterovej lordovi Voldemortovi týždeň potom, čo sa stal tajným strážcom párov. Na konci prvej čarodejníckej vojny teda Remus rôznymi spôsobmi stratil všetkých svojich najbližších. Strata jeho priateľov ho zničila, hoci on a Sirius Black ich neskôr spriatelili.Remus počas školského roku 1993 - 1994 vyučoval na Bradavskej škole čarodejníctva a čarodejníctva ako profesor obrany proti temnému umeniu. Študenti považovali Remusa za najlepšieho učiteľa obrany, aký doteraz mali. Bol tiež profesorom Harryho Pottera (Jamesov syn), ktorého učil, ako vyčarovať telesného Patrona, ale rezignoval po tom, ako Severus Snape odhalil verejnosti, že Remus je vlkolak.V druhej čarodejníckej vojne bojoval proti Death Eaters ešte raz, počas ktorého stratil svojho priateľa Siriusa. V roku 1997 sa Remus oženil s členom Rádu Nymphadora Tonksom a mal syna Edwarda Remusa Lupina, ktorého vymenoval za otca Harryho. Remus bojoval 2. mája 1998 v bitke v Bradaviciach, počas ktorej bola jeho manželkou zavraždená Bellatrix Lestrange. Remusa zavraždili aj smrťožrúti Antonin Dolohov v prvej polovici tej istej bitky. Jeho smrť pomstil Filius Flitwick.Remus sa v roku 1998 na Harryho spolu s Jamesom Potterom, Lily Potterovou a Siriusom Blackom objavil opäť krátko cez Resurrection Stone. Po jeho smrti vychovali jeho syna Andromeda Tonksová a Harry Potter.",
            "Profesor Rubeus Hagrid (6. 6. 1928) bol anglický nevlastný čarodejník, syn pána Hagrida a gigantu Fridwulfu a starší nevlastný brat gigantu Grawp.V roku 1940 navštevoval Hagrid školu čarodejníctva a čarodejníctva v Bradaviciach a bol zaradený do Chrabromilského domu. V treťom roku Hagrida ho zastrašil Tom Riddle za zločin otvorenia Tajomnej komnaty a použitie svojho domáceho maznáčika Acromantulu na útok na niekoľko študentov muklovského pôvodu a nakoniec jedného z nich zabil. Aj keď bol Hagridov prútik vytrhnutý a bol vylúčený, bol vycvičený ako hajňák v Rokforte a na žiadosť Albusa Dumbledora mohol žiť na školskom ihrisku.V roku 1991 dostala Hagrid za úlohu znovu zaviesť Harryho Pottera do čarodejníckeho sveta. V roku 1993 by Hagrid nastúpil na post profesora starostlivosti o magické bytosti po odchode do dôchodku profesora Silvanusa Kettleburna. Ako člen pôvodného aj obnoveného Fénixovho poriadku, Hagrid bojoval v niekoľkých bitkách o Prvú a Druhú čarodejnícku vojnu, vrátane Bitky na Rokforte v roku 1998. Do roku 2017 ešte žil a pravdepodobne stále učil a staral sa o svoje povinnosti v oblasti starostlivosti o deti v Bradaviciach, pozýval Harryho syna Albusa Severusa na čaj, keď sa pripojil do školy, keď ho Harry informoval na stanici, rovnako ako to urobil Hagrid svojmu otcovi Ronovi Weasleymu a Hermione Grangerovej.",
            "Severus Snape (9. januára 1960 - 2. mája 1998) bol anglický čarodejník v krvi krvi, ktorý pôsobil ako majster elixírov (1981 - 1996), profesor obrany proti temným umám (1996 - 1997). a riaditeľ (1997-1998) Bradavickej školy čarodejníctva a čarodejníctva, ako aj člen Rádu Phoenixu a Smrťožrúta. Jeho dvojitý život zohral mimoriadne dôležitú úlohu v oboch čarodejníckych vojnách proti Voldemortovi. Ako jediné dieťa muklov Tobias Snape a škriatkovia Eileen Snape (rodený princ) bol Severus vychovávaný v mudlovskom dome Spinner's End, ktorý bol v tesnej blízkosti domu Evansovej rodiny, hoci v chudobnejšej oblasti. Keď mal deväť rokov, stretol sa s Lily a Petuniou Evansovou a hlboko sa zamiloval do Lily, stal sa jej blízkym priateľom.Severus začal v Bradaviciach s Lily v roku 1971, kde bol zaradený do Slizolinského domu. Toto ho dalo v tom istom roku ako Lily, ale bohužiaľ pre neho v konkurenčných domoch. Severus sa stal bezprostredným nepriateľom Jamesa Pottera a Siriusa Blacka a bol častou obeťou ich šikanovania. Toto ho viedlo k podráždeniu voči Jamesovmu synovi Harrymu, keď bol profesorom. Snape, keď bol mladý, vyvinul vášeň pre Temné umenie, ktorá sa zvyšovala s rastúcou túžbou po pomste. Snape sa zapojil do šikanovania v Slizolinskom dome, z ktorých mnohí boli supremacisti čistej krvi. Toto dalo jeho priateľstvo s Lily, muklovský rodák, pod veľkým tlakom, až kým nebolo nakoniec prerušené v piatom roku. V snahe získať späť Lilyho náklonnosť sa Snape pripojil k Smrťožrútom spolu so skupinou jeho spoluobčanov Slizolinčanov.Severus sa zrejme stal členom klubu Slug Club kvôli jeho talentu v oblasti výroby lektvarov a Horace Slughorn si ho ako študenta udržal tak, že mu držal kópiu formulára Advanced Potion-Making. Napriek tomu Horace nemal veľa nádeje na budúcnosť Severusa, pretože jeho fotografia zostala pozadu za mnohými inými.Krátko pred zavraždením Lily Evansovej lordom Voldemortom Snape zmenil strany a stal sa členom 2. rádu Phoenixu ako dvojitého agenta počas druhej čarodejníckej vojny. S obrovskými ťažkosťami Snape zabránil lordovi Voldemortovi, aby sa dozvedel pravdu o jeho lojalite. Napriek názorom väčšiny ostatných vrátane Harryho počas jeho raného života, Albus Dumbledore Snapovi dôveroval z dôvodov, ktoré boli medzi nimi držané až do ich smrti. Napriek tomu, že Snape zabil Dumbledora, je známe, že s ním mali osobitnú dohodu. Keď zomrel, ukázalo sa, že jeho hlboká silná láska k Lily Evansovej spôsobila, že sa vykúpil, keď sa pripojil k Brumbálovej príčine kvôli jej ochrane (a po jej smrti aj k jej synovi) od lorda Voldemorta.Vzťah medzi Dumbledorom a Snapom by bol nezvyčajne silnou lojalitou, takže Snape súhlasil so zabitím Dumbledora na jeho vlastnú žiadosť. Pred Dumbledorovou smrťou Snape sľúbil, že bude chrániť študentov Rokfortu pred Smrťožrútmi, ktorí nevyhnutne prevezmú kontrolu nad ministerstvom mágie, ako aj nad školou. Snape sa neskôr zúčastnil bitky v Bradaviciach, ale zavraždil ho lord Voldemort, ktorý sa mylne domnieval, že Snape bol majstrom Starej prútiky (nesmierne silná a mocná prútik, ktorý Voldemort hlboko túžil, ako aj jednou z posvätných svätých), keď bola v skutočnosti Harry Potter bol majstrom Staršej prútiky, pretože Draco Malfoy odzbrojil Dumbledora v noci po Dumbledorovej smrti na vrchu Astronomickej veže a Harry odzbrojil Draca v Malfoy Manor.Po Snapovej smrti Harry Potter zabezpečil, aby jeho portrét zostal v Rokforte, napriek tomu, že jeho osobnostné rozdiely boli vysoké. Okrem toho Harry neskôr pomenoval svojho druhého syna Albusa Severusa Pottera na počesť Dumbledora a Severusa, ktorí boli po bitke v Bradaviciach inšpiráciou v Harryho živote. Harryho tiež ovplyvnil Severusov Bradavický dom a slobodne pripustil, že Albus si mohol zvoliť Zmijozela, ak si to želal.",
            "Profesorka Sybill Patricia Trelawney (9. marca, pred rokom 1962 ) bola čarodejnica a profesorka veštenia na Bradavskej škole čarodejníctva a čarodejníctva. Je to veľká vnučka slávenej Cassandry Trelawneyovej, ktorá bola tiež veštcom.Bola to samotná Trelawneyová, ktorá počas svojho pracovného pohovoru s Albusom Dumbledorom urobila proroctvo týkajúce sa lorda Voldemorta a toho, ktorý bol schopný ho poraziť (Voldemort to považoval za Harryho Pottera). Na konci školského roku 1993 - 1994 presne predpovedala únik Petra Pettigrewa a návrat Pána Voldemorta.V školskom roku 1995–1996 bol Trelawney veľmi znepokojený Doloresom Umbridgeom, ktorý ju prepustil a pokúsil sa ju vylúčiť z Rokfortu, aj keď Dumbledore jej umožnil zostať v škole, pričom Umbridgeová bola veľmi urazená, keď mala neskôr. učiť popri kentaurovi Firenze. V školskom roku 1997 - 1998 sa zúčastnila bitky v Bradaviciach, hodila krištáľové gule na hlavy Smrťožrútov a starala sa o zranených a mŕtvych spolu s Padmou Patil.Profesorka Trelawneyová pokračovala vo výučbe do roku 2010, pokračovala vo svojej tradícii predpovedať úmrtia svojich študentov. Keďže Firenze bola privítaná späť k svojmu stádu, je možné, že opäť učí veštenie."};

}
