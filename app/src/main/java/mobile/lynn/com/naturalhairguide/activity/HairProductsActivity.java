package mobile.lynn.com.naturalhairguide.activity;

import android.os.Bundle;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.fragment.HairProductsFragment;

public class HairProductsActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_products);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(android.R.color.transparent);

        HairProductsFragment fragment = new HairProductsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_hair_products, fragment)
                .addToBackStack(null)
                .commit();
    }
}
