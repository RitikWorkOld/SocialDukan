package com.example.socialdukan.Student.fragment.other_services;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.socialdukan.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Card5_Form extends AppCompatActivity {
    TextInputLayout city1,head_name1,head_cont,head_email,no_piecces,others;
CardView card1,card2,card3,card4,card5,card6;
TextView text1,text2,text3,text4,text5,text6;
EditText type;
TextInputEditText other_text;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card5__form );


type=findViewById( R.id.type );
        city1=findViewById( R.id.city );
       no_piecces=findViewById( R.id.no_pieces );
        head_cont=findViewById( R.id.head_cont );
        head_name1=findViewById( R.id.head_name );
        head_email=findViewById( R.id.head_email );
        others=findViewById( R.id.others );
        other_text=findViewById( R.id.other_text );

card1=findViewById( R.id.card_main );
        card2=findViewById( R.id.card_main2 );
        card3=findViewById( R.id.card_main3 );
        card4=findViewById( R.id.card_main5 );
        card5=findViewById( R.id.card_main4 );
        card6=findViewById( R.id.card_main6 );

        text1=findViewById( R.id.card1_text );
        text2=findViewById( R.id.card2_text );
        text3=findViewById( R.id.card3_text );
        text4=findViewById( R.id.card4_text );
        text5=findViewById( R.id.card5_text );
        text6=findViewById( R.id.card6_text );

        final Editable other_1=others.getEditText().getText();
        final Editable city = city1.getEditText().getText();
        final Editable pieces1 = no_piecces.getEditText().getText();
        final Editable headName1 = head_name1.getEditText().getText();
        final Editable headCont1 = head_cont.getEditText().getText();
        final Editable headEmail1 = head_email.getEditText().getText();


        btn=findViewById( R.id.submit_btn );
card1.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        others.setVisibility( View.GONE );
       card1.setCardBackgroundColor( getResources().getColor( R.color.purple ) );
       text1.setTextColor( getResources().getColor( R.color.white ) );
type.setText("T-Shirt");
other_text.setText( "NULL" );


        card2.setCardBackgroundColor( getResources().getColor( R.color.white ) );
        text2.setTextColor( getResources().getColor( R.color.gray ) );

        card3.setCardBackgroundColor( getResources().getColor( R.color.white ) );
        text3.setTextColor( getResources().getColor( R.color.gray ) );

        card4.setCardBackgroundColor( getResources().getColor( R.color.white ) );
        text4.setTextColor( getResources().getColor( R.color.gray ) );

        card5.setCardBackgroundColor( getResources().getColor( R.color.white ) );
        text5.setTextColor( getResources().getColor( R.color.gray ) );

        card6.setCardBackgroundColor( getResources().getColor( R.color.white ) );
        text6.setTextColor( getResources().getColor( R.color.gray ) );
    }
} );
        card2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card2.setCardBackgroundColor( getResources().getColor( R.color.purple ) );
                text2.setTextColor( getResources().getColor( R.color.white ) );
                others.setVisibility( View.GONE );
                type.setText("Hoodies");
                card1.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text1.setTextColor( getResources().getColor( R.color.gray ) );
                other_text.setText( "NULL" );
                card3.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text3.setTextColor( getResources().getColor( R.color.gray ) );

                card4.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text4.setTextColor( getResources().getColor( R.color.gray ) );

                card5.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text5.setTextColor( getResources().getColor( R.color.gray ) );

                card6.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text6.setTextColor( getResources().getColor( R.color.gray ) );
            }
        } );
        card3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card2.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text2.setTextColor( getResources().getColor( R.color.gray ) );
                others.setVisibility( View.GONE );
                type.setText("Diaries");
                other_text.setText( "NULL" );
                card1.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text1.setTextColor( getResources().getColor( R.color.gray ) );

                card3.setCardBackgroundColor( getResources().getColor( R.color.purple ) );
                text3.setTextColor( getResources().getColor( R.color.white ) );

                card4.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text4.setTextColor( getResources().getColor( R.color.gray ) );

                card5.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text5.setTextColor( getResources().getColor( R.color.gray ) );

                card6.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text6.setTextColor( getResources().getColor( R.color.gray ) );
            }
        } );
        card4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card2.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text2.setTextColor( getResources().getColor( R.color.gray ) );
                others.setVisibility( View.GONE );
                type.setText("Badges");
                other_text.setText( "NULL" );
                card1.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text1.setTextColor( getResources().getColor( R.color.gray ) );

                card3.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text3.setTextColor( getResources().getColor( R.color.gray ) );

                card4.setCardBackgroundColor( getResources().getColor( R.color.purple ) );
                text4.setTextColor( getResources().getColor( R.color.white ) );

                card5.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text5.setTextColor( getResources().getColor( R.color.gray ) );

                card6.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text6.setTextColor( getResources().getColor( R.color.gray ) );
            }
        } );
        card5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card2.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text2.setTextColor( getResources().getColor( R.color.gray ) );
                type.setText("Pens");
                other_text.setText( "NULL" );
                others.setVisibility( View.GONE );
                card1.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text1.setTextColor( getResources().getColor( R.color.gray ) );

                card3.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text3.setTextColor( getResources().getColor( R.color.gray ) );

                card4.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text4.setTextColor( getResources().getColor( R.color.gray ) );

                card5.setCardBackgroundColor( getResources().getColor( R.color.purple ) );
                text5.setTextColor( getResources().getColor( R.color.white ) );

                card6.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text6.setTextColor( getResources().getColor( R.color.gray ) );
            }
        } );
        card6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card2.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text2.setTextColor( getResources().getColor( R.color.gray ) );
                others.setVisibility( View.VISIBLE );
                type.setText("Others");
                other_text.setText( "" );

                card1.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text1.setTextColor( getResources().getColor( R.color.gray ) );

                card3.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text3.setTextColor( getResources().getColor( R.color.gray ) );

                card4.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text4.setTextColor( getResources().getColor( R.color.gray ) );

                card5.setCardBackgroundColor( getResources().getColor( R.color.white ) );
                text5.setTextColor( getResources().getColor( R.color.gray ) );

                card6.setCardBackgroundColor( getResources().getColor( R.color.purple ) );
                text6.setTextColor( getResources().getColor( R.color.white ) );
            }
        } );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                            if (!headName1.toString().isEmpty()) {
                                if (!headCont1.toString().isEmpty()) {
                                    if (!headEmail1.toString().isEmpty()) {
                                        if (!city.toString().isEmpty()) {
                                            if (!pieces1.toString().isEmpty()) {
                                                if (!other_text.getText().toString().isEmpty()) {
                                                    if (!type.getText().toString().isEmpty()) {


                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "OtherServiceCard5" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
                                                        databaseReference.keepSynced( true );

                                                        databaseReference.child( "city" ).setValue( city.toString() );
                                                        databaseReference.child( "pieces" ).setValue( pieces1.toString() );
                                                        databaseReference.child( "headname" ).setValue( headName1.toString() );
                                                        databaseReference.child( "headcont" ).setValue( headCont1.toString() );
                                                        databaseReference.child( "heademail" ).setValue( headEmail1.toString() );
                                                        databaseReference.child( "others" ).setValue( other_1.toString() );
                                                        databaseReference.child( "type" ).setValue( type.getText().toString() );
                                                        databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );


                                                        Toast.makeText( Card5_Form.this, "Done", Toast.LENGTH_SHORT ).show();

                                                        Intent intent = new Intent( Card5_Form.this, Thanks_Activity.class );
                                                        startActivity( intent );

                                                    }
                                                    else {
                                                        Toast.makeText( Card5_Form.this, "Please fill all the answers", Toast.LENGTH_SHORT ).show();
                                                    }
                                                }
                                                else {
                                                    Toast.makeText( Card5_Form.this, "Please fill all the answers", Toast.LENGTH_SHORT ).show();
                                                }
                                            }
                                            else {
                                                Toast.makeText(Card5_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else {
                                            Toast.makeText(Card5_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(Card5_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Card5_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {
                                Toast.makeText(Card5_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                            }


            }
        });
    }


}
