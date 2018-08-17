package com.selenium.utils.sikulix;

import io.sterodium.extensions.node.rmi.TargetFactory;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Mouse;

/**
 * @author Alexey Nikolaenko alexey@tcherezov.com
 *         Date: 24/11/2015
 */
public class DefaultUiComponent implements UiComponent {

    private Mouse mouse;
    private TargetFactory targetFactory;
    private ScreenRegion screenRegion;

    DefaultUiComponent(Mouse mouse, TargetFactory targetFactory, ScreenRegion screenRegion) {
        this.mouse = mouse;
        this.targetFactory = targetFactory;
        this.screenRegion = screenRegion;
    }

    @Override
    public void click() {
        mouse.click(screenRegion.getCenter());
    }
}
