package com.projprog.P10MineSeeper;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class CampMines extends Activity implements OnClickListener, OnLongClickListener{

	
	private int [][] field;			//matriz lo que es cada boton(mina = -1)
	private boolean [][]check;
	private MainButton [][] buttons;
	private int compteEnrere;
	private int realMines;
	private int camp;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int minesX;
        int minesY;
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.mines);
        
        
        Bundle datos = this.getIntent().getExtras();
        minesX = datos.getInt("minesX");
        minesY = datos.getInt("minesY");
        
        buttons = new MainButton[minesX][minesY];
        this.crearCamp();
        
        LinearLayout vl = (LinearLayout)findViewById(R.id.linearLayout4);
        
        for(int i=0; i<minesX; i++){
        	LinearLayout hl = new LinearLayout(this);
        	for(int j=0; j<minesY; j++){
        		
        		MainButton b = new MainButton(this);
        		buttons[i][j] = b;
        		b.setPosX(i);
        		b.setPosY(j);
        		b.setOnClickListener(this);
        		b.setOnLongClickListener(this);
        		hl.addView(b);
        	}
        	vl.addView(hl);
        }
        
    }
	
	

	public void crearCamp(){
		
		int nivell;
		int fila, columna;
		int campsX;
		int campsY;
		Random generator = new Random();
		Bundle datos = this.getIntent().getExtras();
		
		
		campsX = datos.getInt("minesX");
		campsY = datos.getInt("minesY");
		compteEnrere = camp = campsX*campsY;
		field = new int[campsX][campsY];
		check = new boolean[campsX][campsY];
		
		for(fila=0; fila<campsX; fila++){
			for(columna=0; columna<campsY; columna++){
				field[fila][columna] = 0;
				check[fila][columna] = false;
			}
		}
		
		int mines;
		nivell = datos.getInt("nivell");
		if(nivell==0){
			mines=0;
		}else{
			if(nivell==10){
				mines = (campsX*campsY)/2;
			}else{
				mines = (int)(((nivell*2)+10)*campsX*campsY)/100;
			}
		}
		realMines = 0;
		
		for(int i=0;i<mines;i++){
			int posX = generator.nextInt(campsX);
			int posY = generator.nextInt(campsY);
			field[posX][posY] = -1;
		}

		for(fila=0; fila<campsX; fila++){
			for(columna=0; columna<campsY; columna++){
				if(field[fila][columna]!=-1){
					field[fila][columna]=omplirTaulell(fila,columna,campsX, campsY);
				}else{
					realMines++;
				}
			}
		}
		
	}



	private int omplirTaulell(int m, int n,int campsX, int campsY) {
		
		int min = 0;
		if(existeix(m-1,n-1,campsX,campsY))	min++;
		if(existeix(m,n-1,campsX,campsY))	min++;
		if(existeix(m+1,n-1,campsX,campsY))	min++;
		if(existeix(m-1,n,campsX,campsY))	min++;
		if(existeix(m+1,n,campsX,campsY))	min++;
		if(existeix(m-1,n+1,campsX,campsY))	min++;
		if(existeix(m,n+1,campsX,campsY))	min++;
		if(existeix(m+1,n+1,campsX,campsY))	min++;
		return min;
	}
	



	private boolean existeix(int m, int n,int campsX, int campsY) {
		if(m<0||n<0||m>=campsX||n>=campsY) return false;
		else{
			if(field[m][n]==-1){
				return true;
			}
		}
		return false;
	}



	public void onClick(View v) {
		
		MainButton b = (MainButton) v;
		int posX = b.getPosX();
		int posY = b.getPosY();
		dig(posX, posY);
	}



	private void dig(int m, int n){
		
		Bundle datos = this.getIntent().getExtras();
		int campsX = datos.getInt("minesX");
		int campsY = datos.getInt("minesY");
		int nivell = datos.getInt("nivell");	
		Log.v("asd","x: "+m+" y: "+n);
		switch(field[m][n]){
			case 0:	if(buttons[m][n].getBanner()!='f'){
						buttons[m][n].setEnabled(false);
						compteEnrere--;
						if(compteEnrere <= realMines){
							Log.v("iop",""+realMines);
							Intent intent = new Intent(CampMines.this, gameWin.class);
							intent.putExtra("nivell", nivell);
							intent.putExtra("minesX", campsX);
							intent.putExtra("minesY", campsY);
			           		startActivity(intent);
							finish();
						}else{
							if(m-1>=0 && n-1>=0)	
								if(field[m-1][n-1]!=-1 && buttons[m-1][n-1].isEnabled())
									dig(m-1,n-1);
							if(n-1>=0)
								if(field[m][n-1]!=-1 && buttons[m][n-1].isEnabled())
									dig(m,n-1);
							if(m+1<campsX && n-1>=0)
								if(field[m+1][n-1]!=-1 && buttons[m+1][n-1].isEnabled())
									dig(m+1,n-1);
							if(m-1>=0)
								if(field[m-1][n]!=-1 && buttons[m-1][n].isEnabled())
									dig(m-1,n);
							if(m+1<campsX)
								if(field[m+1][n]!=-1 && buttons[m+1][n].isEnabled())
									dig(m+1,n);
							if(m-1>=0 && n+1<campsY)
								if(field[m-1][n+1]!=-1 && buttons[m-1][n+1].isEnabled())
									dig(m-1,n+1);
							if(n+1<campsY)
								if(field[m][n+1]!=-1 && buttons[m][n+1].isEnabled())
									dig(m,n+1);
							if(m+1<campsX && n+1<campsY)
								if(field[m+1][n+1]!=-1 && buttons[m+1][n+1].isEnabled())
									dig(m+1,n+1);
						}
					}
					break;
			case -1:if(compteEnrere < camp){
						if(buttons[m][n].getBanner()!='f'){
							Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
							v.vibrate(2000);
							Intent intentII = new Intent(CampMines.this, gameOver.class);
							intentII.putExtra("nivell", nivell);
							intentII.putExtra("minesX", campsX);
							intentII.putExtra("minesY", campsY);
							startActivity(intentII);
							finish();
						}
					}else{
						crearCamp();
						dig(m,n);
					}
	    			break;
		   	default:if(buttons[m][n].getBanner()!='f'){
		   				compteEnrere--;
		   				if(compteEnrere <= realMines){
		   					Log.v("iop",""+realMines);
		   					Intent intent = new Intent(CampMines.this, gameWin.class);
		   					intent.putExtra("nivell", nivell);
		   					intent.putExtra("minesX", campsX);
		   					intent.putExtra("minesY", campsY);
		   	           		startActivity(intent);
		   					finish();
		   				}else{
		   					buttons[m][n].setEnabled(false);
		   					switch(field[m][n]){
		   						case 1: buttons[m][n].setTextColor(Color.BLUE);
		   								break;
		   						case 2:	buttons[m][n].setTextColor(Color.GREEN);
		   								break;
		   						case 3:	buttons[m][n].setTextColor(Color.YELLOW);
		   								break;
		   						case 4:	buttons[m][n].setTextColor(Color.RED);
	   									break;
		    					case 5:	buttons[m][n].setTextColor(Color.WHITE);
	   									break;
		    					case 6:	buttons[m][n].setTextColor(Color.BLACK);
	   									break;
		    					case 7:	buttons[m][n].setTextColor(Color.CYAN);
	   									break;
		    					case 8:	buttons[m][n].setTextColor(Color.MAGENTA);
	   									break;
		    				}
		    				buttons[m][n].setText(""+field[m][n]);
		    			}
		    		}
	       			break;
			}
		}



	public boolean onLongClick(View v) {

		MainButton b = (MainButton) v;
		Bundle datos = this.getIntent().getExtras();
		int campsX = datos.getInt("minesX");
		int campsY = datos.getInt("minesY");
		int nivell = datos.getInt("nivell");
		
		if(b.getBanner()!='f'){
			int x = b.getPosX();
			int y = b.getPosY();
			b.setBanner();
			if(field[x][y] == -1){
				if(compteEnrere <= realMines){
					Log.v("iop",""+realMines);
					Intent intent = new Intent(CampMines.this, gameWin.class);
					intent.putExtra("nivell", nivell);
					intent.putExtra("minesX", campsX);
					intent.putExtra("minesY", campsY);
					startActivity(intent);
					finish();
				}
			}
		}else{
			b.removeFlag();
		}
		
		return true;
	}
}
