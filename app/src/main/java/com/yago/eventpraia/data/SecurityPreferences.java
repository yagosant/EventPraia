package com.yago.eventpraia.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    //Quase um BD, ele armazena e permite recuperar os dados depois de ele fechar
    //recomendados para dados simples
   private SharedPreferences mSharedPreferences;

   //construtor
    public SecurityPreferences(Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("EventoPraia",Context.MODE_PRIVATE);

    }
    //metodos
    public void storeString(String key,String value){
        this.mSharedPreferences.edit().putString(key, value).apply();;

    }
    public String getStoreString(String key){
        return this.mSharedPreferences.getString(key, "");

    }
}
