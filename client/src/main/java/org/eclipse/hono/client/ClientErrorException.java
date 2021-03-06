/*******************************************************************************
 * Copyright (c) 2016, 2017 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package org.eclipse.hono.client;


/**
 * Indicates a client error that occurred as the outcome of a service invocation.
 *
 */
public class ClientErrorException extends ServiceInvocationException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception for a client error code.
     *
     * @param errorCode The code representing the erroneous outcome.
     * @throws IllegalArgumentException if the code is not &ge; 400 and &lt; 500.
     */
    public ClientErrorException(final int errorCode) {
        this(errorCode, null, null);
    }

    /**
     * Creates a new exception for a client error code and a detail message.
     *
     * @param errorCode The code representing the erroneous outcome.
     * @param msg The detail message.
     * @throws IllegalArgumentException if the code is not &ge; 400 and &lt; 500.
     */
    public ClientErrorException(final int errorCode, final String msg) {
        this(errorCode, msg, null);
    }

    /**
     * Creates a new exception for a client error code and a root cause.
     *
     * @param errorCode The code representing the erroneous outcome.
     * @param cause The root cause.
     * @throws IllegalArgumentException if the code is not &ge; 400 and &lt; 500.
     */
    public ClientErrorException(final int errorCode, final Throwable cause) {
        this(errorCode, null, cause);
    }

    /**
     * Creates a new exception for a client error code, a detail message and a root cause.
     *
     * @param errorCode The code representing the erroneous outcome.
     * @param msg The detail message.
     * @param cause The root cause.
     * @throws IllegalArgumentException if the code is not &ge; 400 and &lt; 500.
     */
    public ClientErrorException(final int errorCode, final String msg, final Throwable cause) {
        super(errorCode, msg, cause);
        if (errorCode < 400 || errorCode >= 500) {
            throw new IllegalArgumentException("client error code must be >= 400 and < 500");
        }
    }

}
