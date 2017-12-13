package com.example.kelvin.laiofferproject;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class RestaurantGridFragment extends Fragment {
    private GridView mGridView;
    public RestaurantGridFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_grid, container, false);
        mGridView = (GridView) view.findViewById(R.id.restaurant_grid_view);

        return view;

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void onItemSelected(int position) {
        for (int i = 0 ; i < mGridView.getChildCount(); i++) {
            if (position == 1) {
                mGridView.getChildAt(i).setBackgroundColor(Color.BLUE);
            }
            else {
                mGridView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }

}
