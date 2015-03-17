package com.projprog.P10MineSeeper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class gameOver extends Activity implements OnClickListener{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          requestWindowFeature(Window.FEATURE_NO_TITLE);
          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
          setContentView(R.layout.lose);
        
          Button b = (Button)findViewById(R.id.mainMenu);
          b.setOnClickListener(this);
          Button a = (Button)findViewById(R.id.again);
          a.setOnClickListener(this);
  	}

  	public void onClick(View v) {
  		
  		Bundle datos = this.getIntent().getExtras();
  		int campsX = datos.getInt("minesX");
  		int campsY = datos.getInt("minesY");
  		int nivell = datos.getInt("nivell");
  		
  		switch(v.getId()){
  			case R.id.again:	Intent intent = new Intent(gameOver.this, CampMines.class);
  								intent.putExtra("nivell", nivell);
  								intent.putExtra("minesX", campsX);
  								intent.putExtra("minesY", campsY);
  								startActivity(intent);
  								finish();
  								break;
  			case R.id.mainMenu:	finish();
  								break;
  		}
  		
  	}

  }
