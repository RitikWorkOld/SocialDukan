package com.example.socialdukan.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.feature.MainContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class Desc1Fragment extends Fragment {

    public Desc1Fragment() {
        // Required empty public constructor
    }

    TextView textView;
    String str;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate( R.layout.fragment_desc1, container, false );

        /*textView = view.findViewById(R.id.texttemp);
        str = getArguments().getString("key");
        textView.setText(str);*/

        return view;
    }
}
