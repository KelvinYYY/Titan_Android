package com.example.kelvin.laiofferproject;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {

    OnItemSelectListener mCallback;
    private ListView mListView;
    private DataService dataService;

    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        mListView = (ListView) view.findViewById(R.id.restaurant_list);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant r = (Restaurant) mListView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(MapActivity.EXTRA_LATLNG,
                        new LatLng(r.getLat(), r.getLng()));
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        dataService = new DataService();
        return view;

        //return inflater.inflate(R.layout.fragment_restaurant_list2, container, false);
    }

    public void OnItemSelected(int position) {
        for (int i = 0 ; i < mListView.getChildCount() ; i++) {
            if (position == 1) {
                mListView.getChildAt(i).setBackgroundColor(Color.RED);
            } else{
                mListView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }
    public interface OnItemSelectListener {
        public void OnItemSelected(int position);
    }
    @Override
    public void onStart() {
        super.onStart();
        refreshRestaurantList(dataService);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
    private void refreshRestaurantList(DataService dataService) {
        new GetRestaurantsNearbyAsyncTask(this, dataService).execute();
    }


    private class GetRestaurantsNearbyAsyncTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private Fragment fragment;
        private DataService dataService;

        public GetRestaurantsNearbyAsyncTask(Fragment fragment, DataService dataService) {
            this.fragment = fragment;
            this.dataService = dataService;
        }

        @Override
        protected List<Restaurant> doInBackground(Void... params) {
            return dataService.getNearbyRestaurants();
        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurants) {
            if (restaurants != null) {
                super.onPostExecute(restaurants);
                RestaurantAdapter adapter = new  RestaurantAdapter(fragment.getActivity(), restaurants);
                mListView.setAdapter(adapter);
            } else {
                Toast.makeText(fragment.getActivity(), "Data service error.", Toast.LENGTH_LONG);
            }
        }
    }

}
