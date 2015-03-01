//package com.example.springflower.buybuygo;
//
//import android.annotation.TargetApi;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.database.Cursor;
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.SimpleCursorAdapter;
//import android.widget.TextView;
//
//import com.example.springflower.buybuygo.core.JSONParser;
//
//import org.json.JSONArray;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class SpecialAdapter2 extends BaseAdapter {
//    private LayoutInflater layoutInflater;
//        public SpecialAdapter2(Context context) {
//
//            layoutInflater = (LayoutInflater) context
//                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
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
//}