package com.coolcats.coolcatscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView calculatorView;
    private double currentValue = 0.0;
    private double storedValue = 0.0;
    private Operand operand = Operand.NONE;

    enum Operand {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        MODULUS,
        DIVISION,
        CUBEROOT,
        SIN,
        SINH,
        ASIN,
        COS,
        COSH,
        ACOS,
        TAN,
        TANH,
        ATAN,
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorView = findViewById(R.id.output_textview);
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.ac_button:
                calculatorView.setText("0");
                currentValue = 0.0;
                break;
            case R.id.zero_button:
                if(currentValue != 0) {
                    double value = currentValue * 10;
                    calculatorView.setText(value + "");
                    currentValue = value;
                }
                break;
            case R.id.nine_button:
                setNumber(9);
                break;
            case R.id.eight_button:
                setNumber(8);
                break;
            case R.id.seven_button:
                setNumber(7);
                break;
            case R.id.six_button:
                setNumber(6);
                break;
            case R.id.five_button:
                setNumber(5);
                break;
            case R.id.four_button:
                setNumber(4);
                break;
            case R.id.three_button:
                setNumber(3);
                break;
            case R.id.two_button:
                setNumber(2);
                break;
            case R.id.one_button:
                setNumber(1);
                break;

            case R.id.multi_button:
                doCalculation(Operand.MULTIPLY);
                break;
            case R.id.div_button:
                doCalculation(Operand.DIVISION);
                break;
            case R.id.modulus_button:
                doCalculation(Operand.MODULUS);
                break;
            case R.id.sub_button:
                doCalculation(Operand.MINUS);
                break;
            case R.id.plus_button:
                doCalculation(Operand.PLUS);
                break;
            case R.id.equals_button:
                getResult();
                break;
            case R.id.period_button:

                break;

            case R.id.negate_button:
                break;

            case R.id.mr_button:
                break;

            case R.id.ten_to_power_button:
                break;

            case R.id.log_button:
                break;

            case R.id.exponentional_notational_button:
                break;

            case R.id.rand_button:
                break;

            case R.id.m_minus_button:
                break;

            case R.id.e_power_x_button:
                break;

            case R.id.ln_button:
                break;

            case R.id.e_button:
                break;

            case R.id.pi_button:
                break;

            case R.id.m_plus_button:
                break;

            case R.id.x_power_x_button:
                break;

            case R.id.y_root_x:
                break;

            case R.id.tan_button:
                doCalculation(operand.TAN);
                break;

            case R.id.tanh_button:
                doCalculation(operand.TANH);
                break;

            case R.id.mc_button:
                break;

            case R.id.cos_button:
                doCalculation(operand.COS);
                break;

            case R.id.cosh_button:
                doCalculation(operand.COSH);
                break;

            case R.id.closed_round_brace_button:
                break;

            case R.id.x_power_two_button:
                break;

            case R.id.two_root_x:
                break;

            case R.id.three_root_x:
                doCalculation(operand.CUBEROOT);
                break;

            case R.id.sin_button:
                doCalculation(operand.SIN);
                break;

            case R.id.sinh_button:
                doCalculation(operand.SINH);
                break;

            case R.id.open_round_brace_button:
                break;

            case R.id.second_button:
                break;

            case R.id.one_over_x:
                break;

            case R.id.factorial_button:
                break;

                case R.id.rad_button:
                    break;



        }
    }

    private void doCalculation(Operand operand) {
        getResult();
        storedValue = currentValue;
        currentValue = 0;
        this.operand = operand;
    }

    private void getResult(){

        double result = 0;

        switch (operand){
            default:
                result = currentValue +1;
                break;
            case PLUS:
                result = storedValue + currentValue;
                break;
            case MINUS:
                result = storedValue - currentValue;
                break;
            case DIVISION:
                result = storedValue / currentValue;
                break;
            case MODULUS:
                result = storedValue % currentValue;
                break;
            case MULTIPLY:
                result = storedValue * currentValue;
                break;
            case CUBEROOT:
                result = Math.cbrt(currentValue);
                break;
            case SIN:
                result = Math.sin(currentValue);
                break;
            case SINH:
                result = Math.sinh(currentValue);
                break;
            case ASIN:
                result = Math.asin(currentValue);
                break;
            case COS:
                result = Math.cos(currentValue);
                break;
            case COSH:
                result = Math.cosh(currentValue);
                break;
            case ACOS:
                result = Math.acos(currentValue);
                break;
            case TAN:
                result = Math.tan(currentValue);
                break;
            case TANH:
                result = Math.tanh(currentValue);
                break;
            case ATAN:
                result = Math.atan(currentValue);
                break;
        }

        operand = Operand.NONE;
        storedValue = 0;
        currentValue = result;
        calculatorView.setText(currentValue+"");
    }

    private void setNumber(int num) {
        if(currentValue == 0){
            calculatorView.setText(""+num);
            currentValue = num;
        } else {
            String value = calculatorView.getText().toString() + num;
            Log.d("TAG_X", value);
            currentValue = Double.parseDouble(value);
            calculatorView.setText(value);
        }
    }
}