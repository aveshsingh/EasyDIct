package com.example.easydict;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service implements SensorEventListener {

	 private SensorManager mSensorManager;
	    private Sensor mAccelerometer;
	    private float mAccel; // acceleration apart from gravity
	    private float mAccelCurrent; // current acceleration including gravity
	    private float mAccelLast;
	
	    
	

		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
//			mSensorManager.registerListener(this, mAccelerometer,
//	                SensorManager.SENSOR_DELAY_NORMAL);
//			
			//Toast.makeText(getApplicationContext(), "service started", Toast.LENGTH_LONG).show();
		}
	    
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			Toast.makeText(getApplicationContext(), "service stopped", Toast.LENGTH_LONG).show();
			Intent   BroadCastIntent = new Intent(this,BootReceiver.class);
			sendBroadcast(BroadCastIntent);
			
		
		}


		@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	 super.onStartCommand(intent, flags, startId);
		   mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	         mAccelerometer = mSensorManager
	                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	        mSensorManager.registerListener(this, mAccelerometer,
	                SensorManager.SENSOR_DELAY_NORMAL);
	        return START_STICKY;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		    float x = event.values[0];
	        float y = event.values[1];
	        float z = event.values[2];
	        mAccelLast = mAccelCurrent;
	        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
	        float delta = mAccelCurrent - mAccelLast;
	        mAccel = mAccel * 0.9f + delta; // perform low-cut filter

	        if (mAccel > 11) {
	            //Toast.makeText(getApplicationContext(), "hiiiiiii", Toast.LENGTH_SHORT).show();
	        Intent in = new Intent (getApplicationContext(),HomePage.class);
	        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(in);
	        }
		
		
	}

}
