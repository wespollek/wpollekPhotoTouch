package edu.ucsb.cs.cs185.wpollek.phototouch;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SearchViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Wesley on 2/24/2017.
 */

public class GridFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gridview,container,false);
        GridView gridView = (GridView)rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this.getContext()));
        return rootView;
    }
}
