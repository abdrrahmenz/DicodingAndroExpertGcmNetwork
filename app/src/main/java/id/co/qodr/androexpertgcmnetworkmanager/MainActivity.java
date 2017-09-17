package id.co.qodr.androexpertgcmnetworkmanager;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSetScheduler, btnCancelScheduler;
    private SchedulerTask mSchedulerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSetScheduler = (Button) findViewById(R.id.btn_set_scheduler);
        btnCancelScheduler = (Button) findViewById(R.id.btn_cancel_scheduler);
        btnSetScheduler.setOnClickListener(this);
        btnCancelScheduler.setOnClickListener(this);
        mSchedulerTask = new SchedulerTask(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward result to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_set_scheduler:
                mSchedulerTask.createPeriodicTask();
                Toast.makeText(this, "Periodic Task Created", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel_scheduler:
                mSchedulerTask.cancelPeriodicTask();
                Toast.makeText(this, "Periodic Task Cancelled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
