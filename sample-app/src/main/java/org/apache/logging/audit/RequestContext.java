/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.audit;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.audit.request.ChainedMapping;
import org.apache.logging.log4j.audit.request.ClientServerMapping;
import org.apache.logging.log4j.audit.request.LocalMapping;
import org.apache.logging.log4j.audit.request.RequestContextBase;
import org.apache.logging.log4j.audit.request.RequestContextMapping;
import org.apache.logging.log4j.audit.request.RequestContextMappings;
import org.apache.logging.log4j.core.util.UuidUtil;

public class RequestContext extends RequestContextBase {
    private static final String REQUEST_ID = "requestId";
    private static final String SESSION_ID = "sessionId";
    private static final String ACCOUNT_NUMBER = "accountNumber";
    private static final String IP_ADDRESS = "ipAddress";
    private static final String USER_ID = "userId";
    private static final String LOGIN_ID = "loginId";
    private static final String HOST_NAME = "hostName";
    private static final String CALLING_HOST = "callingHost";

    private static final String HEADER_PREFIX = "mycorp-context-";

    private static RequestContextMapping[] mappingArray =
            new RequestContextMapping[] { new ClientServerMapping(LOGIN_ID),
            new ClientServerMapping(ACCOUNT_NUMBER), new ClientServerMapping(IP_ADDRESS),
            new ClientServerMapping(USER_ID),
            new ClientServerMapping(REQUEST_ID), new ClientServerMapping(SESSION_ID),
            new ChainedMapping(HOST_NAME, CALLING_HOST), new LocalMapping(CALLING_HOST)};

    static {
        RequestContextBase.setMappings(new RequestContextMappings(mappingArray, HEADER_PREFIX));
    }

    public static String getRequestId() {
        String uuidStr = ThreadContext.get(REQUEST_ID);
        UUID uuid;
        if (uuidStr == null) {
            uuid = UuidUtil.getTimeBasedUuid();
            ThreadContext.put(REQUEST_ID, uuid.toString());
        }
        return uuidStr;
    }

    private static void setRequestId(String requestId) {
        if (requestId != null) {
            ThreadContext.put(REQUEST_ID, requestId);
        }
    }

    public static void setSessionId(UUID sessionId) {
        if (sessionId != null) {
            ThreadContext.put(SESSION_ID, sessionId.toString());
        }
    }

    public static void setSessionId(String sessionId) {
        if (sessionId != null) {
            ThreadContext.put(SESSION_ID, sessionId);
        }
    }

    public static void setAccountNumber(Long accountNumber) {
        ThreadContext.put(ACCOUNT_NUMBER, accountNumber.toString());
    }

    public static Long getAccountNumber() {
        String value = ThreadContext.get(ACCOUNT_NUMBER);
        if (value == null || value.length() == 0) {
            return 0L;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return 0L;
        }
    }

    public static void setIpAddress(String address) {
        ThreadContext.put(IP_ADDRESS, address);
    }

    public static String getIpAddress() {
        return ThreadContext.get(IP_ADDRESS);
    }

    public static void setUserId(String userId) {
        ThreadContext.put(USER_ID, userId);
    }

    public static String getUserId() {
        return ThreadContext.get(USER_ID);
    }

    public static void setLoginId(String loginId) {
        ThreadContext.put(LOGIN_ID, loginId);
    }

    public static String getLoginId() {
        return ThreadContext.get(LOGIN_ID);
    }

    public static String getHostName() {
        return ThreadContext.get(HOST_NAME);
    }

    public static void setHostName(String hostName) {
        ThreadContext.put(HOST_NAME, hostName);
    }

    public static String getCallingHost() {
        return ThreadContext.get(CALLING_HOST);
    }

    public static void setCallingHost(String hostName) {
        ThreadContext.put(CALLING_HOST, hostName);
    }
}
