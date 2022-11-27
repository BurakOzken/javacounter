package burak.ozken.counter;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsClass {
    int upperLimit,lowerLimit,currentValue;
    boolean upVib,upSound,lowVib,lowSound;

    static SettingsClass settingsClass = null;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private SettingsClass(Context context) {
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loadValues();
    }

        public static SettingsClass getSettingsClass (Context context){
            if(settingsClass==null){
                settingsClass=new SettingsClass(context);
            }
            return settingsClass;
        }
        public void saveValues(){
            editor.putInt("upperLimit",upperLimit);
            editor.putInt("lowerLimit",lowerLimit);
            editor.putInt("currentValue",currentValue);
            editor.putBoolean("upVib",upVib);
            editor.putBoolean("upSound",upSound);
            editor.putBoolean("lowVib",lowVib);
            editor.putBoolean("lowSound",lowSound);
            editor.commit();
        }
        public void loadValues() {
            upperLimit = sharedPreferences.getInt("upperLimit", 10);
            lowerLimit = sharedPreferences.getInt("lowerLimit", 0);
            currentValue = sharedPreferences.getInt("currentValue", 0);
            upVib = sharedPreferences.getBoolean("upVib", false);
            upSound = sharedPreferences.getBoolean("upSound", false);
            lowVib = sharedPreferences.getBoolean("lowVib", false);
            lowSound = sharedPreferences.getBoolean("lowSound", false);
        }

    }
