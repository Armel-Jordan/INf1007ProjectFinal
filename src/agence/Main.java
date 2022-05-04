package agence;

import agence.storage.StockagePersistant;
import agence.storage.StockageRepository;
import agence.views.IMenuView;
import agence.views.impl.MenuView;

public class Main {
    private final IMenuView menuView = new MenuView();
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        menuView.getMenu();
    }
}
