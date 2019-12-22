package jtree;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author nhile
 */
public class JFTree extends JPanel {

    private File folder;
    private static String pathFolder;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private FileTableModel fileTableModel;
    private JTable table;
    private File currentFile;
    private FileSystemView fileSystemView;
    private ListSelectionListener listSelectionListener;
    private JLabel fileName;
    private JTextField path;
    private JLabel date;
    private JLabel size;
    private static JScrollPane treeScroll;
    private TreeSelectionListener treeSelectionListener;

    public JFTree(String pathFolder) {
        //this.setLayout(new BorderLayout());
        this.pathFolder = pathFolder;

        fileSystemView = FileSystemView.getFileSystemView();
        folder = new File(pathFolder);
        System.out.println("folder: " + folder);
        tree = new JTree();
        treeScroll = new JScrollPane();
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        root = new DefaultMutableTreeNode(folder);
        treeModel = new DefaultTreeModel(root);
        File[] listFiles = folder.listFiles();

        for (File file : listFiles) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
            root.add(node);
        }

        treeSelectionListener = new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent tse) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
                showChildren(node);
            }
        };

        tree.setModel(treeModel);
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(treeSelectionListener);
        tree.setCellRenderer(new FileTreeCellRenderer());
        tree.expandRow(0);
        tree.setSelectionRow(0);
        treeScroll.setViewportView(tree);
        treeScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(treeScroll);
        System.out.println("==============");
    }

    private void initComps() {

        fileName = new JLabel();
        path = new JTextField();
        date = new JLabel();
        size = new JLabel();
        table = new JTable();

        setSize(1000, 600);

        setVisible(true);

    }

    protected void showChildren(DefaultMutableTreeNode node) {
        tree.setEnabled(false);
        SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
            @Override
            public Void doInBackground() {
                File file = (File) node.getUserObject();
                //setFileDetails(file);
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (node.isLeaf()) {
                        for (File child : listFiles) {
                            if (child.isDirectory()) {
                                publish(child);
                            }
                        }
                    }
                    //setTableData(listFiles);
                }
                return null;
            }

            @Override
            protected void process(List<File> chunks) {
                for (File child : chunks) {
                    node.add(new DefaultMutableTreeNode(child));
                }
            }

            @Override
            protected void done() {;
                tree.setEnabled(true);
            }
        };
        worker.execute();
    }

    private void setTableData(File[] files) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (fileTableModel == null) {
                    fileTableModel = new FileTableModel();
                    table.setModel(fileTableModel);
                }
                table.getSelectionModel().removeListSelectionListener(listSelectionListener);
                fileTableModel.setFiles(files);
                table.getSelectionModel().addListSelectionListener(listSelectionListener);

            }
        });
    }

    private void setFileDetails(File file) {
        currentFile = file;
        Icon icon = fileSystemView.getSystemIcon(file);
        fileName.setIcon(icon);
        fileName.setText(fileSystemView.getSystemDisplayName(file));
        path.setText(file.getPath());
        date.setText(new Date(file.lastModified()).toString());
        size.setText(file.length() + " bytes");
    }

}
