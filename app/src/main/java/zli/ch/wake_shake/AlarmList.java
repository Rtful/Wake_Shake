package zli.ch.wake_shake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import zli.ch.wake_shake.databinding.FragmentFirstBinding;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AlarmList extends AppCompatActivity {

    private FragmentFirstBinding binding;
    private ArrayList<Alarm> alarms;
    private SharedPreferences storage;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.alarms = new ArrayList<>();
        this.gson = new Gson();
        this.storage = getSharedPreferences("wake_shake", Context.MODE_PRIVATE);
        //storage.edit().clear().apply();
        if (storage.contains("alarmsJSON")) {
            String alarmsJSON = storage.getString("alarmsJSON", "");
            alarms = gson.fromJson(alarmsJSON, new TypeToken<ArrayList<Alarm>>() {
            }.getType());
        }
        // alarms = new ArrayList<Alarm>();

        setContentView(R.layout.fragment_first);

        // get reference to ListView
        ListView listView = findViewById(R.id.alarms);

        // create adapter
        ArrayAdapter<Alarm> adapter = new ArrayAdapter<Alarm>(this,
                R.layout.alarm, // layout for each item
                R.id.previewTime, // ID of TextView inside layout
                alarms); // the data to populate the list

        listView.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAlarmOnClick();
            }
        });
    }

    private void newAlarmOnClick() {
        ActivityResultLauncher<Intent> startForResult =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                // Get the message string extra from the return intent
                                Intent data = result.getData();
                                if (data != null) {
                                    String message = data.getStringExtra("message");
                                    Log.d("AlarmList", "Received message: " + message);
                                }
                            }
                        });
        Intent intent = new Intent(getBaseContext(), Alarm.class);

        // Put the string extra into the intent
        intent.putExtra("action", "create");

        // Start the activity and pass the intent to it using the contract
        startForResult.launch(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("alarms", String.valueOf(alarms.size()));
        String alarmsJSON = gson.toJson(alarms);
        Log.e("alarmsSaved", alarmsJSON);
        //storage.edit().putString("alarmsJSON", alarmsJSON).apply();
        super.onDestroy();
        binding = null;
    }

    public void addAlarm(Alarm alarm) {

    }
    public static void editAlarm(Alarm alarm) {

    }
}