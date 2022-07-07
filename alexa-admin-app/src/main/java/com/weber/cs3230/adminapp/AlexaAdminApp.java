package com.weber.cs3230.adminapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AlexaAdminApp {

    public static void main(String[] args) {
        AlexaAdminApp alexaAdminApp = new AlexaAdminApp();

        SwingUtilities.invokeLater(() -> alexaAdminApp.showMainFrame());
    }

    private void showMainFrame() {
        JFrame mainFrame = new JFrame();
        mainFrame.setPreferredSize(new Dimension(400, 600));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(createMainPanel());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private JComponent createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Alexa Admin Utility");
        label.setHorizontalAlignment(JLabel.CENTER);
        Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        label.setFont(f1);

        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(createTablePanel(),BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }

    private JComponent createTablePanel() {
        String[] columnNames = {"Intent Name", "Date Added"};
        Object[][] data = {
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"},
                {"DummyData", "02/02/2022"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setVisible(true);
        return scrollPane;
    }

    private JComponent createButtonPanel()  {
        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Delete"));
        buttonPanel.add(new JButton("Edit"));
        return buttonPanel;
    }

}
