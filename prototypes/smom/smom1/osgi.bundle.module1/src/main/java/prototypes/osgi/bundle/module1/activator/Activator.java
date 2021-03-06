package prototypes.osgi.bundle.module1.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("OSGi Bundles Module 1 start...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("OSGi Bundles Module 1 stop...");
    }

}
