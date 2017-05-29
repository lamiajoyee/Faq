package com.example.lamia.faq;

import android.animation.Animator;
import android.support.design.widget.AppBarLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;

import static com.example.lamia.faq.R.styleable.AppBarLayout;

public class MainActivity extends AppCompatActivity{

    FloatingMenuButton floatingButton;
    View invisibleView, fabView;
    AppBarLayout appBar;
    RelativeLayout fabContainer, fabLayout;

    RelativeLayout imgButton;

    FloatingActionButton defaultFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeControls();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon1 = new ImageView(this);
        ImageView itemIcon2 = new ImageView(this);
        ImageView itemIcon3 = new ImageView(this);
        ImageView itemIcon4 = new ImageView(this);

        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .setStartAngle(90)
                .setEndAngle(270)
                .setRadius(100)
                .attachTo(imgButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                CloseEllipse();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                OpenEllipse();
            }
        });

        // custom onClickListener to handle ellipse open/close animation
        /*   floatingButton.setOnClickListener(new View.OnClickListener() {
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
        });*/

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
        //floatingButton = (FloatingMenuButton) findViewById(R.id.my_floating_button);
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        //defaultFAB = (FloatingActionButton) findViewById(R.id.defaultFAB);
        //fabLayout = (RelativeLayout) findViewById(R.id.fab_layout);
        imgButton = (RelativeLayout) findViewById(R.id.img_button);
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
                imgButton.setBackground(getResources().getDrawable(R.drawable.circle_drawable));
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
        imgButton.setBackground(getResources().getDrawable(R.drawable.circle_drawable_dark));
        Animator anim = ViewAnimationUtils.createCircularReveal(invisibleView, x, x, 0, invisibleView.getWidth());
        anim.setDuration(800);
        invisibleView.setVisibility(View.VISIBLE);
        anim.start();
    }
}
