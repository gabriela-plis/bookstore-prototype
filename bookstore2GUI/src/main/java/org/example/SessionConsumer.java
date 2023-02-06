package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SessionConsumer {

    default public HttpSession getSession (HttpServletRequest request) {
        return request.getSession(false);
    }

    public void getSessionAttributes (HttpServletRequest request);
}
