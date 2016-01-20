package com.example.emdadollah.android_lektion3spil;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeHandler implements SensorEventListener {


    private static final float GREANSEVEARDI = 2.3F;
    private static final int SLOP_TID_MS = 499;
    private static final int Nulstil_TID = 3000;

    private OnShakeListener onShakeListener;
    private long ShakeTID;
    private int Shaketeal;

    public void setOnShakeListener(OnShakeListener listener) {
        this.onShakeListener = listener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Bruges ikke
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (onShakeListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            float gForce = (float)Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > GREANSEVEARDI) {
                final long now = System.currentTimeMillis();
                if (ShakeTID + SLOP_TID_MS > now) {
                    return;
                }

                // nulstiller shaketeal efter 3 sekunder
                if (ShakeTID + Nulstil_TID < now) {
                    Shaketeal = 0;
                }

                ShakeTID = now;
                Shaketeal++;

                onShakeListener.onShake(Shaketeal);
            }
        }
    }
}