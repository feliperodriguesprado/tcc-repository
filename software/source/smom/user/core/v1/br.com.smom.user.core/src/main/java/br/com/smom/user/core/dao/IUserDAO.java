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
package br.com.smom.user.core.dao;

import br.com.smom.user.api.exceptions.UserException;
import br.com.smom.user.api.model.entities.UserEntity;
import java.sql.Connection;
import java.util.List;

public interface IUserDAO {
    
    public void setConnection(Connection connection);

    public UserEntity create(UserEntity user) throws UserException;

    public void update(UserEntity user) throws UserException;

    public void delete(UserEntity user) throws UserException;

    public UserEntity getById(int id) throws UserException;
    
    public List<UserEntity> getAll() throws UserException;
    
    public UserEntity getByUsername(UserEntity user) throws UserException;

}