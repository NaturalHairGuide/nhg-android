package mobile.lynn.com.naturalhairguide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.adapter.HairProductsListAdapter;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.HairProduct;

public class HairProductsDetailsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String sectionName = getArguments().getString(Tags.SECTION_NAME);
        getActivity().setTitle(sectionName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hair_product_details, container, false);

        ListView listView = (ListView) view.findViewById(R.id.product_details_list);

        List<HairProduct> products = new ArrayList<HairProduct>();
        products.add(new HairProduct());
        ListAdapter adapter = new HairProductsListAdapter(getActivity(), products);
        listView.setAdapter(adapter);

        return view;
    }
}
