package com.hp.hp.call_log_contentprovider;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    Cursor c;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView)findViewById(R.id.listView1);
        Cursor oneContact = managedQuery( People.CONTENT_URI, null, null, null, null);
        Log.d("TEMP2", "Count: " + oneContact.getCount());
        Log.d("TEMP2", "column Count: " + oneContact.getColumnCount());
        String[] cols = {CallLog.Calls.NUMBER,CallLog.Calls.DURATION,CallLog.Calls.TYPE,CallLog.Calls.DATE};
        c = managedQuery(CallLog.Calls.CONTENT_URI, cols, null, null, null);
        count = c.getCount();
        lv.setAdapter(new CallAdapter(getApplicationContext()));
        lv.setBackgroundColor(Color.BLACK);
        //lv.setBackgroundResource(R.drawable.i7);

    }
    private class CallAdapter extends BaseAdapter
    {
        Context call_context;
        CallAdapter(Context c1)
        {
            call_context = c1;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return count;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub
            c.moveToPosition(arg0);
            String s = c.getString(0) ;
            s+= " | " + c.getString(1);
            s+= " | " + c.getString(2);
            s+= " | " + c.getString(3);
            TextView tv = new TextView(call_context.getApplicationContext());
            tv.setTextColor(Color.WHITE);
            tv.setText(s);
            return tv;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
