import data_access.FileUserDataAccessObject;
import factory.TierListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListViewModel;
import view.TierListView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame();
        application.setSize(700, 650);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        TierListViewModel tierListViewModel = new TierListViewModel("tier");

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");

        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject);
        views.add(tierListView, tierListView.viewName);
        viewManagerModel.setActiveView(tierListView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}