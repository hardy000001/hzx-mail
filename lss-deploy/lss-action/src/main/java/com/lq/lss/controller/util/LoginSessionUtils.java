package com.lq.lss.controller.util;

/**
 *
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.lss.model.SessionUser;

/**
 * @author lanbo
 */
public class LoginSessionUtils {

    public static final String ADMIN_USER = "ADMIN_USER";


    public static String setLoginGetSessionId(HttpServletRequest request, HttpServletResponse response, SessionUser sp) {
        // 提交微信
        String sessionId = sp.getUserId() + "_" + System.currentTimeMillis();
        String encodeSID = SessionHelper.encode(sessionId);
        // 换session
        SessionHelper.clearUserSessionMap(request);
        SessionHelper.initUserSessionMap(request, response, encodeSID);
        LoginSessionUtils.setUserInSessionId(encodeSID, sp);
        return sessionId;
    }

    public static boolean isLogined(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) SessionHelper.getValue(request, ADMIN_USER);
        if (sessionUser != null)
            return true;
        return false;
    }

    public static void setUserInSessionId(String sessionId, SessionUser loginUser) {
        SessionHelper.setValue(sessionId, ADMIN_USER, loginUser);
    }

    public static SessionUser getUserInSession(HttpServletRequest request) {

        return (SessionUser) SessionHelper.getValue(request, ADMIN_USER);
    }

    public static void clearSessionUser(HttpServletRequest request) {
        SessionHelper.removeKey(request, ADMIN_USER);
    }


}
