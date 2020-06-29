package com.social.socialdukan.Student.fragment.Event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.social.socialdukan.Student.fragment.Event.FAQ.FAQ_Managr;

import org.json.JSONObject;

public class PaymentforEvent extends AppCompatActivity implements PaymentResultListener {

    String amount;
    String useremail,usernumber;
    DatabaseReference databaseReferencecmpexp;
    String teamid,eventid,eventname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentfor_event);
        amount = getIntent().getStringExtra("amt");
        useremail = getIntent().getStringExtra("email");
        usernumber = getIntent().getStringExtra("number");
        eventid = getIntent().getStringExtra( "eventid" );
        eventname = getIntent().getStringExtra( "eventname" );
        teamid = FirebaseDatabase.getInstance().getReference().child( "EventRegistration" ).push().getKey();
        startPayment();
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
            String payment = amount;
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

    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment successfully done! " , Toast.LENGTH_SHORT).show();
        databaseReferencecmpexp = FirebaseDatabase.getInstance().getReference().child( "EventRegistration" );
        databaseReferencecmpexp.keepSynced( true );
        //Log.d("HAS","TESTTEST"+username+useremail+usernumber+" "+amount+" "+teamid+" "+eventid+" "+uid);

        //Log.d("HAS","TESTTEST1"+username+useremail+usernumber+" "+amount+" "+teamid+" "+eventid+" "+uid);
        databaseReferencecmpexp.child( teamid ).child( "status" ).setValue( "PAID" );
        databaseReferencecmpexp.child( teamid ).child( "amountpaid" ).setValue( amount );
        databaseReferencecmpexp.child( teamid ).child( "statusError" ).removeValue();
        //databaseReferencecmpexp.child( teamid ).child("username").setValue(username);
        databaseReferencecmpexp.child( teamid ).child("teamid").setValue(teamid);
        databaseReferencecmpexp.child( teamid ).child("useremail").setValue(useremail);
        databaseReferencecmpexp.child( teamid ).child("usernumber").setValue(usernumber);
        databaseReferencecmpexp.child( teamid ).child("eventid").setValue(eventid);
        //databaseReferencecmpexp.child( teamid ).child("uid").setValue(uid);
        databaseReferencecmpexp.child( teamid ).child("pass").setValue("no");
        Toast.makeText(getApplicationContext(), "Please refresh the page " , Toast.LENGTH_LONG).show();

        onBackPressed();
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

        onBackPressed();
    }
}