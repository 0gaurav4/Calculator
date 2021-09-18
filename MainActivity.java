package com.zerogaurav.simplesite.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV, resultsTV;

    String workings ="";
    String formula ="";
    String TempFormula ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        workingsTV=(TextView)findViewById(R.id.workingsTextView);
        workingsTV.setMovementMethod(new ScrollingMovementMethod());
        resultsTV=(TextView)findViewById(R.id.resultTextView);
        resultsTV.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setWorkings(String givenvalue)
    {
        workings = workings + givenvalue;
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkforPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            resultsTV.setText(String.valueOf(result.doubleValue()));
    }

    private void checkforPowerOf()
    {
        ArrayList<Integer> IndexOfPowers = new ArrayList<>();
        for(int i=0; i < workings.length(); i++)
        {
            if(workings.charAt(i) == '^')
                IndexOfPowers.add(i);

        }
        formula=workings;
        TempFormula=workings;
        for(Integer index: IndexOfPowers)
        {
            changeFormula(index);
        }
        formula=TempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft ="";
        String numberRight ="";

        for(int i = index + 1; i< workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i>= 0; i--)
        {
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        TempFormula = TempFormula.replace(original,changed);

    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return  true;

        return  false;
    }

    public void allClearAction(View view) {
           workingsTV.setText("");
           workings ="";
           resultsTV.setText("");
           workings ="";
           leftBracket = true;
    }

    public void clearOnClick(View view)
    {
            workingsTV.setText("");
            workings = "";
            leftBracket = true;

//         workings = String.valueOf(workingsTV.length());
//         if (workings.length() > 0)
//             workingsTV.setText("") = workingsTV.setText(workingsTV.length(0, workingsTV.length() - 1));

//        workingsTV.setText(workings.length() -1);
//         workings="";
//        workingsTV.setTextSize(workingsTV.setTextSize( 0, (float)workings.length() - 1));
//        val length = workingsTV.length()
//        if (length > 0)
//            workingsTV.text = workingsTV.text.subSequence(0, length -1 )
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
        {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view)
    {
        setWorkings("^");
    }

    public void divisionOnClick(View view)
    {
        setWorkings("/");
    }

    public void sevenOnClick(View view)
    {
        setWorkings("7");
    }

    public void eightOnClick(View view)
    {
        setWorkings("8");
    }

    public void nineOnClick(View view)
    {
        setWorkings("9");
    }

    public void timesOnClick(View view)
    {
        setWorkings("*");
    }

    public void fourOnClick(View view)
    {
        setWorkings("4");
    }

    public void fiveOnClick(View view)
    {
        setWorkings("5");
    }

    public void sixOnClick(View view)
    {
        setWorkings("6");
    }

    public void plusOnClick(View view)
    {
        setWorkings("+");
    }

    public void oneOnClick(View view)
    {
        setWorkings("1");
    }

    public void twoOnClick(View view)
    {
        setWorkings("2");
    }

    public void threeOnClick(View view)
    {
        setWorkings("3");
    }

    public void minusOnClick(View view)
    {
        setWorkings("-");
    }

    public void decimalOnClick(View view)
    {
        setWorkings(".");
    }

    public void zeroOnClick(View view)
    {
        setWorkings("0");
    }
}