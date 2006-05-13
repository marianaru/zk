/* ProcessingInstruction.java

{{IS_NOTE

	$Header: //time/potix/rd/cvs/m3/pxcommon/src/com/potix/idom/ProcessingInstruction.java,v 1.8 2006/05/11 07:16:23 tomyeh Exp $
	Purpose: 
	Description: 
	History:
	2001/10/22 20:53:28, Create, Tom M. Yeh.
}}IS_NOTE

Copyright (C) 2001 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.idom;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
import java.io.IOException;

import com.potix.mesg.MCommon;
import com.potix.lang.Objects;
import com.potix.lang.Strings;
import com.potix.lang.SystemException;
import com.potix.util.Maps;
import com.potix.idom.impl.*;

/**
 * The iDOM processing instruction.
 *
 * @author <a href="mailto:tomyeh@potix.com">Tom M. Yeh</a>
 * @version $Revision: 1.8 $ $Date: 2006/05/11 07:16:23 $
 */
public class ProcessingInstruction extends AbstractItem
implements org.w3c.dom.ProcessingInstruction {
	/** The target. */
	protected String _target;
	/** The raw data. */
	protected String _rawData;

	/** Constructor.
	 */
	public ProcessingInstruction(String target, String data) {
		setTarget(target);
		setData(data);
	}
	/** Constructor.
	 */
	public ProcessingInstruction(String target, Map data) {
		setTarget(target);
		setData(data);
	}
	/** Constructor.
	 */
	protected ProcessingInstruction() {
	}

	//-- ProcessingInstruction extras --//
	public final String getTarget() {
		return _target;
	}
	public final void setTarget(String target) {
		checkWritable();
		if (!Objects.equals(_target, target)) {
			Verifier.checkPITarget(target, getLocator());
			_target = target;
			setModified();
		}
	}

	public final String getData() {
		return _rawData;
	}
	public final void setData(String data) {
		checkWritable();

		if (data == null)
			data = "";

		if (!Objects.equals(_rawData, data)) {
			_rawData = data;
			setModified();
		}
	}
	/** Returns the parsed data in the form of Map (never null).
	 */
	public final Map parseData() {
		final Map map = parseToMap(null, getData());
		return map != null ? map: Collections.EMPTY_MAP;
	}
	/**
	 * Sets the raw data with a data map.
	 * Each entry in the data map is a (name, value) pair.
	 *
	 * @exception com.potix.util.IllegalSyntaxException if name contains
	 * an invalid character: '=', ' ', '\'', '"'
	 */
	public final void setData(Map data) {
		final String notAllowed = "= '\"";
		for (final Iterator it = data.keySet().iterator(); it.hasNext();) {
			final String key = (String)it.next();
			final int j = Strings.anyOf(key, notAllowed, 0);
			if (j < key.length()) { //found
				final char cc = key.charAt(j);
				throw new SystemException(MCommon.ILLEGAL_CHAR,
						cc + " (0x" + Integer.toHexString(cc) + ')');
			}
		}

		setData(Maps.toString(data, '"', ' '));
	}

	/**
	 * Parses the raw data into a map.
	 * Each entry in the data map is a (name, value) pair.
	 * This method will convert a value to a number, either Integer
	 * or Double, if appropriate.
	 *
	 * <p>Most of characters are considered as ordinary (like 'a'),
	 * exception '"', '='
	 *
	 * <p>Example, the string wil cause ("a12", Intger(12)),
	 * ("b+3", null), ("345", null), ("c6", "abc=125&3?5"):<br>
	 * a12 =12 b+3 345 c6=\t'abc=125&3?5'
	 *
	 * @exception com.potix.util.IllegalSyntaxException if syntax erros
	 */
	public static final Map parseToMap(Map map, String rawData) {
		if (rawData == null || rawData.trim().length() == 0)
			return map;

		return Maps.parse(map, rawData, ' ', '"');
	}

	//-- Item --//
	public final String getName() {
		return getTarget();
	}
	public final void setName(String name) {
		setTarget(name);
	}
	public final String getText() {
		return getData();
	}
	public final void setText(String text) {
		setData(text);
	}

	//-- Node --//
	public final short getNodeType() {
		return PROCESSING_INSTRUCTION_NODE;
	}

	//-- org.w3c.dom.ProcessingInstruction --//

	//-- Object --//
	public String toString() {
		StringBuffer sb = new StringBuffer(64)
			.append("[PI: ").append(_target);
		if (_rawData.length() > 0)
			sb.append(' ').append(_rawData);
		return sb.append(']').toString();
	}
}
