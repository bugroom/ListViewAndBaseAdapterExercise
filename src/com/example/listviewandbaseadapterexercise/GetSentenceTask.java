package com.example.listviewandbaseadapterexercise;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GetSentenceTask extends AsyncTask<String, String, String>
{
    ProgressDialog pdlg;
    String jsonstr = "";
    JSONObject json = null;
	private String engstr;
	private String chistr;
	private String imagurl;
	private String timestr;
	private String fromstr;
	private String tagstr;
	private View v;
	private TextView dgTxtSpeak;
	private Context context;
	private ProgressBar pb;
	
    public GetSentenceTask(Context context,View v) {
		super();
		this.v = v;
		this.context = context;
	}
    
    
	@Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
		//图灵机器人
		String APIKEY = "71ef0fd1875744639fc163bd70405622"; 
        String info = "笑话";//这是上传给云机器人的问题
        //String INFO = URLEncoder.encode(question, "utf-8"); 
        String url = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + info; 
        String turlingAnswer = "我把悲剧当笑话讲，是希望读的人不那么沉重，而不是要讥笑谁的意思。我从长辈身上学到了这项技能：将悲惨的事情当笑话轻松地讲出来，把听的人架在火上烤--你要是笑，就成了我的共犯；你要是哭，我便笑你。";
        //金山词霸每日一句
        //String url = "http://open.iciba.com/dsapi/";
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            HttpResponse httpResponse = httpClient.execute(httppost);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream is = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            jsonstr = sb.toString();
            Log.i("json", jsonstr);
            json = new JSONObject(jsonstr.toString());
            
/*            //金山词霸返回
            engstr = json.getString("content");
            chistr = json.getString("note");
            imagurl = json.getString("picture");
            timestr =    json.getString("dateline");
            fromstr   =  json.getString("translation");
            JSONArray array = json.getJSONArray("tags");
            for(int i=0;i<array.length();i++)
            {
                JSONObject tag = (JSONObject)array.get(i);
                tagstr += tag.getString("name")+"," ; 
            }
*/            
          //turling返回
            turlingAnswer=json.getString("text");
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return turlingAnswer;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
//        pdlg.dismiss();
/*       imageLoader.DisplayImage(imagurl,imageview);
        eng.setText("    "+engstr);
        chi.setText("    "+chistr);
        tag.setText(tagstr);
        time.setText(timestr);
        from.setText("    "+fromstr);*/
        //txt.setText(chistr);
        //txt.setText(chistr);
        pb.setVisibility(View.GONE);
        dgTxtSpeak= (TextView) v.findViewById(R.id.dialog_txt_right);
		dgTxtSpeak.setMovementMethod(ScrollingMovementMethod.getInstance());
		dgTxtSpeak.setText(result);
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
/*        pdlg = new ProgressDialog(context);
        pdlg.setCancelable(false);
        pdlg.setMessage("正在加载");
        pdlg.show();*/
        pb=(ProgressBar)v.findViewById(R.id.dialog_progressBar);
        pb.setVisibility(View.VISIBLE);
        
    }
}