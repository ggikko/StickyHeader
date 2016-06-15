package ggikko.me.stickyheaderapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by admin on 16. 6. 14..
 */
public class CustomScrollView extends ScrollView {

    Context mContext;

    public CustomScrollView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount()-1);
        int diff = (view.getBottom()-(getHeight()+getScrollY()));

        int scrollY = getScrollY();
        final float scale = getContext().getResources().getDisplayMetrics().density;

        int height = (int)((int)(scrollY - 0.5f)/scale);

        ((MainActivity)mContext).setScrollYValue(scrollY);

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
