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

    FloatingMenuButton floatingButton;
    View invisibleView;
    AppBarLayout appBar;
    RelativeLayout fabContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeControls();

        // custom onClickListener to handle ellipse open/close animation
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(floatingButton.isMenuOpen())
                {
                    CloseEllipse();
                }
                else
                {
                    OpenEllipse();
                }
            }
        });

        // custom listener to handle FAB + ellipse expand/collapse when scrolling
     /*   appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state.name()){
                    case "COLLAPSED":
                        CollapseFab();
                        break;

                    case "EXPANDED":
                        ExpandFab();
                        break;

                    case "IDLE":
                        floatingButton.closeMenu();
                        OpenEllipse();
                        //CollapseFab();
                        break;
                }
            }
        }); */

    }

    // initiate controls
    private void InitializeControls() {
        invisibleView = findViewById(R.id.target);
        floatingButton = (FloatingMenuButton) findViewById(R.id.my_floating_button);
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        fabContainer = (RelativeLayout) findViewById(R.id.fab_container);
    }

    /* expand/collapse controls
     */
    // FAB
    private void ExpandFab() {
        Animator animExp = ViewAnimationUtils.createCircularReveal(floatingButton, floatingButton.getWidth()/2, floatingButton.getWidth()/2, 0, floatingButton.getWidth()/2);
        animExp.setDuration(800);
        fabContainer.setVisibility(View.VISIBLE);
        animExp.start();
    }
    private void CollapseFab() {
        Animator animColl = ViewAnimationUtils.createCircularReveal(floatingButton, floatingButton.getWidth()/2, floatingButton.getWidth()/2, floatingButton.getWidth()/2, 0);
        animColl.setDuration(800);
        animColl.start();
        fabContainer.setVisibility(View.INVISIBLE);
    }

    // ellipse surrounding the FAB
    private void OpenEllipse() {
        int x = invisibleView.getWidth()/2;
        Animator anim = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, invisibleView.getWidth(), 0);
        anim.setDuration(1600);
        anim.start();
        anim.addListener(new Animator.AnimatorListener() {
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
    private void CloseEllipse() {
        int x = invisibleView.getWidth()/2;
        floatingButton.setBackground(getResources().getDrawable(R.drawable.circle_drawable_dark));
        Animator anim = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, 0, invisibleView.getWidth());
        anim.setDuration(800);
        invisibleView.setVisibility(View.VISIBLE);
        anim.start();
    }
}
