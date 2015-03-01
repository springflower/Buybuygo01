package com.example.springflower.buybuygo;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.springflower.buybuygo.ui.couponFrag;
import com.example.springflower.buybuygo.ui.webView;

import java.lang.reflect.Member;

public class ImageAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    public ImageAdapter(Context context) {

        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return couponFrag.images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 每一列的View
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.couponlist_row,
                    null);
        }
        ImageView imageView = (ImageView) convertView
                .findViewById(R.id.imageViewCP);
        imageView.setImageResource(couponFrag.images[position]);
        // imageView監聽
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponFrag.selectPos=position;
                Context c=v.getContext();
                Intent intent = new Intent();
                switch(position){
                    case 0:
                        intent.setClass(parent.getContext(), webView.class);
                        break;
                    case 1:
                        intent.setClass(parent.getContext(), webView.class);
                        break;
                    case 4:
                        intent.setClass(parent.getContext(), couponViewAcitvity.class);
                        break;
                    default:
                        intent.setClass(parent.getContext(), webView.class);
                        break;
                }
                c.startActivity(intent);
            }
        });
//                new i(member
//                .getImageb()) {
//            @Override
//            public void onClick(View v) {
//                ImageView i = new ImageView(ListViewActivity.this);
//                i.setImageResource(inimage);
////					showToast(i);
//                Toast toastview = new Toast();
//                toastview .setView(i);
//                toastview .setDuration(Toast.LENGTH_SHORT);
//                toastview .setGravity(Gravity.CENTER, 0, 0);
//                toastview .show();
//            }
//        });
        return convertView;
    }
}
