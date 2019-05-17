package com.example.formulariodocente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogoToken {
    private Context context1;
    private ProgressDialog dialog;
    final Dialog dialogo;

    public DialogoToken(final Context context) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.activity_verificacion__codigo);
        dialogo.setCancelable(true);
        dialogo.show();
    }
}
