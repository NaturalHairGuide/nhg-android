package mobile.lynn.com.naturalhairguide.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.helper.Tags;

public class HairProductsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Hair Products");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hair_products, container, false);
        setupHairProductOptions(view);
        setupSectionsForTabletView(inflater, container, view);
        return view;
    }

    private void setupSectionsForTabletView(LayoutInflater inflater, ViewGroup container, View view) {
        LinearLayout cleansingSection = (LinearLayout) view.findViewById(R.id.hair_products_cleansing_section);

        if (cleansingSection != null) {
            LinearLayout conditionerSection = (LinearLayout) view.findViewById(R.id.hair_products_conditioner_section);
            LinearLayout stylingSection = (LinearLayout) view.findViewById(R.id.hair_products_styling_section);

            for (int idx = 0; idx < 5; idx++) {
                addProductToSection(inflater, container, cleansingSection, idx);
                addProductToSection(inflater, container, conditionerSection, idx);
                addProductToSection(inflater, container, stylingSection, idx);
            }
        }
    }

    private void setupHairProductOptions(View view) {
        Button cleansingBtn = (Button) view.findViewById(R.id.cleansing);

        if(cleansingBtn != null) {
            Button conditionerBtn = (Button) view.findViewById(R.id.conditioner);
            Button stylingBtn = (Button) view.findViewById(R.id.styling);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sectionName = (String) ((TextView) view).getText();
                    showProductDetails(sectionName);
                }
            };

            cleansingBtn.setOnClickListener(clickListener);
            conditionerBtn.setOnClickListener(clickListener);
            stylingBtn.setOnClickListener(clickListener);
        }
    }

    private void showProductDetails(String sectionName) {
        HairProductsDetailsFragment detailsFragment = new HairProductsDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Tags.SECTION_NAME, sectionName);
        detailsFragment.setArguments(args);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_hair_products, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    private void addProductToSection(LayoutInflater inflater, ViewGroup container, LinearLayout section, int index) {
        View productLayout = inflater.inflate(R.layout.layout_product, container, false);
        section.addView(productLayout, index);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
