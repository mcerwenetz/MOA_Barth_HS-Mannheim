package de.mc.toggleapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ListViewActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        ListView lvWidgets = findViewById(R.id.lv);
        final List<String> widgets = Arrays.asList(getResources().getStringArray(R.array.widgets));
        ArrayAdapter<String> widgetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, widgets);
        lvWidgets.setAdapter(widgetsAdapter);
        AdapterView.OnItemClickListener lOicl = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String widgetName = widgets.get(position);
                Log.v(TAG, "Clicked on " + widgetName);
                String currentPackage = this.getClass().getPackage().getName();
                String className = currentPackage + "." + widgetName + "Activity";
                try{
                    Class<Activity> activity = (Class<Activity>) Class.forName(className);
                    Intent intent = new Intent(ListViewActivity.this, activity);
                    startActivity(intent);
                }catch (ClassNotFoundException e){
                    Log.e(TAG,"class " + className + " not found");
                }
            }
        };
        lvWidgets.setOnItemClickListener(lOicl);

    }
}
