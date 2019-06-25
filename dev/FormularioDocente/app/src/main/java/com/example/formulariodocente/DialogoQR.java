package com.example.formulariodocente;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class DialogoQR {
    final Dialog dialogo;
    private Context context1;
    private ImageView imageView;
    private ProgressDialog dialog;
    private Bitmap bitmap;
    public DialogoQR(final Context context, Bitmap bitmap) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.vista_frm_fotocopia);
        dialogo.setCancelable(true);
        this.bitmap=bitmap;
        init();
        dialogo.show();
    }

    private void init() {
        imageView=dialogo.findViewById(R.id.imagenVistaFotocopia);
        imageView.setImageBitmap(bitmap);
    }

}
