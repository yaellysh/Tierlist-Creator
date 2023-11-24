package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class FollowView extends JPanel implements ActionListener, PropertyChangeListener {

    public void actionedPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }


}
