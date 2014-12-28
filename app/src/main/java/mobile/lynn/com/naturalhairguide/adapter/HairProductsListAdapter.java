package mobile.lynn.com.naturalhairguide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.model.HairProduct;

public class HairProductsListAdapter extends ArrayAdapter<HairProduct> {
    private final List<HairProduct> products;

    public HairProductsListAdapter(Activity context, List<HairProduct> products) {
        super(context, R.layout.list_item_term, R.id.label, products);
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_product, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.product_name);
        name.setText(products.get(position).getName());

        TextView location = (TextView) rowView.findViewById(R.id.product_location);
        location.setText(products.get(position).getLocation());

        TextView price = (TextView) rowView.findViewById(R.id.product_price);
        price.setText("$" + Double.toString(products.get(position).getPrice()));

        return rowView;
    }
}
