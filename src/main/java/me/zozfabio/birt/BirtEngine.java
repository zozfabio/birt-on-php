package me.zozfabio.birt;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

import java.io.Closeable;

/**
 * @author zozfabio
 */
public class BirtEngine {
    
    private static IReportEngine engine;
    
    public static IReportEngine getBirtEngine() {
        if (engine != null) {
            return engine;
        }

        EngineConfig ec = new EngineConfig();
        try {
            Platform.startup(ec);
            
            IReportEngineFactory ref = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

            return engine = ref.createReportEngine(ec);
        } catch (BirtException ex) {
            System.out.println("Erro ao iniciar a engine!");
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Closeable getCloseable() {
        return () -> {
            engine.destroy();
            Platform.shutdown();
            RegistryProviderFactory.releaseDefault();
        };
    }
}
