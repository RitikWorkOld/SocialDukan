package com.social.socialdukan.Student.fragment.Internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.social.socialdukan.Student.fragment.Internship.model.internall_md;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.social.socialdukan.Student.fragment.Internship.model.internall_md;

public class ApplyIntern extends AppCompatActivity {

    TextView ques1,ques2,ques3;
    TextInputLayout answer1,answer2,answer3;
    Button fillbtn;
    String key,notiid;
    String internid;
    String companyid;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_apply_intern);
notiid = FirebaseDatabase.getInstance().getReference().child("Forms")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().getKey();
        key = getIntent().getStringExtra("key");

        ques1 = findViewById(R.id.ques_1);
        ques2 = findViewById(R.id.ques_2);
        ques3 = findViewById(R.id.ques_3);

        answer1 = (TextInputLayout) findViewById(R.id.answer_1);

        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        final Editable text1 = answer1.getEditText().getText();
        final Editable text2 = answer2.getEditText().getText();
        final Editable text3 = answer3.getEditText().getText();

        TextView title = (TextView) findViewById(R.id.title);
        final SpannableString content = new SpannableString("SocialDukan Application");
       // content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        title.setText(content);

        fillbtn = findViewById(R.id.fill_btn);

        DatabaseReference dbques = FirebaseDatabase.getInstance().getReference().child("Internships");
        dbques.keepSynced(true);
        dbques.orderByChild("id").equalTo(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    internall_md valueintern = dataSnapshot1.getValue(internall_md.class);


                    if (!valueintern.getQues1().equals( "Q2. " )){
                        count = 2;
                        ques2.setText(valueintern.getQues1());
                    }
                    if(valueintern.getQues1().equals( "Q2. " )) {
                        ques2.setVisibility(View.GONE);
                        answer2.setVisibility(View.GONE);
                    }

                    if (!valueintern.getQues2().equals( "Q3. " )){
                        count = 3;
                        ques3.setText(valueintern.getQues2());
                    }
                    if(valueintern.getQues2().equals( "Q3. " )) {
                        ques3.setVisibility(View.GONE);
                        answer3.setVisibility(View.GONE);
                    }

                    companyid = valueintern.getCompanyid();
                    internid=valueintern.getId();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 3) {
                    if (!text1.toString().isEmpty()){
                        if (!text2.toString().isEmpty()){
                            if (!text3.toString().isEmpty()){

                                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forms").child(companyid).child(notiid);
                                databaseReference.keepSynced(true);
                                databaseReference.child("id").setValue(notiid);
                                databaseReference.child("answer1").setValue(text1.toString());
                                databaseReference.child("answer2").setValue(text2.toString());
                                databaseReference.child("answer3").setValue(text3.toString());
                                databaseReference.child("internid_status").setValue(key+"Applied");
                                databaseReference.child("internid").setValue(key);
                                databaseReference.child("status").setValue("Applied");
                                databaseReference.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                databaseReference.child("companyid").setValue(companyid);
                                databaseReference.child("internshipuid").setValue(key+FirebaseAuth.getInstance().getCurrentUser().getUid());




                                final DatabaseReference databaseReferenceone = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
                                databaseReferenceone.keepSynced(true);
                                databaseReferenceone.child("answer1").setValue(text1.toString());
                                databaseReferenceone.child("answer2").setValue(text2.toString());
                                databaseReferenceone.child("answer3").setValue(text3.toString());
                                databaseReferenceone.child("internid").setValue(key);
                                databaseReferenceone.child("status").setValue("Applied");
                                databaseReferenceone.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                databaseReferenceone.child("companyid").setValue(companyid);
                                databaseReferenceone.child("internshipuid").setValue(key+FirebaseAuth.getInstance().getCurrentUser().getUid());
                              //  databaseReference.child("id").setValue(key);

                                DatabaseReference dbhelper = FirebaseDatabase.getInstance().getReference().child("Users");
                                dbhelper.keepSynced(true);
                                dbhelper.orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                            User user = dataSnapshot1.getValue(User.class);

                                            String name = user.getName();
                                            String number = user.getContactn();
                                            String imguri = user.getProfileimg();
                                            String email=user.getEmail();

                                            databaseReference.child("username").setValue(name);
                                            databaseReference.child("usernumber").setValue(number);
                                            databaseReference.child("userimg").setValue(imguri);
                                            databaseReference.child("useremail").setValue(email);

                                            databaseReferenceone.child("username").setValue(name);
                                            databaseReferenceone.child("usernumber").setValue(number);
                                            databaseReferenceone.child("userimg").setValue(imguri);
                                            databaseReferenceone.child("useremail").setValue(email);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                Toast.makeText(ApplyIntern.this,"Done",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            else {
                                Toast.makeText(ApplyIntern.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(ApplyIntern.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ApplyIntern.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (count == 2){
                    if (!text1.toString().isEmpty()){
                        if (!text2.toString().isEmpty()){

                            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forms").child(companyid).child(notiid);
                            databaseReference.keepSynced(true);
                            databaseReference.child("id").setValue(notiid);
                            databaseReference.child("answer1").setValue(text1.toString());
                            databaseReference.child("answer2").setValue(text2.toString());
                            databaseReference.child("answer3").setValue("QNP");
                            databaseReference.child("internid_status").setValue(key+"Applied");
                            databaseReference.child("internid").setValue(key);
                            databaseReference.child("status").setValue("Applied");
                            databaseReference.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("companyid").setValue(companyid);
                            databaseReference.child("internshipuid").setValue(key+FirebaseAuth.getInstance().getCurrentUser().getUid());
                            //databaseReference.child("id").setValue(notiid);

                            final DatabaseReference databaseReferencetwo = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
                            databaseReferencetwo.keepSynced(true);
                            databaseReferencetwo.child("answer1").setValue(text1.toString());
                            databaseReferencetwo.child("answer2").setValue(text2.toString());
                            databaseReferencetwo.child("answer3").setValue("QNP");
                            databaseReferencetwo.child("internid").setValue(key);
                            databaseReferencetwo.child("status").setValue("Applied");
                            databaseReferencetwo.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReferencetwo.child("companyid").setValue(companyid);
                            databaseReferencetwo.child("internshipuid").setValue(key+FirebaseAuth.getInstance().getCurrentUser().getUid());
                           // databaseReferencetwo.child("id").setValue(key);


                            DatabaseReference dbhelper = FirebaseDatabase.getInstance().getReference().child("Users");
                            dbhelper.keepSynced(true);
                            dbhelper.orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                        User user = dataSnapshot1.getValue(User.class);

                                        String name = user.getName();
                                        String number = user.getContactn();
                                        String imguri = user.getProfileimg();

                                        databaseReference.child("username").setValue(name);
                                        databaseReference.child("usernumber").setValue(number);
                                        databaseReference.child("userimg").setValue(imguri);

                                        databaseReferencetwo.child("username").setValue(name);
                                        databaseReferencetwo.child("usernumber").setValue(number);
                                        databaseReferencetwo.child("userimg").setValue(imguri);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            Toast.makeText(ApplyIntern.this,"Done",Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else {
                            Toast.makeText(ApplyIntern.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ApplyIntern.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (count == 1){
                    if (!text1.toString().isEmpty()){

                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forms").child(companyid).child(notiid);
                        databaseReference.keepSynced(true);
                        databaseReference.child("id").setValue(notiid);
                        databaseReference.child("answer1").setValue(text1.toString());
                        databaseReference.child("answer2").setValue("QNP");
                        databaseReference.child("answer3").setValue("QNP");
                        databaseReference.child("internid").setValue(key);
                        databaseReference.child("status").setValue("Applied");
                        databaseReference.child("internid_status").setValue(key+"Applied");
                        databaseReference.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReference.child("companyid").setValue(companyid);
                        databaseReference.child("internshipuid").setValue(key+FirebaseAuth.getInstance().getCurrentUser().getUid());
                       // databaseReference.child("id").setValue(notiid);

                        final DatabaseReference databaseReferencethree = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
                        databaseReferencethree.keepSynced(true);
                        databaseReferencethree.child("answer1").setValue(text1.toString());
                        databaseReferencethree.child("answer2").setValue("QNP");
                        databaseReferencethree.child("answer3").setValue("QNP");
                        databaseReferencethree.child("internid").setValue(key);
                        databaseReferencethree.child("status").setValue("Applied");
                        databaseReferencethree.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReferencethree.child("companyid").setValue(companyid);
                        databaseReferencethree.child("internshipuid").setValue(key+FirebaseAuth.getInstance().getCurrentUser().getUid());
                     //   databaseReferencethree.child("id").setValue(key);


                        DatabaseReference dbhelper = FirebaseDatabase.getInstance().getReference().child("Users");
                        dbhelper.keepSynced(true);
                        dbhelper.orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                    User user = dataSnapshot1.getValue(User.class);

                                    String name = user.getName();
                                    String number = user.getContactn();
                                    String imguri = user.getProfileimg();
                                    String useremail=user.getEmail();

                                    databaseReference.child("username").setValue(name);
                                    databaseReference.child("usernumber").setValue(number);
                                    databaseReference.child("userimg").setValue(imguri);
                                    databaseReference.child("useremail").setValue(useremail);

                                    databaseReferencethree.child("username").setValue(name);
                                    databaseReferencethree.child("usernumber").setValue(number);
                                    databaseReferencethree.child("userimg").setValue(imguri);
                                    databaseReferencethree.child("useremail").setValue(useremail);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Toast.makeText(ApplyIntern.this,"Done",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else {
                        Toast.makeText(ApplyIntern.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
