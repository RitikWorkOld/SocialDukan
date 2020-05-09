package com.example.socialdukan.Employe.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.socialdukan.Employe.Login_Register_Employe.Model.Employe;
import com.example.socialdukan.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import static android.view.View.GONE;

public class FragmentOneB extends Fragment {

    private LinearLayout february;
    private Button sbmit_btn;
private Spinner spinner_genre,spinner_payment_type,spinner_campaign;
private TextView brand_name_tv,descri_view;
private EditText city_et,others,others1,lastdate,prod_name,insta_handle,website_link,other_platform;
private String img,description,comp_id,comp_name,genre,pay_type,campag;
  private CheckBox checkBox_insta,checkBox_fb,checkBox_youtube,checkBox_tiktok,checkBox_others;
  private String platform="";






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fragment_one_b,container,false);
brand_name_tv=view.findViewById( R.id.brandname_textview );
descri_view=view.findViewById( R.id.desc_textview );
spinner_genre=view.findViewById( R.id.spinner_genre );
        spinner_payment_type=view.findViewById( R.id.spinner_payment_type );
spinner_campaign=view.findViewById( R.id.spinner_campaign );
sbmit_btn=view.findViewById( R.id.sbmit_btn );
city_et=view.findViewById( R.id.city_edittext );
february=view.findViewById( R.id.feb );
others=view.findViewById( R.id.Others );
others1=view.findViewById( R.id.Others1 );
lastdate=view.findViewById( R.id.last_date );
prod_name=view.findViewById( R.id.prod_name );
insta_handle=view.findViewById( R.id.insta_handle_et );
website_link=view.findViewById( R.id.website_link_et );
other_platform=view.findViewById( R.id.other_platform );

checkBox_fb=view.findViewById( R.id.checkBox_fb );
        checkBox_insta=view.findViewById( R.id.checkBox_insta );
        checkBox_youtube=view.findViewById( R.id.checkBox_youtube );
        checkBox_tiktok=view.findViewById( R.id.checkBox_tiktok );
        checkBox_others=view.findViewById( R.id.checkbox_other );

        checkBox_others.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_platform.setVisibility( View.VISIBLE );
            }
        } );




        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource( getContext(),R.array.Genre,R.layout.spinner_item_intern );

        spinner_genre.setAdapter( adapter2 );
        spinner_genre.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genre=spinner_genre.getSelectedItem().toString();
                if(genre.equals( "Others" )){

others.setVisibility( View.VISIBLE );
                }
                else
                {
                    others.setVisibility( GONE );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource( getContext(),R.array.pay_type,R.layout.spinner_item_intern );

        spinner_payment_type.setAdapter( adapter3 );
        spinner_payment_type.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pay_type=spinner_payment_type.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        ArrayAdapter<CharSequence> adapter4=ArrayAdapter.createFromResource( getContext(),R.array.campaign,R.layout.spinner_item_intern );

        spinner_campaign.setAdapter( adapter4 );
        spinner_campaign.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                campag=spinner_campaign.getSelectedItem().toString();
                if(campag.equals( "Others" )){

                    others1.setVisibility( View.VISIBLE );
                }
                else{
                    others1.setVisibility( GONE );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

lastdate.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showDatePicker();
    }
} );
        sbmit_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!genre.equals("Others"  )){
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                    databaseReference.keepSynced( true );
                    databaseReference.child("category").setValue( genre );

                }
                if(!campag.equals("Others"  )){
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                    databaseReference.keepSynced( true );
                    databaseReference.child("campaign").setValue( campag );
                }


if (!genre.equals( "Others" )&& others.getText().toString().isEmpty()){
    if(!city_et.getText().toString().isEmpty()){
        if(!lastdate.getText().toString().isEmpty()){
            if(!campag.equals( "Others" )&& others1.getText().toString().isEmpty()){
                if(!prod_name.getText().toString().isEmpty()){
                    if(!insta_handle.getText().toString().isEmpty()){
                        if(!website_link.getText().toString().isEmpty()){
                            if(checkBox_fb.isChecked())
                            {
                                platform = platform + "● Facebook"+"\n";

                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                                databaseReference1.keepSynced( true );
                                databaseReference1.child("primary").setValue( platform );

                            }
                            if(checkBox_insta.isChecked()) {

                                platform = platform + "● Instagram"+"\n";
                                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                                databaseReference2.keepSynced( true );
                                databaseReference2.child("primary").setValue( platform );

                            }
                            if(checkBox_youtube.isChecked()) {

                                platform = platform + "● Youtube"+"\n";
                                DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                                databaseReference3.keepSynced( true );
                                databaseReference3.child("primary").setValue( platform );


                            }
                            if(checkBox_tiktok.isChecked()) {

                                platform = platform + "● Tiktok"+"\n";
                                DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                                databaseReference4.keepSynced( true );
                                databaseReference4.child("primary").setValue( platform );


                            }
                            if(checkBox_others.isChecked()) {

                                platform = platform + other_platform.getText().toString()+"\n";
                                DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                                databaseReference4.keepSynced( true );
                                databaseReference4.child("primary").setValue( "● "+platform );

                            }



                            AlertDialog.Builder builder = new AlertDialog.Builder( Objects.requireNonNull( getActivity() ) );
                            builder.setTitle(R.string.app_name);
                            builder.setIcon(R.drawable.social_dukan );
                            builder.setMessage("Do you want to submit your Application?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "InfluencerCard" ).child( comp_id ).child( comp_id );
                                            databaseReference.keepSynced( true );
                                            databaseReference.child("primary").setValue( platform );
                                            databaseReference.child("location").setValue( city_et.getText().toString() );
                                            databaseReference.child("date").setValue( lastdate.getText().toString() );

                                            databaseReference.child("collab_name").setValue( prod_name.getText().toString() );
                                            databaseReference.child("instahandle").setValue( insta_handle.getText().toString() );
                                            databaseReference.child("link").setValue( website_link.getText().toString() );
                                            databaseReference.child("paymenttype").setValue( pay_type );
                                            databaseReference.child("intimguri").setValue( img );
                                            databaseReference.child("key").setValue( comp_id );
                                            databaseReference.child("descrip").setValue( description );

                                            Intent intent = new Intent(getActivity(), Application_Proceed.class);

                                            startActivity(intent);

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();



                        }

                        else{
                            website_link.setError( "Required" );
                            Toast.makeText( getActivity(), "Website link Required", Toast.LENGTH_SHORT ).show();
                        }


                    }

                    else{
                        insta_handle.setError( "Required" );
                        Toast.makeText( getActivity(), "Insta handle Required", Toast.LENGTH_SHORT ).show();
                    }

                }
                else{
                    prod_name.setError( "Required" );
                    Toast.makeText( getActivity(), "Product Name Required", Toast.LENGTH_SHORT ).show();
                }

                }
            else{
                others1.setError( "Required" );
                Toast.makeText( getActivity(), "Other field Required", Toast.LENGTH_SHORT ).show();
            }

        }
        else{
            lastdate.setError( "Required" );
            Toast.makeText( getActivity(), "Date Required", Toast.LENGTH_SHORT ).show();
        }


    }
    else {
        city_et.setError( "Required" );
        Toast.makeText( getActivity(), "City field Required", Toast.LENGTH_SHORT ).show();
    }





        }
else{
others.setError( "Required" );
    Toast.makeText( getActivity(), "Other field Required", Toast.LENGTH_SHORT ).show();
}

            }
        } );

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Employe" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
        databaseReference.keepSynced( true );
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Employe employe = dataSnapshot.getValue(Employe.class);
                img=employe.getProfileimg();
                description=employe.getDescrip();
                comp_id= employe.getCmpid()   ;
                comp_name=employe.getName();
                brand_name_tv.setText( comp_name );
                descri_view.setText( description );



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );






        february.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentOneA feb_frag = new FragmentOneA();

                fragmentTransaction.replace(R.id.container_shedule,feb_frag);
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

                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                            {
                                //open left side
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                FragmentOneA feb_frag = new FragmentOneA();

                                fragmentTransaction.replace(R.id.container_shedule,feb_frag);
                                fragmentTransaction.commit();

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

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            lastdate.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };

}
