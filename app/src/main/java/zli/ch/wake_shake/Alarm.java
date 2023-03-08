package zli.ch.wake_shake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.time.LocalDateTime;

import zli.ch.wake_shake.databinding.FragmentSecondBinding;

public class Alarm extends Activity implements Parcelable {

    private FragmentSecondBinding binding;
    private Shake shake;
    private boolean isEdit = false;
    private boolean vibrate = false;
    private boolean deleteAfter = false;
    private LocalDateTime time;

    public Alarm() {

    }

    protected Alarm(Parcel in) {
        isEdit = in.readByte() != 0;
        vibrate = in.readByte() != 0;
        deleteAfter = in.readByte() != 0;
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a new intent to return the Alarm activity back to the AlarmList activity
        Intent returnIntent = new Intent(this, AlarmList.class);

        // Put the string extra into the return intent
        String message = "Hello from Alarm activity!";
        returnIntent.putExtra("message", message);

        // Set the result to be returned to the AlarmList activity and finish this activity
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeByte((byte) (isEdit ? 1 : 0));
        parcel.writeByte((byte) (vibrate ? 1 : 0));
        parcel.writeByte((byte) (deleteAfter ? 1 : 0));
    }
}