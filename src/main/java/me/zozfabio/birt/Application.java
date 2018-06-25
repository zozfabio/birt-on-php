package me.zozfabio.birt;

import io.soluble.pjb.bridge.JavaBridgeRunner;

/**
 * @author zozfabio
 */
public class Application {

    public static void main(String[] args) {
        JavaBridgeRunner instance = JavaBridgeRunner.getInstance("INET:8080");
        (new Thread(() -> {
            try {
                System.out.println("Servidor JavaBridge aguardando iteração!");
                instance.waitFor();
            } catch (InterruptedException ex) {
                System.out.println("Servidor JavaBridge foi interrompido!");
            } finally {
                instance.destroy();
                System.out.println("Servidor JavaBridge foi destruido!");
            }
        })).start();
    }
}
