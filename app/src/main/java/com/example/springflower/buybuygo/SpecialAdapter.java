package com.example.springflower.buybuygo;

        import java.lang.reflect.Array;
        import java.util.List;
        import java.util.Map;

        import android.annotation.TargetApi;
        import android.content.Context;
        import android.database.Cursor;
        import android.graphics.Color;
        import android.os.Build;
        import android.provider.MediaStore;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.SimpleAdapter;
        import android.widget.SimpleCursorAdapter;
        import android.widget.TextView;

public class SpecialAdapter extends SimpleCursorAdapter {
    //private int[] colors = new int[]{0x30FF0000, 0x300000FF};
    private Context mContext;
    private Context appContext;
    private int layout;
    private Cursor cr;
    private final LayoutInflater inflater;
    private String[] columnName=null;
    //private double distance=0;



    private static final int TYPE_COUNT = 2;
    private static final int TYPE_ITEM_COLORED = 1;
    private static final int TYPE_ITEM_NORMAL = 0;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public SpecialAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.layout=layout;
        this.mContext = context;
        this.inflater=LayoutInflater.from(context);
        this.columnName=from;
        this.cr=c;
    }
    @Override
    public View newView (Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(layout, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        TextView tvtitle=(TextView)view.findViewById(R.id.title_row);
        TextView tvartist=(TextView)view.findViewById(R.id.artist_row);
        TextView tvduration=(TextView)view.findViewById(R.id.duration_row);

        int Title_index=cursor.getColumnIndexOrThrow(columnName[0]);
        int Artist_index=cursor.getColumnIndexOrThrow(columnName[1]);
        int Duration_index=cursor.getColumnIndexOrThrow(columnName[2]);

        tvtitle.setText(cursor.getString(Title_index));
        tvartist.setText(cursor.getString(Artist_index));
        double dd= cursor.getDouble(Duration_index);
        if(dd>1000){
            dd/=1000;
            tvduration.setText("距離: "+ dd+ "公里");
        }else {
            tvduration.setText("距離: " + cursor.getString(Duration_index) + " 公尺");
        }
    }





    /* (non-Javadoc)
     * @see android.widget.SimpleAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {

        //int item = getItem(position);

        return (position%2==0) ? TYPE_ITEM_COLORED : TYPE_ITEM_NORMAL;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        switch (getItemViewType(position)) {
            case TYPE_ITEM_COLORED:
                v.setBackgroundColor(0xFFFFFFFF);
                break;
            case TYPE_ITEM_NORMAL:
                v.setBackgroundColor(0x46FFFFFF);
                break;
        }

        return v;

    }
}
//public class SpecialAdapter<String> extends ArrayAdapter<String> {
//    //private int[] colors = new int[]{0x30FF0000, 0x300000FF};
//
//    private static final int TYPE_COUNT = 2;
//    private static final int TYPE_ITEM_COLORED = 1;
//    private static final int TYPE_ITEM_NORMAL = 0;
//
//    public SpecialAdapter(Context context, int resource, String[] objects) {
//        super(context, android.R.layout.simple_list_item_1, objects);
//        // TODO Auto-generated constructor stub
//    }
//
//    /* (non-Javadoc)
//     * @see android.widget.SimpleAdapter#getView(int, android.view.View, android.view.ViewGroup)
//     */
//    @Override
//    public int getViewTypeCount() {
//        return TYPE_COUNT;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//
//        //int item = getItem(position);
//
//        return (position%2==0) ? TYPE_ITEM_COLORED : TYPE_ITEM_NORMAL;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = super.getView(position, convertView, parent);
//        switch (getItemViewType(position)) {
//            case TYPE_ITEM_COLORED:
//                v.setBackgroundColor(0xFFFFFFFF);
//                break;
//            case TYPE_ITEM_NORMAL:
//                v.setBackgroundColor(0x46FFFFFF);
//                break;
//        }
//
//        return v;
//
//    }
//}