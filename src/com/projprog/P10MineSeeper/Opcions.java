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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Opcions extends Activity implements OnClickListener, SeekBar.OnSeekBarChangeListener{
	
	
	private int nivell;
	private int minesX;
	private int minesY;
	private EditText X;
	private EditText Y;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.per);
        
        Button play = (Button)findViewById(R.id.play);
        play.setOnClickListener(this);
        
        SeekBar mSeekBar = (SeekBar)findViewById(R.id.level);
        mSeekBar.setOnSeekBarChangeListener(this);
        
        X = (EditText)findViewById(R.id.boxesX);
        Y = (EditText)findViewById(R.id.boxesY);
        
        X.setText("8");
        Y.setText("8");
	}

	public void onClick(View v) {
		
		String minesAux = X.getText().toString();
		if(X.getText().toString().equals(""))	minesX = 0;
		else minesX = Integer.parseInt(minesAux);
        minesAux = Y.getText().toString();
        if(Y.getText().toString().equals(""))	minesY = 0;
        else minesY = Integer.parseInt(minesAux);
        
        if(minesX<26 && minesY<26 && minesX>2 && minesY>2){
			Intent intent = new Intent(Opcions.this, CampMines.class);
			intent.putExtra("nivell", nivell);
			intent.putExtra("minesX", minesX);
			intent.putExtra("minesY", minesY);
			startActivity(intent);
			finish();
        }else{
        	if(minesY>25){
        		Toast.makeText(Opcions.this, "Error! Less fields on Y", Toast.LENGTH_LONG).show();
        	}
        	if(minesX>25){
        		Toast.makeText(Opcions.this, "Error! Less fields on X", Toast.LENGTH_LONG).show();
        	}
        	if(minesX<3){
        		Toast.makeText(Opcions.this, "Error! More fields on X", Toast.LENGTH_LONG).show();
        	}
        	if(minesY<3){
        		Toast.makeText(Opcions.this, "Error! More fields on Y", Toast.LENGTH_LONG).show();
        	}
        }
		
	}

	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		TextView lvl = (TextView)findViewById(R.id.lvlName);
		nivell = progress/10;
		switch(nivell){
			case 0: lvl.setText("Heaven");
					break;
			case 1: lvl.setText("Beginner");
					break;
			case 2: lvl.setText("Noob");
					break;
			case 3: lvl.setText("Easy");
					break;
			case 4: lvl.setText("Medium");
					break;
			case 5: lvl.setText("Difficult");
					break;
			case 6: lvl.setText("Expert");
					break;
			case 7: lvl.setText("Proffesional");
					break;
			case 8: lvl.setText("Crazy");
					break;
			case 9: lvl.setText("Insane");
					break;
			case 10: lvl.setText("Hell");
					break;
		}
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
