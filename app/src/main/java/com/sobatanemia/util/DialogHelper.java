package com.sobatanemia.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.sobatanemia.R;


public class DialogHelper {

    public static Dialog loading(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.alert_dialog_loading);
        return dialog;

    }


}

