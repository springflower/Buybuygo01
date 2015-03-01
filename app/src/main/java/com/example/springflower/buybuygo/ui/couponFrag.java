package com.example.springflower.buybuygo.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.springflower.buybuygo.ImageAdapter;
import com.example.springflower.buybuygo.MainActivity;
import com.example.springflower.buybuygo.R;
import com.example.springflower.buybuygo.RefreshableView;
import com.example.springflower.buybuygo.SpecialAdapter;
import com.example.springflower.buybuygo.core.MyDB;

public class couponFrag extends Fragment {

    private final double EARTH_RADIUS = 6378137.0;

    public MyDB db = null;
    private ListView listView;
    public static int[] images = new int[]{R.drawable.akfc,R.drawable.amc,R.drawable.a85,R.drawable.acosmed, R.drawable.aal, R.drawable.aal2};
    public static int selectPos=0;

    private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
        double radLat1 = (lat_a * Math.PI / 180.0);
        double radLat2 = (lat_b * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public couponFrag() {
    }


    public void findviews(){
        ImageAdapter aa=new ImageAdapter(getActivity());
        listView.setAdapter(aa);
    }

    private View v;

    RefreshableView refreshableView;
    String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new MyDB(getActivity());
        v = inflater.inflate(R.layout.refreshlvmain, container, false);
        refreshableView = (RefreshableView) v.findViewById(R.id.refreshable_view);
        listView = (ListView)v.findViewById(R.id.list_view_refresh);
        findviews();
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                Intent intent = new Intent();
//                switch(position){
//                    case 0:
//                        intent.setClass(getActivity(), webView.class);
//                        break;
//                    default:
//                        intent.setClass(getActivity(), webView.class);
//                        break;
//                }
//                startActivity(intent);
            }

        });
        return v;
    }
}
