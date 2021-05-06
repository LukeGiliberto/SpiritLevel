package au.edu.jcu.cp3406.spiritlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensorList){
            Log.i("MainActivity",sensor.toString());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] grav = sensorEvent.values;
        Log.i("MainActivity",String.format("x = %.2f, y = %.2f, z = %.2f",grav[0],grav[1],grav[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int acc) {

        switch (acc){
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                Log.i("MainActivity", "current accuracy: HIGH");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                Log.i("MainActivity", "current accuracy: MED");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                Log.i("MainActivity", "current accuracy: LOW");
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                Log.i("MainActivity", "current accuracy: BAD");
                break;
            default:
                Log.i("MainActivity", "current accuracy: ABSENT");
                break;
        }

    }
}