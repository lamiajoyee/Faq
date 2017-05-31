package com.example.lamia.faq;

import android.nfc.Tag;
import android.support.design.widget.AppBarLayout;
import android.util.Log;

/**
 * Created by Lamia on 5/16/2017.
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    public enum State {
        EXPANDED,
        COLLAPSED,
        COLLAPSING,
        EXPANDING,
        IDLE
    }

    private State mCurrentState = State.IDLE;
    private static final String TAG = "MyActivity";
    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;

        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else if ((Math.abs(i) <= 128)&&(mCurrentState==State.EXPANDED)) {
            if (mCurrentState != State.COLLAPSING) {
                onStateChanged(appBarLayout, State.COLLAPSING);
            }
            mCurrentState = State.COLLAPSING;
        } else if ((Math.abs(i) <= 128)&&(mCurrentState==State.COLLAPSED)) {
            if (mCurrentState != State.EXPANDING) {
                onStateChanged(appBarLayout, State.EXPANDING);
            }
            mCurrentState = State.EXPANDING;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
