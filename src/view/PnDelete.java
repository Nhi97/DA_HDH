/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import util.StringUtils;

/**
 *
 * @author nhile
 */
public class PnDelete extends javax.swing.JPanel {

    private static Color DEFAUT_COLOR_BUTTON = new JButton().getBackground();

    public PnDelete() {
        initComponents();
        initEvents();
    }

    private void initEvents() {
        tfOrDesPathEvent();
        btRenameEvent();
        lbError.setVisible(false);
    }

    private void tfOrDesPathEvent() {
        tfPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String path = tfPath.getText().trim();
                GlobalFunction.setVisibleLb(lbError, path);
            }
        });
    }

    private void btRenameEvent() {
        btDelete.addMouseListener(new MouseAdapter() {
            String parentPath = "";

            @Override
            public void mousePressed(MouseEvent e) {
                btDelete.setBackground(Color.GREEN);
                parentPath = tfPath.getText();
                if (GlobalFunction.isValid(btDelete, lbError, parentPath)) {
                    String pathFolder = parentPath.substring(0, parentPath.lastIndexOf("/"));
                    String nameFolder = parentPath.substring(parentPath.lastIndexOf("/") + 1);
                    if (GlobalFunction.getListFodler(pathFolder).contains(nameFolder)) {
                        try {
                            Process process = Runtime.getRuntime().exec(StringUtils.CMD_DELETE + StringUtils.HOME_PATH + parentPath); // for Linux
                            System.out.println("delete " + StringUtils.CMD_DELETE + StringUtils.HOME_PATH + parentPath);
                            //Process process = Runtime.getRuntime().exec("cmd /c dir"); //for Windows
                            process.waitFor();
                            JOptionPane.showConfirmDialog(null, "Đã Delete thành công", "Message", JOptionPane.YES_OPTION);
                            GlobalFunction.openFolder(StringUtils.HOME_PATH + pathFolder);
                            tfPath.setText("");
                            btDelete.setBackground(DEFAUT_COLOR_BUTTON);

                        } catch (InterruptedException | IOException ex) {
                        }
                    } else {
                        JOptionPane.showConfirmDialog(null, "File/Folder không tồn tại", "Message", JOptionPane.NO_OPTION);
                        btDelete.setBackground(DEFAUT_COLOR_BUTTON);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnEnter = new javax.swing.JPanel();
        lbPath1 = new javax.swing.JLabel();
        tfPath = new javax.swing.JTextField();
        btDelete = new javax.swing.JButton();
        pnError = new javax.swing.JPanel();
        lbError = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        lbPath1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbPath1.setText("Path:");
        pnEnter.add(lbPath1);

        tfPath.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfPath.setPreferredSize(new java.awt.Dimension(300, 40));
        pnEnter.add(tfPath);

        btDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btDelete.setText("Delete");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        pnEnter.add(btDelete);

        add(pnEnter, java.awt.BorderLayout.PAGE_START);

        lbError.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbError.setForeground(new java.awt.Color(204, 0, 0));
        lbError.setText("** Bạn chưa nhập thông tin ");
        pnError.add(lbError);

        add(pnError, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbPath1;
    private javax.swing.JPanel pnEnter;
    private javax.swing.JPanel pnError;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables
}
