package com.gclue.TouchSample002;

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
		// クラスのインスタンスを生成
		MyView mView = new MyView( this );
		// Viewに設定
		setContentView( mView );
	}
}

/**
 * 描画用のクラス。
 */
class MyView extends View {

	/** ペイントオブジェクトを保持。 */
	Paint mPaint = new Paint();
	/** 画像1を保持。 */
	Bitmap mBitmap01;
	/** 画像2を保持。 */
	Bitmap mBitmap02;
	/** タッチダウン判定。 */
	final int touch_down = 1;
	/** タッチアップ判定。 */
	final int touch_up = 2;
	/** タッチタイプを保持。 */
	int type = 1;
	/** バイブレータオブジェクトを保持。 */
	Vibrator vibrator;

	/**
	 * コンストラクタ。
	 * @param context コンテキスト
	 */
	public MyView( Context context ) {
		super( context );
		// Resourceインスタンスの生成 
		Resources res = this.getContext().getResources();
		// 画像の読み込み(res/drawable-hdpi/gclue01.gif) 
		mBitmap01 = BitmapFactory.decodeResource( res, R.drawable.gclue01 );
		// 画像の読み込み(res/drawable-hdpi/gclue02.gif)
		mBitmap02 = BitmapFactory.decodeResource( res, R.drawable.gclue02 );
		// バイブレータを用意する
		vibrator = (Vibrator) context.getSystemService( Context.VIBRATOR_SERVICE );
	}

	/**
	 * 描画処理。
	 */
	@Override
	protected void onDraw( Canvas canvas ) {
		// 背景色を設定
		canvas.drawColor( Color.BLUE );
		// Bitmapイメージの描画 
		if ( type == touch_up ) {
			canvas.drawBitmap( mBitmap01, 0, 0, mPaint );
		} else if ( type == touch_down ) {
			canvas.drawBitmap( mBitmap02, 0, 0, mPaint );
		}
	}

	/**
	 * タッチイベント。
	 */
	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		// タッチした時に実行
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			type = touch_down;
			// 再描画の指示 
			invalidate();
			// バイブレータを動作させる 
			vibrator.vibrate( 1000 );
		} else if ( event.getAction() == MotionEvent.ACTION_UP ) {
			type = touch_up;
			// 再描画の指示 
			invalidate();
		}
		return true;
	}
}