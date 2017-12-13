package com.example.kelvin.laiofferproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.Fragment;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.OnItemSelectListener{
    private Button buttonYelp;
    private Button buttonBackend;
    private BackendListFragment mBackendFragment;
    private RestaurantListFragment mTabletListFragment;
    private RestaurantListFragment mListFragment;
    private RestaurantGridFragment mGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ListView eventListView = (ListView) findViewById(R.id.event_list);

        // Initialize an adapter.
        //RestaurantAdapter adapter = new RestaurantAdapter(this);


        // Assign adapter to ListView.
        //eventListView.setAdapter(adapter);
        // Show different fragments based on screen size.
        Intent intent = getIntent();
        String service = intent.getStringExtra("Service");

        if (service.equals("Yelp")) {
            mListFragment = new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.list_container,
                    mListFragment).commit();
        } else {
            mBackendFragment = new BackendListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.list_container,
                    mBackendFragment).commit();
        }
        /*
        if (!isTablet()) {
            mListFragment = new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.list_container, mListFragment).commit();
        }
        else {
            mTabletListFragment = new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.grid_list_container, mListFragment).commit();
            mGridFragment = new RestaurantGridFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.grid_container, mGridFragment).commit();
        }
        */
        /*
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                YelpApi yelp = new YelpApi();
                yelp.searchForBusinessesByLocation("dinner", "440 Davis Ct, San Francisco, CA", 20);
                return null;
            }
        }.execute();*/

    }

    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }



    private String[] getRestaurantsName() {
        String[] names = {
                "Restaurant1", "Restaurant2", "Restaurant3",
                "Restaurant4", "Restaurant5", "Restaurant6",
                "Restaurant7", "Restaurant8", "Restaurant9",
                "Restaurant10", "Restaurant11", "Restaurant12"};
        return names;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are on onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }

    @Override
    public void OnItemSelected(int position) {
        if (mGridFragment != null) {
            mGridFragment.onItemSelected(position);
        }
        else{
            Intent intent = new Intent(this, RestaurantActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

}
