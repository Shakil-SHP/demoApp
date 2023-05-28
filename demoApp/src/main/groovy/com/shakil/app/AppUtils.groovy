package com.shakil.app

import org.grails.web.util.WebUtils

class AppUtils {
    static saveResponse(Boolean isSuccess, def model){
        return [isSuccess: isSuccess, model: model]
    }

    static getApplicationSession(){
        return WebUtils.retrieveGrailsWebRequest().session
    }

    static infoMessage(String message, boolean status = true){
        println "---------------------"
        println message
        println "---------------------"
        return [info : message, success: status]
    }

    static String baseURL(){
        return  "${getApplicationSession().getServletContext().getContextPath()}/"
    }
}
