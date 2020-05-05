package com.example.socialdukan.Employe.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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

import static android.view.View.GONE;

public class FragmentOneA extends Fragment implements View.OnClickListener {
private RadioGroup radioGroup_detail,radioGroup_type,radioGroup_duration,radioGroup_stipend_perk;
private CheckBox one,two,three,four;
private RadioButton radio_fixed,radio_perfor_based,radio_unpaid,radio_negot;
    private String comp_id;
    private String wcg1;
    private String duration;
    private String month;
    private Spinner spinner_permonth,spinner_choose_scale;
    private String permonth,scale;

    private Button add_detail,add_type,add_start_date,add_respons,add_stip,add_perks,add_skill;


    private LinearLayout march,perform_based_show1,negot_layout,unpaid_noshow;
    EditText other_selected,city_edit_text,no_of_opening,skills,que1,que2,resp_edit_text,main_stipend_et,inc_based_et,neg_to_et;
    TextView perform_based1,title_city,FromET,ToET,another_que,another_que2;
    private NestedScrollView nestedScrollView;
    private RadioButton intern_type_option1,intern_type_option2;
    private RadioButton immed,later;
    private LinearLayout layout1,layout2;
private RadioButton radiobtn1,radiobtn2,radiobtn3,radiobtn4,radiobtn5,
        radiobtn6,radiobtn7,radiobtn8,radiobtn9,radiobtn10,radiobtn11,radiobtn12,radiobtn13_other;
    private View coming_soon;
    private Button sbmit_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fragment_one_a,container,false);

        march = (LinearLayout)view.findViewById(R.id.march);
skills=view.findViewById( R.id.skills );
        coming_soon = (View)view.findViewById(R.id.coming_soon);
        radioGroup_detail=view.findViewById( R.id.group_detail );
        radioGroup_type=view.findViewById( R.id.options_interntype );
        radioGroup_duration=view.findViewById( R.id.intern_duration );
        FromET=view.findViewById( R.id.from_editText );
        spinner_permonth=view.findViewById( R.id.spinner_permonth );
         spinner_choose_scale=view.findViewById( R.id.choose_scale );
        ToET=view.findViewById( R.id.to_editText );
        que1=view.findViewById( R.id.quest_2 );
        resp_edit_text=view.findViewById( R.id.resp_edit_text );
        main_stipend_et=view.findViewById( R.id.main_stipend_edit_text );
        inc_based_et=view.findViewById( R.id.inc_based_et );
neg_to_et=view.findViewById( R.id.neg_to_et );
        add_detail=view.findViewById( R.id.add_detail );
        add_type=view.findViewById( R.id.add_type );
        add_perks=view.findViewById( R.id.add_perks );
        add_respons=view.findViewById( R.id.add_respons );
        add_start_date=view.findViewById( R.id.add_start_date );
        add_stip=view.findViewById( R.id.add_stip );
        add_skill=view.findViewById( R.id.add_skill );

        add_detail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int option = radioGroup_detail.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById( option );
                if(radioButton.getText().equals( "Other" )){
                    if(!other_selected.getText().toString().isEmpty()){
                        DatabaseReference databaseReferencec_detail1 = FirebaseDatabase.getInstance().getReference().child("Internships").child( comp_id);
                        databaseReferencec_detail1.keepSynced(true);
                        databaseReferencec_detail1.child("intname").setValue(other_selected.getText().toString());
                        add_detail.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                        add_detail.setCompoundDrawablePadding( 5 );
                        add_detail.setText("Added");

                    }
                    else
                    {
                        other_selected.setError( "Required" );
                    }



                }
                else{
                    DatabaseReference databaseReferencec_detail1 = FirebaseDatabase.getInstance().getReference().child("Internships").child( comp_id);
                    databaseReferencec_detail1.keepSynced(true);
                    databaseReferencec_detail1.child("intname").setValue(radioButton.getText().toString());
                    add_detail.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_detail.setCompoundDrawablePadding( 5 );
                    add_detail.setText("Added");
                }
            }
        } );
        add_type.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int option_type = radioGroup_type.getCheckedRadioButtonId();
                RadioButton radioButton_type = view.findViewById( option_type );
                if(radioButton_type.getText().toString().equals( "Work from home" )){


                        if (!no_of_opening.getText().toString().isEmpty()) {
                            DatabaseReference databaseReferencec_type = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                            databaseReferencec_type.keepSynced( true );
                            databaseReferencec_type.child( "type" ).setValue( radioButton_type.getText().toString() );
                            databaseReferencec_type.child( "ctext1" ).setValue( "yes" );
                            databaseReferencec_type.child( "no_opening" ).setValue( no_of_opening.getText().toString() );
                            add_type.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                            add_type.setCompoundDrawablePadding( 5 );
                            add_type.setText("Added");
                        }
                        else{
                            no_of_opening.setError( "Required" );
                            Toast.makeText( getActivity(), "Number of opening Required", Toast.LENGTH_SHORT ).show();
                        }




                }

                if (radioButton_type.getText().toString().equals( "Regular (In-Office/On-field)" )){
                    if(!city_edit_text.getText().toString().isEmpty()){
                        if (!no_of_opening.getText().toString().isEmpty()) {
                        DatabaseReference databaseReferencec_detail = FirebaseDatabase.getInstance().getReference().child("Internships").child( comp_id);
                        databaseReferencec_detail.keepSynced(true);
                        databaseReferencec_detail.child("location").setValue(city_edit_text.getText().toString());
                            add_type.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                            add_type.setCompoundDrawablePadding( 5 );
                            add_type.setText("Added");

                        }
                        else{
                            no_of_opening.setError( "Required" );
                            Toast.makeText( getActivity(), "Number of opening Required", Toast.LENGTH_SHORT ).show();
                        }

                    }
                    else{
                        city_edit_text.setError( "Required" );
                        Toast.makeText( getActivity(), "City Required", Toast.LENGTH_SHORT ).show();
                    }

                }


            }
        } );
        add_start_date.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int option_duration=radioGroup_duration.getCheckedRadioButtonId();
                RadioButton radioButton_dur=view.findViewById( option_duration );
                if(radioButton_dur.getText().toString().equals("Later")){
                    if(!FromET.getText().toString().isEmpty()) {
                        if(!ToET.getText().toString().isEmpty()){
                        DatabaseReference databaseReference_duration = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                        databaseReference_duration.keepSynced( true );
                        databaseReference_duration.child( "duration" ).setValue( duration + " " + month );
                        databaseReference_duration.child( "from" ).setValue( FromET.getText().toString() );
                        databaseReference_duration.child( "to" ).setValue( ToET.getText().toString() );
                            add_start_date.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                            add_start_date.setCompoundDrawablePadding( 5 );
                            add_start_date.setText("Added");
                        }
                        else{
                            ToET.setError( "Required" );
                        }
                    }
                    else{
                        FromET.setError( "Required" );
                      //  Toast.makeText( getActivity(), "Duration Field Required", Toast.LENGTH_SHORT ).show();

                    }

                }
                if(radioButton_dur.getText().toString().equals("Immediately (within next 30 days)"))
                {
                    DatabaseReference databaseReference_duration = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReference_duration.keepSynced( true );
                    databaseReference_duration.child("duration").setValue( duration+" "+month );
                    add_start_date.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_start_date.setCompoundDrawablePadding( 5 );
                    add_start_date.setText("Added");

                }

            }
        } );

        add_skill.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!skills.getText().toString().isEmpty()){
                    DatabaseReference databaseReference_skills = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReference_skills.keepSynced( true );
                    databaseReference_skills.child("wca1").setValue( skills.getText().toString());
                    add_skill.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_skill.setCompoundDrawablePadding( 5 );
                    add_skill.setText("Added");
                }
                else{

                    skills.setError( "Required" );
                }
            }
        } );


add_respons.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(!resp_edit_text.getText().toString().isEmpty()){
            DatabaseReference databaseReference_resp = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
            databaseReference_resp.keepSynced( true );
            databaseReference_resp.child("desc2").setValue( resp_edit_text.getText().toString());
            add_respons.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
            add_respons.setCompoundDrawablePadding( 5 );
            add_respons.setText("Added");
        }
        else{
            resp_edit_text.setError( "Required" );
        }
    }
} );
add_stip.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int option = radioGroup_stipend_perk.getCheckedRadioButtonId();
        RadioButton radioButton = view.findViewById( option );
        if(radioButton.getText().equals( "Fixed" )){
            if(!main_stipend_et.getText().toString().isEmpty()) {
                DatabaseReference databaseReferencec_detail1 = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                databaseReferencec_detail1.keepSynced( true );
                databaseReferencec_detail1.child( "amount" ).setValue( main_stipend_et.getText().toString() );
                add_stip.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                add_stip.setCompoundDrawablePadding( 5 );
                add_stip.setText("Added");
            }
            else{
                main_stipend_et.setError( "Required" );
            }
        }
        if(radioButton.getText().equals( "Performance based" )){
            if(!main_stipend_et.getText().toString().isEmpty()) {
                if(!inc_based_et.getText().toString().isEmpty()) {
                    DatabaseReference databaseReferencec_detail1 = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReferencec_detail1.keepSynced( true );
                    databaseReferencec_detail1.child( "amount" ).setValue( main_stipend_et.getText().toString() );
                    databaseReferencec_detail1.child( "incentiveBased" ).setValue( inc_based_et.getText().toString() );
                    add_stip.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_stip.setCompoundDrawablePadding( 5 );
                    add_stip.setText("Added");


                }
                else
                {
                    inc_based_et.setError( "Required" );
                }
            }
            else {
                main_stipend_et.setError( "Required" );
            }

        }
        if(radioButton.getText().equals( "Negotiable" )){
            if(!main_stipend_et.getText().toString().isEmpty()) {
                if(!neg_to_et.getText().toString().isEmpty()){
                    DatabaseReference databaseReferencec_detail1 = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReferencec_detail1.keepSynced( true );
                    databaseReferencec_detail1.child( "amount" ).setValue( main_stipend_et.getText().toString() );
                    databaseReferencec_detail1.child( "negotiatedTo" ).setValue( neg_to_et.getText().toString() );
                    add_stip.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_stip.setCompoundDrawablePadding( 5 );
                    add_stip.setText("Added");
                }
                else {
                    neg_to_et.setError( "Required" );
                }

            }
            else {
                main_stipend_et.setError( "Required" );
            }


        }
        if(radioButton.getText().toString().equals( "Unpaid" )){
            DatabaseReference databaseReferencec_ = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
            databaseReferencec_.keepSynced( true );
            databaseReferencec_.child( "amount" ).setValue( "Unpaid");
            add_stip.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
            add_stip.setCompoundDrawablePadding( 5 );
            add_stip.setText("Added");
        }

    }
} );
        add_perks.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(one.isChecked())
                {
                    wcg1 = wcg1 + " Certificate, ";

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReference.keepSynced( true );
                    databaseReference.child("wcg1").setValue( wcg1+"." );
                    add_perks.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_perks.setCompoundDrawablePadding( 5 );
                    add_perks.setText("Added");
                }
                if(two.isChecked()) {
                    wcg1 = wcg1 + " Letter of recommendation, ";
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReference.keepSynced( true );
                    databaseReference.child("wcg1").setValue( wcg1+"." );
                    add_perks.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_perks.setCompoundDrawablePadding( 5 );
                    add_perks.setText("Added");
                }
                if(three.isChecked()) {
                    wcg1 = wcg1 + " Flexible Work hours, ";
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReference.keepSynced( true );
                    databaseReference.child( "wcg1" ).setValue( wcg1 + "." );
                    add_perks.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_perks.setCompoundDrawablePadding( 5 );
                    add_perks.setText("Added");
                }
                if(four.isChecked()) {
                    wcg1 = wcg1 + " 5 days a week, ";
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Internships" ).child( comp_id );
                    databaseReference.keepSynced( true );
                    databaseReference.child("wcg1").setValue( wcg1+"." );
                    add_perks.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    add_perks.setCompoundDrawablePadding( 5 );
                    add_perks.setText("Added");
                }
            }
        } );


        another_que2=view.findViewById( R.id.anoth_que2 );
        que2=view.findViewById( R.id.quest_3 );
radiobtn1=view.findViewById( R.id.option_1 );
perform_based1=view.findViewById( R.id.perfor_based_show );
immed=view.findViewById( R.id.radio_immed );
another_que=view.findViewById( R.id.anoth_que );
later=view.findViewById( R.id.radio_later );
layout1=view.findViewById( R.id.later_duration1 );
layout2=view.findViewById( R.id.later_duration2 );
radio_fixed=view.findViewById( R.id.radio_fixed );
no_of_opening=view.findViewById( R.id.no_of_opening );
        radio_perfor_based=view.findViewById( R.id.radio_perf_based );
        radio_unpaid=view.findViewById( R.id.radio_unpaid );
        radio_negot=view.findViewById( R.id.radio_nego );
        intern_type_option1=view.findViewById( R.id.option_type1 );
        intern_type_option2=view.findViewById( R.id.option_type2 );
perform_based_show1=view.findViewById( R.id.perform_based_show1 );
radioGroup_stipend_perk=view.findViewById( R.id.stipendperk );
negot_layout=view.findViewById( R.id.negotiable_layout );
unpaid_noshow=view.findViewById( R.id.unpaid_noshow );
other_selected=view.findViewById( R.id.other_selected );
one=view.findViewById( R.id.checkbox_certificate );
        two=view.findViewById( R.id.checkbox_LOR );
        three=view.findViewById( R.id.checkbox_flex_workhr );
        four=view.findViewById( R.id.checkbox_days_week );
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
        title_city=view.findViewById( R.id.title_city );
        city_edit_text=view.findViewById( R.id.city_edittext );
        spinner_choose_scale.setVisibility( GONE );
        another_que.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    que1.setVisibility( View.VISIBLE );
                another_que.setVisibility( GONE );
                another_que2.setVisibility( View.VISIBLE );
            }
        } );
        another_que2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                que2.setVisibility( View.VISIBLE );
                another_que2.setVisibility( GONE );
            }
        } );

        final Spinner spinner_duration=view.findViewById( R.id.spinner_duration );
        final Spinner spinner_month=view.findViewById( R.id.spinner_month );
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource( getContext(),R.array.duration,R.layout.spinner_item_intern );

        spinner_duration.setAdapter( adapter );
        spinner_duration.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                duration=spinner_duration.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource( getContext(),R.array.month,R.layout.spinner_item_intern );

        spinner_month.setAdapter( adapter1 );
        spinner_month.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month=spinner_month.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource( getContext(),R.array.per_month,R.layout.spinner_item_intern );

        spinner_permonth.setAdapter( adapter2 );
        spinner_permonth.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                permonth=spinner_permonth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource( getContext(),R.array.per_scale,R.layout.spinner_item_intern );

        spinner_choose_scale.setAdapter( adapter3 );
        spinner_choose_scale.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scale=spinner_choose_scale.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
        radiobtn1.setOnClickListener( this );
        radiobtn2.setOnClickListener( this );
        radiobtn3.setOnClickListener( this );
        radiobtn4.setOnClickListener( this );
        radiobtn5.setOnClickListener( this );
        radiobtn6.setOnClickListener( this );
        radiobtn7.setOnClickListener( this );
        radiobtn8.setOnClickListener( this );
        radiobtn9.setOnClickListener( this );
        radiobtn10.setOnClickListener( this );
        radiobtn11.setOnClickListener( this );
        radiobtn12.setOnClickListener( this );
        radiobtn13_other.setOnClickListener( this );
        intern_type_option1.setOnClickListener( this );
        intern_type_option2.setOnClickListener( this );
        immed.setOnClickListener( this );
        later.setOnClickListener( this );
        radio_negot.setOnClickListener( this );
        radio_unpaid.setOnClickListener( this );
        radio_perfor_based.setOnClickListener( this );
        radio_fixed.setOnClickListener( this );


        sbmit_btn=view.findViewById( R.id.sbmit_btn );
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Employe" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
        databaseReference.keepSynced( true );
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Employe employe = dataSnapshot.getValue(Employe.class);


                comp_id= employe.getCmpid()   ;
                wcg1=employe.getWcg1();
                if(wcg1==null){

                    wcg1="";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );




        sbmit_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                // Concatenation of the checked options in if

                // isChecked() is used to check whether
                // the CheckBox is in true state or not.



                //---------------------------------------------------------------------------------------------

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


    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.option_1:
            case R.id.option_6:
            case R.id.option_2:
            case R.id.option_3:
            case R.id.option_4:
            case R.id.option_5:
            case R.id.option_7:
            case R.id.option_8:
            case R.id.option_9:
            case R.id.option_10:
            case R.id.option_11:
            case R.id.option_12:
                other_selected.setVisibility( GONE );

                add_detail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_detail.setText("ADD");
                break;
            case R.id.option_13:
                other_selected.setVisibility( View.VISIBLE );
                break;
            case R.id.option_type1:
                title_city.setVisibility( View.VISIBLE );
                city_edit_text.setVisibility( View.VISIBLE );
                add_type.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_type.setText("ADD");
                break;
            case R.id.option_type2:
                title_city.setVisibility( GONE );
                city_edit_text.setVisibility( GONE );
                add_type.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_type.setText("ADD");
                break;
            case R.id.radio_immed:
                layout1.setVisibility( GONE );
                layout2.setVisibility( GONE );
                add_start_date.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_start_date.setText("ADD");
                break;
            case R.id.radio_later:
                layout1.setVisibility( View.VISIBLE );
                layout2.setVisibility( View.VISIBLE );
                add_start_date.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_start_date.setText("ADD");

                break;
            case R.id.radio_fixed:
                negot_layout.setVisibility( GONE );
                perform_based1.setVisibility( GONE );
                add_stip.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_stip.setText("ADD");
                perform_based_show1.setVisibility( GONE );
                spinner_choose_scale.setVisibility( GONE );
                spinner_permonth.setVisibility( View.VISIBLE );
                unpaid_noshow.setVisibility( View.VISIBLE );
                break;
            case R.id.radio_perf_based:
                perform_based1.setVisibility( View.VISIBLE );
                perform_based_show1.setVisibility( View.VISIBLE );
                unpaid_noshow.setVisibility( View.VISIBLE );
                add_stip.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_stip.setText("ADD");
                spinner_permonth.setVisibility( View.VISIBLE );
                negot_layout.setVisibility( GONE );
                spinner_choose_scale.setVisibility( View.VISIBLE );
                break;
            case R.id.radio_nego:
                negot_layout.setVisibility( View.VISIBLE );
                unpaid_noshow.setVisibility( View.VISIBLE );
                perform_based1.setVisibility( GONE );
                add_stip.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_stip.setText("ADD");
                spinner_choose_scale.setVisibility( GONE );
                spinner_permonth.setVisibility( View.VISIBLE );
                perform_based_show1.setVisibility( GONE );
                break;
            case R.id.radio_unpaid:
                negot_layout.setVisibility( GONE );
                spinner_choose_scale.setVisibility( GONE );
                perform_based1.setVisibility( GONE );
                add_stip.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0 );

                add_stip.setText("ADD");
                spinner_permonth.setVisibility( GONE );
                perform_based_show1.setVisibility( GONE );
                unpaid_noshow.setVisibility( GONE );

                break;


        }

    }
}
