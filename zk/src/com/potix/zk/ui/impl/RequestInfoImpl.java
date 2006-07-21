/* RequestInfoImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed Apr 19 13:49:49     2006, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.zk.ui.impl;

import javax.servlet.ServletContext;

import com.potix.util.resource.Locator;

import com.potix.zk.ui.Execution;
import com.potix.zk.ui.WebApp;
import com.potix.zk.ui.Desktop;
import com.potix.zk.ui.Session;
import com.potix.zk.ui.sys.RequestInfo;
import com.potix.zk.ui.http.PageDefinitions;

/**
 * An implementation of {@link RequestInfo}.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 */
public class RequestInfoImpl implements RequestInfo {
	private final WebApp _wapp;
	private final Desktop _desktop;
	private final Session _sess;
	private Locator _locator;
	private final Object _request;

	/** Constructor
	 *
	 * @param wapp the Web application, never null.
	 * @param sess the session, never null.
	 * @param desktop the desktop, or null if not created yet.
	 * @param request the request, or null if not available.
	 * @param locator the locator used to locate taglib and other resources,
	 * never null.
	 */
	public RequestInfoImpl(WebApp wapp, Session sess,
	Desktop desktop, Object request, Locator locator) {
		if (wapp == null || sess == null || locator == null)
			throw new IllegalArgumentException("null");
		_wapp = wapp;
		_sess = sess;
		_desktop = desktop;
		_request = request;
		_locator = locator;
	}
	/** Constructor.
	 *
	 * @param desktop the desktop, never null.
	 * @param request the request, or null if not available.
	 * @param locator the locator used to locate taglib and other resources,
	 * never null.
	 */
	public RequestInfoImpl(Desktop desktop, Object request, Locator locator) {
		this(desktop.getWebApp(), desktop.getSession(), desktop, request, locator);
	}
	/** Construcotr.
	 *
	 * @param exec the current execution, never null
	 * @param path the related path used to get the locator, or null.
	 */
	public RequestInfoImpl(Execution exec, String path) {
		this(exec.getDesktop(), exec.getNativeRequest(), getLocator(exec, path));
	}
	private static Locator getLocator(Execution exec, String path) {
		final Object ctx = exec.getDesktop().getWebApp().getNativeContext();
		if (ctx instanceof ServletContext)
			return PageDefinitions.getLocator((ServletContext)ctx, path);
		throw new UnsupportedOperationException("Unknown context: "+ctx);
	}

	public final WebApp getWebApp() {
		return _wapp;
	}
	public final Session getSession() {
		return _sess;
	}
	public final Desktop getDesktop() {
		return _desktop;
	}
	public final Object getNativeRequest() {
		return _request;
	}
	public final Locator getLocator() {
		return _locator;
	}
	public final void setLocator(Locator locator) {
		if (locator == null) throw new IllegalArgumentException("null");
		_locator = locator;
	}
}
