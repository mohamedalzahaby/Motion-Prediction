package com.example.MotionPrediction.Other;

public abstract class ClassFinder {
    public static String getClassName(Object object) {
        String x = object.getClass().getName();
        int i = x.lastIndexOf('.')+1;
        String className = x.substring(i);
        return className;
    }

    public static String getClassTAG(Object object) {
        String x = object.getClass().getName();
        int i = x.lastIndexOf('.')+1;
        String className = x.substring(i);
        return "mytag_"+className;
    }
}
