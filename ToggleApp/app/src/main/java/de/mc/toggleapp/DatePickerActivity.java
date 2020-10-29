package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class DatePickerActivity extends Activity {

    DatePicker datePicker;
    TextView tvDate, tvDateCont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);
        datePicker = findViewById(R.id.datepicker);
        tvDateCont = findViewById(R.id.tvDateCont);
        tvDate = findViewById(R.id.tvDate);
        Calendar cal = Calendar.getInstance();
        DatePicker.OnDateChangedListener dcl = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(calendar.getTime().toString());
            }
        };

        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), dcl);
    }
}
