package com.example.easydict;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
//import android.support.v4.widget.SearchViewCompatIcs.MySearchView;
//import android.support.v4.widget.SearchViewCompatIcs.MySearchView;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

 public class HomePage extends Activity {
	  TextView wrd,men;
	  ClipboardManager cm;
	  String url;
	  static  String text;
	
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_home_page);
      wrd=(TextView)findViewById(R.id.textView1);
      wrd.setTextColor(Color.BLUE);
      men=(TextView)findViewById(R.id.textView2);
        cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData abc = cm.getPrimaryClip();
        
        ClipData.Item item = abc.getItemAt(0);
        if((item.getText().toString())==null){
        	text = "world"; 
        }
        else{
         text = item.getText().toString();
        }
        // tv.setText(text);
        
         url = dictionaryEntries();
         requestApiButtonClick();
         printWord();
         
    }
        
        
    public  void requestApiButtonClick(){
    	OxfordMain myoxford = new OxfordMain(getApplicationContext());
    	myoxford.execute(url);
    	
    }
    private String dictionaryEntries() {
        final String language = "en";
         String word = text;
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
    public void printWord(){
    	if(OxfordMain.word ==null){
    		wrd.setText("Select a word");
    		men.setText("Then Shake your phone");
    		
    	}
    	else{
    		wrd.setText(text+":-");
    		
    		men.setText(OxfordMain.word);
    	}
    			
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }


//	@Override
//	public boolean onLongClick(View arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
    
}
