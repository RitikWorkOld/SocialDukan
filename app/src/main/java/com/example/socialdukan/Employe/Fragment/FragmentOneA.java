package com.example.socialdukan.Employe.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.socialdukan.Employe.Login_Register_Employe.Model.Employe;
import com.example.socialdukan.R;
import com.example.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentOneA extends Fragment {
private RadioGroup radioGroup_detail,radioGroup_type;
    private String comp_id;
    private LinearLayout march;
    EditText other_selected;
    private NestedScrollView nestedScrollView;
private RadioButton radiobtn1,radiobtn2,radiobtn3,radiobtn4,radiobtn5,
        radiobtn6,radiobtn7,radiobtn8,radiobtn9,radiobtn10,radiobtn11,radiobtn12,radiobtn13_other;
    private View coming_soon;
    private Button sbmit_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fragment_one_a,container,false);

        march = (LinearLayout)view.findViewById(R.id.march);

        coming_soon = (View)view.findViewById(R.id.coming_soon);
        radioGroup_detail=view.findViewById( R.id.group_detail );
        radioGroup_type=view.findViewById( R.id.options_interntype );
radiobtn1=view.findViewById( R.id.option_1 );
other_selected=view.findViewById( R.id.other_selected );
        radiobtn2=view.findViewById( R.id.option_2 );
        radiobtn3=view.findViewById( R.id.option_3 );
        radiobtn4=view.findViewById( R.id.option_4 );
        radiobtn5=view.findViewById( R.id.option_5 );
        radiobtn6=view.findViewById( R.id.option_6 );
        radiobtn7=view.findViewById( R.id.option_7 );
        radiobtn8=view.findViewById( R.id.option_8 );
        radiobtn9=view.findViewById( R.id.option_9 );
        radiobtn10=view.findViewById( R.id.option_10 );
        radiobtn11=view.findViewById( R.id.option_11 );
        radiobtn12=view.findViewById( R.id.option_12 );
        radiobtn13_other=view.findViewById( R.id.option_13 );
        sbmit_btn=view.findViewById( R.id.sbmit_btn );
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Employe" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
        databaseReference.keepSynced( true );
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Employe employe = dataSnapshot.getValue(Employe.class);


                comp_id= employe.getCmpid()   ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        sbmit_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int option = radioGroup_detail.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById( option );
                if(radioButton.getText().equals( "Others" )){

                    other_selected.setVisibility( View.VISIBLE );
                    DatabaseReference databaseReferencec_detail1 = FirebaseDatabase.getInstance().getReference().child("Internship").child( comp_id);
                    databaseReferencec_detail1.keepSynced(true);
                    databaseReferencec_detail1.child("intname").setValue(other_selected.getText().toString());
                }
                else{
                    other_selected.setVisibility( View.GONE );
                DatabaseReference databaseReferencec_detail = FirebaseDatabase.getInstance().getReference().child("Internship").child( comp_id);
                databaseReferencec_detail.keepSynced(true);
                databaseReferencec_detail.child("intname").setValue(radioButton.getText().toString());
                }
                //---------------------------------------------------------------------------------------------
                int option_type = radioGroup_type.getCheckedRadioButtonId();
                RadioButton radioButton_type = view.findViewById( option_type );
                DatabaseReference databaseReferencec_type = FirebaseDatabase.getInstance().getReference().child("Internship").child( comp_id);
                databaseReferencec_type.keepSynced(true);
                databaseReferencec_type.child("type").setValue(radioButton_type.getText().toString());
                //---------------------------------------------------------------------------------------------
            }
        } );





        march.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentOneB mar_frag = new FragmentOneB();

                fragmentTransaction.replace(R.id.container_shedule,mar_frag);
                fragmentTransaction.commit();
            }
        });

        //SWIPE CODE FOR FRAGMENTS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                            {
                                //open right side
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                FragmentOneB mar_frag = new FragmentOneB();

                                fragmentTransaction.replace(R.id.container_shedule,mar_frag);
                                fragmentTransaction.commit();

                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                            {
                                //open left side

                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });



        //SWIPE CODE FOR FRAGMENTS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        return view;
    }
}
