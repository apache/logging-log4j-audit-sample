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
package org.apache.logging.log4j.audit.service.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.logging.log4j.audit.rest.RequestContextFilter;
import org.apache.logging.log4j.audit.service.RequestContext;
import org.springframework.web.WebApplicationInitializer;

public class RequestContextInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        RequestContextFilter filter = new RequestContextFilter(RequestContext.class);
        servletContext.addFilter("requestContextFilter", filter).addMappingForUrlPatterns(null, true, "/*");
        servletContext.log("Added RequestContextFilter");
    }
}
