package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String error = null;
    private String password = "";
    private String repeatPassword = "";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
