package mobile.lynn.com.naturalhairguide.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

import mobile.lynn.com.naturalhairguide.listener.OnScrollViewListener;

public class CustomScrollView extends ScrollView {
    private OnScrollViewListener mOnScrollViewListener;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnScrollViewListener(OnScrollViewListener l) {
        this.mOnScrollViewListener = l;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        mOnScrollViewListener.onScrollChanged( this, l, t, oldl, oldt );
        super.onScrollChanged( l, t, oldl, oldt );
    }
}
