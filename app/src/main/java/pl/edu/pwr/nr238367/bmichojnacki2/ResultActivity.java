package pl.edu.pwr.nr238367.bmichojnacki2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Handling back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // return to main activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        //displaying bmi result and changing background color
        Intent intent = getIntent();
        String id = getResources().getString(R.string.extra_bmi);
        Bundle extras = intent.getExtras();
        TextView bmi = findViewById(R.id.bmi);
        if (extras != null) {
            double bmiVal = extras.getDouble(id);
            displayValue(bmiVal, bmi);
            changeBackgroundColorBasedOnBmiCategory(bmiVal);
        }
    }

    private void displayValue(double bmiVal, TextView textView) {
        textView.setText(String.format(Locale.getDefault(), getString(R.string.result_format), bmiVal));
    }

    private void changeBackgroundColorBasedOnBmiCategory(double bmiVal) {
        int color = 0;
        switch (BmiCategory.classifyBmiCategory(bmiVal)) {
            case UNDERWEIGHT:
                color = ResourcesCompat.getColor(getResources(), R.color.colorBmiUnderweight, null);
                break;
            case NORMAL:
                color = ResourcesCompat.getColor(getResources(), R.color.colorBmiNormal, null);
                break;
            case OVERWEIGHT:
                color = ResourcesCompat.getColor(getResources(), R.color.colorBmiOverweight, null);
                break;
            case OBESE:
                color = ResourcesCompat.getColor(getResources(), R.color.colorBmiObese, null);
                break;
        }
        getWindow().getDecorView().setBackgroundColor(color);
    }
}
