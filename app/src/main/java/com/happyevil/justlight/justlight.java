package com.happyevil.justlight;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class justlight extends Activity
{

    Button lightButton;
    Boolean lightOn;
    Boolean flashAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justlight);

        flashAvailable = this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!flashAvailable)
        {
            lightButton = (Button) findViewById(R.id.light_toggle);
            lightButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (lightOn == true)
                    {
                        lightOff();
                    } else {
                        lightOn();
                    }
                }
            });
        }
    }

    Camera cam;

    public void lightOn()
    {
        lightOn = true;
        cam = android.hardware.Camera.open();
        Camera.Parameters params = cam.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(params);
        cam.startPreview();
    }

    public void lightOff()
    {
        lightOn = false;
        cam.stopPreview();
        cam.release();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.justlight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
