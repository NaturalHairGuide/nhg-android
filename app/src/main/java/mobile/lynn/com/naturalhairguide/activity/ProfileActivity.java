package mobile.lynn.com.naturalhairguide.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.adapter.DictionaryAdapter;
import mobile.lynn.com.naturalhairguide.listener.OnScrollViewListener;
import mobile.lynn.com.naturalhairguide.model.Term;
import mobile.lynn.com.naturalhairguide.view.CustomScrollView;

public class ProfileActivity extends BaseActivity {

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(android.R.color.transparent);

        ListView listView = (ListView) findViewById(R.id.profile_info);
        List<Term> terms = new Select().all().from(Term.class).execute();
        DictionaryAdapter mAdapter = new DictionaryAdapter(this, terms);
        listView.setAdapter(mAdapter);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.attachToListView(listView);

        final ColorDrawable actionBarBackground = new ColorDrawable(Color.rgb(170, 85, 133));
        mActionBar = getSupportActionBar();
        mActionBar.setBackgroundDrawable(actionBarBackground);
        actionBarBackground.setAlpha(0);


//        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
//        addTextViews(ll);

//        CustomScrollView scrollView = (CustomScrollView) findViewById(R.id.profile_scroll_view);
//        scrollView.setOnScrollViewListener(new OnScrollViewListener() {
//
//            @Override
//            public void onScrollChanged(CustomScrollView v, int l, int t, int oldl, int oldt) {
//                actionBarBackground.setAlpha(getAlphaForActionBar(v.getScrollY()));
//            }
//
//            private int getAlphaForActionBar(int scrollY) {
//                int minDist = 0, maxDist = 650;
//
//                if(scrollY > maxDist){
//                    return 255;
//                } else if(scrollY < minDist){
//                    return 0;
//                } else {
//                    return (int)  ((255.0 / maxDist) * scrollY);
//                }
//            }
//        });
    }

    private void addTextViews(LinearLayout ll) {
        for (int i = 0; i < 26; i++) {
            TextView tv1 = new TextView(this);
            tv1.setText(String.valueOf(i));
            tv1.setTextSize(10);
            tv1.setWidth(500);
            tv1.setHeight(500);
            tv1.setBackgroundColor(Color.rgb(255-10*i, 255-10*i, 255-10*i)); //just for fun , varying back grounds
            tv1.setGravity(Gravity.CENTER);
            ll.addView(tv1);
        }
    }
}
