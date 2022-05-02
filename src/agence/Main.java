package agence;

import agence.views.IMenu;
import agence.views.impl.Menu;

public class Main {

    private final IMenu menuView = new Menu();
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        menuView.getChoiceMenu();
    }
}
