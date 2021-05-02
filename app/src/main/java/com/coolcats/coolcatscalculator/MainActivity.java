package com.coolcats.coolcatscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView calculatorView;
    private double currentValue = 0.0;
    private double storedValue = 0.0;
    private double previousStoredValue = 0.0;
    private boolean isRadians = false;
    private Operand operand = Operand.NONE;
    NumberFormat numberFormat;

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
        POW,
        POWERTWO,
        POWERTHREE,
        SQUAREROOT,
        LOG_TEN,
        LOG_TWO,
        LOG,
        EXPONENT,
        TENPOWER,
        FRACTION,
        FACTORIAL,
        Y_ROOT_X
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorView = findViewById(R.id.output_textview);

        numberFormat = new DecimalFormat("0.######E0");
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

            case R.id.rand_button:
                currentValue = Math.random();
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
                if(!calculatorView.getText().toString().contains(".")){
                    String temp1 = calculatorView.getText().toString() + ".0";
                    String temp2 = temp1.substring(0, temp1.length() - 1);
                    calculatorView.setText(temp2);
                    currentValue = Double.parseDouble(temp2);
                }
                break;

            case R.id.negate_button:
                if(currentValue > 0)
                    currentValue = -currentValue;
                else
                    currentValue = +currentValue;
                break;

            case R.id.mr_button:
                calculatorView.setText(previousStoredValue + "");
                break;

            case R.id.ten_to_power_button:
                doCalculation(operand.TENPOWER);
                break;

            case R.id.log_button:
                doCalculation(operand.LOG_TEN);
                break;

            case R.id.exponentional_notational_button:
                numberFormat.format(currentValue);
                break;

            case R.id.m_minus_button:
                previousStoredValue = 0;
                break;

            case R.id.e_power_x_button:
                doCalculation(operand.EXPONENT);
                break;

            case R.id.ln_button:
                doCalculation(operand.LOG);
                break;

            case R.id.e_button:
                currentValue = Math.E;
                break;

            case R.id.pi_button:
                currentValue = Math.PI;
                break;

            case R.id.m_plus_button:
                previousStoredValue = currentValue;
                break;

            case R.id.x_power_y_button:
                doCalculation(operand.POW);
                break;

            case R.id.y_root_x:
                doCalculation(operand.Y_ROOT_X);
                break;

            case R.id.tan_button:
                doCalculation(operand.TAN);
                break;

            case R.id.tanh_button:
                doCalculation(operand.TANH);
                break;

            case R.id.mc_button:
                storedValue = 0.0;
                currentValue = 0.0;
                previousStoredValue = 0.0;
                break;

            case R.id.cos_button:
                doCalculation(operand.COS);
                break;

            case R.id.cosh_button:
                doCalculation(operand.COSH);
                break;

            case R.id.closed_round_brace_button:
                if(!calculatorView.getText().toString().contains(")")){
                    String temp1 = calculatorView.getText().toString()+")";
                    calculatorView.setText(temp1);
                }
                break;

            case R.id.x_power_two_button:
                doCalculation(operand.POWERTWO);
                break;

            case R.id.two_root_x:
                doCalculation(operand.SQUAREROOT);
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
                if(!calculatorView.getText().toString().contains("(")){
                    String temp1 = calculatorView.getText().toString()+"(";
                    calculatorView.setText(temp1);
                }
                break;

            case R.id.second_button:
                break;

            case R.id.one_over_x:
                doCalculation(operand.FRACTION);
                break;

            case R.id.factorial_button:
                doCalculation(operand.FACTORIAL);
                break;

                case R.id.rad_button:
                    if(isRadians == true)
                        currentValue = Math.toRadians(currentValue);
                    else
                        currentValue = Math.toDegrees(currentValue);
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
            case POW:
                result = Math.pow(storedValue, currentValue);
                break;
            case TENPOWER:
                result = Math.pow(10, currentValue);
                break;
            case SQUAREROOT:
                result = Math.sqrt(currentValue);
                break;
            case POWERTWO:
                result = Math.pow(currentValue, 2.0);
                break;
            case POWERTHREE:
                result = Math.pow(currentValue, 3.0);
                break;
            case LOG_TEN:
                result = Math.log10(currentValue);
                break;
            case LOG_TWO:
                break;
            case LOG:
                result = Math.log(currentValue);
                break;
            case EXPONENT:
                result = Math.exp(currentValue);
                break;
            case FRACTION:
                if(currentValue != 0){
                    result = 1 / currentValue;
                }else{
                    Toast.makeText(this, "Not a number", Toast.LENGTH_SHORT);
                }
                break;
            case FACTORIAL:
                int i, fact = 1;

                for(i = 1; i <= currentValue; i++) {
                    fact = fact * i;
                }
                currentValue = fact;
                break;
            case Y_ROOT_X:
                result = Math.round(Math.pow(storedValue, 1/currentValue));
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