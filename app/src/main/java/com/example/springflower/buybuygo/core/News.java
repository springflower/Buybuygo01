package com.example.springflower.buybuygo.core;

import android.os.AsyncTask;
import android.util.Log;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


//public class News extends AsyncTask<List<News>, Void, List> {
//
//    private static final long serialVersionUID = -6641292855569752036L;
//
//    private String title;
//    private String content;
//    private String objectId;
//    private Date creatime;
//    private Date endTime;
//    private double Latitude;    //景點店家緯度
//    private double Longitude;   //景點店家經度
//    private double Distance;    //景點店家距離
//
//    //test
//    private int Cno;
//    private String Cname;
//    private int Cnum;
//    List<News> posts = new ArrayList<News>();
//
//    @Override
//    protected List doInBackground(List<News>... params) {
//        Connection conn = null;
//        try {
//
//
//
//            String driver = "net.sourceforge.jtds.jdbc.Driver";
//            Class.forName(driver).newInstance();
//
//            String connString = "jdbc:jtds:sqlserver://59.126.92.205/StockManage";
//            String sqlusername = "sa";
//            String sqlpassword = "abcd310704";
//
//            conn = DriverManager.getConnection(connString, sqlusername, sqlpassword);
//            Log.w("Connection", "open");
//
//            String articleQuery = "SELECT Cno FROM C";
//
//            PreparedStatement stmt = conn.prepareStatement(articleQuery);
//
//            ResultSet rs;
//
//            rs = stmt.executeQuery();
//
//
//
//            while (rs.next()) {
//
//                News news = new News();
//                //article.article_id = rs.getInt("article_id");
//                //article.username = rs.getString("username");
//                //article.date = rs.getDate("article_date");
//                //article.title = rs.getString("article_title");
//                //article.body = rs.getString("article_description");
//                news.Cno = rs.getInt("Cno");
//                //news.Cname= rs.getString("Cname");
//                //news.Cnum= rs.getInt("Cnum");
//                posts.add(news);
//
//
//            }
//            conn.close();
//
//        } catch (Exception e)
//        {
//            Log.w(e.getMessage(), e);
//        }
//
//
//        return posts;
//    }
//    protected void onPostExecute(List posts) {
//        // Result is here now, may be 6 different List type.
//        this.posts = posts;
//    }


public class News implements Serializable {

    private static final long serialVersionUID = -6641292855569752036L;

    private String title;
    private String content;
    private String objectId;
    private Date creatime;
    private Date endTime;
    private double Latitude;    //景點店家緯度
    private double Longitude;   //景點店家經度
    private double Distance;    //景點店家距離
    public News(String title, String content,String creatime, double latitude, double longitude,
                double myLat, double myLon)
    {
        //將資訊帶入類別屬性

        Latitude = latitude ;
        Longitude = longitude ;
        Distance = Math.pow((myLat-latitude),2)+Math.pow((myLon-longitude),2);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(final String objectId) {
        this.objectId = objectId;
    }

    public Date getCreatime(){
        return creatime;
    }

    public void setCreatime(final Date creatime){
        this.creatime=creatime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(final Date endTime){
        this.endTime=endTime;
    }

}
