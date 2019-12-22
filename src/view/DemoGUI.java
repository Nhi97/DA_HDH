package view;

import common.ButtonEnum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jtree.JFTree;
import util.StringUtils;

/**
 *
 * @author nhile
 */
public class DemoGUI extends javax.swing.JFrame {

    private final Color HIGHT_LIGHT_BUTTON = Color.GREEN;
    private Color DEFAUT_COLOR_BUTTON = new JButton().getBackground();

    public DemoGUI() {
        initComponents();

        initComps();
        initEvents();
    }

    private void initComps() {
        this.setTitle(StringUtils.TITLE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initEvents() {
        onMouseEnterButton();
        onMouseClickButton();
        tfSearchEvent();
        btSearchEvent();
    }

    private void onMouseEnterButton() {

        Component[] comps = pnBottom.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        DEFAUT_COLOR_BUTTON = button.getBackground();
                        button.setBackground(HIGHT_LIGHT_BUTTON);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setBackground(DEFAUT_COLOR_BUTTON);
                    }

                });
            }
        }
    }

    private void onMouseClickButton() {
        Component[] comps = pnBottom.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        resetButtonColor();
                        button.setBackground(HIGHT_LIGHT_BUTTON);
                        DEFAUT_COLOR_BUTTON = button.getBackground();

                        ButtonEnum key = ButtonEnum.convertToEnum(button.getName());
                        pnCenter.removeAll();
                        switch (key) {

                            case CreateFolder: {
                                buttonEvent(new PnCreateFolder(), pnCenter, pnTop);
                                break;
                            }
                            case CreateFile: {
                                buttonEvent(new PnCreateFile(), pnCenter, pnTop);
                                break;
                            }
                            case Rename: {
                                buttonEvent(new PnRename(), pnCenter, pnTop);
                                break;
                            }
                            case Delete: {
                                buttonEvent(new PnDelete(), pnCenter, pnTop);
                                break;
                            }
                            case Move: {
                                buttonEvent(new PnMove(), pnCenter, pnTop);
                                break;
                            }
                            case Copy: {
                                buttonEvent(new PnCopy(), pnCenter, pnTop);
                                break;
                            }

                            case Reset: {
                                pnTop.setVisible(true);
                                break;
                            }
                        }
                        pnCenter.revalidate();
                        pnCenter.repaint();
                    }
                });
            }
        }

    }

    //function of buttton CreateFolder
    private void buttonEvent(JPanel panel, JPanel pnCenter, JPanel pnTop) {
        pnCenter.add(panel);
        pnTop.setVisible(false);

    }

    private void tfSearchEvent() {
        tfSearch.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btSearch.setBackground(DEFAUT_COLOR_BUTTON);
                    String path = StringUtils.HOME_PATH + tfSearch.getText();
                    addLable(path);
                }

            }
        });
    }

    private void btSearchEvent() {
        btSearch.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                btSearch.setBackground(HIGHT_LIGHT_BUTTON);
                String path = StringUtils.HOME_PATH + tfSearch.getText();
                addLable(path);
            }
        });
        tfSearch.setText("");
        btSearch.setBackground(DEFAUT_COLOR_BUTTON);
    }

    private void addLable(String path) {
        JFTree fTree = new JFTree(path);
        pnCenter.add(fTree);
        pnCenter.revalidate();
        pnCenter.repaint();
    }

    //reset color button
    private void resetButtonColor() {
        Component[] comps = pnBottom.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(DEFAUT_COLOR_BUTTON);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        lbFolder = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();
        pnBottom = new javax.swing.JPanel();
        btCreatFolder = new javax.swing.JButton();
        btCreatFile = new javax.swing.JButton();
        btCopy = new javax.swing.JButton();
        btRename = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btMove = new javax.swing.JButton();
        btReset = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbFolder.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbFolder.setText("Folder:");
        pnTop.add(lbFolder);

        tfSearch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfSearch.setPreferredSize(new java.awt.Dimension(300, 40));
        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });
        pnTop.add(tfSearch);

        btSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search32.png"))); // NOI18N
        btSearch.setPreferredSize(new java.awt.Dimension(50, 45));
        pnTop.add(btSearch);

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 10, 30));
        pnBottom.setLayout(new java.awt.GridLayout(2, 4, 10, 10));

        btCreatFolder.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btCreatFolder.setForeground(new java.awt.Color(0, 0, 0));
        btCreatFolder.setText("Create Folder");
        btCreatFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCreatFolder.setName("CREATEFOLDER"); // NOI18N
        btCreatFolder.setPreferredSize(new java.awt.Dimension(125, 38));
        btCreatFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreatFolderActionPerformed(evt);
            }
        });
        pnBottom.add(btCreatFolder);

        btCreatFile.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btCreatFile.setForeground(new java.awt.Color(0, 0, 0));
        btCreatFile.setText("Create File");
        btCreatFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCreatFile.setName("CREATEFILE"); // NOI18N
        btCreatFile.setPreferredSize(new java.awt.Dimension(125, 38));
        btCreatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreatFileActionPerformed(evt);
            }
        });
        pnBottom.add(btCreatFile);

        btCopy.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btCopy.setForeground(new java.awt.Color(0, 0, 0));
        btCopy.setText("Copy");
        btCopy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCopy.setName("COPY"); // NOI18N
        btCopy.setPreferredSize(new java.awt.Dimension(125, 38));
        btCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCopyActionPerformed(evt);
            }
        });
        pnBottom.add(btCopy);

        btRename.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btRename.setForeground(new java.awt.Color(0, 0, 0));
        btRename.setText("Rename");
        btRename.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRename.setName("RENAME"); // NOI18N
        btRename.setPreferredSize(new java.awt.Dimension(125, 38));
        pnBottom.add(btRename);

        btDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btDelete.setForeground(new java.awt.Color(0, 0, 0));
        btDelete.setText("Delete");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.setName("DELETE"); // NOI18N
        btDelete.setPreferredSize(new java.awt.Dimension(125, 38));
        pnBottom.add(btDelete);

        btMove.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btMove.setForeground(new java.awt.Color(0, 0, 0));
        btMove.setText("Move");
        btMove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMove.setName("MOVE"); // NOI18N
        btMove.setPreferredSize(new java.awt.Dimension(125, 38));
        pnBottom.add(btMove);

        btReset.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btReset.setForeground(new java.awt.Color(0, 0, 0));
        btReset.setText("Reset");
        btReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btReset.setName("RESET"); // NOI18N
        btReset.setPreferredSize(new java.awt.Dimension(125, 38));
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        pnBottom.add(btReset);

        getContentPane().add(pnBottom, java.awt.BorderLayout.PAGE_END);

        pnCenter.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchActionPerformed

    private void btCreatFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreatFolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCreatFolderActionPerformed

    private void btCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCopyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCopyActionPerformed

    private void btCreatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreatFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCreatFileActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DemoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DemoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DemoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DemoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DemoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCopy;
    private javax.swing.JButton btCreatFile;
    private javax.swing.JButton btCreatFolder;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btMove;
    private javax.swing.JButton btRename;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSearch;
    private javax.swing.JLabel lbFolder;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
