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

package com.sshtools.ssh;

/**
 * <p>
 * The base interface for all SSH authentication mechanisms and authentication
 * result constants.
 * </p>
 * 
 * @author Lee David Painter
 */
public interface SshAuthentication {

	/** The authentication completed **/
	public static final int COMPLETE = 1;
	/** The authentication failed **/
	public static final int FAILED = 2;
	/** The authentication succeeded but further authentication is required **/
	public static final int FURTHER_AUTHENTICATION_REQUIRED = 3;
	/** The authentication was cancelled by the user */
	public static final int CANCELLED = 4;
	/** The public key provided is acceptable for authentication **/
	public static final int PUBLIC_KEY_ACCEPTABLE = 5;

	/**
	 * Set the username for this authentication attempt.
	 * 
	 * @param username
	 */
	public void setUsername(String username);

	/**
	 * Get the username for this authentication attempt.
	 * 
	 * @return the username used.
	 */
	public String getUsername();

	/**
	 * The SSH authentication method name
	 * 
	 * @return String
	 */
	public String getMethod();
}
