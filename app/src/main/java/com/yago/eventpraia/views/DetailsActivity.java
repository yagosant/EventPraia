package com.yago.eventpraia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.yago.eventpraia.R;
import com.yago.eventpraia.constant.FimdeAnoConstant;
import com.yago.eventpraia.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkConfirm = findViewById(R.id.checkConfirm);
        this.mViewHolder.checkConfirm.setOnClickListener(this);

        this.loadData();

    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String precense = extras.getString(FimdeAnoConstant.PRESENCE_KEY);
            if (precense != null && precense.equals(FimdeAnoConstant.CONFIRMATION_YES))
            this.mViewHolder.checkConfirm.setChecked(true);
        }else{
           this.mViewHolder.checkConfirm.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        if(this.mViewHolder.checkConfirm.isChecked()){
            //salvar presença
            this.mSecurityPreferences.storeString(FimdeAnoConstant.PRESENCE_KEY, FimdeAnoConstant.CONFIRMATION_YES);
        }else{
            //salvar ausencia
            this.mSecurityPreferences.storeString(FimdeAnoConstant.PRESENCE_KEY, FimdeAnoConstant.CONFIRMATION_NO);

        }
    }
    private static class ViewHolder {
        CheckBox checkConfirm;
    }
}
