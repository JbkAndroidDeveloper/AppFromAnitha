package com.example.jbk.appfromanitha;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Factorisation extends AppCompatActivity {


    Button btnFactorize,btnOutputFactors;
    EditText edtInput;
    LinearLayout layoutBtn,layoutLable;
    Context context;
    int btnId=0;
    int currentBtnId=0;
    TextView txtLable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorisation);

        btnFactorize = (Button)findViewById(R.id.btn_done);
        edtInput = (EditText)findViewById(R.id.edt_input);
        layoutBtn = (LinearLayout)findViewById(R.id.layout_btn_output);
        layoutLable = (LinearLayout)findViewById(R.id.layout_lable_output);
        context = getApplicationContext();


        factorize();

    }


    public void createNewButton(int _btnText )
    {
        try {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);

            params.setMargins(20, 20, 20, 20);

            btnOutputFactors = new Button(context);
            currentBtnId = btnId;
            btnOutputFactors.setId(btnId++);

            btnOutputFactors.setText(String.valueOf(_btnText));
            btnOutputFactors.setBackgroundColor(Color.CYAN);
            btnOutputFactors.setTextColor(Color.BLACK);
            layoutBtn.addView(btnOutputFactors);
            setListenerToButton();
            btnOutputFactors.setLayoutParams(params);
        }
        catch (Exception e)
        {

        }
    }

    public void createNewLable(int _txt)
    {

        try {


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 100);

            params.setMargins(20, 20, 20, 20);

            txtLable = new TextView(context);

            txtLable.setText(String.valueOf(_txt));
            txtLable.setLayoutParams(params);
            txtLable.setTextColor(Color.WHITE);
            txtLable.setBackgroundColor(Color.BLUE);
            txtLable.setTextSize(20);
            txtLable.setGravity(Gravity.CENTER);
            layoutLable.addView(txtLable);
        }
        catch (Exception e)
        {

        }
    }

    public void factorize()
    {
        btnFactorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (edtInput.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Enter input", Toast.LENGTH_SHORT).show();
                    } else {

                        refresh();
                        ArrayList<Integer> factorList = new ArrayList<>();

                        factorList = getFactors(Integer.parseInt(edtInput.getText().toString()));

                        if (factorList.size() != 0) {
                            for (int i = 0; i < factorList.size(); i++) {

                                createNewButton(factorList.get(i));
                            }
                        }

                    }
                    hideSoftKeyboard();
                }
                catch (Exception e)
                {

                }
            }
        });
    }


    public ArrayList<Integer> getFactors(int _input)
    {
        ArrayList<Integer> factorList = new ArrayList<>();

        try {

            for (int i = 1; i <= _input; ++i) {
                if (_input % i == 0) {
                    factorList.add(i);
                }
            }
        }
        catch (Exception e)
        {

        }

        return factorList;

    }

    public void refresh()
    {

        if(layoutBtn.getChildCount()>0) {
            btnId = 0;
            currentBtnId = 0;
            layoutBtn.removeAllViews();
            layoutLable.removeAllViews();
        }
    }


    public void setListenerToButton() {

        btnOutputFactors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                try {
                    layoutLable.removeAllViews();

                    for (int i = 0; i < btnId; i++) {
                        ((Button) layoutBtn.findViewById(i)).setBackgroundColor(Color.CYAN);
                        ((Button) layoutBtn.findViewById(i)).setTextColor(Color.BLACK);

                    }
                    showLable(Integer.parseInt(((Button) layoutBtn.findViewById(view.getId())).getText().toString()));

                    ((Button) layoutBtn.findViewById(view.getId())).setBackgroundColor(Color.BLUE);
                    ((Button) layoutBtn.findViewById(view.getId())).setTextColor(Color.WHITE);

                }
                catch (Exception e)
                {

                }
            }
        });
    }

    public  void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtInput.getWindowToken(), 0);
    }

    public void showLable(int _factor)
    {

        try {
            ArrayList<Integer> factorList = new ArrayList<>();

            factorList = getFactors(_factor);

            if (factorList.size() != 0) {
                for (int i = 0; i < factorList.size(); i++) {

                    createNewLable(factorList.get(i));
                }
            }
        }
        catch (Exception e)
        {

        }
    }

}
