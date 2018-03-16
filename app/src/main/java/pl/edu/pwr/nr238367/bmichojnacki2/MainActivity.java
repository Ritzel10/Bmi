package pl.edu.pwr.nr238367.bmichojnacki2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int default_spinner_selection_id = 0;
    private boolean spinnerClickedByUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add logic to app's button
        final Button button = findViewById(R.id.bmiButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText weightText = findViewById(R.id.weightEdit);
                EditText heightText = findViewById(R.id.heightEdit);

                Spinner units = findViewById(R.id.unitSpinner);
                String s = units.getSelectedItem().toString();
                AbstractBmi bmi;
                //pick a BMI calculating class depending on spinner option
                if (s.equals(getResources().getString(R.string.units_kgcm))) {
                    bmi = new BmiMetricCentimeters();

                } else if (s.equals(getResources().getString(R.string.units_lbin))) {
                    bmi = new BmiImperial();

                } else {
                    bmi = new BmiMetric();
                }
                double bmiVal;
                try {
                    bmiVal = parseStringsAndCalculateBmiValue(weightText.getText().toString(), heightText.getText().toString(), bmi);
                }
                //if data are incorrect show a toast with a message
                catch (IllegalArgumentException ex) {
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_bad_data), Toast.LENGTH_SHORT).show();
                    return;
                }

                //if correct send bmi value to Result activity
                Intent intentResult = new Intent(getApplicationContext(), ResultActivity.class);
                intentResult.putExtra(getResources().getString(R.string.extra_bmi), bmiVal);
                startActivity(intentResult);

            }
        });

        //restore saved values
        SharedPreferences preferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        updateSpinnerSelection((Spinner) findViewById(R.id.unitSpinner), preferences.getInt(getString(R.string.extra_unit_id), default_spinner_selection_id));

        EditText weightEdit = findViewById(R.id.weightEdit);
        String weight = preferences.getString(getString(R.string.extra_weight), getString(R.string.default_text));
        updateEditTextValue(weightEdit, weight);
        EditText heightEdit = findViewById(R.id.heightEdit);
        String height = preferences.getString(getString(R.string.extra_height), getString(R.string.default_text));
        updateEditTextValue(heightEdit, height);


        final Spinner spinner = findViewById(R.id.unitSpinner);
        //ontouch and onkey listeners are used to distinguish between user and programmatic click
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.performClick();
                spinnerClickedByUser = true;
                return false;
            }
        });
        spinner.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                spinnerClickedByUser = true;
                return false;

            }
        });
        //clean input values when changing units
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //if spinner was clicked by the user
                if (spinnerClickedByUser) {
                    ((EditText) findViewById(R.id.heightEdit)).setText(getString(R.string.default_text));
                    ((EditText) findViewById(R.id.weightEdit)).setText(getString(R.string.default_text));
                }
                //return to default value
                spinnerClickedByUser = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private double parseStringsAndCalculateBmiValue(String weight, String height, AbstractBmi bmi) {
        double weightVal;
        double heightVal;
        try {
            weightVal = Double.parseDouble(weight);
            heightVal = Double.parseDouble(height);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
        bmi.setHeight(heightVal);
        bmi.setWeight(weightVal);
        try {
            return bmi.calculateBmi();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //show author info activity
            case R.id.action_settings:
                Intent intentAuthor = new Intent(getApplicationContext(), AuthorInfoActivity.class);
                startActivity(intentAuthor);
                return true;

            //save current input values
            case R.id.action_save:
                //create a bundle filled with values
                Bundle values = new Bundle();
                saveValuesToBundle(values);
                //save values from bundle to shared preferences
                saveValuesToSharedPreferences(values, getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE));
                Toast.makeText(getApplicationContext(), getString(R.string.toast_saved), Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        saveValuesToBundle(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void saveValuesToBundle(Bundle bundle) {
        //save input values
        EditText weightText = findViewById(R.id.weightEdit);
        EditText heightText = findViewById(R.id.heightEdit);
        bundle.putString(getString(R.string.extra_weight), weightText.getText().toString());
        bundle.putString(getString(R.string.extra_height), heightText.getText().toString());

        //save spinner value
        Spinner unitsSpinner = findViewById(R.id.unitSpinner);
        bundle.putInt(getString(R.string.extra_unit_id), (int) unitsSpinner.getSelectedItemId());
    }

    private void saveValuesToSharedPreferences(Bundle bundle, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //get values from bundle and put it in sharedpreferences
        editor.putString(getString(R.string.extra_weight), bundle.getString(getString(R.string.extra_weight)));
        editor.putString(getString(R.string.extra_height), bundle.getString(getString(R.string.extra_height)));
        editor.putInt(getString(R.string.extra_unit_id), bundle.getInt(getString(R.string.extra_unit_id)));
        editor.apply();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        //restore spinner value
        Spinner spinner = findViewById(R.id.unitSpinner);
        updateSpinnerSelection(spinner, savedInstanceState.getInt(getString(R.string.extra_unit_id)));

        //restore input values if present
        String weight = savedInstanceState.getString(getString(R.string.extra_weight));
        String height = savedInstanceState.getString(getString(R.string.extra_height));
        EditText weightEdit = findViewById(R.id.weightEdit);
        updateEditTextValue(weightEdit, weight);
        EditText heightEdit = findViewById(R.id.heightEdit);
        updateEditTextValue(heightEdit, height);

    }

    private void updateEditTextValue(EditText editText, String value) {
        if (value != null) {
            editText.setText(value);
        }
    }

    private void updateSpinnerSelection(Spinner spinner, int selectionId) {
        if (spinner != null && selectionId < spinner.getAdapter().getCount()) {
            spinner.setSelection(selectionId, false);
        }
    }

}
