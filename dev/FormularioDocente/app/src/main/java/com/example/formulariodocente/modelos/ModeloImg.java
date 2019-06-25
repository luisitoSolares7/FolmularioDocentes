package com.example.formulariodocente.modelos;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ModeloImg {
    Bitmap bitmap;
    String encode64;

    public ModeloImg(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.encode64 = getBase64(bitmap);
    }

    private String getBase64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageString;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getEncode64() {
        return encode64;
    }

    public void setEncode64(String encode64) {
        this.encode64 = encode64;
    }
}
