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
public class PnMove extends javax.swing.JPanel {

    private static Color DEFAUT_COLOR_BUTTON = new JButton().getBackground();

    public PnMove() {
        initComponents();
        initEvents();
    }

    private void initEvents() {
        tfOrDesPathEvent();
        btRenameEvent();
        lbErrorOr.setVisible(false);
        lbErrorDes.setVisible(false);
    }

    private void tfOrDesPathEvent() {
        tfPathOr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String path = tfPathOr.getText().trim();
                GlobalFunction.setVisibleLb(lbErrorOr, path);
            }
        });

        tfPathDes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String path = tfPathDes.getText().trim();
                GlobalFunction.setVisibleLb(lbErrorDes, path);
            }
        });

    }

    private void btRenameEvent() {

        btMove.addMouseListener(new MouseAdapter() {
            String orPath = "";
            String desPath = "";

            @Override
            public void mousePressed(MouseEvent e) {
                btMove.setBackground(Color.GREEN);
                //Ex: Music/NhiLe
                orPath = tfPathOr.getText().replaceAll("[ ]+", " ").trim();
                desPath = tfPathDes.getText().replaceAll("[ ]+", " ").trim();
                if (GlobalFunction.isValidDouble(btMove, lbErrorOr, lbErrorDes, orPath, desPath)) {
                    try {
                        //Ex:orPath: Music
                        String orFolder = orPath.substring(0, orPath.lastIndexOf("/"));
                        System.out.println("orFolder " + orFolder);

                        //Ex: desPath: nhile
                        String desFolder = desPath.substring(0, desPath.lastIndexOf("/"));
                        System.out.println("desFolder " + desFolder);

                        //Ex: oldFolder: Nhi
                        String oldFolder = orPath.substring(orPath.lastIndexOf("/") + 1);
                        System.out.println("oldFolder " + oldFolder);

                        //Ex: newFolder: Demo
                        String newFolder = desPath.substring(desPath.lastIndexOf("/") + 1);
                        System.out.println("newFolder " + newFolder);

                        //Ex: mv /home/nhile/Music/Nhi /home/nhile/Demo
                        if (GlobalFunction.getListFodler(orFolder).contains(oldFolder)) {
                            Process process = Runtime.getRuntime().exec(StringUtils.CMD_MOVE + StringUtils.HOME_PATH + orFolder + "/" + oldFolder + " " + StringUtils.HOME_PATH + desFolder + "/" + newFolder);
                            System.out.println(StringUtils.CMD_MOVE + StringUtils.HOME_PATH + orFolder + "/" + oldFolder + " " + StringUtils.HOME_PATH + desFolder + "/" + newFolder);
                            JOptionPane.showConfirmDialog(null, "Đã move thành công", "Message", JOptionPane.YES_OPTION);
                            GlobalFunction.openFolder(StringUtils.HOME_PATH + desFolder);
                            tfPathOr.setText("");
                            tfPathDes.setText("");
                            btMove.setBackground(DEFAUT_COLOR_BUTTON);
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
        pnPathOr = new javax.swing.JPanel();
        lbPath1 = new javax.swing.JLabel();
        tfPathOr = new javax.swing.JTextField();
        pnErrorOr = new javax.swing.JPanel();
        lbErrorOr = new javax.swing.JLabel();
        pnCenter = new javax.swing.JPanel();
        pnPathDes = new javax.swing.JPanel();
        lbPath2 = new javax.swing.JLabel();
        tfPathDes = new javax.swing.JTextField();
        pnErrorDes = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbErrorDes = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btMove = new javax.swing.JButton();
        pnBottom = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnTop.setLayout(new java.awt.BorderLayout());

        lbPath1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbPath1.setText("Origin Path: ");
        lbPath1.setPreferredSize(new java.awt.Dimension(178, 22));
        pnPathOr.add(lbPath1);

        tfPathOr.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfPathOr.setPreferredSize(new java.awt.Dimension(300, 40));
        pnPathOr.add(tfPathOr);

        pnTop.add(pnPathOr, java.awt.BorderLayout.PAGE_START);

        lbErrorOr.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbErrorOr.setForeground(new java.awt.Color(204, 0, 0));
        lbErrorOr.setText("** Bạn chưa nhập thông tin ");
        pnErrorOr.add(lbErrorOr);

        pnTop.add(pnErrorOr, java.awt.BorderLayout.CENTER);

        add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setLayout(new java.awt.BorderLayout());

        lbPath2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbPath2.setText("Destination Path:");
        pnPathDes.add(lbPath2);

        tfPathDes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfPathDes.setPreferredSize(new java.awt.Dimension(300, 40));
        pnPathDes.add(tfPathDes);

        pnCenter.add(pnPathDes, java.awt.BorderLayout.PAGE_START);

        pnErrorDes.setLayout(new java.awt.BorderLayout());

        lbErrorDes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbErrorDes.setForeground(new java.awt.Color(204, 0, 0));
        lbErrorDes.setText("** Bạn chưa nhập thông tin ");
        jPanel3.add(lbErrorDes);

        pnErrorDes.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        btMove.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btMove.setText("Move");
        jPanel4.add(btMove);

        pnErrorDes.add(jPanel4, java.awt.BorderLayout.CENTER);

        pnCenter.add(pnErrorDes, java.awt.BorderLayout.PAGE_END);

        add(pnCenter, java.awt.BorderLayout.CENTER);

        pnBottom.setPreferredSize(new java.awt.Dimension(400, 260));
        add(pnBottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btMove;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbErrorDes;
    private javax.swing.JLabel lbErrorOr;
    private javax.swing.JLabel lbPath1;
    private javax.swing.JLabel lbPath2;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnErrorDes;
    private javax.swing.JPanel pnErrorOr;
    private javax.swing.JPanel pnPathDes;
    private javax.swing.JPanel pnPathOr;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTextField tfPathDes;
    private javax.swing.JTextField tfPathOr;
    // End of variables declaration//GEN-END:variables
}
