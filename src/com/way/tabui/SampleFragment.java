package com.way.tabui;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 每个Tab中的fragment
 * 
 * @author way
 * 
 */
public class SampleFragment extends Fragment {

	
	private static final String ARG_TEXT = "text";
	private static final String ARG_PAGER = "pager";
	private GridView gv;
	public static SampleFragment newInstance(String text, int pager) {
		SampleFragment f = new SampleFragment();

		Bundle args = new Bundle();
		args.putString(ARG_TEXT, text);
		args.putInt(ARG_PAGER, pager);
		f.setArguments(args);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_sample, container, false);
		gv=(GridView)v.findViewById(R.id.itemlist);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
       
        	HashMap<String, Object> map1=new HashMap<String, Object>();
        	map1.put("Img", R.drawable.g1);
        	map1.put("Text", "机器人小夜灯");
        	lstImageItem.add(map1);
        	HashMap<String, Object> map2=new HashMap<String, Object>();
        	map2.put("Img", R.drawable.g2);
        	map2.put("Text", "手工小笔筒");
        	lstImageItem.add(map2);
        	HashMap<String, Object> map3=new HashMap<String, Object>();
        	map3.put("Img", R.drawable.g3);
        	map3.put("Text", "《高等数学》");
        	lstImageItem.add(map3);
        	HashMap<String, Object> map4=new HashMap<String, Object>();
        	map4.put("Img", R.drawable.g4);
        	map4.put("Text", "手绘明信片");
        	lstImageItem.add(map4);
        	HashMap<String, Object> map5=new HashMap<String, Object>();
        	map5.put("Img", R.drawable.g5);
        	map5.put("Text", "IPhone5转让");
        	lstImageItem.add(map5);
        	HashMap<String, Object> map6=new HashMap<String, Object>();
        	map6.put("Img", R.drawable.g6);
        	map6.put("Text", "卡西欧tr350s");
        	lstImageItem.add(map6);
        
        SimpleAdapter saImgItem= new SimpleAdapter(v.getContext(), 
        							lstImageItem,
        							R.layout.item, 
        							new String[] {"Img","Text"},
        							new int[] {R.id.ItemImage,R.id.ItemText}
        							);
      gv.setAdapter(saImgItem);
      /*
       gv.setOnItemClickListener(new OnItemClickListener() 
       { 
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
           { 
				Intent intent=new Intent();
				intent.setClass(getActivity(), DetailActivity.class);
				getActivity().startActivity(intent); 
           } 
       }); */
        /*if(getArguments().getString(ARG_TEXT).equalsIgnoreCase("广场")){
        	
        }*/
		/*((TextView) v.findViewById(R.id.text)).setText(getArguments()
				.getString(ARG_TEXT));*/
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setText(getArguments().getString(ARG_TEXT));
		int pager = getArguments().getInt(ARG_PAGER);
		if (pager == MainActivity.PAGE_FILE_SYSTEM)// 如果是文件系统的Fragment
			title.setBackgroundColor(Color.parseColor("#ff3995e3"));// 蓝色标题栏
		else
			title.setBackgroundColor(Color.parseColor("#ffde4125"));
		return v;
	}
}
