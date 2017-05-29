package com.example.lamia.faq;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;

/**
 * Created by Lamia on 5/20/2017.
 */
//@CoordinatorLayout.DefaultBehavior(FloatingActionButton.Behavior.class)
public class FloatingButton extends RelativeLayout {
    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.FloatingButton, 0, 0);

        Drawable ellipseColor = a.getDrawable(R.styleable.FloatingButton_ellipseColor);
        a.recycle();

        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_floating, this, true);

        View ellipseView = (View) getChildAt(0);
        ellipseView.setBackground(ellipseColor);

        View fmb= getChildAt(1);
        //fmb.setBackgroundColor(valueColor);

        //mImage = (ImageView) getChildAt(2);
    }
}
