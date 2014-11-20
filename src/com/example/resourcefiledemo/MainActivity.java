package com.example.resourcefiledemo;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView tv1,tv2;
    Button bt1,bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1=(TextView) findViewById(R.id.textView1);
		tv2=(TextView) findViewById(R.id.textView2);
		bt1=(Button) findViewById(R.id.button1);
		bt2=(Button) findViewById(R.id.button2);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				InputStream is=null;
				is=getResources().openRawResource(R.raw.raw_file);
				byte[] reader=new byte[is.available()];
				while(is.read(reader)!=-1){tv1.setText(new String(reader));}
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				XmlPullParser myXpp=getResources().getXml(R.xml.people);
				String msg="";
				while(myXpp.next()!=XmlPullParser.END_DOCUMENT){
					String nodeName=myXpp.getName();
					if(nodeName!=null&&nodeName.equals("person")){
						int attrCount =myXpp.getAttributeCount();
						for(int i=0;i<attrCount;i++){
							String attrName=myXpp.getAttributeName(i);
							if(attrName!=null&&attrName.equals("name")){
								msg+="姓名:"+myXpp.getAttributeValue(i);
							}else if(attrName!=null&&attrName.equals("age")){
								msg+="年龄:"+myXpp.getAttributeValue(i);
							}else if(attrName!=null&&attrName.equals("height")){
								msg+="身高:"+myXpp.getAttributeValue(i);
							}
						}
						msg+="\n";
					}
				}
				tv2.setText(msg);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
