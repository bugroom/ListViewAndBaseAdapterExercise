package com.example.listviewandbaseadapterexercise;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
//import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ArrayList<People> people = new ArrayList<People>();
	private View view_custom;
    private Context mContext;
    private AlertDialog dialog = null;
    private AlertDialog.Builder builder = null;
    //private TextView dgTxtSpeak;
    //private TextView dgTxtLeft;
    private GetSentenceTask getSentence;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = MainActivity.this;
		initPeople();
		
		PeopleAdapter peopleAdapter = new PeopleAdapter(mContext,R.layout.item_people,people);
		
		ListView listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(peopleAdapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				People peopleItem = people.get(position);
				showDialog(peopleItem);
			}
			
		});
	}

	protected void showDialog(People peopleItem) {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(mContext);
		view_custom=LayoutInflater.from(mContext).inflate(R.layout.dialog_people, null);
		((TextView) view_custom.findViewById(R.id.dialog_txt_name)).setText("ÐÕÃû£º"+peopleItem.getName());
		((TextView) view_custom.findViewById(R.id.dialog_txt_sex)).setText(	"ÐÔ±ð£º"+peopleItem.getSex());
		((ImageView) view_custom.findViewById(R.id.dialog_img_head)).setImageResource(peopleItem.getImgId());
		
		getSentence =new GetSentenceTask(mContext,view_custom);
		getSentence.execute();
		view_custom.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		view_custom.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		builder.setView(view_custom);
		builder.setCancelable(false);
		dialog=builder.create();
		dialog.show();
		
	}
	
	private void initPeople() {
		int img_boy	=R.drawable.ic_boy;
		int img_girl=R.drawable.ic_girl;
		for(int i=0;i<5;i++){
			people.add(new People(img_boy, "ÓàÎÄÆæ", "ÄÐ"));
			people.add(new People(img_boy, "ÅíÓºä¿", "ÄÐ"));
			people.add(new People(img_boy, "Ð»½õ²¨", "ÄÐ"));
			people.add(new People(img_boy, "ÔøË¼º²", "ÄÐ"));
			people.add(new People(img_boy, "½ðÌÎ", "ÄÐ"));
			people.add(new People(img_girl, "ÓàÑó", "Å®"));
			people.add(new People(img_boy, "Û³²¨", "ÄÐ"));
			people.add(new People(img_boy, "ÎâèÈ", "ÄÐ"));
			people.add(new People(img_boy, "ÅË³Â½¨", "ÄÐ"));
			people.add(new People(R.drawable.ic_launcher, "·Ö¸îÏß", "------ÎÒÊÇ·Ö¸îÏß------"));
		}
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
