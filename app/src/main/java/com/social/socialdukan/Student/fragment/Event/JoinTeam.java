package com.social.socialdukan.Student.fragment.Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.socialdukan.R;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Miscellaneous.User;

public class JoinTeam extends AppCompatActivity {

    String maxmem,minmem;
    EditText teamname;
    EditText member1,member2,member3,member4,member5,member6,member7,member8,member9,member10;
    String mem1,mem2,mem3,mem4,mem5,mem6,mem7,mem8,mem9,mem10;
    Button next_btn;
    String teamid;
    String useremail,usernumber,username;
    String amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_join_team);

        teamid = FirebaseDatabase.getInstance().getReference().child("EventRegistration").push().getKey();

        member1 = findViewById(R.id.member1);
        member2 = findViewById(R.id.member2);
        member3 = findViewById(R.id.member3);
        member4 = findViewById(R.id.member4);
        member5 = findViewById(R.id.member5);
        member6 = findViewById(R.id.member6);
        member7 = findViewById(R.id.member7);
        member8 = findViewById(R.id.member8);
        member9 = findViewById(R.id.member9);
        member10 = findViewById(R.id.member10);

        next_btn = findViewById(R.id.next_btn);
        teamname = findViewById(R.id.team_name);

        minmem = getIntent().getStringExtra("minmem");
        maxmem = getIntent().getStringExtra("maxmem");
        amt = getIntent().getStringExtra("amt");

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(Integer.valueOf(maxmem),Integer.parseInt(minmem));
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);

                    useremail = user.getEmail();
                    username = user.getName();
                    usernumber = user.getContactn();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        assert maxmem != null;
        switch (Integer.parseInt(maxmem)){
            case 2:
                member4.setVisibility(View.GONE);
                member3.setVisibility(View.GONE);
                member5.setVisibility(View.GONE);
                member6.setVisibility(View.GONE);
                member7.setVisibility(View.GONE);
                member8.setVisibility(View.GONE);
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 3:
                member4.setVisibility(View.GONE);
                member5.setVisibility(View.GONE);
                member6.setVisibility(View.GONE);
                member7.setVisibility(View.GONE);
                member8.setVisibility(View.GONE);
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 4:
                member5.setVisibility(View.GONE);
                member6.setVisibility(View.GONE);
                member7.setVisibility(View.GONE);
                member8.setVisibility(View.GONE);
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 5:
                member6.setVisibility(View.GONE);
                member7.setVisibility(View.GONE);
                member8.setVisibility(View.GONE);
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 6:
                member7.setVisibility(View.GONE);
                member8.setVisibility(View.GONE);
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 7:
                member8.setVisibility(View.GONE);
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 8:
                member9.setVisibility(View.GONE);
                member10.setVisibility(View.GONE);
                break;
            case 9:
                member10.setVisibility(View.GONE);
                break;
            case 10:
                break;
        }

        switch (Integer.parseInt(minmem)){
            case 2:
                member3.setHint("Member 3 (Optional)");
                member4.setHint("Member 4 (Optional)");
                member5.setHint("Member 5 (Optional)");
                member6.setHint("Member 6 (Optional)");
                member7.setHint("Member 7 (Optional)");
                member8.setHint("Member 8 (Optional)");
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 3:
                member4.setHint("Member 4 (Optional)");
                member5.setHint("Member 5 (Optional)");
                member6.setHint("Member 6 (Optional)");
                member7.setHint("Member 7 (Optional)");
                member8.setHint("Member 8 (Optional)");
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 4:
                member5.setHint("Member 5 (Optional)");
                member6.setHint("Member 6 (Optional)");
                member7.setHint("Member 7 (Optional)");
                member8.setHint("Member 8 (Optional)");
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 5:
                member6.setHint("Member 6 (Optional)");
                member7.setHint("Member 7 (Optional)");
                member8.setHint("Member 8 (Optional)");
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 6:
                member7.setHint("Member 7 (Optional)");
                member8.setHint("Member 8 (Optional)");
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 7:
                member8.setHint("Member 8 (Optional)");
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 8:
                member9.setHint("Member 9 (Optional)");
                member10.setHint("Member 10 (Optional)");
                break;
            case 9:
                member10.setHint("Member 10 (Optional)");
                break;
            case 10:
                break;
        }
    }

    public void getData(int max,int min){

        switch (max){
            case 2:
                boolean valid2 = Validate(Integer.parseInt(minmem));
                if (valid2){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 3:
                boolean valid3 = Validate(Integer.parseInt(minmem));
                if (valid3){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 4:
                boolean valid4 = Validate(Integer.parseInt(minmem));
                if (valid4){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 5:
                boolean valid5 = Validate(Integer.parseInt(minmem));
                if (valid5){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    mem5 = member5.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem5.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*5);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member5").setValue(mem5);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 6:
                boolean valid6 = Validate(Integer.parseInt(minmem));
                if (valid6){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    mem5 = member5.getText().toString();
                    mem6 = member6.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem5.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*5);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member5").setValue(mem5);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem6.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*6);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member6").setValue(mem6);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 7:
                boolean valid7 = Validate(Integer.parseInt(minmem));
                if (valid7){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    mem5 = member5.getText().toString();
                    mem6 = member6.getText().toString();
                    mem7 = member7.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem5.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*5);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member5").setValue(mem5);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem6.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*6);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member6").setValue(mem6);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem7.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*7);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member7").setValue(mem7);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 8:
                boolean valid8 = Validate(Integer.parseInt(minmem));
                if (valid8){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    mem5 = member5.getText().toString();
                    mem6 = member6.getText().toString();
                    mem7 = member7.getText().toString();
                    mem8 = member8.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem5.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*5);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member5").setValue(mem5);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem6.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*6);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member6").setValue(mem6);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem7.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*7);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member7").setValue(mem7);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem8.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*8);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member8").setValue(mem8);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 9:
                boolean valid9 = Validate(Integer.parseInt(minmem));
                if (valid9){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    mem5 = member5.getText().toString();
                    mem6 = member6.getText().toString();
                    mem7 = member7.getText().toString();
                    mem8 = member8.getText().toString();
                    mem9 = member9.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem5.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*5);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member5").setValue(mem5);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem6.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*6);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member6").setValue(mem6);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem7.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*7);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member7").setValue(mem7);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem8.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*8);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member8").setValue(mem8);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem9.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*9);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member9").setValue(mem9);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;

            case 10:
                boolean valid10 = Validate(Integer.parseInt(minmem));
                if (valid10){
                    mem1 = member1.getText().toString();
                    mem2 = member2.getText().toString();
                    mem3 = member3.getText().toString();
                    mem4 = member4.getText().toString();
                    mem5 = member5.getText().toString();
                    mem6 = member6.getText().toString();
                    mem7 = member7.getText().toString();
                    mem8 = member8.getText().toString();
                    mem9 = member9.getText().toString();
                    mem10 = member10.getText().toString();
                    if (!mem1.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt));
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("leadername").setValue(mem1);
                        dbref.child("teamid").setValue(teamid);
                        dbref.child("teamname").setValue(teamname.getText().toString());
                        dbref.child("username").setValue(username);
                        dbref.child("useremail").setValue(useremail);
                        dbref.child("usernumber").setValue(usernumber);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem2.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*2);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member2").setValue(mem2);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem3.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*3);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member3").setValue(mem3);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem4.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*4);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member4").setValue(mem4);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem5.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*5);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member5").setValue(mem5);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem6.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*6);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member6").setValue(mem6);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem7.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*7);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member7").setValue(mem7);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem8.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*8);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member8").setValue(mem8);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem9.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*9);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member9").setValue(mem9);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                    if (!mem10.equals("")){
                        String totalamt = String.valueOf(Integer.parseInt(amt)*10);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("EventRegistration").child(teamid);
                        dbref.keepSynced(true);
                        dbref.child("member10").setValue(mem10);
                        dbref.child("totalamt").setValue(totalamt);
                    }
                }
                break;
        }

    }

    public boolean Validate(int num){

        if (num == 2){

            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    return true;
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 3){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        return true;
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 4){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            return true;
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 5){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            if (!member5.getText().toString().isEmpty()){
                                return true;
                            }
                            else {
                                member5.setError("Empty");
                                member5.requestFocus();
                                return false;
                            }
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 6){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            if (!member5.getText().toString().isEmpty()){
                                if (!member6.getText().toString().isEmpty()){
                                    return true;
                                }
                                else {
                                    member6.setError("Empty");
                                    member6.requestFocus();
                                    return false;
                                }
                            }
                            else {
                                member5.setError("Empty");
                                member5.requestFocus();
                                return false;
                            }
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 7){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            if (!member5.getText().toString().isEmpty()){
                                if (!member6.getText().toString().isEmpty()){
                                    if (!member7.getText().toString().isEmpty()){
                                        return true;
                                    }
                                    else {
                                        member7.setError("Empty");
                                        member7.requestFocus();
                                        return false;
                                    }
                                }
                                else {
                                    member6.setError("Empty");
                                    member6.requestFocus();
                                    return false;
                                }
                            }
                            else {
                                member5.setError("Empty");
                                member5.requestFocus();
                                return false;
                            }
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 8){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            if (!member5.getText().toString().isEmpty()){
                                if (!member6.getText().toString().isEmpty()){
                                    if (!member7.getText().toString().isEmpty()){
                                        if (!member8.getText().toString().isEmpty()){
                                            return true;
                                        }
                                        else {
                                            member8.setError("Empty");
                                            member8.requestFocus();
                                            return false;
                                        }
                                    }
                                    else {
                                        member7.setError("Empty");
                                        member7.requestFocus();
                                        return false;
                                    }
                                }
                                else {
                                    member6.setError("Empty");
                                    member6.requestFocus();
                                    return false;
                                }
                            }
                            else {
                                member5.setError("Empty");
                                member5.requestFocus();
                                return false;
                            }
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 9){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            if (!member5.getText().toString().isEmpty()){
                                if (!member6.getText().toString().isEmpty()){
                                    if (!member7.getText().toString().isEmpty()){
                                        if (!member8.getText().toString().isEmpty()){
                                            if (!member9.getText().toString().isEmpty()){
                                                return true;
                                            }
                                            else {
                                                member9.setError("Empty");
                                                member9.requestFocus();
                                                return false;
                                            }
                                        }
                                        else {
                                            member8.setError("Empty");
                                            member8.requestFocus();
                                            return false;
                                        }
                                    }
                                    else {
                                        member7.setError("Empty");
                                        member7.requestFocus();
                                        return false;
                                    }
                                }
                                else {
                                    member6.setError("Empty");
                                    member6.requestFocus();
                                    return false;
                                }
                            }
                            else {
                                member5.setError("Empty");
                                member5.requestFocus();
                                return false;
                            }
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else if (num == 10){
            if (teamname.getText().toString().isEmpty()){
                teamname.setError("Empty");
                teamname.requestFocus();
                return false;
            }
            else if (!member1.getText().toString().isEmpty()){
                if (!member2.getText().toString().isEmpty()){
                    if (!member3.getText().toString().isEmpty()){
                        if (!member4.getText().toString().isEmpty()){
                            if (!member5.getText().toString().isEmpty()){
                                if (!member6.getText().toString().isEmpty()){
                                    if (!member7.getText().toString().isEmpty()){
                                        if (!member8.getText().toString().isEmpty()){
                                            if (!member9.getText().toString().isEmpty()){
                                                if (!member10.getText().toString().isEmpty()){
                                                    return true;
                                                }
                                                else {
                                                    member10.setError("Empty");
                                                    member10.requestFocus();
                                                    return false;
                                                }
                                            }
                                            else {
                                                member9.setError("Empty");
                                                member9.requestFocus();
                                                return false;
                                            }
                                        }
                                        else {
                                            member8.setError("Empty");
                                            member8.requestFocus();
                                            return false;
                                        }
                                    }
                                    else {
                                        member7.setError("Empty");
                                        member7.requestFocus();
                                        return false;
                                    }
                                }
                                else {
                                    member6.setError("Empty");
                                    member6.requestFocus();
                                    return false;
                                }
                            }
                            else {
                                member5.setError("Empty");
                                member5.requestFocus();
                                return false;
                            }
                        }
                        else {
                            member4.setError("Empty");
                            member4.requestFocus();
                            return false;
                        }
                    }
                    else {
                        member3.setError("Empty");
                        member3.requestFocus();
                        return false;
                    }
                }
                else {
                    member2.setError("Empty");
                    member2.requestFocus();
                    return false;
                }
            }
            else {
                member2.setError("Empty");
                member2.requestFocus();
                return false;
            }
        }

        else {
            return true;
        }
    }
}