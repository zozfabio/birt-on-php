package me.zozfabio.birt;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

/**
 * @author zozfabio
 */
public class BirtEngine {
    
    private static IReportEngine engine;

    public static IReportEngine getBirtEngine() {
        return engine;
    }

    static void init() {
        EngineConfig ec = new EngineConfig();
        try {
            Platform.startup(ec);

            IReportEngineFactory ref = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

            engine = ref.createReportEngine(ec);
        } catch (Throwable ex) {
            System.out.println("Error on engine init! " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    static void destroy() {
        try {
            engine.destroy();
            Platform.shutdown();
            RegistryProviderFactory.releaseDefault();
        } catch (Throwable ex) {
            System.out.println("Error on engine destroy!");
            ex.printStackTrace();
        }

    }
}
