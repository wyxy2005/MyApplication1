package com.example.gll.myapplication.util;

import android.content.Context;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StreamUtils {
	
	public static String get(Context context, int id) {
		InputStream stream = context.getResources().openRawResource(id);
		return read(stream);
	}
	
	public static String read(InputStream stream) {
		return read(stream, "utf-8");
	}
	
	public static String read(InputStream is, String encode) {
        if (is != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                return sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getBase64(byte[] base) {
        String base64 = null;
        try {
            base64 = Base64.encodeToString(base, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }
}
