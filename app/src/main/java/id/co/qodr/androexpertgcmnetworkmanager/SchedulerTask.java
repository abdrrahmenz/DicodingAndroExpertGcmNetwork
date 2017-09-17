package id.co.qodr.androexpertgcmnetworkmanager;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

/**
 * Created by adul on 06/09/17.
 */

public class SchedulerTask {

    private GcmNetworkManager mGcmNetworkManager;

    public SchedulerTask(Context context) {
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    /*Kode dibawah merepresentasikan pembuatan sebuah task yang akan dijalankan secara terjadwal
    oleh sistem android dengan beberapa kriteria yang ditentukan seperti berikut :
        1. setService() -> Method ini menentukan GcmTaskService yang akan dijalankan ketika kriteria dipenuhi
        2. setPeriod() -> Method ini menentukan interval task yang akan dijalankan dalam satuan detik.
        3. setFlex() -> Method ini menentukan range waktu untuk ekseskusi task yang akan dijalankan.
           Misal kita set period = 30 dan flex = 10 maka task akan dijalankan di range antara 20 sampai 30.
        4. setTag() -> Method ini untuk set nilai tag dari task yang akan dijalankan
        5. setPersisted() -> Method ini akan membuat semua task yang akan dijalankan dipertahankan
           ketika terjadi proses reboot device.*/
    public void createPeriodicTask() {
        Task periodicTask = new PeriodicTask.Builder()
                .setService(SchedulerService.class)
                .setPeriod(60)
                .setFlex(10)
                .setTag(SchedulerService.TAG_TASK_WEATHER_LOG)
                .setPersisted(true)
                .build();

        /* Setelah selesai pembuatan obyek task selanjutnya kita hanya tinggal men-set task tersebut
        untuk dijalankan oleh GcmNetworkManager seperti ini. */
        mGcmNetworkManager.schedule(periodicTask);
    }

    public void cancelPeriodicTask(){
        if (mGcmNetworkManager != null){
            mGcmNetworkManager.cancelTask(SchedulerService.TAG_TASK_WEATHER_LOG, SchedulerService.class);
        }
    }
}
