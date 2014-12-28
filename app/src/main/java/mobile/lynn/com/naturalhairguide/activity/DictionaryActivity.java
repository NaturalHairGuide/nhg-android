package mobile.lynn.com.naturalhairguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.adapter.DictionaryAdapter;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.Term;
import mobile.lynn.com.naturalhairguide.service.DictionaryService;

public class DictionaryActivity extends BaseActivity {

    private DictionaryAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        handleIntent(getIntent());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(android.R.color.transparent);

        setupListView();
    }

    private void setupListView() {
        ListView listView = (ListView) findViewById(R.id.dictionary_list);
        listView.invalidateViews();
        populateList();
        listView.setAdapter(mAdapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DictionaryActivity.this, DictionaryTermActivity.class);
                intent.putExtra(Tags.TERM_ID, id);
                startActivity(intent);
            }
        });

        mAdapter.notifyDataSetChanged();
    }

    private void populateList() {
        List<Term> terms = new Select().all().from(Term.class).execute();
        mAdapter = new DictionaryAdapter(this, terms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dictionary_search, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        menu.findItem(R.id.search)
                .setIcon(new IconDrawable(this, Iconify.IconValue.fa_search)
                        .colorRes(R.color.white)
                        .actionBarSize());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (mAdapter != null) {
                    mAdapter.getFilter().filter(s);
                    mAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            mAdapter.notifyDataSetChanged();
        }
    }
}
