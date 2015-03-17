package com.projprog.P10MineSeeper;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class P10MineSweeperActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
       
        Button jugar = (Button)findViewById(R.id.jugar); 
        Button per = (Button)findViewById(R.id.per);
        Button exit = (Button)findViewById(R.id.exit);
        
        
        jugar.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
            	Log.v("onClick", "hola");
            	Intent intent = new Intent(P10MineSweeperActivity.this, Joc.class);
           		startActivity(intent);
            }
        });
        
        
        exit.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
            	Log.v("onClick", "hola");
            	finish();
            }
        });
        
             
        per.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
            	Log.v("onClick", "hola");
            	Intent intent = new Intent(P10MineSweeperActivity.this, Opcions.class);
           		startActivity(intent);
            }
        });
    }
}