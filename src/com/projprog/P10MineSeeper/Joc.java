package com.projprog.P10MineSeeper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Joc extends Activity implements OnClickListener{
	
	private int nivell;
	private int minesX;
	private int minesY;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.nou);
        
        Button btt1 = (Button)findViewById(R.id.btt1);
        Button btt2 = (Button)findViewById(R.id.btt2);
        Button btt3 = (Button)findViewById(R.id.btt3);
        
        btt1.setOnClickListener(this);
        btt2.setOnClickListener(this);
        btt3.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
			case R.id.btt1:	nivell = 1;
							minesX = 8;
							minesY = 8;
							break;
			case R.id.btt2:	nivell = 4;
							minesX = 12;
							minesY = 12;
							break;
			case R.id.btt3:	nivell = 7;
							minesX = 15;
							minesY = 15;
							break;
		}
		Intent intent = new Intent(Joc.this, CampMines.class);
		intent.putExtra("nivell", nivell);
		intent.putExtra("minesX", minesX);
		intent.putExtra("minesY", minesY);
		startActivity(intent);
		finish();

	}

}
