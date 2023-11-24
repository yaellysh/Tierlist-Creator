
import factory.TierListFactory;
import interface_adapter.tierlist.TierListViewModel;
import view.TierListView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setSize(700, 650);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.add(new JLabel("Hello World"));

        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}