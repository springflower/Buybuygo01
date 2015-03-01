package com.example.springflower.buybuygo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class couponViewAcitvity extends ActionBarActivity {

    ImageView imageView;
    TextView textView;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_view_acitvity);
        imageView = (ImageView)findViewById(R.id.imageView_cv);
        textView = (TextView)findViewById(R.id.textView_cv);
        textView2 = (TextView)findViewById(R.id.textView_cv2);
        imageView.setImageResource(R.drawable.aal3);
        textView.setText("聖誕優惠專案\n2014 12月1號~2014 12月31號");
        textView2.setText("出示即可享受以上優惠");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.coupon_view_acitvity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
