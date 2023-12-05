package use_case.menu;

public class MenuInputData {
    final private String selected;

    public MenuInputData(String selected) {
        this.selected = selected;
    }

    String getSelected() {
        return selected;
    }
}
