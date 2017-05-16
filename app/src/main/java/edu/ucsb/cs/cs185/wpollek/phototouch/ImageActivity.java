package edu.ucsb.cs.cs185.wpollek.phototouch;
import com.almeros.android.multitouch.*;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity implements View.OnTouchListener{

    private ScaleGestureDetector scaleG;
    private RotateGestureDetector rotateG;
    private MoveGestureDetector moveG;
    private float scalespan = 1.0f;
    private float rotatedeg = 0.f;
    private float movex = 0.f;
    private float movey = 0.f;
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scalespan *= detector.getScaleFactor();
            return true;
        }
    }
    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener{
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            rotatedeg-=detector.getRotationDegreesDelta();
            return true;
        }
    }
    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener{
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF d = detector.getFocusDelta();
            movex += d.x;
            movey += d.y;
            return true;
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        scaleG.onTouchEvent(event);
        rotateG.onTouchEvent(event);
        moveG.onTouchEvent(event);
        ImageView iv = (ImageView) findViewById(R.id.activity_image);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix mat = new Matrix();
        Intent in = getIntent();
        Bundle b = in.getExtras();
        int i =0;
        if (b!=null){
            i = (int) b.get("id");
        }
        Bitmap bitmap = BitmapManager.getBitmap(i);
        float cenx = bitmap.getWidth()/2;
        float ceny = bitmap.getHeight()/2;
        float tranx = iv.getWidth()/2;
        float trany = iv.getHeight()/2;
        float scalefax = (cenx*2)/(ceny*2);
        mat.postRotate(rotatedeg,cenx,ceny);
        mat.postScale(scalespan*scalefax,scalespan*scalefax);
        //Different images exhibit different scaling behavior
        //guessing it has to do with resolution of pictures vs
        //downloads, this combination for translate seemed to
        //work the best for all different quality images
        mat.postTranslate(movex+tranx-cenx,movey+trany-ceny);

        iv.setImageMatrix(mat);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        scaleG = new ScaleGestureDetector(getApplicationContext(), new ScaleListener());
        rotateG=new RotateGestureDetector(getApplicationContext(),new RotateListener());
        moveG = new MoveGestureDetector(getApplicationContext(),new MoveListener());
        //figure out what combination of matrices to give natural feel
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imv = (ImageView) findViewById(R.id.activity_image);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        int i =0;
        if (b!=null){
            i = (int) b.get("id");
        }
        Bitmap bitmap = BitmapManager.getBitmap(i);
        imv.setImageBitmap(bitmap);
        // extending or implementing the touch listener, this can implement ontouchlistener
        imv.setOnTouchListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
