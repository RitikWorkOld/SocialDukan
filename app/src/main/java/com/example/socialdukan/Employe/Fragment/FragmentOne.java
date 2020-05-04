package com.example.socialdukan.Employe.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdukan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_shedule,new FragmentOneA());
        fragmentTransaction.commit();


        final View view = inflater.inflate( R.layout.fragment_one, container, false );
        return view;
    }
}
