/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Admin
 */
public class EncryptDecrypt {
     // bắt buộc phải có static để gọi trực tiếp nếu kg sẽ lỗi
    // vì nó sẽ kg lưu biến
    public static String Encrypt(String keys) {
        //để lưu dữ liệu mã hóa
        byte[] diget = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //chuyển định dạng dữ liệu về dạng UTF8
            //UTF8 là định dạng
            md.update(keys.getBytes("UTF8"));
            //digest() để mã hóa
            diget=md.digest();
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(EncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return DatatypeConverter.printBase64Binary(diget);
    }
}
