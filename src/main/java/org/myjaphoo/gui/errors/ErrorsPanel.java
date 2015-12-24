/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MessageView.java
 *
 * Created on 09.01.2012, 18:28:49
 */
package org.myjaphoo.gui.errors;

import org.mlsoft.eventbus.Subscribe;
import org.myjaphoo.MyjaphooController;

import java.util.Collection;

/**
 *
 * @author mla
 */
public class ErrorsPanel extends javax.swing.JPanel {

    private boolean flat = true;
    private ErrorTreeNode root = new ErrorTreeNode();

    /**
     * Creates new form MessageView
     */
    public ErrorsPanel(MyjaphooController controller) {
        initComponents();
        jXTreeTable1.setTreeTableModel(new ErrorTreeModel(root, flat));
        jXTreeTable1.setTreeCellRenderer(new ErrorRenderer());

        controller.getEventBus().register(this);
    }

    @Subscribe(onETD = true)
    public void updateErrorMessages(ErrorUpdateEvent evt) {

        Collection<ErrorTreeNode> newNodes = evt.getNodes();
        setErrorMessages(evt.getGroupName(), newNodes);
        updateTreeModel();
    }

    private void updateTreeModel() {
        jXTreeTable1.setTreeTableModel(new ErrorTreeModel(root, flat));
        jXTreeTable1.setTreeCellRenderer(new ErrorRenderer());
    }

    private void setErrorMessages(String groupName, Collection<ErrorTreeNode> newNodes) {
        ErrorTreeNode node = findOrCreateNode(groupName);
        node.getChildren().clear();

        node.getChildren().addAll(newNodes);
    }

    private ErrorTreeNode findOrCreateNode(String groupName) {

        for (ErrorTreeNode node : root.getChildren()) {
            if (groupName.equals(node.getMessage())) {
                return node;
            }
        }
        ErrorTreeNode node = new ErrorTreeNode();
        node.setMessage(groupName);
        root.addChild(node);
        return node;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButtonGroup = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTreeTable1 = new org.jdesktop.swingx.JXTreeTable();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(org.myjaphoo.MyjaphooApp.class).getContext().getResourceMap(ErrorsPanel.class);
        jButtonGroup.setText(resourceMap.getString("jButtonGroup.text")); // NOI18N
        jButtonGroup.setFocusable(false);
        jButtonGroup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGroup.setName("jButtonGroup"); // NOI18N
        jButtonGroup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGroupActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGroup);

        add(jToolBar1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jXTreeTable1.setName("jXTreeTable1"); // NOI18N
        jScrollPane1.setViewportView(jXTreeTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGroupActionPerformed
        flat = !flat;
        updateTreeModel();

    }//GEN-LAST:event_jButtonGroupActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGroup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXTreeTable jXTreeTable1;
    // End of variables declaration//GEN-END:variables

}
