package view;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import util.StringUtils;

/**
 *
 * @author nhile
 */
public class GlobalFunction extends JFrame {

    public static void setVisibleLb(JLabel label, String value) {
        if (value.isEmpty()) {
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }
    }

    public static boolean isValid(JButton button, JLabel lbError, String path) {
        if (path.isEmpty()) {
            lbError.setVisible(true);
            button.setBackground(new JButton().getBackground());
            return false;
        }
        return true;
    }

    public static boolean isValidDouble(JButton button, JLabel lbErrorOr, JLabel lbErrorDes, String oriPath, String desPath) {
        if (oriPath.isEmpty()) {
            lbErrorOr.setVisible(true);
            button.setBackground(new JButton().getBackground());
            if (desPath.isEmpty()) {
                lbErrorDes.setVisible(true);
                button.setBackground(new JButton().getBackground());
                return false;
            }
            return false;
        }

        return true;
    }
    
    public static void openFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(PnCreateFolder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public static List<String> getListFodler(String pathFolder) {
        List<String> listFolder = new ArrayList<>();
        String[] arrString;
        try {
            Process process = Runtime.getRuntime().exec(StringUtils.CMD_SHOW_FOLDER + StringUtils.HOME_PATH + pathFolder); // for Linux
            System.out.println("path: " + StringUtils.CMD_SHOW_FOLDER + StringUtils.HOME_PATH + pathFolder);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                arrString = line.split(" ");
                for (String s : arrString) {
                    listFolder.add(s);
                }
            }
        } catch (Exception e) {
        }
        return listFolder;
    }
}
