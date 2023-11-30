import data_access.FileUserDataAccessObject;
import factory.RandomTierListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import view.RandomTierListView;
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
        SelectorViewModel selectorViewModel = new SelectorViewModel("selector");
        RandomTierListViewModel randomTierListViewModel = new RandomTierListViewModel("random");

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel);

        views.add(randomTierListView, randomTierListView.viewName);
        viewManagerModel.setActiveView(randomTierListView.viewName);
        viewManagerModel.firePropertyChanged();

//        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel);
//
//        views.add(selectorView, selectorView.viewName);
//        viewManagerModel.setActiveView(selectorView.viewName);
//        viewManagerModel.firePropertyChanged();

//        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject);
//
//        views.add(tierListView, tierListView.viewName);
//        viewManagerModel.setActiveView(tierListView.viewName);
//        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}