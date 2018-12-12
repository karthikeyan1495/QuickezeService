package com.star.quickezeservice.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;


public class MySnackBar {

    private static final MySnackBar ourInstance = new MySnackBar();

    public static MySnackBar getInstance() {
        return ourInstance;
    }

    private MySnackBar() {
    }

    public void showPositiveSnackBar(Activity activity, String message){
        showBar(activity,message,"#ffffff");
    }

    public void showNagativeSnackBar(Activity activity, String message){
        showBar(activity,message,"#ffffff");
    }

    private void showBar(Activity activity,String message, String color){
        TSnackbar snackbar = TSnackbar.make(activity.findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        snackbar.setMaxWidth(Util.getInstance().getScreenWidth(activity));
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#ff0000"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor(color));
        textView.setTextSize(17);
        snackbar.show();
    }


}
