package com.sobatanemia;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.service.AlarmReceiver;
import com.sobatanemia.util.PreferenceUtil;
import com.sobatanemia.util.SharedPrefHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.sobatanemia.STATIC_NAME.ALARM_TIME;
import static com.sobatanemia.STATIC_NAME.WEEKS;

public class MainActivity extends AppCompatActivity {


    Button button;
    EditText tanggal, jam;
    Calendar alarmCalendar = Calendar.getInstance();
    PreferenceUtil pref;
    SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        pref = new PreferenceUtil(this);
        prefHelper = new SharedPrefHelper(this);
        setupViews();
        setListeners();
    }

    private void setupViews() {
        button = findViewById(R.id.button);
        tanggal = findViewById(R.id.ed_tgl);
        jam = findViewById(R.id.ed_jam);
        long alarmTime = pref.getLong(ALARM_TIME);
        if (alarmTime != 0) {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat df2 = new SimpleDateFormat("HH:mm");
            Date date = new Date(alarmTime);
            tanggal.setText(df.format(date));
            jam.setText(df2.format(date));
        }
        int weeks = prefHelper.getInt(WEEKS);
        if (weeks > 4) {
            pref.putInt(WEEKS, 1);
        }
    }

    private void setListeners() {

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int monthOfYear = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int minute = calendar.get(Calendar.MINUTE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.my_dialog_theme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, final int y, final int m, final int d) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, R.style.my_dialog_theme, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                alarmCalendar.set(Calendar.YEAR, y);
                                alarmCalendar.set(Calendar.MONTH, m);
                                alarmCalendar.set(Calendar.DAY_OF_MONTH, d);
                                alarmCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                alarmCalendar.set(Calendar.MINUTE, minute);
                                alarmCalendar.set(Calendar.SECOND, 0);
                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                DateFormat df2 = new SimpleDateFormat("HH:mm");
                                tanggal.setText(df.format(alarmCalendar.getTime()));
                                jam.setText(df2.format(alarmCalendar.getTime()));

                            }
                        }, hour, minute, true);
                        timePickerDialog.show();
                    }
                }, year, monthOfYear, dayOfMonth);
                datePickerDialog.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(tanggal.getText().toString())) {
                    unregister();
                    register(alarmCalendar.getTimeInMillis());
                    Toast.makeText(MainActivity.this, "Menyimpan alarm", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Silahkan isikan tanggal dan jam..", Toast.LENGTH_SHORT).show();
//                    unregister();
                }
            }
        });

    }

    // 登録
    private void register(long alarmTimeMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getPendingIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(alarmTimeMillis, null), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent);
        }
        // 保存
        pref.putLong(ALARM_TIME, alarmTimeMillis);
    }

    // 解除
    private void unregister() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPendingIntent());
        pref.delete(ALARM_TIME);
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setClass(this, AlarmReceiver.class);
        // 複数のアラームを登録する場合はPendingIntent.getBroadcastの第二引数を変更する
        // 第二引数が同じで第四引数にFLAG_CANCEL_CURRENTがセットされている場合、2回以上呼び出されたときは
        // あとからのものが上書きされる
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
