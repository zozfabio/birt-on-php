package com.example.birt;

import io.soluble.pjb.bridge.JavaBridgeRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zozfabio
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        JavaBridgeRunner instance = JavaBridgeRunner.getInstance("INET:8080");
        (new Thread(() -> {
            try {
                logger.info("Servidor JavaBridge aguardando iteração!");
                instance.waitFor();
            } catch (InterruptedException ex) {
                logger.info("Servidor JavaBridge foi interrompido!");
            } finally {
                instance.destroy();
                logger.info("Servidor JavaBridge foi destruido!");
            }
        })).start();
    }
}
