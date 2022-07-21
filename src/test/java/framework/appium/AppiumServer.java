package framework.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AppiumServer {
    private static AppiumDriverLocalService service;

    public static void startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        try {
            service = AppiumDriverLocalService.buildService(builder);
            log.info("Start appium server");
            service.start();
        } catch (Exception e) {
            log.info("Exception in the appium server start: " + e);
            if (service.isRunning()) {
                log.info("Found that appium service is running. Stopping appium server..");
                service.stop();
                service.clearOutPutStreams();
                log.info("Build appium service..");
                service = AppiumDriverLocalService.buildService(builder);
                log.info("Start appium server again..");
                service.start();
            }
        }
    }

    public static void stopServer() {
        log.info("Stop appium server");
        service.stop();
    }
}
