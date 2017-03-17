package com.example.listviewandbaseadapterexercise;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PeopleAdapter extends BaseAdapter{

	private Context context;
	private int layoutId;
	private ArrayList<People> data;
	
	public PeopleAdapter(Context context,int layoutId,ArrayList<People> data){
		this.context=context;
		this.layoutId=layoutId;
		this.data=data;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data==null? 0:data.size();
	}

	@Override
	public People getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	//ÿ�������������Ļ��ʱ����getView()
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		People people=getItem(position);	
		View view ;
		ViewHolder viewHolder;
		
		if(convertView==null){
			/*java.lang.UnsupportedOperationException: addView(View, LayoutParams) is not supported in AdapterView  
			ԭ����������취��
			��ʹ��AdapterViewʱ����������getView���������ú�����һ��Parent�Ĳ�����Ҳ���Ǹ�������
			public View  getView(int position, View  convertView, ViewGroup  parent)  
			ArrayAdapter��BaseAdapter��SimpleAdapter���ǲ�֧�ֵģ���˸ò���Ӧ����null������ͻᱨ������쳣*/
			view = LayoutInflater.from(context).inflate(layoutId, null);
			viewHolder= new ViewHolder();
			viewHolder.imgHead=(ImageView) view.findViewById(R.id.img_head);
			viewHolder.txtName=(TextView) view.findViewById(R.id.txt_name);
			viewHolder.txtSex=(TextView) view.findViewById(R.id.txt_sex);
			view.setTag(viewHolder);//��ViewHolder�洢��View��
		}else{
			view = convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		viewHolder.imgHead.setImageResource(people.getImgId());
		viewHolder.txtName.setText(people.getName());
		viewHolder.txtSex.setText(people.getSex());
		return view;
	}
	class ViewHolder{
		ImageView imgHead;
		TextView txtName,txtSex;
	}

}
