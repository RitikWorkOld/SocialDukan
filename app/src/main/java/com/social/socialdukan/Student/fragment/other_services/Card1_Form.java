package com.social.socialdukan.Student.fragment.other_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.socialdukan.R;
import com.social.socialdukan.Student.fragment.MyDashboard.applied_intern_md;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Objects;

public class Card1_Form extends AppCompatActivity implements View.OnClickListener {
TextInputLayout evnt_name,evnt_venue,city1,exp_football,head_name,head_cont,head_email;
EditText number;
public String cmpid;
EditText name_et,venue_et,city_et,exp_et,head_name_et,email_et;
EditText evnt_date;
private FirebaseAuth mFirebaseAuth;
Button btn;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card1__form );
        name_et=findViewById( R.id.name_et );
        venue_et=findViewById( R.id.venut_et );
        city_et=findViewById( R.id.city_et );
        exp_et=findViewById( R.id.exp_et );
        head_name_et=findViewById( R.id.head_name_et );
        email_et=findViewById( R.id.email_et );

        evnt_name=findViewById( R.id.evnt_name );
        evnt_date=findViewById( R.id.evnt_date );
        evnt_venue=findViewById( R.id.evnt_venue );
        city1=findViewById( R.id.city );
        number=findViewById( R.id.number );
        mFirebaseAuth=FirebaseAuth.getInstance();
        exp_football=findViewById( R.id.exp_football );
        head_cont=findViewById( R.id.head_cont );
        head_name=findViewById( R.id.head_name );
        head_email=findViewById( R.id.head_email );

        final Editable eventname = evnt_name.getEditText().getText();

        final Editable eventvenue = evnt_venue.getEditText().getText();
        final Editable city = city1.getEditText().getText();
        final Editable exprec_football = exp_football.getEditText().getText();
        final Editable headName = head_name.getEditText().getText();
        final Editable headCont = head_cont.getEditText().getText();
        final Editable headEmail = head_email.getEditText().getText();


        btn=findViewById( R.id.submit_btn );




evnt_date.setOnClickListener( this );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eventname.toString().isEmpty()) {
                    if (!evnt_date.getText().toString().isEmpty()) {
                        if (!eventvenue.toString().isEmpty()) {
                            if (!city.toString().isEmpty()) {
                                if (!exprec_football.toString().isEmpty()) {
                                    if (!headCont.toString().isEmpty()) {
                                        if((number.length() == 10)) {
                                            if (!headName.toString().isEmpty()) {
                                                if (!headEmail.toString().isEmpty()) {
                                                    String notiid = FirebaseDatabase.getInstance().getReference().child("OtherServiceCard1")
                                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().getKey();

                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "OtherServiceCard1" ).child( notiid )
                                                            ;
                                                    databaseReference.keepSynced( true );
                                                    databaseReference.child( "eventname" ).setValue( eventname.toString() );
                                                    databaseReference.child( "eventdate" ).setValue( evnt_date.getText().toString() );
                                                    databaseReference.child( "eventvenue" ).setValue( eventvenue.toString() );
                                                    databaseReference.child( "city" ).setValue( city.toString() );
                                                    databaseReference.child( "expfootball" ).setValue( exprec_football.toString() );
                                                    databaseReference.child( "headname" ).setValue( headName.toString() );
                                                    databaseReference.child( "headcont" ).setValue( headCont.toString() );
                                                    databaseReference.child( "heademail" ).setValue( headEmail.toString() );
                                                    databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );
                                                    databaseReference.child( "id" ).setValue(notiid);
                                                    databaseReference.child( "read" ).setValue("no");

                                                    //Toast.makeText( Card1_Form.this, "Done", Toast.LENGTH_SHORT ).show();

                                                    Intent intent = new Intent( Card1_Form.this, Thanks_Activity.class );
                                                    name_et.setText( "" );
                                                    venue_et.setText( "" );
                                                    city_et.setText( "" );
                                                    exp_et.setText( "" );
                                                    head_name_et.setText( "" );
                                                    number.setText( "" );
                                                    email_et.setText( "" );
evnt_date.setText( "" );
                                                    startActivity( intent );

                                                } else {
                                                    Toast.makeText( Card1_Form.this, "Please fill all the answers", Toast.LENGTH_SHORT ).show();
                                                }
                                            } else {
                                                Toast.makeText( Card1_Form.this, "Please fill all the answers", Toast.LENGTH_SHORT ).show();
                                            }
                                        }

                                        else {
                                            number.setError(getString(R.string.input_error_phone_invalid));
                                            number.requestFocus();
                                            }


                                    }//
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
