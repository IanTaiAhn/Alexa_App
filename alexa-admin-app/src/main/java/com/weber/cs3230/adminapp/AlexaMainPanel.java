package com.weber.cs3230.adminapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlexaMainPanel extends JPanel{
    private long count = 10;
    JTable table;
    private final String[] columnNames = {"ID", "Intent Names", "Date Added"};
    List<AlexaIntentObject> list = new ArrayList<>();
    DefaultTableModel model;
    public AlexaMainPanel() {
        // Order matters !
        populateTable();
        add(createTitle(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JLabel createTitle() {
        JLabel label = new JLabel("Alexa Admin Utility");
        label.setHorizontalAlignment(JLabel.CENTER);
        Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        label.setFont(f1);
        return label;
    }

        private JComponent createTablePanel() {
        model = new DefaultTableModel(getTableData(), columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setVisible(true);
        return scrollPane;
    }
    // Come back, refactor, and clean it up.
    private JPanel createButtonPanel()  {
        JPanel buttons = new JPanel(new GridLayout());
        JButton addBut = new JButton("Add");
        JButton editBut = new JButton("Edit");
        JButton deleteBut = new JButton("Delete");

        addBut.addActionListener(e -> {
            JDialog addDialog = new JDialog();
            JPanel addPanel = new JPanel(new GridLayout(2, 2));
            JTextField saveIntent = new JTextField();

            JButton saveBut = new JButton("Save");
            JButton cancelBut = new JButton("Cancel");

            addDialog.setVisible(true);
            saveBut.addActionListener(r -> {
                LocalDateTime currentLocalDateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

                AlexaIntentObject intent = new AlexaIntentObject(count++, saveIntent.getText(), formattedDateTime);
                list.add(intent);
                model.setDataVector(getTableData(), columnNames);
                addDialog.dispose();
                addDialog.setVisible(false);
            });
            cancelBut.addActionListener(t -> {
                addDialog.dispose();
                addDialog.setVisible(false);
            });
            addPanel.add(new JLabel("IntentName"));
            addPanel.add(saveIntent);
            addPanel.add(saveBut);
            addPanel.add(cancelBut);
            addPanel.setVisible(true);

            addDialog.setSize(new Dimension(300, 200));
            addDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addDialog.add(addPanel);
        });
            editBut.addActionListener(e -> {
                if (table.getSelectedRow() > -1) {
                    JDialog addDialog = new JDialog();
                    JPanel addPanel = new JPanel(new GridLayout(2, 2));
                    JTextField saveIntent = new JTextField();

                    JButton saveBut = new JButton("Save");
                    JButton cancelBut = new JButton("Cancel");

                    addDialog.setVisible(true);
                    saveBut.addActionListener(r -> {

                        list.get(table.getSelectedRow()).setIntentName(saveIntent.getText());
                        model.setDataVector(getTableData(), columnNames);
                        addDialog.dispose();
                        addDialog.setVisible(false);
                    });
                    cancelBut.addActionListener(t -> {
                        addDialog.dispose();
                        addDialog.setVisible(false);
                        System.out.println("working");
                    });

                    addPanel.add(new JLabel("IntentName"));
                    addPanel.add(saveIntent);
                    addPanel.add(saveBut);
                    addPanel.add(cancelBut);
                    addPanel.setVisible(true);

                    addDialog.setSize(new Dimension(300, 200));
                    addDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    addDialog.add(addPanel);
                }
            });
            deleteBut.addActionListener(e -> {
                if (table.isRowSelected(table.getSelectedRow())) {
                    System.out.println(list.size());
                    list.remove(table.getSelectedRow());
                    model.setDataVector(getTableData(), columnNames);
                }
            });
        buttons.add(addBut);
        buttons.add(editBut);
        buttons.add(deleteBut);
        return buttons;
    }

    private Object[][] getTableData() {
        java.util.List<Object[]> rows = new ArrayList<>();
        for (AlexaIntentObject el : list)   {
            Object[] row = new Object[3];
            row[0] = el.getID();
            row[1] = el.getIntentName();
            row[2] = el.getDateAdded();
            rows.add(row);
        }
        return rows.toArray(new Object[0][0]);
    }

    private void populateTable()    {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
        AlexaIntentObject intent0 = new AlexaIntentObject(0, "PullAltitude", formattedDateTime);
        AlexaIntentObject intent1 = new AlexaIntentObject(1, "SkydivingGear", formattedDateTime);
        AlexaIntentObject intent2 = new AlexaIntentObject(2, "CircleOfAwareness", formattedDateTime);
        AlexaIntentObject intent3 = new AlexaIntentObject(3, "SkydivingRush", formattedDateTime);
        AlexaIntentObject intent4 = new AlexaIntentObject(4, "DecisionAltitude", formattedDateTime);
        AlexaIntentObject intent5 = new AlexaIntentObject(5, "StayCalm", formattedDateTime);
        AlexaIntentObject intent6 = new AlexaIntentObject(6, "ParamountPartOfSkydiving", formattedDateTime);
        AlexaIntentObject intent7 = new AlexaIntentObject(7, "Jumprun", formattedDateTime);
        AlexaIntentObject intent8 = new AlexaIntentObject(8, "WindsAloft", formattedDateTime);
        AlexaIntentObject intent9 = new AlexaIntentObject(9, "Freefall", formattedDateTime);

        list.add(intent0);
        list.add(intent1);
        list.add(intent2);
        list.add(intent3);
        list.add(intent4);
        list.add(intent5);
        list.add(intent6);
        list.add(intent7);
        list.add(intent8);
        list.add(intent9);
    }
}
