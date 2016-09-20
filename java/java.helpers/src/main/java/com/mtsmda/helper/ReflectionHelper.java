package com.mtsmda.helper;

import java.lang.annotation.*;
import java.lang.reflect.Field;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by dminzat on 9/1/2016.
 */
public class ReflectionHelper {

    public static <T> Field getField(T object, String fieldName) throws NoSuchFieldException {
        Field field = null;
        try {
            return object.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            try {
                return object.getClass().getField(fieldName);
            } catch (NoSuchFieldException e1) {
                boolean isSuperClass = false;
                Class<?> superClass = null;
                Class<?> currentClass = object.getClass();
                do {
                    try {
                        superClass = currentClass.getSuperclass();
                        isSuperClass = ObjectHelper.objectIsNotNull(superClass);
                        if(isSuperClass){
                            currentClass = currentClass.getSuperclass();
                            field = superClass.getDeclaredField(fieldName);
                        }
                        if (ObjectHelper.objectIsNotNull(field)) {
                            break;
                        }
                    } catch (NoSuchFieldException e2) {

                    }
                }
                while (isSuperClass);
            }
        }
        return field;
    }

    public static <T> Field[] getFields(T object) {
        return object.getClass().getDeclaredFields();
    }

    public static <T> boolean isFieldInClass(T object, String fieldName) {
        try {
            Field field = getField(object, fieldName);
            if(ObjectHelper.objectIsNull(field)){
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static <T, V> V getFieldValueInClass(T object, String fieldName) {
        try {
            Field field = getField(object, fieldName);
            field.setAccessible(true);
            V v = (V) field.get(object);
            field.setAccessible(false);
            return v;
        } catch (Exception e) {
            return null;
        }
    }

    /*public static <T> Field getMethod(T object, String methodName) {
        try {
            return object.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException | SecurityException e) {
            *//*return object.getClass().getField(methodName);*//*
        }
    }*/

    /*public static <O, A extends Annotation, AR> AR getAnnotation(O object, String fieldName, Class<A> aClass, AR ar) {
        try {
            Field field = getField(object, fieldName);
            A declaredAnnotation = field.getDeclaredAnnotation(aClass);

            return (AR)declaredAnnotation;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public static <O> Annotation[] getFieldAnnotations(O object, String fieldName) {
        try {
            Field fieldR = getField(object, fieldName);
            return fieldR.getDeclaredAnnotations();
        } catch (NoSuchFieldException e) {
            return null;
        }
    }*/

}