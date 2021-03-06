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
package br.com.smom.log.api.enums;

import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.services.FormatterMessages;

public enum LogMessages implements Messages {

    ERROR_LOG_CONFIGURATION(0, "");

    private int code;
    private String description;

    private LogMessages(int code, String message) {
        this.code = code;
        this.description = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return FormatterMessages.log(this);
    }

    @Override
    public String getMessage(String cause) {
        return FormatterMessages.log(this, cause);
    }

    @Override
    public String getMessage(Throwable cause) {
        return FormatterMessages.log(this, cause);
    }

    @Override
    public Messages[] getValue() {
        return values();
    }

    @Override
    public String getName() {
        return name();
    }

}
