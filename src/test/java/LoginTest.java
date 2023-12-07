import data_access.FileUserDataAccessObject;
import interface_adapter.menu.MenuViewModel;
import org.junit.Test;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginTest {

    @Test
    public void testLogin() {
        FileUserDataAccessObject object = TierListTest.instantiate();
        LoginInteractor interactor = new LoginInteractor(object, new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assert user.getUser().getUsername().equals("Yael");
            }

            @Override
            public void prepareFailView(String error) {
                assert false;
            }

            @Override
            public void returnToMenu(MenuViewModel menuViewModel) {
                assert false;
            }
        });
        interactor.execute(new LoginInputData("Yael", "potatoes"));
    }

}
