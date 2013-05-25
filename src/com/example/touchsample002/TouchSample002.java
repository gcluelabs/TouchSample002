package com.example.touchsample002;

import com.gclue.TouchSample002.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

public class TouchSample002 extends Activity {

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		MyView mView = new MyView( this );
		setContentView( mView );
	}
}

class MyView extends View {

	Paint mPaint = new Paint();
	Bitmap mBitmap01;
	Bitmap mBitmap02;
	final int touch_down = 1;
	final int touch_up = 2;
	int type = 1;
	Vibrator vibrator;

	public MyView( Context context ) {
		super( context );
		Resources res = this.getContext().getResources();
		mBitmap01 = BitmapFactory.decodeResource( res, R.drawable.gclue01 );
		mBitmap02 = BitmapFactory.decodeResource( res, R.drawable.gclue02 );
		vibrator = (Vibrator) context.getSystemService( Context.VIBRATOR_SERVICE );
	}

	@Override
	protected void onDraw( Canvas canvas ) {
		canvas.drawColor( Color.BLUE );
		if ( type == touch_up ) {
			canvas.drawBitmap( mBitmap01, 0, 0, mPaint );
		} else if ( type == touch_down ) {
			canvas.drawBitmap( mBitmap02, 0, 0, mPaint );
		}
	}

	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			type = touch_down;
			invalidate();
			vibrator.vibrate( 1000 );
		} else if ( event.getAction() == MotionEvent.ACTION_UP ) {
			type = touch_up;
		}
		return true;
	}
}