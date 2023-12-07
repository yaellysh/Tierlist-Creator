import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import def.DefaultCustomTierListOutputBoundary;
import def.DefaultRandomTierListOutputBoundary;
import def.DefaultSignupOutputBoundary;
import def.DefaultTierListOutputBoundary;
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
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_existing.ViewExistingState;
import interface_adapter.view_existing.ViewExistingViewModel;
import org.junit.Test;
import use_case.custom_tierlist.CustomTierListInputData;
import use_case.custom_tierlist.CustomTierListInteractor;
import use_case.random_tierlist.RandomTierListInputData;
import use_case.random_tierlist.RandomTierListInteractor;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.tierlist.TierListDataAccessInterface;
import view.*;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInteractor;
import view.TierListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TierListTest {

    public static void miniMain(String path) throws InterruptedException, IOException {

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

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(path);
        ChatGPTDataAccessObject chatGPTDataAccessObject = new ChatGPTDataAccessObject();

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel, userDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);

        views.add(randomTierListView, randomTierListView.viewName);

        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);

        MenuView menuView = MenuFactory.create(viewManagerModel, menuViewModel, loginViewModel, signupViewModel);
        views.add(menuView, menuView.viewName);

        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel, userDataAccessObject, viewExistingViewModel, menuViewModel, new SearchViewModel("search"));

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

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, userDataAccessObject, tierListViewModel);
        views.add(followView, followView.viewName);

        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);

    }
    public static void miniMain() throws InterruptedException, IOException {
        miniMain("src/main/resources/users.json");

    }
    public static FileUserDataAccessObject instantiate() {
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/test/resources/users.json");
        SignupInteractor signupInteractor = new SignupInteractor(object, new DefaultSignupOutputBoundary());
        signupInteractor.execute(new SignupInputData("Yael", "potatoes", "potatoes"));
        return object;
    }
    public Component getView(String viewName) throws InterruptedException, IOException {
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
            } else if (Objects.equals(viewName, "custom")) {
                if (component instanceof CustomTierListView) {
                    return component;
                }
            } else if (Objects.equals(viewName, "tierlist")) {
                if (component instanceof TierListView) {
                    return component;
                }
            } else if (Objects.equals(viewName, "login")) {
                if (component instanceof LoginView) {
                    return component;
                }
            }
        }
        return null;
    }
    @Test
    public void testCustomTierList() {
        FileUserDataAccessObject object = instantiate();
        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(new DefaultCustomTierListOutputBoundary(), object);
        User user = object.getUser("Yael");
        String[] items = new String[TierList.LENGTH];
        for (int i = 0; i < TierList.LENGTH; i++) {
            items[i] = ("Item " + i);
        }

        customTierListInteractor.execute(new CustomTierListInputData(user, "Test", items));

        TierListInteractor tierListInteractor = new TierListInteractor(object, new DefaultTierListOutputBoundary());
        tierListInteractor.execute(new TierListInputData("Yael", "Test", "Item 3", Tier.B));
        assert user.getTierList("Test").getItem("Item 3").getTier().equals(Tier.B);
        assert object
                .getUser("Yael")
                .getTierList("Test")
                .getItem("Item 1")
                .getTier()
                .equals(Tier.S);
        object.removeUser("Yael");
    }

    @Test
    public void testRandomTierList() {
        FileUserDataAccessObject object = instantiate();
        ChatGPTDataAccessObject gptObject = new ChatGPTDataAccessObject();
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(gptObject, object, new DefaultRandomTierListOutputBoundary());
        randomTierListInteractor.execute(new RandomTierListInputData("Conan Gray songs", object.getUser("Yael")));
        TierList tierList = object.getUser("Yael").getTierList("Conan Gray songs");
        assert tierList != null;
        assert tierList.getItems().size() == TierList.LENGTH;
        assert tierList.getItems().stream().map(Item::getName).anyMatch(s -> s.equals("Heather"));
        tierList.getItems().stream().map(Item::getTier).forEach(s -> {assert s == Tier.S;});
        object.removeUser("Yael");
    }


    private static Tier getTierList() throws IOException {
        TierListDataAccessInterface object = new FileUserDataAccessObject("src/test/resources/users.json");
        return object.getUser("Yael").getTierList("Test").getItem("Item 1").getTier();
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
    public void checkMenuSignUpButton() throws InterruptedException, IOException {
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
    public void checkMenuLoginButton() throws InterruptedException, IOException {
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
    public void checkSignUp() throws InterruptedException, IOException {
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
    public void checkLogin() throws InterruptedException, IOException {
        LoginView loginView = (LoginView) getView("login");

        InputPanel usernameInputPanel = (InputPanel) loginView.getComponent(1);
        JTextField usernameInput = usernameInputPanel.getTextField();

        PasswordInputPanel passwordInputPanel = (PasswordInputPanel) loginView.getComponent(3);
        JTextField passwordInput = passwordInputPanel.getTextField();

        usernameInput.setText("Test");
        passwordInput.setText("Test");

        JPanel buttonPanelWrapper = (JPanel) loginView.getComponent(5);
        ButtonPanel buttonPanel = (ButtonPanel) buttonPanelWrapper.getComponent(1);
        JButton logInButton = buttonPanel.getButton();

        logInButton.doClick();
    }

    @Test
    public void checkViewExistingTierList() throws InterruptedException, IOException {
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
    public void checkRandomTierList() throws InterruptedException, IOException {
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

    @Test
    public void checkCustomTierList() throws InterruptedException, IOException {
        CustomTierListView customTierListView = (CustomTierListView) getView("custom");

        InputPanel titleInputPanel = (InputPanel) customTierListView.getComponent(2);
        JTextField titleInput = titleInputPanel.getTextField();
        titleInput.setText("Test");

        JPanel largePanel = (JPanel) customTierListView.getComponent(5);
        JPanel leftPanel = (JPanel) largePanel.getComponent(0);
        JPanel rightPanel = (JPanel) largePanel.getComponent(1);

        Map<Integer, String> items = new HashMap<>();
         for (int i = 0; i < TierList.LENGTH; i++) {
             items.put(i, "Item " + (i + 1));
             if (i < TierList.LENGTH / 2) {
                 InputPanel itemInputPanel = (InputPanel) leftPanel.getComponent(i);
                 JTextField itemInput = itemInputPanel.getTextField();
             } else {
                 InputPanel itemInputPanel = (InputPanel) rightPanel.getComponent((i - TierList.LENGTH/2));
                 JTextField itemInput = itemInputPanel.getTextField();
             }
         }

         CustomTierListState customTierListState=  customTierListView.customTierListViewModel.getState();
         customTierListState.setTitle("Custom Test");
         customTierListState.setItems(items);

         customTierListView.customTierListViewModel.setState(customTierListState);

         JPanel submitButtonPanel = (JPanel) customTierListView.getComponent(6);
         JButton submitButton = (JButton) submitButtonPanel.getComponent(1);

         submitButton.doClick();

         Component currentView = getCurrentView();

         assert currentView instanceof TierListView;
     }

    private static Tier getTierList(String item) throws IOException {
        TierListDataAccessInterface object = new FileUserDataAccessObject("src/main/resources/users.json");
        return object.getUser("Test").getTierList("Test").getItem(item).getTier();
    }
    @Test
    public void checkTierListDropDown() throws IOException, InterruptedException {
        miniMain();

        TierListView tierListView = (TierListView) getView("tierlist");
        tierListView.tierListViewModel.firePropertyChanged();
        JPanel dropDownPanel = (JPanel) tierListView.getComponent(3);
        JPanel leftPanel = (JPanel) dropDownPanel.getComponent(0);

        LabelDropDownPanel labelDropDownPanel = (LabelDropDownPanel) leftPanel.getComponent(0);
        JComboBox<String> labelDropDown = labelDropDownPanel.getDropDown();

        Tier initialTier = getTierList(labelDropDownPanel.getLabelName());
        assert initialTier.equals(Tier.S);

        Thread.sleep(100);
        labelDropDown.setSelectedItem("A");
        Thread.sleep(100);

        Tier updatedTier = getTierList(labelDropDownPanel.getLabelName());
        assert updatedTier.equals(Tier.A);

        labelDropDown.setSelectedItem("S");
        Thread.sleep(100);

    }

    @Test
    public void checkCustomBackView() throws IOException, InterruptedException {
        CustomTierListView customTierListView = (CustomTierListView) getView("custom");

        JPanel backButtonPanel = (JPanel) customTierListView.getComponent(6);
        JButton backButton = (JButton) backButtonPanel.getComponent(0);

        backButton.doClick();

        Component currentView = getCurrentView();
        assert currentView instanceof SelectorView;
    }

    @Test
    public void checkRandomBackView() throws IOException, InterruptedException {
        RandomTierListView randomTierListView = (RandomTierListView) getView("random");

        JPanel inputPanel = (JPanel) randomTierListView.getComponent(3);
        JPanel backButtonPanel = (JPanel) inputPanel.getComponent(1);
        JButton backButton = (JButton) backButtonPanel.getComponent(0);

        backButton.doClick();

        Component currentView = getCurrentView();
        assert currentView instanceof SelectorView;
    }
    @Test
    public void checkExistingBackView() throws IOException, InterruptedException {
        ViewExistingView viewExistingView = (ViewExistingView) getView("view existing");
        viewExistingView.updateScreen();

        JPanel backButtonPanel = (JPanel) viewExistingView.getComponent(5);
        JButton backButton = (JButton) backButtonPanel.getComponent(0);

        backButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof SelectorView;
    }

    @Test
    public void checkCancelButtonLogin() throws IOException, InterruptedException {
        LoginView loginView = (LoginView) getView("login");

        JPanel buttonPanelWrapper = (JPanel) loginView.getComponent(5);
        ButtonPanel buttonPanel = (ButtonPanel) buttonPanelWrapper.getComponent(0);
        JButton cancelButton = buttonPanel.getButton();

        cancelButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof MenuView;
    }

    @Test
    public void checkCancelButtonSignUp() throws IOException, InterruptedException {
        SignupView signupView = (SignupView) getView("signup");

        JPanel buttonPanelWrapper = (JPanel) signupView.getComponent(4);
        ButtonPanel buttonPanel = (ButtonPanel) buttonPanelWrapper.getComponent(0);
        JButton cancelButton = buttonPanel.getButton();

        cancelButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof MenuView;
    }

    @Test
    public void checkSelectorButtons() throws InterruptedException, IOException {
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
