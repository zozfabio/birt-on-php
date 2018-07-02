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
                System.out.println("JavaBridge server waiting!");
                BirtEngine.init();
                instance.waitFor();
            } catch (InterruptedException ex) {
                System.out.println("JavaBridge server interrupted!");
            } finally {
                instance.destroy();
                BirtEngine.destroy();
                System.out.println("JavaBridge server destroied!");
            }
        })).start();
    }
}
