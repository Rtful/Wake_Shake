package zli.ch.wake_shake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import java.time.LocalDateTime;

import zli.ch.wake_shake.databinding.FragmentSecondBinding;

public class AlarmFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Shake shake;
    private boolean isEdit = false;
    private boolean vibrate = false;
    private boolean deleteAfter = false;
    private LocalDateTime time;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Bundle args = getArguments();
        if (args != null) {
            String action = args.getString("action");
            Log.e("args", action);
            switch (action) {
                case "edit":
                    TextView alarmText = requireView().findViewById(R.id.alarmText);
                    alarmText.setText("Alarm editieren");
                    this.isEdit = true;
                    break;
                case "delete":
                    break;
            }
        }
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String alarmJSON = gson.toJson(alarms);
                //Log.e("alarmsSaved", alarmsJSON);
                //storage.edit().putString("alarmsJSON", alarmsJSON).apply();
            }
        });

        binding.deleteAfterSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch deleteAfterSwitch = requireView().findViewById(R.id.deleteAfterSwitch);
                deleteAfter = deleteAfterSwitch.isChecked();
            }
        });
        binding.vibrationSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch deleteAfterSwitch = requireView().findViewById(R.id.vibrationSwitch);
                vibrate = deleteAfterSwitch.isChecked();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}