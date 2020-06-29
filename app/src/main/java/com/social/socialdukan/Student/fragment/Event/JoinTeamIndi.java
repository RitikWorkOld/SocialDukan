package com.social.socialdukan.Student.fragment.Event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdukan.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;

import org.json.JSONObject;

import com.razorpay.PaymentResultListener;

import com.social.socialdukan.Student.Miscellaneous.User;
import com.social.socialdukan.Student.fragment.Internship.model.EventRegistered;

import org.json.JSONObject;

public class JoinTeamIndi extends AppCompatActivity implements PaymentResultListener {

    String maxmem,minmem;
    EditText teamname;
    EditText member1,member2,member3,member4,member5,member6,member7,member8,member9,member10;
    String mem1,mem2,mem3,mem4,mem5,mem6,mem7,mem8,mem9,mem10;
    Button next_btn;
   // String teamid_main;
    TextView amount;
    String teamid;
    String eventid,eventname;
    DatabaseReference databaseReferencecmpexp;
    String useremail,usernumber,username,uid;
    private ProgressBar progressBars;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join_team );
        progressBars = findViewById(R.id.progressBar2);
        progressBars.setVisibility(View.GONE);
String amt=getIntent().getStringExtra( "amt" );
         eventid=getIntent().getStringExtra( "eventid" );
         eventname=getIntent().getStringExtra( "eventname" );
        teamid = FirebaseDatabase.getInstance().getReference().child( "EventRegistration" ).push().getKey();
amount=findViewById( R.id.amt_txt );
        member1 = findViewById( R.id.member1 );
        member2 = findViewById( R.id.member2 );
        member3 = findViewById( R.id.member3 );
        member4 = findViewById( R.id.member4 );
        member5 = findViewById( R.id.member5 );
        member6 = findViewById( R.id.member6 );
        member7 = findViewById( R.id.member7 );
        member8 = findViewById( R.id.member8 );
        member9 = findViewById( R.id.member9 );
        member10 = findViewById( R.id.member10 );

        next_btn = findViewById( R.id.next_btn );
        teamname = findViewById( R.id.team_name );

        member3.setVisibility( View.GONE );
        member4.setVisibility( View.GONE );
        member5.setVisibility( View.GONE );
        member6.setVisibility( View.GONE );
        member7.setVisibility( View.GONE );
        member8.setVisibility( View.GONE );
        member9.setVisibility( View.GONE );
        member10.setVisibility( View.GONE );
teamname.setVisibility( View.GONE );
member1.setHint( "Name" );
member2.setHint( "Email" );
amount.setText( amt );
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Users" );
        databaseReference.keepSynced( true );
        databaseReference.orderByChild( "uid" ).equalTo( FirebaseAuth.getInstance().getCurrentUser().getUid() ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue( User.class );

                    useremail = user.getEmail();
                    username = user.getName();
                    usernumber = user.getContactn();
                    uid = user.getUid();
                    member1.setText( username );
                    member2.setText( useremail );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

next_btn.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {


            progressBars.setVisibility( View.VISIBLE );

        progressBars.setVisibility( View.GONE );
if(!member1.getText().toString().isEmpty()){
    if(!member2.getText().toString().isEmpty()){
        if(!amount.getText().toString().isEmpty()){
         /*   databaseReferencecmpexp = FirebaseDatabase.getInstance().getReference().child( "EventRegistration" );
            databaseReferencecmpexp.keepSynced( true );


            databaseReferencecmpexp.orderByChild( "eventid" ).equalTo( eventid ).addListenerForSingleValueEvent( new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        EventRegistered event = dataSnapshot1.getValue(EventRegistered.class);
                        assert event != null;
                        teamid_main=event.getTeamid();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            } );
            Intent intent = new Intent( getApplicationContext(), Payment_Individual.class);
            intent.putExtra("useremail",useremail);
            intent.putExtra("username",username);
            intent.putExtra("usernumber",usernumber);
            intent.putExtra("uid",uid);
            intent.putExtra("amount",amount.getText().toString());
            intent.putExtra("teamid",teamid_main);
            intent.putExtra("eventid",eventid);
            startActivity(intent);*/
            startPayment();
            progressBars.setVisibility( View.GONE );
        }
        else
        {        progressBars.setVisibility( View.GONE );

        }


    }
    else{
        progressBars.setVisibility( View.GONE );
        member2.setError( "Required" );
    }
}
else{
    progressBars.setVisibility( View.GONE );
    member1.setError( "Required" );
}
    }
} );

    }

    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Social Dukan");
            options.put("description", "Event Payment");

   // options.put("razorpay_order_id", "order_9A33XWu170gUtm");
            options.put("image", "https://i.imgur.com/9yp1SSY.png");
            options.put("currency", "INR");
          //  options.put("payment_capture", true);
            String payment = amount.getText().toString();
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", useremail);
            Log.d("HAS","TEST"+useremail+" "+usernumber);
            preFill.put("contact", usernumber);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        // payment successfull pay_DGU19rDsInjcF2
       // Log.e(TAG, " payment successfull "+ s.toString());
        Toast.makeText(this, "Payment successfully done! " , Toast.LENGTH_SHORT).show();

      databaseReferencecmpexp = FirebaseDatabase.getInstance().getReference().child( "EventRegistration" );
        databaseReferencecmpexp.keepSynced( true );
        Log.d("HAS","TESTTEST"+username+useremail+usernumber+" "+amount.getText().toString()+" "+teamid+" "+eventid+" "+uid);


                    Log.d("HAS","TESTTEST1"+username+useremail+usernumber+" "+amount.getText().toString()+" "+teamid+" "+eventid+" "+uid);
                    databaseReferencecmpexp.child( teamid ).child( "status" ).setValue( "PAID" );
                    databaseReferencecmpexp.child( teamid ).child( "amountpaid" ).setValue( amount.getText().toString() );
                    databaseReferencecmpexp.child( teamid ).child( "statusError" ).removeValue();

                    databaseReferencecmpexp.child( teamid ).child("username").setValue(username);
                    databaseReferencecmpexp.child( teamid ).child("teamid").setValue(teamid);
                    databaseReferencecmpexp.child( teamid ).child("useremail").setValue(useremail);
                    databaseReferencecmpexp.child( teamid ).child("usernumber").setValue(usernumber);
        databaseReferencecmpexp.child( teamid ).child("eventid").setValue(eventid);
                    databaseReferencecmpexp.child( teamid ).child("uid").setValue(uid);
                    databaseReferencecmpexp.child( teamid ).child("pass").setValue("no");
                    Toast.makeText(getApplicationContext(), "Swipe down to refresh" , Toast.LENGTH_LONG).show();
                   finish();



                }





    @Override
    public void onPaymentError(int i, String s) {
      // Log.e(TAG,  "error code "+String.valueOf(i)+" -- Payment failed "+s.toString()  );
        try {
            databaseReferencecmpexp.child( teamid ).child( "status" ).setValue( "ERROR" );
            databaseReferencecmpexp.child( teamid ).child( "statusError" ).setValue( "Not-Paid" );

            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }

}