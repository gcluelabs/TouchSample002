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
		// �N���X�̃C���X�^���X�𐶐�
		MyView mView = new MyView( this );
		// View�ɐݒ�
		setContentView( mView );
	}
}

/**
 * �`��p�̃N���X�B
 */
class MyView extends View {

	/** �y�C���g�I�u�W�F�N�g��ێ��B */
	Paint mPaint = new Paint();
	/** �摜1��ێ��B */
	Bitmap mBitmap01;
	/** �摜2��ێ��B */
	Bitmap mBitmap02;
	/** �^�b�`�_�E������B */
	final int touch_down = 1;
	/** �^�b�`�A�b�v����B */
	final int touch_up = 2;
	/** �^�b�`�^�C�v��ێ��B */
	int type = 1;
	/** �o�C�u���[�^�I�u�W�F�N�g��ێ��B */
	Vibrator vibrator;

	/**
	 * �R���X�g���N�^�B
	 * @param context �R���e�L�X�g
	 */
	public MyView( Context context ) {
		super( context );
		// Resource�C���X�^���X�̐��� 
		Resources res = this.getContext().getResources();
		// �摜�̓ǂݍ���(res/drawable-hdpi/gclue01.gif) 
		mBitmap01 = BitmapFactory.decodeResource( res, R.drawable.gclue01 );
		// �摜�̓ǂݍ���(res/drawable-hdpi/gclue02.gif)
		mBitmap02 = BitmapFactory.decodeResource( res, R.drawable.gclue02 );
		// �o�C�u���[�^��p�ӂ���
		vibrator = (Vibrator) context.getSystemService( Context.VIBRATOR_SERVICE );
	}

	/**
	 * �`�揈���B
	 */
	@Override
	protected void onDraw( Canvas canvas ) {
		// �w�i�F��ݒ�
		canvas.drawColor( Color.BLUE );
		// Bitmap�C���[�W�̕`�� 
		if ( type == touch_up ) {
			canvas.drawBitmap( mBitmap01, 0, 0, mPaint );
		} else if ( type == touch_down ) {
			canvas.drawBitmap( mBitmap02, 0, 0, mPaint );
		}
	}

	/**
	 * �^�b�`�C�x���g�B
	 */
	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		// �^�b�`�������Ɏ��s
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			type = touch_down;
			// �ĕ`��̎w�� 
			invalidate();
			// �o�C�u���[�^�𓮍삳���� 
			vibrator.vibrate( 1000 );
		} else if ( event.getAction() == MotionEvent.ACTION_UP ) {
			type = touch_up;
			// �ĕ`��̎w�� 
			invalidate();
		}
		return true;
	}
}