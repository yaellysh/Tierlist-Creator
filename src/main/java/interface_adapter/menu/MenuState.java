package interface_adapter.menu;

public class MenuState {
    private String selected = "";

    public MenuState(MenuState copy) {
        selected = copy.selected;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public MenuState() {}

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

}
