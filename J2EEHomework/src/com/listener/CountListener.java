package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CountListener
 *
 */
@WebListener
public class CountListener implements ServletContextListener,
HttpSessionListener, HttpSessionAttributeListener {
	public static final String logInSessionName="login";
	//在线总人数
	private static int allNumCounter = 0;
	//在线已登陆
	private static int onlineCounter = 0;
	//在线游客
	private static int guestCounter = 0;

	public CountListener() {
		// TODO Auto-generated constructor stub
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("allNumCounter",
				Integer.toString(allNumCounter));
		servletContext.setAttribute("onlineCounter",
				Integer.toString(onlineCounter));
		servletContext.setAttribute("guestCounter",
				Integer.toString(guestCounter));
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		visit(session);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		if (logInSessionName.equals(se.getName())) {
			HttpSession session = se.getSession();
			logIn(session);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		if (logInSessionName.equals(se.getName())) {
			HttpSession session = se.getSession();
			logOut(session);
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	public synchronized void visit(HttpSession session) {
		ServletContext servletContext = session.getServletContext();
		guestCounter++;
		allNumCounter = onlineCounter + guestCounter;

		servletContext.setAttribute("allNumCounter",
				Integer.toString(allNumCounter));
		servletContext.setAttribute("onlineCounter",
				Integer.toString(onlineCounter));
		servletContext.setAttribute("guestCounter",
				Integer.toString(guestCounter));
	}

	public synchronized void sessionInvalid(HttpSession session) {
		ServletContext servletContext = session.getServletContext();
		if (session.getAttribute(logInSessionName) != null) {
			onlineCounter--;

		} else {
			guestCounter--;
		}
		allNumCounter = onlineCounter + guestCounter;

		servletContext.setAttribute("allNumCounter",
				Integer.toString(allNumCounter));
		servletContext.setAttribute("onlineCounter",
				Integer.toString(onlineCounter));
		servletContext.setAttribute("guestCounter",
				Integer.toString(guestCounter));
	}

	public synchronized void logIn(HttpSession session) {
		ServletContext servletContext = session.getServletContext();
		//
		onlineCounter++;
		//
		guestCounter--;
		allNumCounter = onlineCounter + guestCounter;

		servletContext.setAttribute("allNumCounter",
				Integer.toString(allNumCounter));
		servletContext.setAttribute("onlineCounter",
				Integer.toString(onlineCounter));
		servletContext.setAttribute("guestCounter",
				Integer.toString(guestCounter));
	}

	public synchronized void logOut(HttpSession session) {
		ServletContext servletContext = session.getServletContext();
		onlineCounter--;
guestCounter++;
		allNumCounter = onlineCounter + guestCounter;

		servletContext.setAttribute("allNumCounter",
				Integer.toString(allNumCounter));
		servletContext.setAttribute("onlineCounter",
				Integer.toString(onlineCounter));
		servletContext.setAttribute("guestCounter",
				Integer.toString(guestCounter));
	}

}
