package zli.ch.wake_shake;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import zli.ch.wake_shake.databinding.FragmentFirstBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AlarmList extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayList<Alarm> alarms;
    private SharedPreferences storage;
    private Gson gson;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.alarms = new ArrayList<>();
        this.gson = new Gson();
        this.storage = this.requireActivity().getSharedPreferences("wake_shake", Context.MODE_PRIVATE);
        //storage.edit().clear().apply();
        if (storage.contains("alarmsJSON")) {
            String alarmsJSON = storage.getString("alarmsJSON", "");
            alarms = gson.fromJson(alarmsJSON, new TypeToken<ArrayList<Alarm>>() {
            }.getType());
        }
        // alarms = new ArrayList<Alarm>();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("action", "add");
                NavHostFragment.findNavController(AlarmList.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        Log.e("alarms", String.valueOf(alarms.size()));
        String alarmsJSON = gson.toJson(alarms);
        Log.e("alarmsSaved", alarmsJSON);
        //storage.edit().putString("alarmsJSON", alarmsJSON).apply();
        super.onDestroyView();
        binding = null;
    }

    public void addAlarm(Alarm alarm) {

    }
    public static void editAlarm(Alarm alarm) {

    }
}