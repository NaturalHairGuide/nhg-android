package mobile.lynn.com.naturalhairguide.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditTextView extends EditText {
    public CustomEditTextView(Context context) {
        super(context);
        setupFontFace();
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupFontFace();
    }

    public CustomEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupFontFace();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupFontFace();
    }

    private void setupFontFace() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/SpecialElite.ttf");
        setTypeface(font);
    }
}
