/*
 * Copyright (c) 2019 IBM Corporation and others
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.app;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.MicroProfileApplication;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class AppContainerConfig implements SharedContainerConfiguration {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>()
                    .withDatabaseName("testdb")
                    .withNetworkAliases("postgres")
                    .withUsername("testuser")
                    .withPassword("testpass")
                    .withExposedPorts(5432)
                    .withInitScript("init.sql");
	
    @Container
    public static MicroProfileApplication app = new MicroProfileApplication()
                    .withAppContextRoot("/myservice")
                    .withEnv("PG_HOST", "postgres")
                    .withEnv("PG_PORT", "" + PostgreSQLContainer.POSTGRESQL_PORT)
                    .withEnv("PG_USER", postgres.getUsername())
                    .withEnv("PG_PASS", postgres.getPassword())
                    .withEnv("PG_DBNAME", postgres.getDatabaseName());
    
    @Override
    public void startContainers() {
        postgres.start();
        app.start();
    }

}
