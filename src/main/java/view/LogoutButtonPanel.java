package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogoutButtonPanel extends JPanel {
    JButton logoutButton;

    public JButton getLogoutButton() {
        return logoutButton;
    }

    LogoutButtonPanel() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        this.logoutButton = new JButton("Log Out");
        logoutButton.setPreferredSize(new Dimension(100, 20));

        this.setMaximumSize(new Dimension(800, 20));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 0, 0));

        buttonPanel.add(logoutButton);
        this.add(buttonPanel, BorderLayout.WEST);
    }
}
