package com.example.practice3_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonClean, buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonPower, buttonPercent, buttonEq;
    private TextView operation, result;
    private EditText number1, number2;
    private String currentOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // поиск элементов по ID
        buttonClean = findViewById(R.id.buttonClean);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonPower = findViewById(R.id.buttonPower);
        buttonPercent = findViewById(R.id.buttonPercent);
        buttonEq = findViewById(R.id.buttonEq);

        operation = findViewById(R.id.operation);
        result = findViewById(R.id.result);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        // подключение слушателя нажатий к кнопкам
        buttonClean.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonPower.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonEq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();

        // кнопки операций: только запоминаем знак, НЕ считаем
        if (id == R.id.buttonAdd) {
            setOperation("+");

        } else if (id == R.id.buttonSubtract) {
            setOperation("-");

        } else if (id == R.id.buttonMultiply) {
            setOperation("*");

        } else if (id == R.id.buttonDivide) {
            setOperation("/");

        } else if (id == R.id.buttonPower) {
            setOperation("^");

        } else if (id == R.id.buttonPercent) {
            setOperation("%");

        } else if (id == R.id.buttonClean) {
            clearAll();

        } else if (id == R.id.buttonEq) {
            calculate();
        }
    }

    // установка операции и показ её в operation
    private void setOperation(String newOperation) {
        currentOperation = newOperation;
        operation.setText(newOperation);
        result.setText(""); // сброс старой операции
    }

    private void clearAll() {
        currentOperation = "";
        number1.setText("");
        number2.setText("");
        operation.setText("");
        result.setText("");
    }

    private void calculate() {
        String strNum1 = number1.getText().toString().trim();
        String strNum2 = number2.getText().toString().trim();

        if (strNum1.isEmpty() || strNum2.isEmpty()) {
            result.setText("Введите числа");
            return;
        }

        if (currentOperation.isEmpty()) {
            result.setText("Выберите операцию");
            return;
        }

        double num1, num2;
        try {
            num1 = Double.parseDouble(strNum1);
            num2 = Double.parseDouble(strNum2);
        } catch (NumberFormatException e) {
            result.setText("Ошибка ввода");
            return;
        }

        double res;

        switch (currentOperation) {
            case "+":
                res = num1 + num2;
                break;

            case "-":
                res = num1 - num2;
                break;

            case "*":
                res = num1 * num2;
                break;

            case "/":
                if (num2 == 0) {
                    result.setText("Нельзя на 0");
                    return;
                }
                res = num1 / num2;
                break;

            case "^":
                res = Math.pow(num1, num2);
                break;

            case "%":
                if (num2 == 0) {
                    result.setText("Нельзя на 0");
                    return;
                }
                res = num1 % num2;
                break;

            default:
                result.setText("Неизвестная операция");
                return;
        }
        result.setText(String.valueOf(res));
    }
}