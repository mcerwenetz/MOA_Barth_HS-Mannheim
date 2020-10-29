package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.Calendar;

public class TimePickerActivity extends Activity {

    DatePicker datePicker;
    TextView tvDate, tvDateCont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);
        TimePicker tp = findViewById(R.id.tp);
        tp.setIs24HourView(true);
        final TextView tvTp = findViewById(R.id.tvTp);
        TimePicker.OnTimeChangedListener tcl = new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND,0);
                tvTp.setText(calendar.getTime().toString());
            }
        };

        tp.setOnTimeChangedListener(tcl);
        Calendar cal = Calendar.getInstance();
        tp.setHour(cal.get(Calendar.HOUR_OF_DAY));
        tp.setMinute(cal.get(Calendar.MINUTE));

    }
}
