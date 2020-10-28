package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class EditTextActivity extends Activity {

    EditText etEdit;
    TextView tvOben;
    TextView tvUnten;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittext);
        Button update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                tvUnten.setText(etEdit.getText());
            }
        });
        etEdit = findViewById(R.id.et_edit);
        etEdit.addTextChangedListener(textWatcher);
        tvOben = findViewById(R.id.tv_oben);
        tvUnten = findViewById(R.id.tv_unten);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            tvOben.setText(etEdit.getText());
        }
    };
}
