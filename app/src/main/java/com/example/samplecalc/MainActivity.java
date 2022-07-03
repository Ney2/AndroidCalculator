package com.example.samplecalc;

import android.support.v7.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String values){
        String oldstr = display.getText().toString();
        int cursorpt = display.getSelectionStart();
        String leftstr = oldstr.substring(0, cursorpt);
        String  rightstr = oldstr.substring(cursorpt);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(values);
            display.setSelection(cursorpt + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftstr, values, rightstr));
            display.setSelection(cursorpt + 1);
        }
    }

    public void zerobtn(View view) {
        updateText("0");
    }
    public void onebtn(View view) {
        updateText("1");
    }
    public void twobtn(View view) {
        updateText("2");
    }
    public void threebtn(View view) {
        updateText("3");
    }
    public void fourbtn(View view) {
        updateText("4");
    }
    public void fivebtn(View view) {
        updateText("5");
    }
    public void sixbtn(View view) {
        updateText("6");
    }
    public void sevenbtn(View view) {
        updateText("7");
    }
    public void eightbtn(View view) {
        updateText("8");
    }
    public void ninebtn(View view) {
        updateText("9");
    }
    public void addbtn(View view) {
        updateText("+");
    }
    public void subbtn(View view) {
        updateText("-");
    }
    public void mulbtn(View view) {
        updateText("x");
    }
    public void divbtn(View view) {
        updateText("/");
    }
    public void expbtn(View view) {
        updateText("^");
    }
    public void parentbtn(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textlen = display.getText().length();

        for (int i=0; i<cursorPos; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")) {
                openPar +=1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")) {
                closePar +=1;
            }
        }

        if(openPar == closePar || display.getText().toString().substring(textlen-1, textlen).equals("(")) {
            updateText("(");
        }
        else if(closePar < openPar && !display.getText().toString().substring(textlen-1, textlen).equals("(")) {
            updateText(")");
        }

        display.setSelection(cursorPos + 1);
    }
    public void ptbtn(View view) {
        updateText(".");
    }
    public void equalsbtn(View view) {
        String userInput = display.getText().toString();
        userInput = userInput.replaceAll("x", "*");
        Expression exp = new Expression(userInput);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
    public void clearbtn(View view) {
        display.setText("");
    }
    public void plusMinusbtn(View view) {
        updateText("-");
    }
    public void backspace(View view) {
       int cursorPos = display.getSelectionStart();
       int textLen = display.getText().length();

       if(cursorPos != 0 && textLen != 0) {
           SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
           selection.replace(cursorPos-1, cursorPos, "");
           display.setText(selection);
           display.setSelection(cursorPos - 1);
       }
    }
}