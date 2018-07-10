package developer.code.kpchandora.firebasejobdispatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class ConnectivityReceiver extends BroadcastReceiver {

//    private ConnectivityReceiverListener mConnectivityReceiverListener;

    private static final String TAG = "ConnectivityReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
//        mConnectivityReceiverListener.onNetworkConnectionChanged(isConnected(context));

        context.sendBroadcast(new Intent(MainActivity.BROAD_CAST_ONE)
        .putExtra("isOnline", isConnected(context)));
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null){
            return false;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
