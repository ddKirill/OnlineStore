package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.ProductEntity;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.OutputStream;

//@Service
//public class OpenPictureService {
//
//    private ProductEntity productEntity;
//
//    @Autowired
//    public OpenPictureService(ProductEntity productEntity) {
//        this.productEntity = productEntity;
//    }
//
//    public File getImage() {
//
//        File image = new File(productEntity.getLocationImage());
//        ResponseEntity
//        return ;
//    }
//
//}
