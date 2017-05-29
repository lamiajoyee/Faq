package com.example.lamia.faq;

import android.content.res.TypedArray;
import android.view.View;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.animation.MenuAnimationHandler;

import java.util.ArrayList;

/**
 * Created by Lamia on 5/29/2017.
 */

public class CustomFloatingActionMenu extends FloatingActionMenu {
    public CustomFloatingActionMenu(View mainActionView, int startAngle, int endAngle, int radius, ArrayList<Item> subActionItems, MenuAnimationHandler animationHandler, boolean animated, MenuStateChangeListener stateChangeListener) {
        super(mainActionView, startAngle, endAngle, radius, subActionItems, animationHandler, animated, stateChangeListener);


    }
}
