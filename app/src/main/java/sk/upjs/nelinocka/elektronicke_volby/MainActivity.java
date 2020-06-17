package sk.upjs.nelinocka.elektronicke_volby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("startTimeForVoting", "20200614203400");
        editor.putString("endTimeForVoting", "20200618080500");
        editor.commit();

/*        if (currentDateAndTime.compareTo(date_end) >= 0) {
            showNotification();
            //  String channel_id = createNotificationChannel(this.getApplicationContext());
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this.getApplicationContext(), channel_id);
        }*/

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new CandidateListViewFragment()).commit();

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        CandidateViewModel candidateViewModel =
                viewModelProvider.get(CandidateViewModel.class);
        candidateViewModel.getSelectedCandidate().observe(this,
                this::showDetailFragment);
    }

    private void showDetailFragment(String name) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new DetailCandidateFragment())
                .addToBackStack(null).commit();
    }

    private void showNotification() {
        System.out.println("SAK ROBIIIIIIIIIM");
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification.Builder n = new Notification.Builder(this)
                .setContentTitle("New notif")
                .setContentText("Subj")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setChannelId("1");
        Notification not = n.build();


        notificationManager.notify(1, not);
    }

}
