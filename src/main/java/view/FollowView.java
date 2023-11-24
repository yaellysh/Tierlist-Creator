package view;

import javax.swing.*;

import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FollowView extends JPanel implements ActionListener, PropertyChangeListener {

    public FollowView(TierListController tierListController, TierListViewModel tierListViewModel) {
        
    }   

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

}
