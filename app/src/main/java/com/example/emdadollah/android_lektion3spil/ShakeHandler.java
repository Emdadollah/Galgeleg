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
        // ignore
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

            // gForce will be close to 1 when there is no movement.
            float gForce = (float)Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > GREANSEVEARDI) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
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