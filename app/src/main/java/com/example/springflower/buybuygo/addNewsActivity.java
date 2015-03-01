package com.example.springflower.buybuygo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.springflower.buybuygo.core.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class addNewsActivity extends ActionBarActivity implements View.OnClickListener {
    private Calendar m_Calendar = Calendar.getInstance();
    private EditText etDateTime = null;
    private EditText etDateTime2= null;
    private EditText etPrice;
    private EditText etPeopleNum;
    private EditText etaddUrl;
    private int pYear;
    private int pMonth;
    private int pDay;
    DatePickerDialog dialog;
    TimePickerDialog dialog2;
    TextView tv01;
    EditText editTextTitle;
    EditText editTextContent;
    EditText editTextFeat01;
    EditText editTextFeat02;
    EditText editTextFeat03;
    EditText editTextFeat04;
    EditText editTextFeat05;
    EditText editTextFeat06;
    EditText editTextFeat07;
    EditText editTextFeat08;
    EditText editTextFeat09;
    Date endDate;
    Time endTime;
    String ss="";
    private ProgressDialog pDialog;
    private static String url_create_product = "http://59.126.92.205:8080/android_connect/create_product.php";
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    //@InjectView(R.id.editTextDateTime)

    private Spinner spnEncCon;
    private Spinner spnFeatNum;
    String[] endConditions = {"時間", "金額", "人數"};
    int sec =1;
    int selectedFeatureNum=1;
    int hasTextFeats=0;
    Integer[] featcounts = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Button button01;
    Button button02;
    //@InjectView(R.id.button_addNews_SelectDateTime)
    Button btnSelectDateTime = null;
    Button btnSelectDateTime2=null;
    double setLat;
    double setLon;

    static final int PICK_CONTACT_REQUEST = 1;  // The request code



    DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            m_Calendar.set(Calendar.YEAR, year);
            m_Calendar.set(Calendar.MONTH, monthOfYear);
            m_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
            etDateTime.setText(sdf.format(m_Calendar.getTime()));
            endDate=m_Calendar.getTime();
        }
    };
    TimePickerDialog.OnTimeSetListener timepicker = new TimePickerDialog.OnTimeSetListener()
    {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            m_Calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            m_Calendar.set(Calendar.MINUTE, minute);
            //endTime.setTime(m_Calendar.get);
            String myFormat = "aa HH-mm";
            String myFormat2 = "HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
            SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.TAIWAN);
            ss = sdf2.format(m_Calendar.getTime());
            Toast.makeText(addNewsActivity.this, ss, Toast.LENGTH_LONG).show();
            etDateTime2.setText(sdf.format(m_Calendar.getTime()));
            //etDateTime2.setText( endTime.toString());
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //pDialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        findViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_news, menu);
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

    private void pickLatLon() {
        Intent intent = new Intent();
        intent.setClass(addNewsActivity.this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", setLat);
        bundle.putDouble("lng", setLon);
        intent.putExtras(bundle);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);

    }
    public void findViews(){
        editTextTitle = (EditText)findViewById(R.id.editTextNewsTitle);
        editTextContent = (EditText)findViewById(R.id.editTextNewsContent);
        editTextFeat01 = (EditText)findViewById(R.id.editTextaddFeat1);
        editTextFeat02 = (EditText)findViewById(R.id.editTextaddFeat2);
        editTextFeat03 = (EditText)findViewById(R.id.editTextaddFeat3);
        editTextFeat04 = (EditText)findViewById(R.id.editTextaddFeat4);
        editTextFeat05 = (EditText)findViewById(R.id.editTextaddFeat5);
        editTextFeat06 = (EditText)findViewById(R.id.editTextaddFeat6);
        editTextFeat07 = (EditText)findViewById(R.id.editTextaddFeat7);
        editTextFeat08 = (EditText)findViewById(R.id.editTextaddFeat8);
        editTextFeat09 = (EditText)findViewById(R.id.editTextaddFeat9);
        etDateTime = (EditText) findViewById(R.id.editTextDate);
        etDateTime2 = (EditText) findViewById(R.id.editTextTime);
        etPrice = (EditText) findViewById(R.id.editText_addNews_Price);
        etPeopleNum = (EditText) findViewById(R.id.editText_addNews_PeopleNum);
        etaddUrl = (EditText) findViewById(R.id.editTextaddNewUrl);
        btnSelectDateTime = (Button)findViewById(R.id.button_addNews_SelectDate);
        btnSelectDateTime.setOnClickListener(this);
        btnSelectDateTime2 = (Button)findViewById(R.id.button_addNews_SelectTime);
        btnSelectDateTime2.setOnClickListener(this);
        sec = 1;

        button01 = (Button)findViewById(R.id.button);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateNewProduct().execute();
            }
        });
        button02 = (Button)findViewById(R.id.button2);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        setLat=bundle.getDouble("myLat");
        setLon=bundle.getDouble("myLng");
        button02.setText("取貨位置\n("+ setLat+" "+setLon+")");
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickLatLon();
            }
        });
        spnEncCon = (Spinner) findViewById(R.id.spinner_endCond);
        spnFeatNum =(Spinner) findViewById(R.id.spinner_featNum);
        ArrayAdapter<String> adapterEndCond = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, endConditions);
        ArrayAdapter<Integer> adapterFeatNum = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, featcounts);
        adapterFeatNum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEncCon.setAdapter(adapterEndCond);
        spnEncCon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etDateTime.setVisibility(View.VISIBLE);
                        etDateTime2.setVisibility(View.VISIBLE);
                        btnSelectDateTime.setVisibility(View.VISIBLE);
                        btnSelectDateTime2.setVisibility(View.VISIBLE);
                        etPrice.setVisibility(View.GONE);
                        etPeopleNum.setVisibility(View.GONE);
                        sec =1;
                        break;
                    case 1:
                        etDateTime.setVisibility(View.GONE);
                        etDateTime2.setVisibility(View.GONE);
                        btnSelectDateTime.setVisibility(View.GONE);
                        btnSelectDateTime2.setVisibility(View.GONE);
                        etPrice.setVisibility(View.VISIBLE);
                        etPeopleNum.setVisibility(View.GONE);
                        sec = 2;
                        break;
                    case 2:
                        etDateTime.setVisibility(View.GONE);
                        etDateTime2.setVisibility(View.GONE);
                        btnSelectDateTime.setVisibility(View.GONE);
                        btnSelectDateTime2.setVisibility(View.GONE);
                        etPrice.setVisibility(View.GONE);
                        etPeopleNum.setVisibility(View.VISIBLE);
                        sec=3;
                        break;
                    default:
                        etDateTime.setVisibility(View.VISIBLE);
                        etDateTime2.setVisibility(View.VISIBLE);
                        btnSelectDateTime.setVisibility(View.VISIBLE);
                        btnSelectDateTime2.setVisibility(View.VISIBLE);
                        etPrice.setVisibility(View.GONE);
                        etPeopleNum.setVisibility(View.GONE);
                        sec=1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnFeatNum.setAdapter(adapterFeatNum);
        spnFeatNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.GONE);
                        editTextFeat03.setVisibility(View.GONE);
                        editTextFeat04.setVisibility(View.GONE);
                        editTextFeat05.setVisibility(View.GONE);
                        editTextFeat06.setVisibility(View.GONE);
                        editTextFeat07.setVisibility(View.GONE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 1:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.GONE);
                        editTextFeat04.setVisibility(View.GONE);
                        editTextFeat05.setVisibility(View.GONE);
                        editTextFeat06.setVisibility(View.GONE);
                        editTextFeat07.setVisibility(View.GONE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 2:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.GONE);
                        editTextFeat05.setVisibility(View.GONE);
                        editTextFeat06.setVisibility(View.GONE);
                        editTextFeat07.setVisibility(View.GONE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 3:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.VISIBLE);
                        editTextFeat05.setVisibility(View.GONE);
                        editTextFeat06.setVisibility(View.GONE);
                        editTextFeat07.setVisibility(View.GONE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 4:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.VISIBLE);
                        editTextFeat05.setVisibility(View.VISIBLE);
                        editTextFeat06.setVisibility(View.GONE);
                        editTextFeat07.setVisibility(View.GONE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 5:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.VISIBLE);
                        editTextFeat05.setVisibility(View.VISIBLE);
                        editTextFeat06.setVisibility(View.VISIBLE);
                        editTextFeat07.setVisibility(View.GONE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 6:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.VISIBLE);
                        editTextFeat05.setVisibility(View.VISIBLE);
                        editTextFeat06.setVisibility(View.VISIBLE);
                        editTextFeat07.setVisibility(View.VISIBLE);
                        editTextFeat08.setVisibility(View.GONE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 7:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.VISIBLE);
                        editTextFeat05.setVisibility(View.VISIBLE);
                        editTextFeat06.setVisibility(View.VISIBLE);
                        editTextFeat07.setVisibility(View.VISIBLE);
                        editTextFeat08.setVisibility(View.VISIBLE);
                        editTextFeat09.setVisibility(View.GONE);
                        selectedFeatureNum=position+1;
                        break;
                    case 8:
                        editTextFeat01.setVisibility(View.VISIBLE);
                        editTextFeat02.setVisibility(View.VISIBLE);
                        editTextFeat03.setVisibility(View.VISIBLE);
                        editTextFeat04.setVisibility(View.VISIBLE);
                        editTextFeat05.setVisibility(View.VISIBLE);
                        editTextFeat06.setVisibility(View.VISIBLE);
                        editTextFeat07.setVisibility(View.VISIBLE);
                        editTextFeat08.setVisibility(View.VISIBLE);
                        editTextFeat09.setVisibility(View.VISIBLE);
                        selectedFeatureNum=position+1;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(addNewsActivity.this);
            pDialog.setMessage("Creating News..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            SharedPreferences prefence;
            prefence = getSharedPreferences("Users", MODE_PRIVATE );
            String _title = editTextTitle.getText().toString();
            String _creatUser = prefence.getString("UserAccount", "");
            String _getLat = String.valueOf(setLat);
            String _getLon = String.valueOf(setLon);
            String _content = editTextContent.getText().toString();
            String _url = etaddUrl.getText().toString();
            String _featruesNum = String.valueOf(selectedFeatureNum);
            String _endDate=null;
            String _endTime=null;
            String _endPersonNum=null;
            String _endPrice=null;
            if(sec==1) {
                _endDate = etDateTime.getText().toString();
                _endTime = ss;
                _endPersonNum =null;
                _endPrice=null;
            }else if(sec == 2) {
                _endDate=null;
                _endTime=null;
                _endPersonNum=null;
                _endPrice = etPrice.getText().toString();
            }else if(sec == 3) {
                _endDate=null;
                _endTime=null;
                _endPersonNum = etPeopleNum.getText().toString();
                _endPrice=null;
            }


            String _features= "";
            if(!"".equals(editTextFeat01.getText().toString())){
                _features= _features.concat(editTextFeat01.getText().toString());
                hasTextFeats++;

            }
            if(!"".equals(editTextFeat02.getText().toString()) && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat02.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat03.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat03.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat04.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat04.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat05.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat05.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat06.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat06.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat07.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat07.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat08.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat08.getText().toString());
                hasTextFeats++;
            }
            if(!editTextFeat09.getText().toString().equals("") && hasTextFeats<selectedFeatureNum){
                _features= _features.concat( "," + editTextFeat09.getText().toString());
                hasTextFeats++;
            }
            if(selectedFeatureNum<hasTextFeats){
                selectedFeatureNum=hasTextFeats;
            }
            _featruesNum = String.valueOf(selectedFeatureNum);




            String _buyRecord;

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", _title));
            params.add(new BasicNameValuePair("endprice", _endPrice));
            params.add(new BasicNameValuePair("content", _content));
            params.add(new BasicNameValuePair("creatuser", _creatUser));
            params.add(new BasicNameValuePair("getlat", _getLat));
            params.add(new BasicNameValuePair("getlon", _getLon));
            params.add(new BasicNameValuePair("url", _url));
            params.add(new BasicNameValuePair("featuresnum", _featruesNum));
            params.add(new BasicNameValuePair("feats", _features));
            params.add(new BasicNameValuePair("enddate", _endDate));
            params.add(new BasicNameValuePair("endtime",_endTime));
            params.add(new BasicNameValuePair("endpersonnum",_endPersonNum));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {   //Toast.makeText(this, "YOYO", Toast.LENGTH_SHORT).show();
        switch (resultCode)
        {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                setLat = bundle.getDouble("backlat");
                setLon = bundle.getDouble("backlng");
                button02.setText("取貨位置\n("+ setLat+" "+setLon+")");
                //Toast.makeText(this, "YOYO", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnSelectDateTime.getId())
        {
            dialog =
                    new DatePickerDialog(addNewsActivity.this,
                            datepicker,
                            m_Calendar.get(Calendar.YEAR),
                            m_Calendar.get(Calendar.MONTH),
                            m_Calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
        if (v.getId() == btnSelectDateTime2.getId())
        {
            dialog2 =
                    new TimePickerDialog(addNewsActivity.this,
                            timepicker,
                            m_Calendar.get(Calendar.HOUR_OF_DAY),
                            m_Calendar.get(Calendar.MINUTE), false);
            dialog2.show();
        }
    }
}
