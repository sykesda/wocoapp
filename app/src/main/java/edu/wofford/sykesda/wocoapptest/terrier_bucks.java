package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.internal.IMapFragmentDelegate;

public class terrier_bucks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrier_bucks);


        final ImageButton openW = findViewById(R.id.wBtn);
        openW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openW();
            }
        });

        final ImageButton openBucks = findViewById(R.id.bucksBtn);
        openBucks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBucks();
            }
        });

        final  ImageButton openSchedule = findViewById(R.id.scheduleBtn);
        openSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSchedule();
            }
        });
//
        final ImageButton openExam = findViewById(R.id.examBtn);
        openExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExam();
            }
        });
    }

    protected void openW(){
        visitSite("https://connect.wofford.edu/myWofford/registrar/wnumber.aspx");
    }
//
    protected void openBucks(){
        visitSite("https://get.cbord.com/wofford/full/login.php");
    }

    protected void openSchedule(){
        visitSite("http://my.wofford.edu/cp/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=/cp/school/schedule");
    }
//
    protected void openExam(){
        visitSite("https://connect.wofford.edu/myWofford/registrar/myExamSchedule.aspx?ticket=ST-2283-7SvCMNaG6pfg6xCIYXmc");
    }

    private  void  visitSite(String url){
        Intent siteIntent = new Intent(Intent.ACTION_VIEW);
        siteIntent.setData(Uri.parse(url));
        startActivity(siteIntent);
    }
}