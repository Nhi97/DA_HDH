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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import util.StringUtils;

/**
 *
 * @author nhile
 */
public class PnRename extends javax.swing.JPanel {

    private static Color DEFAUT_COLOR_BUTTON = new JButton().getBackground();

    public PnRename() {
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
        btRename.addMouseListener(new MouseAdapter() {
            String parentPath = "";

            @Override
            public void mousePressed(MouseEvent e) {
                btRename.setBackground(Color.GREEN);
                //Ex: Music/NhiLe Demo
                parentPath = tfPath.getText().replaceAll("[ ]+", " ").trim();
                if (GlobalFunction.isValid(btRename, lbError, parentPath)) {
                    try {
                        //Ex:Music
                        String folderPath = parentPath.substring(0, parentPath.lastIndexOf("/"));
                        System.out.println("pathFolder " + folderPath);
                        //Ex:NhiLe
                        String oldName = parentPath.substring(parentPath.lastIndexOf("/") + 1, parentPath.indexOf(" "));
                        System.out.println("nameOld " + oldName);
                        //Ex:Demo
                        String newName = parentPath.substring(parentPath.indexOf(" ") + 1);
                        System.out.println("nameNew " + newName);
                        //Ex: mv /home/nhile/Music/Nhi /home/nhile/Music/Demo
                        if (GlobalFunction.getListFodler(folderPath).contains(oldName)) {
                            Process process = Runtime.getRuntime().exec(StringUtils.CMD_RENAME + StringUtils.HOME_PATH + folderPath + "/" + oldName + " " + StringUtils.HOME_PATH + folderPath + "/" + newName);
                            System.out.println(StringUtils.CMD_RENAME + StringUtils.HOME_PATH + folderPath + "/" + oldName + " " + StringUtils.HOME_PATH + folderPath + "/" + newName);
                            JOptionPane.showConfirmDialog(null, "Đã rename thành công", "Message", JOptionPane.YES_OPTION);
                            GlobalFunction.openFolder(StringUtils.HOME_PATH + folderPath);
                            tfPath.setText("");
                            btRename.setBackground(DEFAUT_COLOR_BUTTON);
                        } else {
                            JOptionPane.showConfirmDialog(null, "File/Folder không tồn tại", "Message", JOptionPane.YES_OPTION);
                        }
                    } catch (IOException ex) {
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
        btRename = new javax.swing.JButton();
        pnError = new javax.swing.JPanel();
        lbError = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        lbPath1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbPath1.setText("Path:");
        pnEnter.add(lbPath1);

        tfPath.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfPath.setPreferredSize(new java.awt.Dimension(300, 40));
        pnEnter.add(tfPath);

        btRename.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btRename.setText("Rename");
        pnEnter.add(btRename);

        add(pnEnter, java.awt.BorderLayout.PAGE_START);

        lbError.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbError.setForeground(new java.awt.Color(204, 0, 0));
        lbError.setText("** Bạn chưa nhập thông tin ");
        pnError.add(lbError);

        add(pnError, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRename;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbPath1;
    private javax.swing.JPanel pnEnter;
    private javax.swing.JPanel pnError;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables
}
