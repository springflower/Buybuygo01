//package com.example.springflower.buybuygo;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class LazyAdapter extends BaseAdapter {
//
//    private Activity activity;
//    private ArrayList<HashMap<String, String>> data;
//    private static LayoutInflater inflater=null;
//    //public ImageLoader imageLoader; //用來下載圖片的類，後面有介紹
//
//    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
//        activity = a;
//        data=d;
//        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //imageLoader=new ImageLoader(activity.getApplicationContext());
//    }
//
//    public int getCount() {
//        return data.size();
//    }
//
//    public Object getItem(int position) {
//        return position;
//    }
//
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View vi=convertView;
//        if(convertView==null)
//            vi = inflater.inflate(R.layout.list_row, null);
//
//        TextView title = (TextView)vi.findViewById(R.id.title); // 標題
//        TextView artist = (TextView)vi.findViewById(R.id.artist); // 歌手名
//        TextView duration = (TextView)vi.findViewById(R.id.duration); // 時長
//        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // 縮略圖
//
//        HashMap<String, String> song = new HashMap<String, String>();
//        song = data.get(position);
//
//        // 設置ListView的相關值
//        title.setText(song.get(CustomizedListView.KEY_TITLE));
//        artist.setText(song.get(CustomizedListView.KEY_ARTIST));
//        duration.setText(song.get(CustomizedListView.KEY_DURATION));
//        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
//        return vi;
//    }
//}