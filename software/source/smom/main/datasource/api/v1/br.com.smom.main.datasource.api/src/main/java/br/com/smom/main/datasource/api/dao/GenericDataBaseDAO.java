/*
 * Smom - Software Module Management.
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
package br.com.smom.main.datasource.api.dao;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.enums.DataSourceMessages;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericDataBaseDAO {

    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected int executeInsert(String query, Object... params) throws DataSourceException {

        int rowsAffected;
        int generatedKey = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    generatedKey = resultSet.getInt(1);
                }
                return generatedKey;
            } else {
                if (logService != null) {
                    logService.error(DataSourceMessages.ERROR_CREATE_ENTITY.getMessage("Cause=none rows affected."));
                }
                throw new DataSourceException(DataSourceMessages.ERROR_CREATE_ENTITY.getMessage("Cause=none rows affected."));
            }

        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES, e);
        }
    }

    protected void executeUpdate(String query, Object... params) throws DataSourceException {

        int rowsAffected;
        PreparedStatement preparedStatement;
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 2) {
                if (logService != null) {
                    logService.error(DataSourceMessages.ERROR_UPDATE_ENTITY.getMessage("Cause=none rows affected."));
                }
                throw new DataSourceException(DataSourceMessages.ERROR_UPDATE_ENTITY.getMessage("Cause=none rows affected."));
            }

        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES, e);
        }
    }

    protected ResultSet executeQuery(String query, Object... params) throws DataSourceException {

        PreparedStatement preparedStatement;
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES, e);
        }
    }

    protected ResultSet executeQuery(String query) throws DataSourceException {

        PreparedStatement preparedStatement;
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_EXECUTE_QUERY_POSTGRES, e);
        }
    }

}
