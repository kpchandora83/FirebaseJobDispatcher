package developer.code.kpchandora.firebasejobdispatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;

public class MainActivity extends AppCompatActivity {


    private FirebaseJobDispatcher dispatcher;

    public static final String BROAD_CAST_ONE = "broadcast_1";
    private static final String TAG = "MainActivity";
    public final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        registerReceiver(receiver, new IntentFilter(BROAD_CAST_ONE));

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: "+intent.getBooleanExtra("isOnline", false));
        }
    };

    private void schedule(){
        Job job = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("unique_tag")
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .build();

        dispatcher.mustSchedule(job);
    }

    public void onClick(View view) {
        Log.i(TAG, "onClick: s");
        schedule();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
