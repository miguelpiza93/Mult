package com.app.multiplicando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TablaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        int tabla = getIntent().getIntExtra("Tabla", 0);
        int idResource = 0;
        switch (tabla){
            case 1:
                idResource = R.drawable.tabla1;
                break;
            case 2:
                idResource = R.drawable.tabla2;
                break;
            case 3:
                idResource = R.drawable.tabla3;
                break;
            case 4:
                idResource = R.drawable.tabla4;
                break;
            case 5:
                idResource = R.drawable.tabla5;
                break;
            case 6:
                idResource = R.drawable.tabla6;
                break;
            case 7:
                idResource = R.drawable.tabla7;
                break;
            case 8:
                idResource = R.drawable.tabla8;
                break;
            case 9:
                idResource = R.drawable.tabla9;
                break;
            case 10:
                idResource = R.drawable.tabla10;
                break;
        }

        if(idResource == 0){
            idResource = R.drawable.image_not_available;
        }

        RelativeLayout layout=(RelativeLayout)findViewById(R.id.idLayoutTabla);
        layout.setBackgroundResource(idResource);
    }
}
