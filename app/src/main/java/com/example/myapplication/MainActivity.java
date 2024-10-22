package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, plus, equal, minus, multi, div,dot,per,negpos,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calc);

        textView = findViewById(R.id.txt);
        btn = findViewById(R.id.c);

        btn0 = findViewById(R.id.zero);
        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 = findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 = findViewById(R.id.nine);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal);
        minus = findViewById(R.id.minus);
        multi = findViewById(R.id.multi);
        div = findViewById(R.id.div);
        dot = findViewById(R.id.dot);
        negpos = findViewById(R.id.negpos);
        back = findViewById(R.id.back);
        per = findViewById(R.id.per);

        setbutton(btn, "");
        setbutton(btn0, "0");
        setbutton(btn1, "1");
        setbutton(btn2, "2");
        setbutton(btn3, "3");
        setbutton(btn4, "4");
        setbutton(btn5, "5");
        setbutton(btn6, "6");
        setbutton(btn7, "7");
        setbutton(btn8, "8");
        setbutton(btn9, "9");
        setbutton(plus, "+");
        setbutton(equal, "=");
        setbutton(minus, "-");
        setbutton(multi, "*");
        setbutton(div, "/");
        setbutton(dot, ".");
        setbutton(negpos, "+/-");
        setbutton(back, "⌫");
        setbutton(per, "%");


    }

    Double firstvalue = 0.0;
    Double secondvalue = 0.0;


    void setbutton(Button btn, String s) {
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (s.equals(""))
                {
                    textView.setText("");
                }

                else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
                {
                    Double temp= 0.0;
                    temp=firstvalue;

                    try
                    {
                        firstvalue = Double.parseDouble(textView.getText().toString());

                        if(sign.plus)
                        {
                            firstvalue=firstvalue+temp;
                            Log.d("-----", "onClick: "+firstvalue);
                        }
                        if(sign.minus)
                        {
                            firstvalue= temp-firstvalue;
                            Log.d("+---", "onClick: "+firstvalue);
                            Log.d("+---", "onClick: "+temp);
                        }
                        if(sign.multi)
                        {
                            firstvalue=firstvalue*temp;
                        }
                        if(sign.div)
                        {
                            firstvalue=firstvalue/temp;
                        }
                    }
                    catch (Exception e)
                    {
                        Log.d("===", "NumberFormatException====: " + e.getMessage());
                    }

                    Log.d("===", "onClick: " + firstvalue);

                    textView.setText("");

                    if (s.equals("+"))
                    {
                        sign.plus=true;
                    }
                    if (s.equals("-"))
                    {
                        sign.minus=true;
                    }
                    if (s.equals("*"))
                    {
                        sign.multi=true;
                    }
                    if (s.equals("/"))
                    {
                        sign.div=true;
                    }
                }

                else if (s.equals("%"))
                {
                    if (!textView.getText().toString().isEmpty())
                    {
                        firstvalue = Double.parseDouble(textView.getText().toString());
                        firstvalue=firstvalue/100;
                        textView.setText(""+firstvalue);
                    }
                }

                else if (s.equals("="))
                {
                    ans();
                }

                else if (s.equals("0"))
                {
                    if (!textView.getText().toString().startsWith("0") || textView.getText().toString().length()>1)
                    {
                        textView.setText(textView.getText().toString().concat(s));
                    }
                }

                else if (s.equals("."))
                {
                    if (!textView.getText().toString().contains(".") )
                    {
                        if (textView.getText().toString().isEmpty())
                        {
                            textView.setText(textView.getText().toString().concat("0."));
                        }
                        else
                        {
                            textView.setText(textView.getText().toString().concat("."));
                        }
                    }
                }

                else if (s.equals("⌫"))
                {
                    if (!textView.getText().toString().equals(""))
                    {
                        textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-1));
                    }
                }

                else if (s.equals("+/-"))
                {
                    if (textView.getText().toString().contains("-"))
                    {
                        textView.setText(textView.getText().toString().replace("-",""));
                    }
                    else
                    {
                        textView.setText("-"+textView.getText());
                    }
                }

                else
                {
                    textView.setText(textView.getText().toString().concat(s));
                }

            }
        });

    }

    private void ans()
    {
        secondvalue = Double.parseDouble(textView.getText().toString());
        Double answer = 0.0;

        if (sign.plus)
        {
            answer=firstvalue+secondvalue;
        }
        if (sign.minus)
        {
            answer=firstvalue-secondvalue;
        }
        if (sign.multi)
        {
            answer=firstvalue*secondvalue;
        }
        if (sign.div)
        {
            answer=firstvalue/secondvalue;
        }



        sign.plus=false;
        sign.minus=false;
        sign.multi=false;
        sign.div=false;


        textView.setText(answer.toString());

    }
}

class sign
{
    static boolean plus=false,minus=false,multi=false,div=false;

}