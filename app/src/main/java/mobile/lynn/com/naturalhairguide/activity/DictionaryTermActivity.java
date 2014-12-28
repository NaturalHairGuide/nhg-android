package mobile.lynn.com.naturalhairguide.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.Term;
import mobile.lynn.com.naturalhairguide.model.TermImage;

public class DictionaryTermActivity extends BaseActivity {

    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_dictionary_term);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(android.R.color.transparent);

        Bundle args = getIntent().getExtras();
        long id = args.getLong(Tags.TERM_ID);
        term = Term.load(Term.class, id);

        setTitle(term.getTerm());

        TextView definition = (TextView) findViewById(R.id.definition);
        definition.setText(term.getDefinition());

        ImageView mainImage = (ImageView) findViewById(R.id.main_image);
        mainImage.setImageResource(term.getMainImage());

        LinearLayout termImages = (LinearLayout) findViewById(R.id.term_images);

        for(TermImage image : term.images()) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.layout_term_image, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.term_image);
            imageView.setImageResource(image.getLocation());

            termImages.addView(imageView);
        }
    }
}
