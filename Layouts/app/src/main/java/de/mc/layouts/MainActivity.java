package de.mc.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        ListView lvLayouts = findViewById(R.id.lvLayouts);

        List<String> layoutActivites = Arrays.asList(getResources().getStringArray(R.array.layouts));
        ArrayAdapter<String> layoutadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, layoutActivites);
        lvLayouts.setAdapter(layoutadapter);
        AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String layoutName = layoutActivites.get(position);
                String currentpackage = this.getClass().getPackage().getName();
                String classname = currentpackage + "." +  layoutName;
                try{
                    Class<Activity> activityClass = (Class<Activity>) Class.forName(classname);
                    Intent intent = new Intent(MainActivity.this, activityClass);
                    startActivity(intent);
                }catch (ClassNotFoundException e){

                }
            }
        };
        lvLayouts.setOnItemClickListener(oicl);


    }
}
