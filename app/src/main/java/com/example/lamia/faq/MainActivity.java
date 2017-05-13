package com.example.lamia.faq;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    float initialX, initialY;

    FloatingMenuButton floatingButton;
    View invisibleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        invisibleView = (View) findViewById(R.id.target);
        floatingButton = (FloatingMenuButton) findViewById(R.id.my_floating_button);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingButton.setBackground(getResources().getDrawable(R.drawable.circle_drawable_dark));
                int x = invisibleView.getWidth()/2;
                Animator anim = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, 0, invisibleView.getWidth());
                anim.setDuration(800);
                invisibleView.setVisibility(View.VISIBLE);
                anim.start();
            }
        });

        floatingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getActionMasked();

                switch (action) {

                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
//invisibleView.setX(initialX);
                        //invisibleView.setY(initialY);
                        Log.d(TAG, "Action was DOWN");
                        break;

                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "Action was MOVE");
                        break;

                    case MotionEvent.ACTION_UP:
                        float finalX = event.getX();
                        float finalY = event.getY();

                        Log.d(TAG, "Action was UP");

                        if (initialX < finalX) {
                            Log.d(TAG, "Left to Right swipe performed");
                        }

                        if (initialX > finalX) {
                            Log.d(TAG, "Right to Left swipe performed");
                        }

                        if (initialY < finalY) {
                            Log.d(TAG, "Up to Down swipe performed");
                        }

                        if (initialY > finalY) {
                            Log.d(TAG, "Down to Up swipe performed");
                        }

                        break;

                    case MotionEvent.ACTION_CANCEL:
                        Log.d(TAG,"Action was CANCEL");
                        break;

                    case MotionEvent.ACTION_OUTSIDE:
                        Log.d(TAG, "Movement occurred outside bounds of current screen element");
                        break;
                }

                return true;
            }
        });
    }


    public void toggleAnim(View view) {
        //invisibleView = findViewById(R.id.target);
        int x = invisibleView.getWidth()/2;
        Animator anim = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, 0, 64);
        anim.setDuration(3600);
        anim.start();
    }


}
