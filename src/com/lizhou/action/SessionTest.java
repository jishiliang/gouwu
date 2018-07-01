package com.lizhou.action;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionTest implements HttpSessionAttributeListener, HttpSessionListener {


    public void attributeRemoved(HttpSessionBindingEvent e)  { 
        System.out.println("remove: "+e.getName() +"¡·"+e.getValue());
    }

    public void attributeAdded(HttpSessionBindingEvent e)  { 
    	System.out.println("add: "+e.getName() +"¡·"+e.getValue());
         
    }

    public void attributeReplaced(HttpSessionBindingEvent e)  { 
    	System.out.println("replace: "+e.getName() +"¡·"+e.getValue());
         
    }

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("sessionDestroyed");
		
	}
	
}
