package com.dipanshu.sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors =sm.getSensorList(Sensor.TYPE_ALL);
        Log.e("MAIN ACTIVITY","number of sensors="+sensors.size());

        for (Sensor s :sensors){
            Log.e("TAG",":"+s.toString());
        }

        Sensor accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor proximitySensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] sensorvalue=event.values;

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

            Log.e("TAG1","Grav in x dir." + sensorvalue[0]);
            Log.e("TAG1","Grav in y dir." + sensorvalue[1]);
            Log.e("TAG1","Grav in z dir." + sensorvalue[2]);

            int r,g,b;
            r= (int) sensorvalue[0]*26;
            g= (int) sensorvalue[1]*26;
            b= (int) sensorvalue[2]*26;

            ConstraintLayout constraintLayout=findViewById(R.id.cl);

            int color= Color.rgb(r,g,b);
            constraintLayout.setBackgroundColor(color);

        }
//        else if (event.sensor.getType()==Sensor.TYPE_PROXIMITY){
//            Log.e("TAG1","proximity distance" + sensorvalue[0]);
//
//        }
        Log.e("TAG1","on Sensor changed:");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sm.unregisterListener(this);
    }
}
