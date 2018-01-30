package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

import static android.Manifest.permission.CALL_PHONE;

public class directoryExtraDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_extra_details);

        Bundle getContact = getIntent().getExtras();

        final HashMap givenToMe = (HashMap) getContact.getSerializable("contact");

        if(givenToMe != null) {

            String hold = "";

            // This makes sure we have the prefix, if there.
            if(givenToMe.containsKey("prefix") && !givenToMe.get("prefix").equals("NULL")) {
                hold = hold.concat(givenToMe.get("prefix").toString() + " ");
            }

            // This ensures we have the right name/preferred
            if(!givenToMe.get("preferred").toString().equals("")) {
                hold = hold.concat(givenToMe.get("preferred").toString()) + " ";
            } else  {
                hold = hold.concat(givenToMe.get("first").toString()) + " ";
            }

            // We add middle, if there
            if(!givenToMe.get("middle").toString().equals("NULL")) {
                hold = hold.concat(givenToMe.get("middle").toString()) + " ";
            }

            // We add last name
            hold = hold.concat(givenToMe.get("last").toString()) + " ";

            // Now we deal with the suffix
            if(!givenToMe.get("suffix").toString().equals("") && !givenToMe.get("suffix").toString().equals("NULL")) {
                hold = hold.concat(givenToMe.get("suffix").toString());
            }

            String email = givenToMe.get("email").toString();

            TextView theName = findViewById(R.id.theFullNameTxtView);
            TextView theEmail = findViewById(R.id.theEmailAddrTxtView);

            // We fix name and email
            theName.setText(hold);
            theEmail.setText(email);

            // Now we process the other fields.
            // TODO: Make sure to update the visibility of the button
            TableRow phoneRow = findViewById(R.id.phoneNumberRow);
            Button phoneBtn = findViewById(R.id.startCallBtn);
            if(givenToMe.containsKey("phone1")) {
                // This is a staff member, there's a number.
                TextView phoneNumberView = findViewById(R.id.thePhoneNumberTxtView);
                phoneNumberView.setText(givenToMe.get("phone1").toString());
            } else {
                // This is probably a student, no phone number. Won't display
                phoneRow.setVisibility(View.INVISIBLE);
                phoneBtn.setVisibility(View.INVISIBLE);
            }


            TableRow cpoRow = findViewById(R.id.theCPORow);
            if(!givenToMe.get("cpo").toString().equals("NULL")) {
                TextView cpoTxtView = findViewById(R.id.theCPOTxtView);
                cpoTxtView.setText(givenToMe.get("cpo").toString());
            } else {
                // For whatever reason this contact doesn't have a cpo box
                cpoRow.setVisibility(View.INVISIBLE);
            }

            TableRow departmentRow = findViewById(R.id.theDepartmentRow);
            if(givenToMe.containsKey("department1") && !givenToMe.get("department1").toString().equals("NULL")) {
                TextView theDepartmentView = findViewById(R.id.theDepartmentTxtView);
                theDepartmentView.setText(givenToMe.get("department1").toString());
            } else {
                departmentRow.setVisibility(View.INVISIBLE);
            }

            TableRow officeRow = findViewById(R.id.theOfficeRow);
            if(givenToMe.containsKey("office") && !givenToMe.get("office").equals("NULL")) {
                TextView theOfficeView = findViewById(R.id.theOfficeTxtView);
                theOfficeView.setText(givenToMe.get("office").toString());
            } else {
                officeRow.setVisibility(View.INVISIBLE);
            }

            TableRow websiteRow = findViewById(R.id.theWebsiteRow);
            Button websiteBtn = findViewById(R.id.openWebsiteBtn);
            if(givenToMe.containsKey("personalWeb") && !givenToMe.get("personalWeb").toString().equals("NULL")) {
                TextView websiteTxt = findViewById(R.id.theWebsiteTxtView);
                websiteTxt.setText(givenToMe.get("personalWeb").toString());
            } else {
                websiteRow.setVisibility(View.INVISIBLE);
                websiteBtn.setVisibility(View.INVISIBLE);
            }

            phoneBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent callingIntent = new Intent(Intent.ACTION_DIAL);
                    callingIntent.setData(Uri.parse("tel:" + givenToMe.get("phone1").toString()));
                    startActivity(callingIntent);
                }
            });

            Button emailBtn = findViewById(R.id.sendEmailBtn);
            emailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{givenToMe.get("email").toString()});
                    startActivity(emailIntent);
                }
            });

            phoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uriUrl = Uri.parse("http://" + givenToMe.get("personalWeb").toString());
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(openBrowser);
                }
            });

        }

    }
}
