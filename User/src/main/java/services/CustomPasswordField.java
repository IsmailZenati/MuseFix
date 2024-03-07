package services;

import javafx.scene.control.PasswordField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CustomPasswordField extends PasswordField {

    public CustomPasswordField() {
        super();
        setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.isShortcutDown() && event.getCode().equals(KeyCode.V)) {
            // Handle paste operation
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.hasString()) {
                String content = clipboard.getString();
                replaceSelection(content);
            }
        }
    }
}
