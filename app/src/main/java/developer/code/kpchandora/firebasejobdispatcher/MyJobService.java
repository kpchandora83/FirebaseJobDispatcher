package developer.code.kpchandora.firebasejobdispatcher;

import android.content.IntentFilter;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";
    private ConnectivityReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        receiver = new ConnectivityReceiver();
    }

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.i(TAG, "onStartJob: ");
        registerReceiver(receiver, new IntentFilter(MainActivity.CONNECTIVITY_ACTION));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.i(TAG, "onStopJob: ");
        unregisterReceiver(receiver);
        return true;
    }
}
