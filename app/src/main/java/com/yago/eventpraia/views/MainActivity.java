package com.yago.eventpraia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yago.eventpraia.R;
import com.yago.eventpraia.constant.FimdeAnoConstant;
import com.yago.eventpraia.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private  ViewHolder mviewHolder = new ViewHolder();
    //formatando a data atual
    private  static SimpleDateFormat SIMPLE_DATE_FOMRAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mviewHolder.textDaysLeft = findViewById(R.id.textDaysLeft);
        this.mviewHolder.textToday = findViewById(R.id.textToday);
        this.mviewHolder.btnConfirm = findViewById(R.id.btnConfirm);

        this.mviewHolder.btnConfirm.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);


        //pegando a data atual
        this.mviewHolder.textToday.setText(SIMPLE_DATE_FOMRAT.format(Calendar.getInstance().getTime()));

        String daysLeft =  String.format("%s %s", String.valueOf(this.getDaysLeft()),getString(R.string.dias));
        this.mviewHolder.textDaysLeft.setText(daysLeft);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //metodo para pegar a presença
        this.VerificaPresenca();
    }

    @Override
    public void onClick(View v) {

        String presence = this.mSecurityPreferences.getStoreString(FimdeAnoConstant.PRESENCE_KEY);

        Intent trocaTela = new Intent(MainActivity.this, DetailsActivity.class);
        //carrega os dados
        trocaTela.putExtra(FimdeAnoConstant.PRESENCE_KEY, presence);
        startActivity(trocaTela);
    }

    private int getDaysLeft(){
        //pega a data de hoje IMPORTANTE
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        //dia maximo do ano
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;
    }

    //metodo para pegar a presença
    private void VerificaPresenca1() {
      String presence =  this.mSecurityPreferences.getStoreString(FimdeAnoConstant.PRESENCE_KEY);

      //verifica e ,mostra o texto no botão
        if (presence.equals("")){
            this.mviewHolder.btnConfirm.setText(getString(R.string.txt_btn));
        }else if(presence.equals(FimdeAnoConstant.CONFIRMATION_YES)){
            this.mviewHolder.btnConfirm.setText(getString(R.string.btn_sim));
        }else{
            this.mviewHolder.btnConfirm.setText(getString(R.string.btn_nao));
        }

    }
    private void VerificaPresenca(){
        String presence = this.mSecurityPreferences.getStoreString(FimdeAnoConstant.PRESENCE_KEY);
        if (presence.equals("")){
            this.mviewHolder.btnConfirm.setText(R.string.txt_btn);
        }else if(presence.equals(FimdeAnoConstant.CONFIRMATION_YES)){
            this.mviewHolder.btnConfirm.setText(getString(R.string.btn_sim));
        }else if(presence.equals(FimdeAnoConstant.CONFIRMATION_NO)){
            this.mviewHolder.btnConfirm.setText(R.string.btn_nao);
        }
    }

    public static class ViewHolder{
        TextView textToday, textDaysLeft;
        Button btnConfirm;
    }

    
}
