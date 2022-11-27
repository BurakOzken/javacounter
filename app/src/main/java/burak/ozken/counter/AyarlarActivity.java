package burak.ozken.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class AyarlarActivity extends AppCompatActivity {

Button btn_up_minus, btn_up_plus,btn_low_minus,btn_low_plus;
CheckBox up_vib, up_sound, low_vib,low_sound;
EditText low_value,up_value;
SettingsClass settingsClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);

    btn_up_minus=(Button) findViewById(R.id.btn_up_minus);
    btn_up_plus=(Button) findViewById(R.id.btn_up_plus);
    btn_low_minus=(Button) findViewById(R.id.btn_low_minus);
    btn_low_plus=(Button) findViewById(R.id.btn_low_plus);
    up_vib= (CheckBox) findViewById(R.id.up_vib);
    up_sound=(CheckBox) findViewById(R.id.up_sound);
    low_vib= (CheckBox) findViewById(R.id.low_vib);
    low_sound=(CheckBox) findViewById(R.id.low_sound);
    up_value=(EditText) findViewById(R.id.up_value);
    low_value=(EditText) findViewById(R.id.low_value);

settingsClass=SettingsClass.getSettingsClass(getApplicationContext());

        btn_up_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.upperLimit++;
                up_value.setText(String.valueOf(settingsClass.upperLimit));
            }
        });
        btn_up_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.upperLimit--;
                up_value.setText(String.valueOf(settingsClass.upperLimit));
            }
        });
        btn_low_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.lowerLimit++;
                low_value.setText(String.valueOf(settingsClass.lowerLimit));
            }
        });
        btn_low_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.lowerLimit--;
                low_value.setText(String.valueOf(settingsClass.lowerLimit));
            }
        });

        up_vib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.upVib =b;
            }
        });
        up_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.upSound =b;
            }
        });
        low_vib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.lowVib =b;
            }
        });
        low_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.lowSound =b;
            }
        });
        up_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (up_value.getText().toString().length() !=0){
                    settingsClass.upperLimit = Integer.parseInt(up_value.getText().toString());
                }
            }
        });
        low_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (low_value.getText().toString().length() !=0){
                    settingsClass.lowerLimit = Integer.parseInt(low_value.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        up_value.setText(String.valueOf(settingsClass.upperLimit));
        low_value.setText(String.valueOf(settingsClass.lowerLimit));
        up_vib.setChecked(settingsClass.upVib);
        up_sound.setChecked(settingsClass.upSound);
        low_vib.setChecked(settingsClass.lowVib);
        low_sound.setChecked(settingsClass.lowSound);
    }

    @Override
    protected void onPause() {
        super.onPause();
        settingsClass.saveValues();

    }
}