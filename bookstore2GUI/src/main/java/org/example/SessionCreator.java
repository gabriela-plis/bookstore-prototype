package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SessionCreator {

    default public HttpSession createSession (HttpServletRequest request) {
        return request.getSession();
    }
    public void setSessionAttributes(HttpServletRequest request, HttpSession session);
}
