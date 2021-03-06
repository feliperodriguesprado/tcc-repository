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
package br.com.smom.main.util.api.services;

import br.com.smom.main.util.api.enums.UtilMessages;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * Class responsible for getting bundles.
 */
public class BundleProvider {

    public static Bundle getBundle(Class<?> classFromBundle) {
        Bundle bundle = FrameworkUtil.getBundle(classFromBundle);

        if (bundle != null) {
            InternalLog.info(UtilMessages.INFO_BUNDLE_FOUND.getDescription() + ": " + bundle.getSymbolicName());
            InternalLog.info(String.format(UtilMessages.INFO_BUNDLE_FOUND.getDescription() + ": %s.", bundle.getSymbolicName()));
            return bundle;
        } else {
            InternalLog.warning(UtilMessages.WARN_BUNDLE_NOT_FOUND.getMessage("Class " + classFromBundle.getName() + " was not found as a bundle"));
            return null;
        }
    }

    public static Bundle[] getBundleList() {

        BundleContext bundleContext;

        try {
            bundleContext = getBundle(BundleProvider.class).getBundleContext();
            return bundleContext.getBundles();
        } catch (Exception e) {
            InternalLog.warning(UtilMessages.ERROR_GET_BUNDLE_LIST.getMessage(e.getMessage()));
            return null;
        }
    }

}
