package com.product.application.util;

import com.product.application.configuration.CustomUserDetails;
import com.product.application.exception.ProductException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Integer getUserId() {
       try {
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
           return userDetails.getId();
       }catch (Exception ex){
           throw new ProductException("ERROR");
       }
    }
    public static String get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
