package uitcourse.j11.nt118.appmusichtcl.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import uitcourse.j11.nt118.appmusichtcl.Adapter.MainViewPagerAdapter;
import uitcourse.j11.nt118.appmusichtcl.Adapter.OfflineViewPagerAdapter;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Allbums;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Allsongs;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Artists;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Downloads;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Offline;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Playlists;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Tim_Kiem;
import uitcourse.j11.nt118.appmusichtcl.Fragment.Fragment_Trang_Chu;
import uitcourse.j11.nt118.appmusichtcl.Model.Baihat;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.R;

public class OfflineActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Integer vitri;
    //ArrayList<AudioMod el> listnhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        anhxa();
        init();
        GetDataFromIntent();
        Log.d("Test Intent",String.valueOf(vitri));
        tabLayout.getTabAt(vitri).select();
        toolbar = (Toolbar) findViewById(R.id.toolbaroffline);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
    }



    private void init()
    {
        OfflineViewPagerAdapter offlineViewPagerAdapter = new OfflineViewPagerAdapter(getSupportFragmentManager());
        offlineViewPagerAdapter.addFragment(new Fragment_Allsongs(),"All songs");
        offlineViewPagerAdapter.addFragment(new Fragment_Playlists(),"Playlists");
        //offlineViewPagerAdapter.addFragment(new Fragment_Downloads(),"Downloads");
        offlineViewPagerAdapter.addFragment(new Fragment_Artists(),"Artists");
        offlineViewPagerAdapter.addFragment(new Fragment_Allbums(),"Allbums");


        viewPager.setAdapter(offlineViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconallsong);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconplaylist);
        //tabLayout.getTabAt(2).setIcon(R.drawable.icondownloadforder);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconartistoffline);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconalbumoffline);
    }

    private void anhxa()
    {
        tabLayout = findViewById(R.id.tabsoffline);
        viewPager = findViewById(R.id.viewpageroffline);
    }

    private void GetDataFromIntent() {

        Intent intent = getIntent();

        if(intent!=null)
        {

            if(intent.hasExtra("vitri")){
                vitri = intent.getIntExtra("vitri",0);
                //Baihat baihat = intent.getParcelableExtra("cakhuc");
                //Toast.makeText(this, vitri, Toast.LENGTH_SHORT).show();
            }
            /*
            if(intent.hasExtra("danhsachbaihattrongthietbi"))
            {
                listnhac = (ArrayList<AudioModel>) intent.getSerializableExtra("danhsachbaihattrongthietbi");
            }*/


        }
    }

}
