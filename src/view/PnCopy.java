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
public class PnCopy extends javax.swing.JPanel {

    private static Color DEFAUT_COLOR_BUTTON = new JButton().getBackground();

    public PnCopy() {
        initComponents();
        initEvents();
    }

    private void initEvents() {
        tfFromToEvent();
        btCopyEvent();
        lbErrorFrom.setVisible(false);
        lbErrorTo.setVisible(false);
    }

    private void tfFromToEvent() {
        tfFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String path = tfFrom.getText().trim();
                GlobalFunction.setVisibleLb(lbErrorFrom, path);
            }
        });

        tfTo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String path = tfTo.getText().trim();
                GlobalFunction.setVisibleLb(lbErrorTo, path);
            }
        });
    }

    private void btCopyEvent() {
        btCopy.addMouseListener(new MouseAdapter() {
            String fromPath = "";
            String toPath = "";

            @Override
            public void mousePressed(MouseEvent e) {
                btCopy.setBackground(Color.GREEN);
                //Ex: Music/NhiLe
                fromPath = tfFrom.getText().replaceAll("[ ]+", " ").trim();
                toPath = tfTo.getText().replaceAll("[ ]+", " ").trim();
                if (GlobalFunction.isValidDouble(btCopy, lbErrorFrom, lbErrorTo, fromPath, toPath)) {
                    try {
                        //Ex: fromPath: Music
                        String orFolder = fromPath.substring(0, fromPath.lastIndexOf("/"));
                        System.out.println("orFolder " + orFolder);

                        //Ex: toPath: nhile
                        String desFolder = toPath.substring(0, toPath.lastIndexOf("/"));
                        System.out.println("desFolder " + desFolder);

                        //Ex: oldFolder: Nhi
                        String nameFolder = fromPath.substring(fromPath.lastIndexOf("/") + 1);
                        System.out.println("oldFolder " + nameFolder);

                        //Ex: mv /home/nhile/Music/Nhi /home/nhile/Demo
                        if (GlobalFunction.getListFodler(orFolder).contains(nameFolder)) {
                            Process process = Runtime.getRuntime().exec(StringUtils.CMD_COPY + StringUtils.HOME_PATH + orFolder + "/" + nameFolder + " " + StringUtils.HOME_PATH + desFolder);
                            System.out.println(StringUtils.CMD_COPY + StringUtils.HOME_PATH + orFolder + "/" + nameFolder + " " + StringUtils.HOME_PATH + desFolder);
                            JOptionPane.showConfirmDialog(null, "Đã Copy thành công", "Message", JOptionPane.YES_OPTION);
                            GlobalFunction.openFolder(StringUtils.HOME_PATH + desFolder);
                            tfFrom.setText("");
                            tfTo.setText("");
                            btCopy.setBackground(DEFAUT_COLOR_BUTTON);
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

        pnTop = new javax.swing.JPanel();
        pnEnterFrom = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfFrom = new javax.swing.JTextField();
        pnErrorFrom = new javax.swing.JPanel();
        lbErrorFrom = new javax.swing.JLabel();
        pnBottom = new javax.swing.JPanel();
        pnCenter = new javax.swing.JPanel();
        pnEnterTo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfTo = new javax.swing.JTextField();
        pnErrorTo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbErrorTo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btCopy = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnTop.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("From Path:");
        pnEnterFrom.add(jLabel3);

        tfFrom.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfFrom.setName(""); // NOI18N
        tfFrom.setPreferredSize(new java.awt.Dimension(300, 40));
        tfFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFromActionPerformed(evt);
            }
        });
        pnEnterFrom.add(tfFrom);

        pnTop.add(pnEnterFrom, java.awt.BorderLayout.PAGE_START);

        lbErrorFrom.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbErrorFrom.setForeground(new java.awt.Color(204, 0, 0));
        lbErrorFrom.setText("** Bạn chưa nhập thông tin ");
        pnErrorFrom.add(lbErrorFrom);

        pnTop.add(pnErrorFrom, java.awt.BorderLayout.PAGE_END);

        add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnBottom.setPreferredSize(new java.awt.Dimension(1000, 160));
        add(pnBottom, java.awt.BorderLayout.PAGE_END);

        pnCenter.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("To Path:");
        jLabel4.setPreferredSize(new java.awt.Dimension(112, 22));
        pnEnterTo.add(jLabel4);

        tfTo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfTo.setName(""); // NOI18N
        tfTo.setPreferredSize(new java.awt.Dimension(300, 40));
        tfTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfToActionPerformed(evt);
            }
        });
        pnEnterTo.add(tfTo);

        pnCenter.add(pnEnterTo, java.awt.BorderLayout.PAGE_START);

        pnErrorTo.setLayout(new java.awt.BorderLayout());

        lbErrorTo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbErrorTo.setForeground(new java.awt.Color(204, 0, 0));
        lbErrorTo.setText("** Bạn chưa nhập thông tin ");
        jPanel1.add(lbErrorTo);

        pnErrorTo.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        btCopy.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btCopy.setText("Copy");
        jPanel2.add(btCopy);

        pnErrorTo.add(jPanel2, java.awt.BorderLayout.CENTER);

        pnCenter.add(pnErrorTo, java.awt.BorderLayout.CENTER);

        add(pnCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tfFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFromActionPerformed

    private void tfToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfToActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCopy;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbErrorFrom;
    private javax.swing.JLabel lbErrorTo;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnEnterFrom;
    private javax.swing.JPanel pnEnterTo;
    private javax.swing.JPanel pnErrorFrom;
    private javax.swing.JPanel pnErrorTo;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTextField tfFrom;
    private javax.swing.JTextField tfTo;
    // End of variables declaration//GEN-END:variables
}
