/*
 * Copyright 2015 Smom - Software Module Management.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.smom.datasource.core.service;

import br.com.smom.datasource.core.PostgreSQLDataSource;
import br.com.smom.datasource.exported.api.PostgreSQL;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PostgreSQLService implements PostgreSQL {

    @Inject
    PostgreSQLDataSource postgreSQLDataSource;
    
    @Override
    public Connection getConnection() throws Exception {
        try {
            System.out.println(postgreSQLDataSource.toString());
            return postgreSQLDataSource.getConnection();
        } catch (SQLException ex) {
            String msgError = "Error get connection PostgreSQL. Cause: " + ex.getMessage();
            System.out.println(msgError);
            throw new Exception(msgError);
        }
    }

    @Override
    public void commit(Connection connection) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
            connection.close();
        }
    }

    @Override
    public void rollback(Connection connection) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }

}
