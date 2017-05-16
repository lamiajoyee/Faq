package com.example.lamia.faq;

import android.animation.Animator;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;

import static com.example.lamia.faq.R.styleable.AppBarLayout;

public class MainActivity extends AppCompatActivity{

    private String TAG = MainActivity.class.getSimpleName();
    float initialX, initialY;

    FloatingMenuButton floatingButton;
    View invisibleView;
    AppBarLayout appBar;
    RelativeLayout fab_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        invisibleView = (View) findViewById(R.id.target);
        floatingButton = (FloatingMenuButton) findViewById(R.id.my_floating_button);
        appBar = (AppBarLayout) findViewById(R.id.appbar);
        fab_container =(RelativeLayout) findViewById(R.id.fab_container);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = invisibleView.getWidth()/2;
                if(floatingButton.isMenuOpen())
                {
                    floatingButton.setBackground(getResources().getDrawable(R.drawable.circle_drawable_dark));
                    Animator anim = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, 0, invisibleView.getWidth());
                    anim.setDuration(800);
                    invisibleView.setVisibility(View.VISIBLE);
                    anim.start();
                }
                else{
                    Animator anim2 = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, invisibleView.getWidth(), 0);
                    anim2.setDuration(1600);
                    anim2.start();
                    anim2.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            invisibleView.setVisibility(View.INVISIBLE);
                            floatingButton.setBackground(getResources().getDrawable(R.drawable.circle_drawable));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                }
            }
        });


        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());

                switch (state.name()){
                    case "COLLAPSED":
                        //floatingButton.closeMenu();
                        Animator animColl = ViewAnimationUtils.createCircularReveal(floatingButton, floatingButton.getWidth()/2, floatingButton.getWidth()/2, floatingButton.getWidth()/2, 0);
                        animColl.setDuration(800);
                        animColl.start();
                        fab_container.setVisibility(View.INVISIBLE);
                        break;

                    case "EXPANDED":
                        //floatingButton.closeMenu();
                        Animator animExp = ViewAnimationUtils.createCircularReveal(floatingButton, floatingButton.getWidth()/2, floatingButton.getWidth()/2, 0, floatingButton.getWidth()/2);
                        animExp.setDuration(800);
                        fab_container.setVisibility(View.VISIBLE);
                        animExp.start();
                        break;

                    case "IDLE":
                        floatingButton.closeMenu();
                        //Animator animExp = ViewAnimationUtils.createCircularReveal(floatingButton, floatingButton.getWidth()/2, floatingButton.getWidth()/2, 0, floatingButton.getWidth()/2);
                        //animExp.setDuration(800);
                        //fab_container.setVisibility(View.VISIBLE);
                        //animExp.start();
                        break;
                }
            }
        });

    }
}
