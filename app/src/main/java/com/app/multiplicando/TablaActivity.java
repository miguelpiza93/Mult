package com.app.multiplicando;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class TablaActivity extends AppCompatActivity {

    private HashMap<Integer, Integer> tablesMap;
    private int actualTable;
    private Button butBack;
    private Button butNext;
    private Button butPractice;
    private TextView tvTablePractice;
    private TextView tvTitle;
    private Button butClosePractice;
    private boolean isPracticing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        System.gc();
        actualTable = getIntent().getIntExtra("Tabla", 0);
        initializeComponents();

        addListenerBackNextButtons();
    }

    private void addListenerBackNextButtons() {
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualTable--;
                changeTable();
                if (isPracticing) {
                    changeTablePractice();
                }
            }
        });

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualTable++;
                changeTable();
                if (isPracticing) {
                    changeTablePractice();
                }
            }
        });
        butPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTablePractice.setVisibility(View.VISIBLE);
                butPractice.setVisibility(View.INVISIBLE);
                butClosePractice.setVisibility(View.VISIBLE);
                changeTablePractice();

                isPracticing = true;
            }
        });

        butClosePractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butPractice.setVisibility(View.VISIBLE);
                butClosePractice.setVisibility(View.INVISIBLE);

                isPracticing = false;
                changeTable();
            }
        });
    }

    private void changeTablePractice() {
        String tableSource = getString(R.string.tabla_practice);
        String tableToShow = tableSource.replace("mul", String.valueOf(actualTable));

        tvTablePractice.setText(tableToShow);
    }

    private void initializeComponents() {
        tablesMap = new HashMap<>();
        tablesMap.put(1, R.drawable.tabla1);
        tablesMap.put(2, R.drawable.tabla2);
        tablesMap.put(3, R.drawable.tabla3);
        tablesMap.put(4, R.drawable.tabla4);
        tablesMap.put(5, R.drawable.tabla5);
        tablesMap.put(6, R.drawable.tabla6);
        tablesMap.put(7, R.drawable.tabla7);
        tablesMap.put(8, R.drawable.tabla8);
        tablesMap.put(9, R.drawable.tabla9);
        tablesMap.put(10, R.drawable.tabla10);

        butBack = findViewById(R.id.butBack);
        butNext = findViewById(R.id.butNext);
        butPractice = findViewById(R.id.butPractice);
        butClosePractice = findViewById(R.id.butClosePractice);

        tvTablePractice = findViewById(R.id.tvTablesPractice);
        tvTitle = findViewById(R.id.tvTitle);

        changeTable();
    }

    private void changeTable() {
        int idResource = 0;
        String title="";
        if (tablesMap.containsKey(actualTable)) {
            idResource = tablesMap.get(actualTable);
            title = String.format(getString(R.string.table_title),String.valueOf(actualTable));
        } else {
            idResource = R.drawable.image_not_available;
        }

        if(!isPracticing)
        {
            String tableSource = getString(R.string.tabla_practice);
            String tableToShow = tableSource.replace("mul", String.valueOf(actualTable));
            String tableToShowResult = tableToShow;
            for (int i=1; i< 11; i++)
            {
                tableToShowResult = tableToShow.replaceFirst("__", String.valueOf(actualTable*i));
                tableToShow = tableToShowResult;
            }

            tvTablePractice.setText(tableToShowResult);
        }
        ConstraintLayout layout = findViewById(R.id.idLayoutTabla);
        layout.setBackgroundResource(idResource);

        tvTitle.setText(title);

        evaluateActualTableToDisableButtond();
    }

    private void evaluateActualTableToDisableButtond() {
        if (actualTable == 1) {
            butBack.setVisibility(View.INVISIBLE);
            butNext.setVisibility(View.VISIBLE);
        } else if (actualTable == 10) {
            butBack.setVisibility(View.VISIBLE);
            butNext.setVisibility(View.INVISIBLE);
        } else {
            butBack.setVisibility(View.VISIBLE);
            butNext.setVisibility(View.VISIBLE);
        }
    }
}
