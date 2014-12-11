package com.siersolutions.util;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.siersolutions.service.BussinessException;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {
	
	private static Log log = LogFactory.getLog(JsfExceptionHandler.class);
	private ExceptionHandler wrapped;
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable e = context.getException();
			boolean isHandle = false;
			BussinessException modalException = getBussinessException(e);

			try {
				if (e instanceof ViewExpiredException) {
					isHandle = true;
					redirect("/");
				}else if(modalException != null){
					isHandle = true;
					FacesUtil.addErrorMessage(modalException.getMessage());
				}else{
					isHandle = true;
					log.error("Error in the system: " + e.getMessage(), e);
					redirect("/error.xhtml");
				}
			} finally {
				if(isHandle){
					events.remove();
				}
			}
		}
		getWrapped().handle();
	}

	private BussinessException getBussinessException(Throwable e){
		if(e instanceof BussinessException){
			return (BussinessException)e;
		}else if(e.getCause() != null){
			return getBussinessException(e.getCause());
		}
		return null;
	}
	
	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();
			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}
}
