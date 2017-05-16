package edu.ucsb.cs.cs185.wpollek.phototouch;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Wesley on 2/23/2017.
 */

public class ListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listview,container,false);
        ListView listView = (ListView)rootView.findViewById(R.id.listview);
        listView.setAdapter(new ImageAdapter(this.getContext()));
        return rootView;
    }
}
