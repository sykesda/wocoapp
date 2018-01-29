package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import static android.Manifest.permission.CALL_PHONE;

public class directoryExtraDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_extra_details);

        Bundle getContact = getIntent().getExtras();

        HashMap givenToMe = (HashMap) getContact.getSerializable("contact");

        if(givenToMe != null) {
            // Only work on here, else we'll generate possible crashes

            String firstName = (String) givenToMe.get("first");
            String middleName = (String) givenToMe.get("middle");
            String lastName = (String) givenToMe.get("last");
            String phone = "";

            if(givenToMe.containsKey("phone")) {
                phone = (String) givenToMe.get("phone");
            }

            String preferred = (String) givenToMe.get("preferred");
            String suffix = (String) givenToMe.get("suffix");
            final String email = (String) givenToMe.get("email");

            // TextView heyThere = (TextView) findViewById(R.id.infoTest);

            String startWith;

            if(preferred.equals("")) {
                startWith = firstName;
            } else {
                startWith = preferred;
            }

            String hold = suffix + " " + startWith + " " + middleName + " " + lastName;

            TextView wholeName = (TextView) findViewById(R.id.theNameYay);
            TextView emailAddrView = (TextView) findViewById(R.id.theEmailAddr);

            final Button callButton = (Button) findViewById(R.id.callButton);
            Button emailButton = (Button) findViewById(R.id.emailButton);

            wholeName.setText(hold);
            emailAddrView.setText(email);

            LinearLayout heyWorld = (LinearLayout) findViewById(R.id.wholePhoneField);

            if(!phone.equals("")) {
                // We have no phone number
                heyWorld.setVisibility(View.VISIBLE);
                callButton.setVisibility(View.VISIBLE);

                TextView phoneNumberTxtView = (TextView) findViewById(R.id.thePhoneNumber);
                phoneNumberTxtView.setText(phone);

                final String phoneNo = phone;

                callButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent callingIntent = new Intent(Intent.ACTION_DIAL);
                        callingIntent.setData(Uri.parse("tel:" + phoneNo));
                        startActivity(callingIntent);
                    }
                });

            } else {
                heyWorld.setVisibility(View.INVISIBLE);
                callButton.setVisibility(View.INVISIBLE);
            }

            emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    startActivity(emailIntent);
                }
            });



        }

    }
}
