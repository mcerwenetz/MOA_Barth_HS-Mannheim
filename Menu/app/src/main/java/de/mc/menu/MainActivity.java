package de.mc.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MenuApp";



    private ActionMode am = null;

    ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.contextactions, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return handleMenuSelectedItem(item);
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            MainActivity.this.am = null;
        }
    };

    private boolean handleMenuSelectedItem(MenuItem item) {
        int position = pairsAdapter.getSelectedPosition();
        if(position == -1){
            return super.onContextItemSelected(item);
        }
        if(item.getItemId() == R.id.caItem1){

        }
    }

    AdapterView.OnItemLongClickListener oilcl = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.v(TAG, "long clicked on ActionBar");
            if (am != null)
                return  false;
            am = MainActivity.this.startActionMode(actionModeCallback);
            pairsAdapter.setSelectedPosition(position);
            return true;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1){
            finish();
            return true;
        } else if (item.getItemId() == R.id.item2){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menusearch);
        if (searchItem.isEnabled()){
            searchItem.setEnabled(false);
        } else {
            searchItem.setEnabled(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
