package com.social.socialdukan.Student.fragment.Event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdukan.R;
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
    String teamid_main;
    TextView amount;
    String teamid;
    String eventid,eventname;
    DatabaseReference databaseReferencecmpexp;
    String useremail,usernumber,username,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join_team );
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
if(!member1.getText().toString().isEmpty()){
    if(!member2.getText().toString().isEmpty()){
        if(!amount.getText().toString().isEmpty()){
startPayment();
        }

    }
}
    }
} );

    }

    public void startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
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


        databaseReferencecmpexp.orderByChild( "eventid" ).equalTo( eventid ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    EventRegistered event = dataSnapshot1.getValue(EventRegistered.class);
                    assert event != null;
                    teamid_main=event.getTeamid();
                    databaseReferencecmpexp.child( teamid_main ).child( "status" ).setValue( "PAID" );
                    databaseReferencecmpexp.child( teamid_main ).child( "amountpaid" ).setValue( amount.getText().toString() );
                    databaseReferencecmpexp.child( teamid_main ).child( "statusError" ).removeValue();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


    }

    @Override
    public void onPaymentError(int i, String s) {
      // Log.e(TAG,  "error code "+String.valueOf(i)+" -- Payment failed "+s.toString()  );
        try {
            databaseReferencecmpexp.child( teamid_main ).child( "status" ).setValue( "ERROR" );
            databaseReferencecmpexp.child( teamid_main ).child( "statusError" ).setValue( "Not-Paid" );

            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }

}