package com.example.socialdukan.Student.fragment.other_services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.Employe.Reg_Employe;
import com.example.socialdukan.R;
import com.example.socialdukan.Student.Login_Register_Student.Login_Student;
import com.example.socialdukan.Student.fragment.Internship.ApplyIntern;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Card1_Form extends AppCompatActivity implements View.OnClickListener {
TextInputLayout evnt_name,evnt_venue,city1,exp_football,head_name,head_cont,head_email;

EditText evnt_date;
Button btn;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card1__form );
        evnt_name=findViewById( R.id.evnt_name );
        evnt_date=findViewById( R.id.evnt_date );
        evnt_venue=findViewById( R.id.evnt_venue );
        city1=findViewById( R.id.city );
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

        TextView title = (TextView) findViewById(R.id.title);
        SpannableString content = new SpannableString("Form");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        title.setText(content);



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
                                        if (!headName.toString().isEmpty()) {
                                            if (!headEmail.toString().isEmpty()) {


                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "OtherServiceCard1" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid());
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



                                                Toast.makeText( Card1_Form.this, "Done", Toast.LENGTH_SHORT ).show();

                                                Intent intent = new Intent( Card1_Form.this, Thanks_Activity.class);
                                                startActivity(intent);

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
