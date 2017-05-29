package com.example.lamia.faq;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.List;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;
import rjsv.floatingmenu.floatingmenubutton.subbutton.SubButton;

public class ScrollingBehavior extends CoordinatorLayout.Behavior<RelativeLayout> {
    private int toolbarHeight;

    public ScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.toolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RelativeLayout fab, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RelativeLayout fab, View dependency) {


        if (dependency instanceof AppBarLayout) {

            RelativeLayout img_btn = (RelativeLayout) fab.getChildAt(1);

            /*if(fmb.isMenuOpen()){
                List<SubButton> sbs = fmb.getSubMenuButtons();
                for (SubButton sb:sbs) {
                    sb.setY(-dependency.getScrollY());
                }
            }*/
            fab.setTranslationY(-dependency.getScrollY());
        }
        return true;
    }
}