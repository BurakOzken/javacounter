package burak.ozken.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn_minus,btn_plus,btn_settings;
    TextView tv_value;

    SettingsClass settingsClass;

    Vibrator vibrator = null;
    MediaPlayer mediaPlayer = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_settings = (Button) findViewById(R.id.btn_ayar);
        tv_value = (TextView) findViewById(R.id.value);

        Context context = getApplicationContext();
        settingsClass = SettingsClass.getSettingsClass(context);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateValue(1);
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateValue(-1);
            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AyarlarActivity.class));
            }
        });


    }
    public void updateValue(int step){
        if (step <0){
            if (settingsClass.currentValue + step < settingsClass.lowerLimit){
                settingsClass.currentValue = settingsClass.lowerLimit;
                if (settingsClass.lowVib){
                    alertVib();
                }
                if (settingsClass.lowSound){
                    alertSound();
                }
            }
            else
                settingsClass.currentValue +=step;
        }
        if (step >0){
            if (settingsClass.currentValue + step > settingsClass.upperLimit){
                settingsClass.currentValue = settingsClass.upperLimit;
                if (settingsClass.upVib){
                    alertVib();
                }
                if (settingsClass.upSound){
                    alertSound();
                }
            }
            else
                settingsClass.currentValue += step;
        }
        tv_value.setText(String.valueOf(settingsClass.currentValue));

    }
    @Override
    protected void onResume() {
        super.onResume();
        tv_value.setText(String.valueOf(settingsClass.currentValue));

    }

    @Override
    protected void onPause() {
        super.onPause();
        settingsClass.saveValues();

    }

    public void alertSound(){
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
    public void alertVib(){
        if (vibrator.hasVibrator()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));
            }
            vibrator.vibrate(1000);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN)
                    updateValue(-5);
                return  true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN)
                    updateValue(5);
                return  true;
        }
        return super.dispatchKeyEvent(event);
    }
}