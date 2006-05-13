/* XawkException.java

{{IS_NOTE
	$Id: XawkException.java,v 1.3 2006/02/27 03:42:07 tomyeh Exp $
	Purpose:
		
	Description:
		
	History:
		Mon Sep  2 20:59:27  2002, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2002 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.xawk;

/**
 * Represents Xawk fails to parse a XML.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.3 $ $Date: 2006/02/27 03:42:07 $
 * @see Xawk
 */
public class XawkException extends com.potix.lang.CommonException {
	public XawkException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public XawkException(String s) {
		super(s);
	}
	public XawkException(Throwable cause) {
		super(cause);
	}
	public XawkException() {
	}

	public XawkException(int code, Object[] fmtArgs, Throwable cause) {
		super(code, fmtArgs, cause);
	}
	public XawkException(int code, Object fmtArg, Throwable cause) {
		super(code, fmtArg, cause);
	}
	public XawkException(int code, Object[] fmtArgs) {
		super(code, fmtArgs);
	}
	public XawkException(int code, Object fmtArg) {
		super(code, fmtArg);
	}
	public XawkException(int code, Throwable cause) {
		super(code, cause);
	}
	public XawkException(int code) {
		super(code);
	}
}
