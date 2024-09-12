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
    TextInputLayout evnt_name, evnt_venue, city1, exp_football, head_name, head_cont, head_email;
    EditText number;
    EditText evnt_date;
    private FirebaseAuth mFirebaseAuth;
    Button btn;
    private int mYear, mMonth, mDay;
    TextInputLayout description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1__form);

        // Initialize UI elements
        evnt_name = findViewById(R.id.evnt_name);
        evnt_venue = findViewById(R.id.evnt_venue);
        city1 = findViewById(R.id.city);
        description = findViewById(R.id.evnt_desc);
        exp_football = findViewById(R.id.exp_football);
        head_name = findViewById(R.id.head_name);
        head_cont = findViewById(R.id.head_cont);
        head_email = findViewById(R.id.head_email);
        number = findViewById(R.id.number);
        evnt_date = findViewById(R.id.evnt_date);
        btn = findViewById(R.id.submit_btn);
        mFirebaseAuth = FirebaseAuth.getInstance();

        // Set listeners
        evnt_date.setOnClickListener(this);

        btn.setOnClickListener(v -> {
            // Retrieve and validate inputs
            String eventname = evnt_name.getEditText() != null ? evnt_name.getEditText().getText().toString().trim() : "";
            String eventdate = evnt_date.getText().toString().trim();
            String eventvenue = evnt_venue.getEditText() != null ? evnt_venue.getEditText().getText().toString().trim() : "";
            String city = city1.getEditText() != null ? city1.getEditText().getText().toString().trim() : "";
            String expfootball = exp_football.getEditText() != null ? exp_football.getEditText().getText().toString().trim() : "";
            String headName = head_name.getEditText() != null ? head_name.getEditText().getText().toString().trim() : "";
            String headCont = head_cont.getEditText() != null ? head_cont.getEditText().getText().toString().trim() : "";
            String headEmail = head_email.getEditText() != null ? head_email.getEditText().getText().toString().trim() : "";
            String desc = description.getEditText() != null ? description.getEditText().getText().toString().trim() : "";


            if (eventname.isEmpty()) {
                evnt_name.setError("Please fill in the event name");
                return;
            }

            if (eventdate.isEmpty()) {
                evnt_date.setError("Please select the event date");
                return;
            }
            if (eventvenue.isEmpty()) {
                evnt_venue.setError("Please fill in the event venue");
                return;
            }
            if (desc.isEmpty()) {
                evnt_name.setError("Please fill in the description");
                return;
            }
            if (city.isEmpty()) {
                city1.setError("Please fill in the city");
                return;
            }
            if (expfootball.isEmpty()) {
                exp_football.setError("Please fill in the expected football experience");
                return;
            }
            if (headCont.isEmpty() || number.length() != 10) {
                head_cont.setError("Please enter a valid contact number");
                return;
            }
            if (headName.isEmpty()) {
                head_name.setError("Please fill in the head name");
                return;
            }
            if (headEmail.isEmpty()) {
                head_email.setError("Please fill in the head email");
                return;
            }

            // If all fields are filled correctly, proceed with saving data
            String notiid = FirebaseDatabase.getInstance().getReference().child("OtherServiceCard1")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().getKey();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("OtherServiceCard1").child(notiid);
            databaseReference.keepSynced(true);
            databaseReference.child("eventname").setValue(eventname);
            databaseReference.child("eventdate").setValue(eventdate);
            databaseReference.child("eventvenue").setValue(eventvenue);
            databaseReference.child("city").setValue(city);
            databaseReference.child("expfootball").setValue(expfootball);
            databaseReference.child("headname").setValue(headName);
            databaseReference.child("headcont").setValue(headCont);
            databaseReference.child("heademail").setValue(headEmail);
            databaseReference.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
            databaseReference.child("id").setValue(notiid);
            databaseReference.child("read").setValue("no");
            databaseReference.child("desc").setValue(desc);

            // Clear fields after submission
            evnt_name.getEditText().setText("");
            evnt_date.setText("");
            description.getEditText().setText("");
            evnt_venue.getEditText().setText("");
            city1.getEditText().setText("");
            exp_football.getEditText().setText("");
            head_name.getEditText().setText("");
            head_cont.getEditText().setText("");
            head_email.getEditText().setText("");
            number.setText("");

            // Navigate to the Thanks activity
            Intent intent = new Intent(Card1_Form.this, Thanks_Activity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        if (v == evnt_date) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) ->
                            evnt_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year),
                    mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
