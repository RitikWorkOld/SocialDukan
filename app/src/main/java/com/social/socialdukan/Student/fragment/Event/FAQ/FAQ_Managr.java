package com.social.socialdukan.Student.fragment.Event.FAQ;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.socialdukan.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FAQ_Managr extends Fragment {
    private RecyclerView rv_internall;
    private ImageView close_btn;
    private ArrayList<Faq_model> arrayList;
    private DatabaseReference drinternall;
 String key;
    private FirebaseRecyclerOptions<Faq_model> optionsinternall;
    private FirebaseRecyclerAdapter<Faq_model, Faq_viewholder> adapterinternall;


    public FAQ_Managr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate( R.layout.activity_faq_manager, container, false );
        close_btn = view.findViewById(R.id.close_btn);
        rv_internall = view.findViewById(R.id.rv_faq_events);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(getContext()));
        final Bundle bundle = getArguments();

       key = bundle.getString("key");
        drinternall = FirebaseDatabase.getInstance().getReference().child("Events").child(key).child("faq").child(key);
        drinternall.keepSynced(true);
        arrayList = new ArrayList<Faq_model>();
        optionsinternall = new FirebaseRecyclerOptions.Builder<Faq_model>().setQuery(drinternall,Faq_model.class).build();

        adapterinternall = new FirebaseRecyclerAdapter<Faq_model, Faq_viewholder>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull Faq_viewholder holder, int i, @NonNull final Faq_model model) {

                holder.question.setText( model.getQuestion() );
                holder.answer.setText( model.getAnswer() );


            }

            @NonNull
            @Override
            public Faq_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Faq_viewholder(LayoutInflater.from( getActivity()).inflate(R.layout.fragment_f_a_q__managr,parent,false));
            }
        };
        rv_internall.setAdapter(adapterinternall);
        adapterinternall.startListening();
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                fragmentManager.popBackStack();
            }
        });



        return view;
    }
}
