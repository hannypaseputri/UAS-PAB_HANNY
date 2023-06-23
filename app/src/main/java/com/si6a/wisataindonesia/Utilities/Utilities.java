package com.si6a.wisataindonesia.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import com.si6a.wisataindonesia.model.TravelData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utilities {
    public static final String PREFERENCE_FILE_KEY = "N3PXSS8BnUGaho5w4uBmtuzdKcpZNvWQXxhf76bF";
    public static final String BASE_URL = "https://expressjs-mongodb-wisata-app.vercel.app/";
    private static final String KEY_LOGGED_IN = "loggedIn";
    private static final String KEY_UID = "userId";
    public static TravelData travelData;

    public static void clearSharedPref(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(KEY_LOGGED_IN);
        editor.remove(KEY_UID);
        editor.clear();
        editor.apply();
    }

    public static void setIsLoggedIn(Context context, Boolean isLoggedIn) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public static Boolean isLoggedIn(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_LOGGED_IN, false);
    }


    public static void setUID(Context context, String UID) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_UID, UID);
        editor.apply();
    }

    public static String getUID(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        return sp.getString(KEY_UID, null);
    }


    /**
     * Convert Image to Base64
     * @param context
     * @param uri
     * @return
     */
    public static String convertImageToBase64(Context context, Uri uri) {
        String result = "";
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] bytes = stream.toByteArray();
            result = Base64.encodeToString(bytes, Base64.URL_SAFE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Uri convertBase64ToUri(Context context, String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.URL_SAFE);
        String fileName = System.currentTimeMillis() + ".png"; // You can change the file name and extension according to your requirements
        File outputFile = new File(context.getCacheDir(), fileName);

        try {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(decodedBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(outputFile);
    }


}
