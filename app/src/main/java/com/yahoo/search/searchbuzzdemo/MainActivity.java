/*
* Copyright 2016, Yahoo Inc.
* Copyrights licensed under the New BSD License.
* See the accompanying LICENSE file for terms.
*/

package com.yahoo.search.searchbuzzdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.search.android.trending.Constants;
import com.yahoo.search.android.trending.settings.TrendingViewSettings;
import com.yahoo.search.android.trending.view.ITrendingViewListener;
import com.yahoo.search.android.trending.view.TrendingView;
import com.yahoo.search.searchbuzzdemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ITrendingViewListener, View.OnClickListener {


    private LinearLayout mTrendingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrendingLayout = (LinearLayout) findViewById(R.id.trending_layout);
        TrendingView trendingView = new TrendingView();
        TrendingViewSettings.Builder builder = new TrendingViewSettings.Builder("EnterYourAppId", Constants.TrendingCategory.DEFAULT);
//      Other configuration options
//      builder.setNumTerms(5);
        builder.setTypeTag("SB_DEMO_APP"); //API to send optional data to server. Max size of param - 64 chars.
        builder.setCommercialIconDimension(50, 50); //Pixel width, height
        trendingView.initialize(this, this, builder.build());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    /**
     * Yahoo search SDK method called when trending terms are ready.
     * @param trendingViews
     */

    @Override
    public void onTrendingViewReady(ArrayList<TextView> trendingViews) {
        Log.v("MainActivity", "view size" + trendingViews.size());
        for (TextView trendingView: trendingViews) {
            mTrendingLayout.addView(trendingView);
            trendingView.setOnClickListener(this);
        }
    }

    /**
     * Yahoo search SDK method called when error in receiving trending terms.
     * @param error
     * Error Code 100 : Network Exception
     * Error Code 200 : JSON Exception
     */

    @Override
    public void onTrendingViewError(int errorCode, String error) {
        Toast.makeText(MainActivity.this, "errorCode - " + errorCode + ":" + error, Toast.LENGTH_SHORT).show();
    }
    /**
     * Developer defined method for action after click event on trending terms.
     * @param v
     */

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, ""+ ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();

        //This is a list of Y! search urls with appropriate params and trending term as query.
        //Developer can do their own implementation here.
        URLSpan[] urls= ((TextView) v).getUrls();
        String url = urls[0].getURL();
        if(url != null) {
            Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            MainActivity.this.startActivity(browseIntent);
        }
    }
}