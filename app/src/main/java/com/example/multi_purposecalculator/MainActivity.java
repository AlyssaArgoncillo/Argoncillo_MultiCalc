package com.example.multi_purposecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        TextView resultTv,solutionTv;
        MaterialButton btn_clear,btn_left_p,btn_right_p;
        MaterialButton btn_divide,btn_multiply,btn_add,btn_subtract,btn_compute;
        MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
        MaterialButton btn_allclear,btn_decimal;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            resultTv = findViewById(R.id.resultTv);
            solutionTv = findViewById(R.id.solutionTv);

            assignId(btn_clear,R.id.btn_clear);
            assignId(btn_left_p,R.id.btn_left_p);
            assignId(btn_right_p,R.id.btn_right_p);
            assignId(btn_divide,R.id.btn_divide);
            assignId(btn_multiply,R.id.btn_multiply);
            assignId(btn_add,R.id.btn_add);
            assignId(btn_subtract,R.id.btn_subtract);
            assignId(btn_compute,R.id.btn_compute);
            assignId(btn0,R.id.btn0);
            assignId(btn1,R.id.btn1);
            assignId(btn2,R.id.btn2);
            assignId(btn3,R.id.btn3);
            assignId(btn4,R.id.btn4);
            assignId(btn5,R.id.btn5);
            assignId(btn6,R.id.btn6);
            assignId(btn7,R.id.btn7);
            assignId(btn8,R.id.btn8);
            assignId(btn9,R.id.btn9);
            assignId(btn_allclear,R.id.btn_allclear);
            assignId(btn_decimal,R.id.btn_decimal);





        }

        void assignId(MaterialButton btn,int id){
            btn = findViewById(id);
            btn.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            MaterialButton button =(MaterialButton) view;
            String buttonText = button.getText().toString();
            String dataToCalculate = solutionTv.getText().toString();

            if(buttonText.equals("AC")){
                solutionTv.setText("");
                resultTv.setText("0");
                return;
            }
            if(buttonText.equals("=")){
                solutionTv.setText(resultTv.getText());
                return;
            }
            if(buttonText.equals("C")){
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }else{
                dataToCalculate = dataToCalculate+buttonText;
            }
            solutionTv.setText(dataToCalculate);

            String finalResult = getResult(dataToCalculate);

            if(!finalResult.equals("Err")){
                resultTv.setText(finalResult);
            }

        }

        String getResult(String data){
            try{
                Context context  = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
                if(finalResult.endsWith(".0")){
                    finalResult = finalResult.replace(".0","");
                }
                return finalResult;
            }catch (Exception e){
                return "Err";
            }
        }

    }