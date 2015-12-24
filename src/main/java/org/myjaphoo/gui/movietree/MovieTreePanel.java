/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MovieTreePanel.java
 *
 * Created on 27.10.2009, 12:42:16
 */
package org.myjaphoo.gui.movietree;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.jdesktop.swingx.table.TableColumnExt;
import org.mlsoft.structures.AbstractTreeNode;
import org.mlsoft.structures.TreeStructure;
import org.mlsoft.structures.Trees;
import org.myjaphoo.MovieNode;
import org.myjaphoo.MovieTreeModel;
import org.myjaphoo.gui.editor.rsta.GroupBySyntaxCellRenderer;
import org.myjaphoo.gui.thumbtable.ThumbPanel;
import org.myjaphoo.model.db.ChronicEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author mla
 */
public class MovieTreePanel extends javax.swing.JPanel {

    private final static ResourceBundle localeBundle = ResourceBundle.getBundle("org/myjaphoo/gui/movietree/resources/MovieTreePanel");
    public static final Logger LOGGER = LoggerFactory.getLogger(MovieTreePanel.class.getName());
    private MoviePanelController controller;
    private ThumbPanel thumbPanel;
    private final MovieTreeModel treemodel = new MovieTreeModel(new MovieStructureNode(localeBundle.getString("EMPTY")));

    private boolean inUpdateProcess = false;

    /**
     * Creates new form MovieTreePanel
     */
    public MovieTreePanel(MoviePanelController controller, ThumbPanel thumbPanel) {
        this.controller = controller;
        this.thumbPanel = thumbPanel;
        initComponents();
        jXMovieTreeTable.setTreeTableModel(treemodel);
        //jXMovieTreeTable.setTreeTableModel(controller.getFilter().createMovieTreeModel());
        jXMovieTreeTable.setTreeCellRenderer(new MovieTreeCellRenderer((MovieTree) jXMovieTreeTable, controller));
        jXMovieTreeTable.addMouseListener(new PopupMovieTreeListener(controller, (MovieTree) jXMovieTreeTable));
    }

    public void selectNode(MovieNode node2Select) {
        if (jXMovieTreeTable.getTreeTableModel() instanceof MovieTreeModel) {
            controller.setCurrentDir(node2Select);
            selectMovieNodeInTree(node2Select);
        }
    }

    private void selectMovieNodeInTree(MovieNode node2Select) {
        MovieTreeModel model = (MovieTreeModel) jXMovieTreeTable.getTreeTableModel();
        TreeStructure[] path = findNodeInThisModel(model, node2Select);
        if (path != null) {
            setAndShowPath(path);
        }
    }

    private void setAndShowPath(TreeStructure[] path) {
        TreePath treePath = new TreePath(path);

        jXMovieTreeTable.scrollPathToVisible(treePath);
        jXMovieTreeTable.expandPath(treePath);
        jXMovieTreeTable.getTreeSelectionModel().setSelectionPath(treePath);
    }

    private void selectMovieStructureNodeInTree(MovieStructureNode node) {
        MovieTreeModel model = (MovieTreeModel) jXMovieTreeTable.getTreeTableModel();
        TreeStructure[] path = findStructureNodeInThisModel(model, node);
        if (path != null) {
            setAndShowPath(path);
        }
    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar5 = new javax.swing.JToolBar();
        jCheckBoxPruneTree = new javax.swing.JCheckBox();
        jCheckBoxListChildren = new javax.swing.JCheckBox();
        jCheckBoxCondenseDuplicates = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        jXMovieTreeTable = new org.myjaphoo.gui.movietree.MovieTree(controller);

        setMinimumSize(new java.awt.Dimension(256, 25));
        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jToolBar5.setRollover(true);
        jToolBar5.setName("jToolBar5"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(org.myjaphoo.MyjaphooApp.class).getContext().getResourceMap(MovieTreePanel.class);
        jCheckBoxPruneTree.setText(resourceMap.getString("jCheckBoxPruneTree.text")); // NOI18N
        jCheckBoxPruneTree.setFocusable(false);
        jCheckBoxPruneTree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBoxPruneTree.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBoxPruneTree.setName("jCheckBoxPruneTree"); // NOI18N
        jCheckBoxPruneTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jCheckBoxPruneTree.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxPruneTreeStateChanged(evt);
            }
        });
        jToolBar5.add(jCheckBoxPruneTree);

        jCheckBoxListChildren.setText(resourceMap.getString("jCheckBoxListChildren.text")); // NOI18N
        jCheckBoxListChildren.setFocusable(false);
        jCheckBoxListChildren.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBoxListChildren.setName("jCheckBoxListChildren"); // NOI18N
        jCheckBoxListChildren.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jCheckBoxListChildren.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxListChildrenStateChanged(evt);
            }
        });
        jToolBar5.add(jCheckBoxListChildren);

        jCheckBoxCondenseDuplicates.setText(resourceMap.getString("jCheckBoxCondenseDuplicates.text")); // NOI18N
        jCheckBoxCondenseDuplicates.setFocusable(false);
        jCheckBoxCondenseDuplicates.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBoxCondenseDuplicates.setName("jCheckBoxCondenseDuplicates"); // NOI18N
        jCheckBoxCondenseDuplicates.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jCheckBoxCondenseDuplicates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCondenseDuplicatesActionPerformed(evt);
            }
        });
        jToolBar5.add(jCheckBoxCondenseDuplicates);

        add(jToolBar5, java.awt.BorderLayout.NORTH);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jXMovieTreeTable.setName("jXMovieTreeTable"); // NOI18N
        jXMovieTreeTable.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jXMovieTreeTableValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(jXMovieTreeTable);

        add(jScrollPane5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxPruneTreeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxPruneTreeStateChanged
        if (evt.getSource() == jCheckBoxPruneTree) {
            if (controller.isPruneTree() != jCheckBoxPruneTree.getModel().isSelected()) {
                controller.setPruneTree(jCheckBoxPruneTree.getModel().isSelected());
                MovieStructureNode root = controller.createMovieTreeModel();
                updateMovieTree(root);
            }
        }
    }//GEN-LAST:event_jCheckBoxPruneTreeStateChanged

    public void updateMovieTree(MovieStructureNode root) {
        StopWatch w = new StopWatch();
        inUpdateProcess = true;
        try {
            w.start();
            AbstractTreeNode oldRoot = (AbstractTreeNode) treemodel.getRoot();
            treemodel.setRoot(root);


            final AbstractTreeNode selDir = controller.getCurrentSelectedDir();
            // set the selected dir delayed and not directly after changing the tree model.
            // otherwise the select ui change would be directly resetted afterwards (in most cases)
            // executing this later in the event queue makes it stable:
            // todo: there is something strange in the event sequence, otherwise this would not be necessary
            // unfortunately not easy to analyze...
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (selDir != null) {
                        trySelectNodeInTree(selDir);
                    }
                    initColumnWidth();
                    jXMovieTreeTable.getColumnExt(0).setTitle(controller.getUserDefinedStruct());
                    jXMovieTreeTable.getColumnExt(4).setCellRenderer(new GroupBySyntaxCellRenderer());
                }
            });


            jCheckBoxListChildren.setSelected(controller.isListChildMovies());
            jCheckBoxPruneTree.setSelected(controller.isPruneTree());
            if (oldRoot != null) {
                oldRoot.destruct();
            }
            w.stop();
            LOGGER.info("update movie tree duration: " + w.toString()); //NOI18N
        } finally {
            inUpdateProcess = false;
        }
    }

    private void trySelectNodeInTree(AbstractTreeNode node) {
        if (node instanceof MovieNode) {
            selectMovieNodeInTree((MovieNode) node);
        } else if (node instanceof MovieStructureNode) {
            selectMovieStructureNodeInTree((MovieStructureNode) node);
        }
    }


    private void jCheckBoxListChildrenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxListChildrenStateChanged
        if (evt.getSource() == jCheckBoxListChildren) {
            if (controller.isListChildMovies() != jCheckBoxListChildren.getModel().isSelected()) {
                controller.setListChildMovies(jCheckBoxListChildren.getModel().isSelected());
                thumbPanel.refillThumbView(controller.getThumbsModel());
            }
        }
    }//GEN-LAST:event_jCheckBoxListChildrenStateChanged

    private void jCheckBoxCondenseDuplicatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCondenseDuplicatesActionPerformed
        if (evt.getSource() == jCheckBoxCondenseDuplicates) {
            if (controller.isCondenseDuplicates() != jCheckBoxCondenseDuplicates.isSelected()) {
                controller.setCondenseDuplicates(jCheckBoxCondenseDuplicates.isSelected());
                controller.fireStructureChanged();
            }
        }
    }//GEN-LAST:event_jCheckBoxCondenseDuplicatesActionPerformed

    private void jXMovieTreeTableValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXMovieTreeTableValueChanged
        if (!inUpdateProcess) {
            controller.setCurrentDir((AbstractMovieTreeNode) evt.getPath().getLastPathComponent());
            thumbPanel.refillThumbView(controller.getThumbsModel());
            controller.updateChronic();
        }
    }//GEN-LAST:event_jXMovieTreeTableValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxCondenseDuplicates;
    private javax.swing.JCheckBox jCheckBoxListChildren;
    private javax.swing.JCheckBox jCheckBoxPruneTree;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar jToolBar5;
    private org.jdesktop.swingx.JXTreeTable jXMovieTreeTable;
    // End of variables declaration//GEN-END:variables

    private static Trees.EqualNodeFunction<TreeStructure> PATHNAME_EQ = new Trees.EqualNodeFunction<TreeStructure>() {

        @Override
        public boolean isEqual(TreeStructure node1, TreeStructure node2) {
            if (node1 instanceof MovieStructureNode && node2 instanceof MovieStructureNode) {
                return StringUtils.equals(((MovieStructureNode) node1).getName(), ((MovieStructureNode) node2).getName());
            } else {
                return false;
            }
        }
    };

    private TreeStructure[] findStructureNodeInThisModel(MovieTreeModel model, MovieStructureNode node) {
        MovieStructureNode foundNode = (MovieStructureNode) Trees.pathSearch((TreeStructure) model.getRoot(), node, PATHNAME_EQ);
        if (foundNode != null) {
            return model.getPathToRoot(foundNode);
        } else {
            return null;
        }
    }

    /**
     * Suche die node in diesem Modell und returniere einen Pfad fÃƒÂ¼r dieses Modell.
     * Da es sich um eine Node AUS EINEM ANDEREN MODELL handeln kann, darf NICHT das
     * getPath2Root aufgerufen werden ,weil das einen Pfad mit Objekten des anderen Modells erzeugen wÃƒÂ¼rde.
     * Wir mÃƒÂ¼ssen mittels dateinameverlgeich oder so, die node in diesem Modell suchen, u. dann daraufhin
     * den Pfad ermitteln
     *
     * @param model
     * @param node2Select
     * @return
     */
    private TreeStructure[] findNodeInThisModel(MovieTreeModel model, MovieNode node2Select) {
        // suche die entsprechende korrespondierende Node fÃƒÂ¼r diese Node in dem model:
        // suche mittels der movie-id:
        final Long movieId = node2Select.getMovieEntry().getId();
        AbstractMovieTreeNode root = (AbstractMovieTreeNode) model.getRoot();

        MovieNode foundNode = (MovieNode) Trees.searchDepthFirstSearch(root, new Trees.SearchFunction() {
            @Override
            public boolean found(TreeStructure node) {
                return (node instanceof MovieNode && ((MovieNode) node).getMovieEntry().getId() == movieId);
            }
        });
        // von dieser gefundenen Node dÃƒÂ¼rfen wir nun getPathToRoot aufrufen:
        if (foundNode != null) {
            return model.getPathToRoot(foundNode);
        } else {
            return null;
        }
    }


    private void initColumnWidth() {
        // set prototype widths for the columns:
        for (int i = 0; i < MovieTreeModel.COLUMNS.length; i++) {
            int width = i == 0 ? 200 : 40;
            TableColumnExt col = jXMovieTreeTable.getColumnExt(MovieTreeModel.COLUMNS[i]);
            if (col != null) {
                col.setPreferredWidth(width);
            }
        }
    }

    public void updateChronic(ChronicEntry c) {
        jCheckBoxPruneTree.setSelected(c.getView().isPruneTree());
        jCheckBoxListChildren.setSelected(c.getView().isListChildMovies());
    }
}
