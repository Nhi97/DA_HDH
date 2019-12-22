package jtree;

import java.io.File;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

public class FileTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private File[] files;

    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();

    public FileTableModel() {
        this(new File[0]);
    }

    public FileTableModel(File[] files) {
        this.files = files;
    }
    private String[] columns = {
        "Icon",
        "File/Folder",
        "Last Modified",
        "Path/name",
        "Size"
    };

    @Override
    public String getColumnName(int index) {
        return columns[index];
    }

    public int getColumnCount() {
        return columns.length;
    }

    public int getRowCount() {
        return files.length;
    }

    public File getFile(int row) {
        return files[row];
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        switch (column) {
            case 0:
                return fileSystemView.getSystemIcon(file);
            case 1:
                return fileSystemView.getSystemDisplayName(file);
            case 2:
                return file.lastModified();
            case 3:
                return file.getPath();
            case 4:
                return file.length();
            default:
                System.err.println("Logic Error");
        }
        return "";
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return ImageIcon.class;
            case 2:
                return Date.class;
            case 4:
                return Long.class;
        }
        return String.class;
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
}
