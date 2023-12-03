import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import factory.CustomTierListFactory;
import factory.RandomTierListFactory;
import factory.SelectorFactory;
import factory.TierListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        JFrame application = new JFrame();
        application.setResizable(false);
        application.setSize(800, 700);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        TierListViewModel tierListViewModel = new TierListViewModel("tier list");
        SelectorViewModel selectorViewModel = new SelectorViewModel("selector");
        RandomTierListViewModel randomTierListViewModel = new RandomTierListViewModel("random");
        CustomTierListViewModel customTierListViewModel = new CustomTierListViewModel("custom");

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");
        ChatGPTDataAccessObject chatGPTDataAccessObject  = new ChatGPTDataAccessObject();

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel, userDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);

        views.add(randomTierListView, randomTierListView.viewName);

        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel);

        views.add(selectorView, selectorView.viewName);

        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);

        views.add(tierListView, tierListView.viewName);
        CustomTierListView customTierListView = CustomTierListFactory.create(viewManagerModel, customTierListViewModel, tierListViewModel, tierListView, selectorViewModel, userDataAccessObject);

        views.add(customTierListView, customTierListView.viewName);

//        viewManagerModel.setActiveView(tierListView.viewName);
//        viewManagerModel.setActiveView(randomTierListView.viewName);
        viewManagerModel.setActiveView(selectorView.viewName);
//        viewManagerModel.setActiveView(customTierListView.viewName);
//        viewManagerModel.setActiveView(tierListView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}