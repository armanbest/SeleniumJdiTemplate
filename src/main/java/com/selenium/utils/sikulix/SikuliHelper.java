package com.selenium.utils.sikulix;

import io.sterodium.extensions.client.FileExtensionClient;
import io.sterodium.extensions.client.SikuliExtensionClient;
import io.sterodium.extensions.node.rmi.TargetFactory;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import java.io.File;
import java.net.URL;

public class SikuliHelper {

    public static final int TIMEOUT = 3000;
    public static final int QUERY = 100;

    private final DesktopScreenRegion desktop;
    private final TargetFactory targetFactory;
    private final Mouse mouse;
    private final Keyboard keyboard;

    private final String remoteFilePath;

    public SikuliHelper() {
        desktop = new DesktopScreenRegion();
        targetFactory = new TargetFactory();
        mouse = new DesktopMouse();
        keyboard = new DesktopKeyboard();
        remoteFilePath = "";
    }

    public SikuliHelper(RemoteWebDriver driver) {
        URL hub = ((HttpCommandExecutor) driver.getCommandExecutor()).getAddressOfRemoteServer();
        SikuliExtensionClient client = new SikuliExtensionClient(hub.getHost(), hub.getPort(), driver.getSessionId().toString());
        FileExtensionClient fileClient = new FileExtensionClient(hub.getHost(), hub.getPort(), driver.getSessionId().toString());
        desktop = client.getDesktop();
        targetFactory = client.getTargetFactory();
        mouse = client.getMouse();
        keyboard = client.getKeyboard();

        client.uploadResourceBundle("images");
        remoteFilePath = fileClient.upload("files") + File.separator + "files" + File.separator;
    }

    public TextBox findTextBox(String resource) {
        ScreenRegion screenRegion = waitForElement(resource);
        return new DefaultTextBox(mouse, keyboard, targetFactory, screenRegion);
    }

    public UiComponent find(String resource) {
        ScreenRegion screenRegion = waitForElement(resource);
        return new DefaultUiComponent(mouse, targetFactory, screenRegion);
    }


    private ScreenRegion waitForElement(String resource) {
        ImageTarget imageTarget = targetFactory.createImageTarget(resource);

        ScreenRegion screenRegion = tryToFind(desktop, imageTarget);
        if (screenRegion != null) {
            return screenRegion;
        }
        throw new ElementNotFoundException(resource);

    }

    private static ScreenRegion tryToFind(ScreenRegion desktop, ImageTarget imageTarget) {
        for (int i = 0; i < TIMEOUT / QUERY; i++) {
            try {
                ScreenRegion screenRegion = desktop.find(imageTarget);
                if (screenRegion != null) {
                    return screenRegion;
                }
                Thread.sleep(QUERY);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        return null;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }
}
