package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    EditText Num1;
    EditText Num2;
    EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Num1 = findViewById(R.id.number1_editText);
        Num2 = findViewById(R.id.number2_editText);
        result = findViewById(R.id.result);

        registerForContextMenu(result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int numb1 = Integer.parseInt(Num1.getText().toString());
        int numb2 = Integer.parseInt(Num2.getText().toString());

        switch (item.getItemId()) {
            case R.id.division_option:
                result.setText(String.valueOf(numb1 / numb2));
                return true;
            case R.id.add_option:
                result.setText(String.valueOf(numb1 + numb2));
                return true;
            case R.id.subtract_option:
                result.setText(String.valueOf(numb1 - numb2));
                return true;
            case R.id.multiply_option:
                result.setText(String.valueOf(numb1 * numb2));
                return true;
            case R.id.multiply3_option:
                int res = Integer.parseInt(result.getText().toString());
                result.setText(String.valueOf(res * 3));
                return true;
            case R.id.multiply4_option:
                int res4 = Integer.parseInt(result.getText().toString());
                result.setText(String.valueOf(res4 * 4));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int numb1 = Integer.parseInt(Num1.getText().toString());
        int numb2 = Integer.parseInt(Num2.getText().toString());

        if (item.getItemId() == R.id.division_option){
            result.setText(String.valueOf(numb1 / numb2));
        }else if(item.getItemId() == R.id.add_option){
            result.setText(String.valueOf(numb1 + numb2));
        }else if(item.getItemId()== R.id.subtract_option){
            result.setText(String.valueOf(numb1 - numb2));
        }else if(item.getItemId()== R.id.multiply_option){
            result.setText(String.valueOf(numb1 * numb2));
        }else
            return super.onContextItemSelected(item);
        return true;
    }
}

