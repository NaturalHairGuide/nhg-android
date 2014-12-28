package mobile.lynn.com.naturalhairguide.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.model.Term;

public class DictionaryAdapter extends ArrayAdapter<Term> implements Filterable {

    private final HashMap<String, Integer> azIndexer;
    private final String[] sections;
    private List<Term> allTerms = new ArrayList<Term>();
    private List<Term> currentTerms = new ArrayList<Term>();

    public DictionaryAdapter(Context context, List<Term> terms) {
        super(context, R.layout.list_item_term, R.id.label, terms);
        this.allTerms = terms;
        this.currentTerms = terms;

        Collections.sort(terms);

        azIndexer = new HashMap<String, Integer>(); //stores the positions for the start of each letter

        int size = terms.size();
        for (int i = size - 1; i >= 0; i--) {
            String element = terms.get(i).getTerm();
            azIndexer.put(element.substring(0, 1), i);
        }

        Set<String> keys = azIndexer.keySet();

        Iterator<String> it = keys.iterator();
        ArrayList<String> keyList = new ArrayList<String>();

        while (it.hasNext()) {
            String key = it.next();
            keyList.add(key);
        }
        Collections.sort(keyList);
        sections = new String[keyList.size()];
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return currentTerms.size();
    }

    @Override
    public Term getItem(int i) {
        return currentTerms.get(i);
    }

    @Override
    public long getItemId(int i) {
        return currentTerms.get(i).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_term, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        textView.setText(currentTerms.get(position).getTerm());

        return rowView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return currentTerms.size();
    }

    @Override
    public boolean isEmpty() {
        return currentTerms.isEmpty();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Term> filteredResult = getFilteredResults(charSequence);

                FilterResults results = new FilterResults();
                results.values = filteredResult;
                results.count = filteredResult.size();

                return results;
            }

            private List<Term> getFilteredResults(CharSequence charSequence) {
                String queryString = charSequence.toString().toLowerCase();

                if (queryString.length() == 0){
                    return allTerms;
                }

                ArrayList<Term> listResult = new ArrayList<Term>();
                for (Term t : allTerms){
                    if (t.getTerm().toLowerCase().contains(queryString)){
                        listResult.add(t);
                    }
                }
                return listResult;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                currentTerms = (ArrayList<Term>) filterResults.values;
                DictionaryAdapter.this.notifyDataSetChanged();
            }
        };
    }
}
