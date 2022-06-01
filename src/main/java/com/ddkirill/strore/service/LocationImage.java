package com.ddkirill.strore.service;

import java.io.File;

public class LocationImage {

    private final File image;

    public LocationImage(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }
}
