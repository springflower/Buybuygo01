package com.example.springflower.buybuygo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.springflower.buybuygo.core.MyDB;
import com.example.springflower.buybuygo.ui.couponFrag;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity{
//    //SQL連接
//    private String UserName = "sa";
//    private String Password = "abcd310704";
//    public String temp;
//    private ArrayList<News> newses;
    public double globelDistance =0;
    public double dLat=0;
    public double dLng=0;
    private final double EARTH_RADIUS = 6378137.0;
    public MyDB db = null;
    public List<Integer> joinedNews = new ArrayList<Integer>();








    //ACTION BAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.action_new:
                openEdit();
                break;
            default:
                break;
                //return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    public void openEdit()
    {
        //Toast.makeText(this, "按了 Edit 鈕", Toast.LENGTH_LONG).show();
        Intent intent0 = new Intent();
        intent0.setClass(this, addNewsActivity.class);
        //intent0.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
//        bundle.putString("title", title);
//        bundle.putString("content", content);
        //getLoc();
        bundle.putDouble("myLat", dLat);
        bundle.putDouble("myLng", dLng);
        intent0.putExtras(bundle);
        startActivity(intent0);
    }



    //滑動分頁
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    private Location getLocation(Context context) {
        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null)
            location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        return location;
    }
    public void getLoc(){
        Location mLocation = getLocation(this);
        dLat = mLocation.getLatitude();//取得現在所在的緯度
        dLng = mLocation.getLongitude();//取得現在所在的經度
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDB(this);			//建立DB物件
        getLoc();

        joinedNews.add(1);
        joinedNews.add(3);
        joinedNews.add(5);
        joinedNews.add(7);
        joinedNews.add(6);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        @Override
//        public Fragment getItem(final int position) {
//            final Fragment result;
//            switch (position) {
//                case 0:
//                    result = new NewsListFragment();
//                    break;
//                case 1:
//                    result = new UserListFragment();
//                    break;
//                case 2:
//                    result = new CheckInsListFragment();
//                    break;
//                default:
//                    result = null;
//                    break;
//            }
//            if (result != null) {
//                result.setArguments(new Bundle()); //TODO do we need this?
//            }
//            return result;
//        }


//
//        public Fragment getItem(int i) {
//            Fragment fragment = new DummySectionFragment();
//            Bundle args = new Bundle();
//            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
//            fragment.setArguments(args);
//            return fragment;
//        }
        public Fragment getItem(int position) {
            Fragment fragment;
            //Bundle args = new Bundle();

            //args.putInt(newsFragment.ARG_SECTION_NUMBER, i + 1);
            //fragment.setArguments(args);
            switch (position){
                case 0:
                    fragment = new couponFrag();
                    break;
                case 1:
                    fragment = new newsFragment();
                    break;
                case 2:
                    fragment = new news2Fragment();
                    break;
                default:
                    fragment = null;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 1: return getString(R.string.title_section1).toUpperCase();
                case 2: return getString(R.string.title_section2).toUpperCase();
                case 0: return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
    }

    /**
     * 事件頁面
     */
    public class newsFragment extends Fragment {
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

        public newsFragment() {
        }
        //SQL連接
        private ListView listView;

        Cursor cursor;
        long myid;
        int contextPos=-1;

        public void findviews(){
            db.open();
            db.append("淘寶番薯","as title1", 24.551248, 120.814456, 2, "選項1", "選項2", "springflower" ,
                    gps2m(23.551248,120.814456,dLat,dLng),"0922555666");
            db.append("鐵鍬","as title2", 24.551230, 120.814422, 2, "選項1", "選項2", "david",
                    gps2m(24.551230,120.814422,dLat,dLng), "0924666888");
            db.append("蛋捲","as title3", 24.551244, 120.814458, 2, "選項1", "選項2", "springflower",
                    gps2m(24.551244,120.814458,dLat,dLng), "0955666777");
            db.append("你的媽媽","as title4", 24.551230, 120.814440, 2, "選項1", "選項2", "david",
                    gps2m(24.551230,120.814440,dLat,dLng), "0912345678");
            db.append("高級衛生紙","as title5", 24.551240, 120.814435, 2, "選項1", "選項2", "springflower",
                    gps2m(24.551240,120.814435,dLat,dLng), "0933445667");
            db.append("CI甲","as title6", 24.551255, 120.814456, 2, "選項1", "選項2", "david",
                    gps2m(24.551255,120.814456,dLat,dLng), "0966555444");

            db.append("好吃奶凍卷","http://ppt.cc/niUa\n\n總之就是好吃\n運費350\n超過20人有九折",
                    24.545478, 120.812335, 2, "草莓", "巧克力", "springflower",
                    gps2m(24.545478,120.812335,dLat,dLng), "0934086172");
            db.append2("springflower", "0912345678");
            cursor = db.getALL();			//載入全部資料
            UpdateAdapter(cursor);			//載入資料表到ListView
            //UpdateAdapter2();
        }
        @SuppressLint("NewApi")
        private void UpdateAdapter(Cursor cursor) {
            if (cursor != null && cursor.getCount() >=0 ){
                SpecialAdapter adapter = new SpecialAdapter(getActivity(),
                        R.layout.testlistview_row,
                        cursor,
                        new String[] {"title", "creatby", "distance"},
                        new int[] { android.R.id.text1, android.R.id.text2},
                        0);
                listView.setAdapter(adapter);
            }

        }

//        @SuppressLint("NewApi")
//        private void UpdateAdapter2() {
//            TextX3Adapter adapter = new TextX3Adapter(this.getActivity());
//                listView.setAdapter(adapter);
//            }
//
//        }
//    private class TextX3Adapter extends BaseAdapter {
//        private LayoutInflater layoutInflater;
//        public TextX3Adapter(Context context) {
//
//            layoutInflater = (LayoutInflater) context
//                    .getSystemService(LAYOUT_INFLATER_SERVICE);
//        }
//        @Override
//        public int getCount() {
//            return memberlist.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        // 每一列的View
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = layoutInflater.inflate(R.layout.listviewcontent,
//                        null);
//            }
//            Member member = memberlist.get(position);
//            ImageView imageView = (ImageView) convertView
//                    .findViewById(R.id.imageView);
//            imageView.setImageResource(member.getImageb());
//            TextView textView = (TextView) convertView
//                    .findViewById(R.id.tV_name);
//            textView.setText(member.getName());
//            TextView textView2 = (TextView) convertView
//                    .findViewById(R.id.tV_price);
//            textView2.setText((member).getPrice());
//
//            // imageView監聽
//            imageView.setOnClickListener(new inStringOnClickListener(member
//                    .getImageb()) {
//                @Override
//                public void onClick(View v) {
//                    ImageView i = new ImageView(ListViewActivity.this);
//                    i.setImageResource(inimage);
////					showToast(i);
//                    Toast toastview = new Toast(ListViewActivity.this);
//                    toastview .setView(i);
//                    toastview .setDuration(Toast.LENGTH_SHORT);
//                    toastview .setGravity(Gravity.CENTER, 0, 0);
//                    toastview .show();
//                }
//            });
//            // textView (商品名稱) 監聽
//            textView.setOnClickListener(new inStringOnClickListener(member
//                    .getName()) {
//                @Override
//                public void onClick(View v) {
////					Toast.makeText(ListViewActivity.this, "商品名稱：" + instring,
////							Toast.LENGTH_SHORT).show();
//                    showToast("商品名稱：" + instring);
//                }
//            });
//            // textView2 (商品價錢) 監聽
//            textView2.setOnClickListener(new inStringOnClickListener(member
//                    .getPrice()) {
//                @Override
//                public void onClick(View v) {
////					Toast.makeText(ListViewActivity.this, "商品價格：" + instring,
////							Toast.LENGTH_SHORT).show();
//                    showToast("商品價格：" + instring);
//                }
//            });
//            return convertView;
//        }
//    }



        //private ListView listView;
        private View v,k;

        RefreshableView refreshableView;
        String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            /////SQL
//            List postsList = null;
//            try {
//                postsList = new News().execute().get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }


            v = inflater.inflate(R.layout.refreshlvmain, container, false);
            refreshableView = (RefreshableView) v.findViewById(R.id.refreshable_view);
            listView = (ListView)v.findViewById(R.id.list_view_refresh);
            final String[] arr = new String[]{
                    "喔喔喔喔","你妳你你","阿忒忒凹","我我我我我","喔嘿嘿喔黑","踢踢踢踢你","G", "H", "I", "J", "K", "L"
            };
            final String[] arr2 = new String[]{
                    "springflower9999", "springflower9998", "springflower9997",
                    "springflower9996", "srpignflower9995", "springflower9994",
                    "G", "H", "I", "J", "K", "L"
            };
            //arr[0]=temp;
            //postsList.add(1, "123");
//            SpecialAdapter<String> adapter =
//                    new SpecialAdapter<String>(getActivity(),
//                            android.R.layout.simple_list_item_1,arr);
//            listView.setAdapter(adapter);
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




//            SpecialAdapter<String> adapter =
//                    new SpecialAdapter<String>(getActivity(),
//                            android.R.layout.simple_list_item_1,arr);
//            listView.setAdapter(adapter);
//            refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    refreshableView.finishRefreshing();
//                }
//            }, 0);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getActivity(), "你按下" + arr[position], Toast.LENGTH_SHORT).show();
                }

            });
            return v;
        }
    }



    ///////////////////////MAPS//////////////////////////////////
    public class mapFragment extends Fragment {
        public mapFragment() {
        }
        MapView mapView;
        Cursor cursor;
        GoogleMap map;
        private Marker markerMe;
        private Marker mark2;
        private Marker mark3;
        private Marker mark4;
        private Marker mark5;
        private Marker mark6;
        private Marker mark7;
        private Marker mark8;
        private Marker mark9;
        private Marker mark10;


        UiSettings uiSettings;
//        private void setUpMap(){
//            map.setTrafficEnabled(true);
//            map.setMyLocationEnabled(true);
//            uiSettings = map.getUiSettings();
//
//        }
//        private void initMap(){
//
//            if(mapView == null){
//                map= mapView.getMap();
//            }
//            if(mapView != null){
//                setUpMap();
//            }
//        }

//private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
//    double radLat1 = (lat_a * Math.PI / 180.0);
//    double radLat2 = (lat_b * Math.PI / 180.0);
//    double a = radLat1 - radLat2;
//    double b = (lng_a - lng_b) * Math.PI / 180.0;
//    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
//            + Math.cos(radLat1) * Math.cos(radLat2)
//            * Math.pow(Math.sin(b / 2), 2)));
//    s = s * EARTH_RADIUS;
//    s = Math.round(s * 10000) / 10000;
//    return s;
//}


        private double dLat = 0;
        private double dLng = 0;
        //private GeoPoint geoPoint;
        private String mapLable = "";
        public Context context;

        private Location getLocation(Context context) {
            LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null)
                location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            return location;
        }
        private void showMarkerMe(double lat, double lng){
            if (markerMe != null) {
                markerMe.remove();
            }

            MarkerOptions markerOpt = new MarkerOptions();
            markerOpt.position(new LatLng(lat, lng));
            markerOpt.title("我在這裡");
            markerMe = map.addMarker(markerOpt);


            //Toast.makeText(this, "lat:" + lat + ",lng:" + lng, Toast.LENGTH_SHORT).show();
        }

        private void showMarkerMe2(double lat, double lng){
            if (markerMe != null) {
                markerMe.remove();
            }

            MarkerOptions markerOpt = new MarkerOptions();
            markerOpt.position(new LatLng(lat, lng));
            markerOpt.title("我在這裡");
            markerMe = map.addMarker(markerOpt);


            //Toast.makeText(this, "lat:" + lat + ",lng:" + lng, Toast.LENGTH_SHORT).show();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.testmapfrag, container, false);
            // Gets the MapView from the XML layout and creates it
            context = getApplicationContext();
            Location mLocation = getLocation(this.context);
            dLat = mLocation.getLatitude();//取得現在所在的緯度
            dLng = mLocation.getLongitude();//取得現在所在的經度
            //db.open();
//                        db.append("淘寶番薯","as title", 24.551248, 120.814456, 2, "選項1", "選項2", "springflower" ,
//                    gps2m(23.551248,120.814456,dLat,dLng));
//            db.append("鐵鍬","as title", 24.551248, 120.814456, 2, "選項1", "選項2", "david",
//                    gps2m(24.551248,120.814456,dLat,dLng));
//            db.append("蛋捲","as title", 24.551248, 120.814456, 2, "選項1", "選項2", "springflower",
//                    gps2m(24.551248,120.814456,dLat,dLng));
//            db.append("你的媽媽","as title", 24.551248, 120.814456, 2, "選項1", "選項2", "david",
//                    gps2m(24.551248,120.814456,dLat,dLng));
//            db.append("高級衛生紙","as title", 24.551248, 120.814456, 2, "選項1", "選項2", "springflower",
//                    gps2m(24.551248,120.814456,dLat,dLng));
//            db.append("CI甲","as title", 24.551248, 120.814456, 2, "選項1", "選項2", "david",
//                    gps2m(24.551248,120.814456,dLat,dLng));



            //cursor=db.getALL();




            try {
                MapsInitializer.initialize(getActivity());
            } catch (Exception e) {
                Log.e("Address Map", "Could not initialize google play", e);
            }

            switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) )
            {
                case ConnectionResult.SUCCESS:
                    Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    mapView = (MapView) v.findViewById(R.id.mapFrag);
                    mapView.onCreate(savedInstanceState);
                    // Gets to GoogleMap from the MapView and does initialization stuff
                    if(mapView!=null)
                    {
                        map = mapView.getMap();
                        map.getUiSettings().setMyLocationButtonEnabled(true);
                        map.setMyLocationEnabled(true);
                        showMarkerMe(dLat, dLng);
                        //showMarkerMe2(cursor);
                        //showMarkerMe(dLat+0.555, dLng+0.597);
                        //map.isMyLocationEnabled();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(24.5575137, 120.8140948), 15);
                        map.animateCamera(cameraUpdate);

                        //map.setMyLocationEnabled(true);
                    }
                    break;
                case ConnectionResult.SERVICE_MISSING:
                    Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                    break;
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                    Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                    break;
                default: Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
            }




            // Updates the location and zoom of the MapView

            return v;
        }

        @Override
        public void onResume() {
            mapView.onResume();
            super.onResume();
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }
        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }
    }

    //    public static class DummySectionFragment extends Fragment {
//        public DummySectionFragment() {
//        }
//
//        public static final String ARG_SECTION_NUMBER = "section_number";
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            TextView textView = new TextView(getActivity());
//            textView.setGravity(Gravity.CENTER);
//            Bundle args = getArguments();
//            textView.setText(Integer.toString(args.getInt(ARG_SECTION_NUMBER)));
//            return textView;
//        }
//    }

//    public class DummySectionFragment extends Fragment {
//        RefreshableView refreshableView;
//        ListView listView;
//        ArrayAdapter<String> adapter;
//        String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            setContentView(R.layout.activity_main);
//            refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
//            listView = (ListView) findViewById(R.id.list_view);
//            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
//            listView.setAdapter(adapter);
//            refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    refreshableView.finishRefreshing();
//                }
//            }, 0);
//
//            //listView.setOnCreateContextMenuListener(new aa());
//        }
//
//    }









    ////////////////////////////////////////////////////////////
    public class news2Fragment extends Fragment {
        private String title="";
        private String content="";
        private String creator=null;
        private String userphone=null;
        private Double sentLat;
        private Double sentLng;
        List<String> sss= new ArrayList<String>();
        List<String> sss2= new ArrayList<String>();
        List<String> sss3= new ArrayList<String>();
        List<Double> dlat= new ArrayList<Double>();
        List<Double> dlng= new ArrayList<Double>();
        //Map<String, Object> map = new HashMap<String, Object>();
        //List<Map<String, Object>> ss1= new ArrayList<Map<String, Object>>();
        //private
        public news2Fragment() {
        }
        private ListView listView;
        Cursor cursor;

        public void findviews(){

        }
        private View v;
        RefreshableView refreshableView;
        String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.refreshlvmain, container, false);
            refreshableView = (RefreshableView) v.findViewById(R.id.refreshable_view);
            listView = (ListView)v.findViewById(R.id.list_view_refresh);
            final String[] arr = new String[]{
                    "喔喔喔喔","你妳你你","阿忒忒凹","我我我我我","喔嘿嘿喔黑","踢踢踢踢你","G", "H", "I", "J", "K", "L"
            };
            //arr[0]=temp;
            //postsList.add(1, "123");

            String[] ss= new String[10];



            int temp=0;
            for(int i:joinedNews) {
                Cursor c = db.get(i);
                sss.add(c.getString(1));
                sss2.add(c.getString(2));
                sss3.add(c.getString(10));
                dlat.add(c.getDouble(3));
                dlng.add(c.getDouble(4));
                //map.put("title", c.getString(1));
                //map.put("content", c.getString(2));
                //map.put("creator", c.getString(8));
                //ss1.add(map);
                //title=c.getString(1);
                //content=c.getString(2);
                creator= c.getString(8);
                userphone=c.getString(10);
                //temp++;
            }
            temp=0;
            ArrayAdapter adapter;
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sss);
            listView.setAdapter(adapter);
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
                    // TODO Auto-generated method stub
                    //Map m=ss1.get(position);
                    //title= (String) ss1.get(position).get("title");
                    title= sss.get(position);
                    content= sss2.get(position);
                    userphone = sss3.get(position);
                    sentLat= dlat.get(position);
                    sentLng= dlng.get(position);
                    //content= (String) ss1.get(position).get("content");
                    //title= (String) ss1.get(position).get("title");
                    Intent intent2 = new Intent();
                    //intent2.setClass(getActivity(), detailActivity.class);
                    //intent2.setClass(getActivity(), DetailsActivity.class);
                    intent2.setClass(getActivity(), newsJoinActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title);
                    bundle.putString("content", content);
                    bundle.putString("userphone", userphone);
                    bundle.putDouble("dlat", sentLat);
                    bundle.putDouble("dlng", sentLng);
                    //bundle.putString("Gender", gender);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                }

            });
            return v;
        }
    }
}