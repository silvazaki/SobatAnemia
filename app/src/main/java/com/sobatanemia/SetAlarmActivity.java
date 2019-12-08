package com.sobatanemia;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.michaldrabik.classicmaterialtimepicker.CmtpDateDialogFragment;
import com.michaldrabik.classicmaterialtimepicker.CmtpTimeDialogFragment;
import com.michaldrabik.classicmaterialtimepicker.OnDatePickedListener;
import com.michaldrabik.classicmaterialtimepicker.OnTime24PickedListener;
import com.michaldrabik.classicmaterialtimepicker.model.CmtpDate;
import com.michaldrabik.classicmaterialtimepicker.model.CmtpTime24;

public class SetAlarmActivity extends AppCompatActivity {

    EditText tanggal, jam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        tanggal = findViewById(R.id.ed_tgl);
        jam = findViewById(R.id.ed_jam);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePickerDialog();
            }
        });
    }

    private void showTime24PickerDialog() {
        CmtpTimeDialogFragment dialog = CmtpTimeDialogFragment.newInstance();
        dialog.setInitialTime24(23, 30);
        dialog.setOnTime24PickedListener(new OnTime24PickedListener() {
            @Override
            public void onTimePicked(CmtpTime24 cmtpTime24) {
//                Toast.makeText(SetAlarmActivity.this, cmtpTime24+"", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show(getSupportFragmentManager(), "TimePicker");
    }

    private void showDateTimePickerDialog() {
        CmtpDateDialogFragment dialog = CmtpDateDialogFragment.newInstance();
        dialog.setInitialDate(1, 6, 1990);
        dialog.setCustomYearRange(1990, 2020);
        dialog.setCustomSeparator(".");
        dialog.setOnDatePickedListener(new OnDatePickedListener() {
            @Override
            public void onDatePicked(CmtpDate cmtpDate) {
//                Toast.makeText(SetAlarmActivity.this, cmtpDate+"", Toast.LENGTH_SHORT).show();
                showTime24PickerDialog();
            }
        });
        dialog.show(getSupportFragmentManager(), "TimePicker");
    }
}
