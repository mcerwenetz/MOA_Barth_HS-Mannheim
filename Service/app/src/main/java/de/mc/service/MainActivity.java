package de.mc.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private Button btnAdd;
    private Button btnBind;
    private Button btnUnBind;
    private TextView result;
    private EditText etNumber1;
    private EditText etNumber2;

//    Eigentlich localservice requested
//    Frage ob Bindung schonmal angefragt wurde, nicht ob gebunden. Verwirrend.
    private boolean localServiceBound = false;
    private LocalService localService;
    private final ServiceConnection localServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            Übelst verkopft: service ist kein Service sondern n IBinder.
//            Ziel der Methode ist es Instanzvariable localService an den Service des Binders
//            service zu binden, der selbst kein Service ist aber mit getService() einen holen kann.
//            Da wir LocalService wollen müssen wir den IBinder zu erstmal in den LocalBinder
//            casten. Denn nur dann können wir mit getService einen LocalService holen.
            LocalService.LocalBinder localBinder = (LocalService.LocalBinder) service;
            localService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            localServiceBound=false;
            localService=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUi();
    }

    private void bindUi() {
        btnAdd = findViewById(R.id.btnAdd);
        result = findViewById(R.id.tvResult);
        etNumber1 = findViewById(R.id.etnumber1);
        etNumber2 = findViewById(R.id.etnumber2);
        btnBind = findViewById(R.id.btnBind);
        btnUnBind = findViewById(R.id.btnunbind);
        btnAdd.setOnClickListener(v -> startParamsLocal());
        btnBind.setOnClickListener(v -> bindLocal());
        btnUnBind.setOnClickListener(v -> unBindLocal());
    }

    private Integer getNumber1(){
        return Integer.parseInt(etNumber1.getText().toString());
    }

    private Integer getNumber2(){
        return Integer.parseInt(etNumber2.getText().toString());
    }

    private void setResult(Integer res){
        result.setText(res.toString());
    }

    public void useLocal(){
        if(localService == null){
            Log.e(TAG, "local service not bound yet");
            return;
        }
        if (etNumber1.getText().toString().equals("") || etNumber2.getText().toString().equals("")){
            return;
        }
        Integer result = localService.doAdd(getNumber1(), getNumber2());
        setResult(result);
    }

    public void bindLocal(){
        Intent intent = new Intent(this, LocalService.class);
        localServiceBound = bindService(intent, localServiceConnection, Context.BIND_AUTO_CREATE);
//          Asynchron localServiceBound heißt nicht dass Service schon gebunden ist.
//          Wie Gesagt, verwirrend, weil eigentlich: localServiceRequested.
    }

    public void unBindLocal(){
        if(localServiceBound){
            localServiceBound=false;
            localService = null;
            unbindService(localServiceConnection);
        }
    }

//    Wird nur benutzt wenn man nen Dienst hat der kein return hat
    public void startParamsLocal(){
        Log.v(TAG, "starte über paramslocal");
        Intent intent = new Intent(this, LocalService.class);
//        intent.setAction(LocalService.CMD_COMPUTE);
        intent.setAction(LocalService.CMD_SAY);
        intent.putExtra("number1", getNumber1());
        intent.putExtra("number2", getNumber2());
        startService(intent);
    }
}