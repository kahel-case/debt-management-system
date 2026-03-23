package sys.controller;

import javafx.scene.layout.VBox;

public class PageManager {
    public static void switchPage(VBox[] panels, VBox page) {
        for (VBox panel : panels) {
            panel.setVisible(panel == page);
        }
    }
}
