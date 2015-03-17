package com.projprog.P10MineSeeper;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Button;

public class MainButton extends Button{
	
	Drawable img, img2;
	private int posX;
	private int posY;
	private char flag;
	
	public MainButton(Context context) {
		super(context);
		this.setVisibility(VISIBLE);
		this.setWidth(60);
		this.setHeight(60);
		img = getContext().getResources().getDrawable( R.drawable.miniflag );	
		this.setGravity(Gravity.CENTER);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public char getBanner() {
		return flag;
	}
	
	public void setBanner(){	
		
		this.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
		flag = 'f';
	}
	
	public void removeFlag(){

		this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		flag = '0';
	}
		
}
