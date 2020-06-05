/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.food.clicktofood.Adapter;

import java.io.File;

public class InternalDataProvider {

    private final String TAG = "xapp_"+this.getClass().getSimpleName();

    private static InternalDataProvider instance ;

    private File imageFile;

    private InternalDataProvider(){

    }

    public static InternalDataProvider getInstance(){
        if(instance == null){
            instance = new InternalDataProvider();
        }
        return instance;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

}
