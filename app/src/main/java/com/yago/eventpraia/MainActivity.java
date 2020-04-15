package com.yago.eventpraia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  ViewHolder mviewHolder = new ViewHolder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mviewHolder.textDaysLeft = findViewById(R.id.textDaysLeft);
        this.mviewHolder.textToday = findViewById(R.id.textToday);
        this.mviewHolder.btnConfirm = findViewById(R.id.btnConfirm);

        //evento de clique
        this.mviewHolder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trocaTela = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(trocaTela);
            }
        });
    }

    public static class ViewHolder{
        TextView textToday, textDaysLeft;
        Button btnConfirm;
    }
}
