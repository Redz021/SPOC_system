package com.example.demo.config;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

//@Component
//public class sessionListener implements HttpSessionListener, HttpSessionAttributeListener {
//    @Override
//    public void sessionCreated(HttpSessionEvent event) {
//        System.out.println("---sessionCreated----");
//
//        HttpSession session = event.getSession();
//        System.out.println("sessionId"+session.getId());
//        ServletContext application = session.getServletContext();
//        // 在application范围由一个HashSet集保存所有的session
//        @SuppressWarnings("unchecked")
//        HashSet<HttpSession> sessions = (HashSet<HttpSession>) application.getAttribute("sessions");
//        if (sessions == null) {
//            sessions = new HashSet<HttpSession>();
//            application.setAttribute("sessions", sessions);
//        }
//        // 新创建的session均添加到HashSet集中
//        sessions.add(session);
//        // 可以在别处从application范围中取出sessions集合
//        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
//
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {
//        System.out.println("---sessionDestroyed----");
//        HttpSession session = event.getSession();
//        System.out.println("deletedSessionId: " + session.getId());
//        System.out.println(session.getCreationTime());
//        System.out.println(session.getLastAccessedTime());
//        ServletContext application = session.getServletContext();
//        HashSet<?> sessions = (HashSet<?>) application.getAttribute("sessions");
//        // 销毁的session均从HashSet集中移除
//        sessions.remove(session);
//
//
//    }
//
//}


