package com.perfomatix.namelistapp.utility;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import java.util.Random;

/**
 * This class contains common utility methods
 */
public class Utils {

    /**
     * Method to pic a random color from a list of colors stored in a color array
     * @param context Application context
     * @param colorArray Color array identifier stored in colors.xml
     * @return a random color identifier from the color array
     */
    public static int getColor(Context context, int colorArray){
        int[] rainbow = context.getResources().getIntArray(colorArray);
        int i = 0;
        i = new Random().nextInt(rainbow.length);
        return rainbow[i];
    }

    /**
     * Method to set Oval background for Avatar placeholder and assign random colors to it
     * @param v The root view to be set oval background
     * @param backgroundColor the color to be set for the oval background
     */
    public static void customView(View v, int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setCornerRadius(50);
        shape.setColor(backgroundColor);
        v.setBackground(shape);
    }
}
