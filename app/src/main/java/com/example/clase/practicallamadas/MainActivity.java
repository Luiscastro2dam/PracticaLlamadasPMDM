package com.example.clase.practicallamadas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.clase.practicallamadas.baseDatos.GestorLlamadas;
import com.example.clase.practicallamadas.baseDatos.TablaLlamadas;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

//Un BroadcastReceiver es un componente android que permite el registro de eventos del sistema.
public class MainActivity extends AppCompatActivity {

    private GestorLlamadas gestor;
    private WebView webView;
    List<TablaLlamadas> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestor=new GestorLlamadas(this);


        Llamada reclla = new Llamada();
        registerReceiver(reclla, new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED));

        webView= (WebView)findViewById(R.id.webView2);
        webView.loadUrl("file:///android_asset/javascript.html");
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "interfaz");

    }
    public void mostrar(){
        webView= (WebView)findViewById(R.id.webView2);
        webView.loadUrl("file:///android_asset/javascript.html");
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "interfaz");
    }
    @JavascriptInterface
    public int datos(int i,int dia){
        for(TablaLlamadas llamadas : lista){
            if(llamadas.getDia()==dia && llamadas.getLlamada()==i){
                System.out.println("luisentradatos");
                return gestor.devorverdatos(llamadas.getLlamada(), llamadas.getDia());
            }
        }
        return 0;
    }

    public class Llamada extends BroadcastReceiver
    {

        Context mContext;


        @Override
        public void onReceive(Context mContext, Intent intent)
        {
            try
            {

                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

                if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
                {
                    Toast.makeText(mContext, "llamada entrante", Toast.LENGTH_LONG).show();
                    // entrantes  llaamda=1   domingo=0
                    GregorianCalendar fecha = new GregorianCalendar();
                    int dia=fecha.get(Calendar.DAY_OF_WEEK);
                    System.out.println(fecha.get(Calendar.DAY_OF_WEEK));

                    TablaLlamadas llamada = new TablaLlamadas();
                    llamada.setDia(dia);
                    llamada.setLlamada(1);
                    gestor.insert(llamada);
                }

                if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
                {
                    Toast.makeText(mContext, "llamada saliente", Toast.LENGTH_LONG).show();
                    //  salientes
                    GregorianCalendar fecha = new GregorianCalendar();
                    int dia=fecha.get(Calendar.DAY_OF_WEEK);
                    System.out.println(fecha.get(Calendar.DAY_OF_WEEK));

                    TablaLlamadas llamada = new TablaLlamadas();
                    llamada.setDia(dia);
                    llamada.setLlamada(0);
                    gestor.insert(llamada);
                }

                if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
                {

                    Toast.makeText(mContext, "colgado", Toast.LENGTH_LONG).show();
                    // colgar

                }
            }
            catch(Exception e)
            {
                System.out.println("error entrada/salida/corte");
            }

        }

    }
    //--------------------on resume on pause-----------------------------------------//
    @Override
    protected void onResume() {
        gestor.open();
        super.onResume();
        lista=gestor.select();
         System.out.println("luis" + lista.toString());
    }

    @Override
    protected void onPause() {
        gestor.close();
        super.onPause();
    }
    //------------menu principal--------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.borrar: {
                 gestor.delete();
                return true;
            }
            case R.id.actualizar: {
                gestor.select();
                this.mostrar();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------

}

