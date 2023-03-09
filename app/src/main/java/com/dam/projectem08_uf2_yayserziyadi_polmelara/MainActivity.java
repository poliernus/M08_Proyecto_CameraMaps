package com.dam.projectem08_uf2_yayserziyadi_polmelara;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    Button btnLogin,btnRegister;
    int numColor = 1;
    ImageView imageView;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        imageView = findViewById(R.id.imageView);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegister();
            }
        });

        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setOnTouchListener(this);
    }

    public void openActivityLogin(){
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
    }

    public void openActivityRegister(){
        Intent intent = new Intent(this, activityRegister.class);
        startActivity(intent);
    }
    private void colorChange(){
        if (numColor ==6){
            numColor = 1;
        }
        switch (numColor){
            case 1:
                imageView.setColorFilter(MainActivity.this.getResources().getColor(R.color.purple_200));
                break;
            case 2:
                imageView.setColorFilter(MainActivity.this.getResources().getColor(R.color.purple_500));
                break;
            case 3:
                imageView.setColorFilter(MainActivity.this.getResources().getColor(R.color.purple_700));
                break;
            case 4:
                imageView.setColorFilter(MainActivity.this.getResources().getColor(R.color.teal_200));
                break;
            case 5:
                imageView.setColorFilter(MainActivity.this.getResources().getColor(R.color.teal_700));
                Toast.makeText(this, "Toast",Toast.LENGTH_SHORT).show();
                break;
        }
        numColor++;
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        final float xc = imageView.getWidth() / 2;
        final float yc = imageView.getHeight() / 2;

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                imageView.clearAnimation();
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);
                break;
            }
            case MotionEvent.ACTION_UP : {
                mPrevAngle = mCurrAngle = 0;
                break;
            }
        }
        return true;
    }

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        imageView.startAnimation(rotate);
    }

}
