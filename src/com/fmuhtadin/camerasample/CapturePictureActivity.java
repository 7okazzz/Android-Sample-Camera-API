package com.fmuhtadin.camerasample;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CapturePictureActivity extends Activity {
	
	protected final static String DEBUG_TAG = "CapturePictureActivity";
	private Camera camera;
	private CameraPreview mPreview;
    private int cameraId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture_picture);
		
		if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
	      Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
	    } else {
	      camera = Camera.open();
	    }
		
		mPreview = new CameraPreview(this, camera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
	}
	
	@Override
	  protected void onPause() {
	    if (camera != null) {
	      camera.release();
	      camera = null;
	    }
	    super.onPause();
	  }
	
	public void capturePicture(View view) {
	    camera.takePicture(null, null,
	        new PictureHandler(this));
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.capture_picture, menu);
		return true;
	}

}
