/* AttributesMap.java

{{IS_NOTE
	$Id: AttributesMap.java,v 1.2 2006/02/27 03:41:54 tomyeh Exp $
	Purpose:
		
	Description:
		
	History:
		Tue Dec  6 22:40:23     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.el.impl;

import java.util.Set;
import java.util.AbstractSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Enumeration;

/**
 * A sketetal implementation for Map to wrap something with enumeration of
 * attributes, which must be String.
 *
 * <p>It is mainly used to implement sessionScope and requestScope in EL.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.2 $ $Date: 2006/02/27 03:41:54 $
 */
public abstract class AttributesMap extends StringKeysMap {
	private Set _entries;
	public Set entrySet() {
		if (_entries == null) {
			_entries = new AbstractSet() {
				public int size() {
					return AttributesMap.this.size();
				}
				public boolean contains(Object o) {
					return AttributesMap.this.containsKey(o);
				}
				public boolean isEmpty() {
					return AttributesMap.this.isEmpty();
				}
				public Iterator iterator() {
					return new EntryIter();
				}
			};
		}
		return _entries;
	}

	public int size() {
		int sz = 0;
		for (Enumeration e = getKeys(); e.hasMoreElements(); ++sz)
			e.nextElement();
		return sz;
	}
	public boolean isEmpty() {
		return !getKeys().hasMoreElements();
	}
	public Object put(Object key, Object val) {
		final Object o = getValue((String)key);
		setValue((String)key, val);
		return o;
	}
	public Object remove(Object key) {
		final Object o = getValue((String)key);
		removeValue((String)key);
		return o;
	}

	/** Sets the value of the specified key.
	 * <p>Default: throw exception.
	 */
	protected void setValue(String key, Object val) {
		throw new UnsupportedOperationException("readonly");
	}
	/** Removes the key and its associated value.
	 * <p>Default: throw exception.
	 */
	protected void removeValue(String key) {
		throw new UnsupportedOperationException("readonly");
	}
}
