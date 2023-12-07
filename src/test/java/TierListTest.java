import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import factory.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_existing.ViewExistingState;
import interface_adapter.view_existing.ViewExistingViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import org.junit.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;


public class TierListTest {

    public static void miniMain() throws InterruptedException {

        JFrame application = new JFrame("Tierlist Maker");
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
        ViewExistingViewModel viewExistingViewModel = new ViewExistingViewModel("view existing");
        FollowViewModel followViewModel = new FollowViewModel("follow User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("view User");
        MenuViewModel menuViewModel = new MenuViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        User user = new User("Test", "Password");

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < TierList.LENGTH; i++) {
            items.add(new Item("Item " + (i + 1)));
        }

        TierList tierList = new TierList("Test", items);
        user.addTierList(tierList);
        TierListState tierListState = tierListViewModel.getState();
        tierListState.setUser(user);
        tierListState.setTierList("Test");
        tierListViewModel.setState(tierListState);

        SelectorState selectorState = selectorViewModel.getState();
        selectorState.setUser(user);
        selectorViewModel.setState(selectorState);

        RandomTierListState randomTierListState = randomTierListViewModel.getState();
        randomTierListState.setUser(user);
        randomTierListViewModel.setState(randomTierListState);

        CustomTierListState customTierListState = customTierListViewModel.getState();
        customTierListState.setUser(user);
        customTierListViewModel.setState(customTierListState);

        ViewExistingState viewExistingState = viewExistingViewModel.getState();
        viewExistingState.setUser(user);
        viewExistingViewModel.setState(viewExistingState);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");
        ChatGPTDataAccessObject chatGPTDataAccessObject = new ChatGPTDataAccessObject();

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel, userDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);

        views.add(randomTierListView, randomTierListView.viewName);

        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);

        MenuView menuView = MenuFactory.create(viewManagerModel, menuViewModel, loginViewModel, signupViewModel);
        views.add(menuView, menuView.viewName);

        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel, userDataAccessObject, viewExistingViewModel, menuViewModel);

        views.add(selectorView, selectorView.viewName);

        SignupView signupView = SignupFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, selectorViewModel);
        views.add(loginView, loginView.viewName);

        ViewExistingView viewExistingView = ViewExistingFactory.create(viewManagerModel, viewExistingViewModel, tierListViewModel, selectorViewModel, userDataAccessObject);
        views.add(viewExistingView, viewExistingView.viewName);

        CustomTierListView customTierListView = CustomTierListFactory.create(viewManagerModel, customTierListViewModel, tierListViewModel, tierListView, selectorViewModel, userDataAccessObject);

        views.add(customTierListView, customTierListView.viewName);
        views.add(tierListView, tierListView.viewName);

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject, userDataAccessObject);
        views.add(followView, followView.viewName);

        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);

    }

    public JComboBox getDropDown() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);

        TierListView sv = (TierListView) jp2.getComponent(0);

        return (JComboBox) sv
                .getComponent(3)
                .getComponentAt(0, 0)
                .getComponentAt(73, 0)
                .getComponentAt(89, 5);
    }

    public Component getView(String viewName) throws InterruptedException {
        miniMain();

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel viewPanel = (JPanel) jp.getComponent(0);

        for (Component component : viewPanel.getComponents()) {
            if (Objects.equals(viewName, "menu")) {
                if (component instanceof MenuView) {
                    return component;
                }
            } else if (Objects.equals(viewName, "signup")) {
                if (component instanceof SignupView) {
                    return component;
                }
            } else if (Objects.equals(viewName, "selector")) {
                if (component instanceof SelectorView) {
                    return component;
                }
            } else if (Objects.equals(viewName, "view existing")) {
                if (component instanceof ViewExistingView) {
                    return component;
                }
            } else if (Objects.equals(viewName, "random")) {
                if (component instanceof RandomTierListView) {
                    return component;
                }
            }
        }
        return null;
    }

    public Component getCurrentView() {
        Component currentView = null;

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel viewPanel = (JPanel) jp.getComponent(0);

        Component[] cards = viewPanel.getComponents();
        for (Component card : cards) {
            if (card.isVisible()) {
                currentView = card;
            }
        }
        return currentView;
    }

    @Test
    public void checkMenuSignUpButton() throws InterruptedException {
        miniMain();

        MenuView menuView = (MenuView) getView("menu");
        assert menuView != null;
        JPanel signUpWrapperPanel = (JPanel) menuView.getComponent(3);
        JPanel signUpPanel = (JPanel) signUpWrapperPanel.getComponent(0);
        JButton signUpButton = (JButton) signUpPanel.getComponent(0);

        signUpButton.doClick();
        Thread.sleep(100);
        Component currentView = getCurrentView();
        assert currentView instanceof SignupView;
    }

    @Test
    public void checkMenuLoginButton() throws InterruptedException {
        miniMain();

        MenuView menuView = (MenuView) getView("menu");
        assert menuView != null;

        JPanel loginWrapperPanel = (JPanel) menuView.getComponent(7);
        JPanel loginPanel = (JPanel) loginWrapperPanel.getComponent(0);
        JButton loginButton = (JButton) loginPanel.getComponent(0);

        loginButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof LoginView;
    }

    @Test
    public void checkSignUp() throws InterruptedException {
        SignupView signupView = (SignupView) getView("signup");

        InputPanel usernameInputPanel = (InputPanel) signupView.getComponent(1);
        JTextField usernameInput = usernameInputPanel.getTextField();

        PasswordInputPanel passwordInputPanel = (PasswordInputPanel) signupView.getComponent(2);
        JTextField passwordInput = passwordInputPanel.getTextField();

        PasswordInputPanel repeatPasswordInputPanel = (PasswordInputPanel) signupView.getComponent(3);
        JTextField repeatPasswordInput = repeatPasswordInputPanel.getTextField();

        usernameInput.setText("Test");
        passwordInput.setText("Test");
        repeatPasswordInput.setText("Test");

        JPanel buttonPanelWrapper = (JPanel) signupView.getComponent(4);
        ButtonPanel buttonPanel = (ButtonPanel) buttonPanelWrapper.getComponent(1);
        JButton signUpButton = buttonPanel.getButton();

        signUpButton.doClick();

    }

    @Test
    public void checkViewExistingTierList() throws InterruptedException {
        ViewExistingView viewExistingView = (ViewExistingView) getView("view existing");
        viewExistingView.updateScreen();

        LabelDropDownPanel tierlistSelectionPanel = (LabelDropDownPanel) viewExistingView.getComponent(3);
        JComboBox<String> dropDown = tierlistSelectionPanel.getDropDown();
        dropDown.setSelectedItem("Test");

        JPanel submitButtonPanel = (JPanel) viewExistingView.getComponent(5);
        JButton submitButton = (JButton) submitButtonPanel.getComponent(1);

        submitButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof TierListView;
    }

    @Test
    public void checkRandomTierList() throws InterruptedException {
        RandomTierListView randomTierListView = (RandomTierListView) getView("random");

        JPanel inputPanel = (JPanel) randomTierListView.getComponent(3);
        JTextField input = (JTextField) inputPanel.getComponent(0);

        JPanel submitButtonPanel = (JPanel) inputPanel.getComponent(1);

        JButton submitButton = (JButton) submitButtonPanel.getComponent(1);

        input.setText("subjects in school");

        RandomTierListState randomTierListState = randomTierListView.randomTierListViewModel.getState();
        randomTierListState.setPrompt("subjects in school");
        randomTierListView.randomTierListViewModel.setState(randomTierListState);

        submitButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof TierListView;

     }

//    @Test
//    public void checkDropDown() throws IOException, InterruptedException {
//        miniMain();
//        Tier initialTier = getTierList();
//        assert initialTier.equals(Tier.S);
//        JComboBox dropDown = getDropDown();
//        Thread.sleep(100);
//        dropDown.setSelectedItem("A");
//        Thread.sleep(100);
//
//        Tier updatedTier = getTierList();
//        assert updatedTier.equals(Tier.A);
//        dropDown.setSelectedItem("S");
//        Thread.sleep(100);
//    }

    @Test
    public void checkSelectorButtons() throws InterruptedException {
        SelectorView selectorView = (SelectorView) getView("selector");
        assert selectorView != null;

        List<JButton> buttons = new ArrayList<>();

        for (Component component : selectorView.getComponents()) {
            if (component instanceof ButtonPanel) {
                buttons.add(((ButtonPanel) component).getButton());
            }
        }
        for (JButton button : buttons) {
            button.doClick();
            if (button.getText().equals("View Existing")) {
                Component currentView = getCurrentView();
                assert currentView instanceof ViewExistingView;
            } else if (button.getText().equals("Random")) {
                Component currentView = getCurrentView();
                assert currentView instanceof RandomTierListView;
            } else if (button.getText().equals("Custom")) {
                Component currentView = getCurrentView();
                assert currentView instanceof CustomTierListView;
            }
        }
    }
}
