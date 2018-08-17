package com.selenium.utils.sikulix;

import io.sterodium.extensions.node.rmi.TargetFactory;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;

import java.awt.event.KeyEvent;

/**
 * @author Alexey Nikolaenko alexey@tcherezov.com
 *         Date: 24/11/2015
 */
public class DefaultTextBox extends DefaultUiComponent implements TextBox {

    private Keyboard keyboard;

    DefaultTextBox(Mouse mouse, Keyboard keyboard, TargetFactory targetFactory, ScreenRegion screenRegion) {
        super(mouse, targetFactory, screenRegion);
        this.keyboard = keyboard;
    }

    @Override
    public void write(String text) {
        keyboard.type(text);
    }

    @Override
    public void press(int key) {
        keyboard.keyDown(key);
        keyboard.keyUp(key);
    }

    @Override
    public void deleteAllText() {
        keyboard.keyDown(KeyEvent.VK_CONTROL);
        keyboard.keyDown(KeyEvent.VK_A);
        keyboard.keyUp(KeyEvent.VK_CONTROL);
        keyboard.keyUp(KeyEvent.VK_A);
        keyboard.keyDown(KeyEvent.VK_DELETE);
        keyboard.keyUp(KeyEvent.VK_DELETE);
    }
}
