/**
 * Copyright 2003-2014 SSHTOOLS Limited. All Rights Reserved.
 *
 * For product documentation visit https://www.sshtools.com/
 *
 * This file is part of J2SSH Maverick.
 *
 * J2SSH Maverick is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * J2SSH Maverick is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with J2SSH Maverick.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sshtools.events;

/**
 * <p>
 * The root class from which all event state objects shall be derived.
 * <p>
 * All Events are constructed with a reference to the object, the "source", that
 * is logically deemed to be the object upon which the Event in question
 * initially occurred upon.
 * 
 * @since JDK1.1
 */

public class EventObject {

	/**
	 * The object on which the Event initially occurred.
	 */
	protected transient Object source;

	/**
	 * Constructs a prototypical Event.
	 * 
	 * @param source
	 *            The object on which the Event initially occurred.
	 * @exception IllegalArgumentException
	 *                if source is null.
	 */
	public EventObject(Object source) {
		if (source == null) {
			throw new IllegalArgumentException("null source");
		}

		this.source = source;
	}

	/**
	 * The object on which the Event initially occurred.
	 * 
	 * @return The object on which the Event initially occurred.
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * Returns a String representation of this EventObject.
	 * 
	 * @return A a String representation of this EventObject.
	 */
	public String toString() {
		return getClass().getName() + "[source=" + source + "]";
	}
}
