package com.example.socialdukan.Student.fragment.other_services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Internship.ApplyIntern;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Card1_Form extends AppCompatActivity implements View.OnClickListener {
EditText evnt_name,evnt_date,evnt_venue,city,exp_football,head_name,head_cont,head_email;
Button btn;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card1__form );
        evnt_name=findViewById( R.id.evnt_name );
        evnt_date=findViewById( R.id.evnt_date );
        evnt_venue=findViewById( R.id.evnt_venue );
        city=findViewById( R.id.city );
        exp_football=findViewById( R.id.exp_football );
        head_cont=findViewById( R.id.head_cont );
        head_name=findViewById( R.id.head_name );
        head_email=findViewById( R.id.head_email );

        btn=findViewById( R.id.submit_btn );

evnt_date.setOnClickListener( this );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!evnt_name.getText().toString().isEmpty()) {
                    if (!evnt_date.getText().toString().isEmpty()) {
                        if (!evnt_venue.getText().toString().isEmpty()) {
                            if (!city.getText().toString().isEmpty()) {
                                if (!exp_football.getText().toString().isEmpty()) {
                                    if (!head_cont.getText().toString().isEmpty()) {
                                        if (!head_name.getText().toString().isEmpty()) {
                                            if (!head_email.getText().toString().isEmpty()) {


                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "OtherServiceCard1" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                databaseReference.keepSynced( true );
                                                databaseReference.child( "eventname" ).setValue( evnt_name.getText().toString() );
                                                databaseReference.child( "eventdate" ).setValue( evnt_date.getText().toString() );
                                                databaseReference.child( "eventvenue" ).setValue( evnt_venue.getText().toString() );
                                                databaseReference.child( "city" ).setValue( city.getText().toString() );
                                                databaseReference.child( "expfootball" ).setValue( exp_football.getText().toString() );
                                                databaseReference.child( "headname" ).setValue( head_name.getText().toString() );
                                                databaseReference.child( "headcont" ).setValue( head_cont.getText().toString() );
                                                databaseReference.child( "heademail" ).setValue( head_email.getText().toString() );
                                                databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );



                                                Toast.makeText( Card1_Form.this, "Done", Toast.LENGTH_SHORT ).show();
                                                finish();

                                            }
                                            else {
                                                Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else {
                                            Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {
                                Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Card1_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == evnt_date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get( Calendar.YEAR );
            mMonth = c.get( Calendar.MONTH );
            mDay = c.get( Calendar.DAY_OF_MONTH );


            DatePickerDialog datePickerDialog = new DatePickerDialog( this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            evnt_date.setText( dayOfMonth + "-" + (monthOfYear + 1) + "-" + year );

                        }
                    }, mYear, mMonth, mDay );
            datePickerDialog.show();
        }
    }
}
