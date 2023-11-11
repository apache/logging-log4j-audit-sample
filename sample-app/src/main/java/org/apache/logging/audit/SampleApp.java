/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.logging.audit;

import java.math.BigDecimal;
import java.net.InetAddress;

import org.apache.logging.log4j.audit.LogEventFactory;
import org.apache.logging.log4j.audit.event.Deposit;
import org.apache.logging.log4j.audit.event.Login;
import org.apache.logging.log4j.core.util.NetUtils;

public class SampleApp {

    public static void main(String[] args) throws Exception {
        String hostName = NetUtils.getLocalHostname();
        RequestContext.setHostName(hostName);
        String inetAddress = InetAddress.getLocalHost().getHostAddress();
        RequestContext.setIpAddress(inetAddress);
        RequestContext.setLoginId("testuser");
        Login login = LogEventFactory.getEvent(Login.class);
        login.logEvent();
        String result = login("testuser");
        login.setCompletionStatus(result);
        login.logEvent();
        Deposit deposit = LogEventFactory.getEvent(Deposit.class);
        deposit.setAccount(123456);
        deposit.setAmount(new BigDecimal(100.00));
        deposit.logEvent();
        result = deposit(deposit);
        deposit.setCompletionStatus(result);
        deposit.logEvent();
        RequestContext.clear();
    }

    private static String login(String user) {
        RequestContext.setUserId("1111");
        RequestContext.setAccountNumber(12345L);
        return "Success";
    }

    private static String deposit(Deposit deposit) {
        return "Success";
    }
}
