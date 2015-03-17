package com.projprog.P10MineSeeper;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class gameWin extends Activity implements OnClickListener{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.win);
        
        LinearLayout ll = (LinearLayout)findViewById(R.id.fons);
        ll.setBackgroundColor(color.white);
        
        Button b = (Button)findViewById(R.id.mainMenu2);
        b.setOnClickListener(this);
        
        Button a = (Button)findViewById(R.id.again2);
        a.setOnClickListener(this);
	}

	public void onClick(View v) {
		
		Bundle datos = this.getIntent().getExtras();
		int campsX = datos.getInt("minesX");
		int campsY = datos.getInt("minesY");
		int nivell = datos.getInt("nivell");
		
		switch(v.getId()){
			case R.id.again2:	Intent intent = new Intent(gameWin.this, CampMines.class);
								intent.putExtra("nivell", nivell);
								intent.putExtra("minesX", campsX);
								intent.putExtra("minesY", campsY);
								startActivity(intent);
								finish();
								break;
			case R.id.mainMenu2:finish();
								break;
		}
		
	}

}
