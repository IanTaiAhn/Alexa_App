package com.weber.cs3230.adminapp;

import com.weber.cs3230.adminapp.api.ApiClient;
import com.weber.cs3230.adminapp.api.IntentDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlexaMainPanel extends JPanel{
    private long count = 10;
    private JTable table;
    private final String[] columnNames = {"ID", "Intent Names", "Date Added"};
    private List<AlexaIntent> list = new ArrayList<>();
    private DefaultTableModel model;

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
    private JPanel createButtonPanel()  {
        JPanel buttons = new JPanel(new GridLayout());
        JButton addBut = new JButton("Add");
        JButton editBut = new JButton("Edit");
        JButton deleteBut = new JButton("Delete");

        addBut.addActionListener(e -> {
            LockoutCheck.lastButClick = System.currentTimeMillis();
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

                AlexaIntent intent = new AlexaIntent(count++, saveIntent.getText(), formattedDateTime, new ArrayList());
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
                LockoutCheck.lastButClick = System.currentTimeMillis();
                if (table.getSelectedRow() > -1) {
                    JDialog addDialog = new JDialog();
                    JPanel addPanel = new JPanel(new GridLayout(2, 2));
                    JTextField saveIntent = new JTextField();

                    JButton saveBut = new JButton("Save");
                    JButton cancelBut = new JButton("Cancel");
                    JButton intentBut = new JButton("Answers");

                    System.out.println(list.size());
                    System.out.println(list.get(table.getSelectedRow()).getID());
                    addDialog.setVisible(true);
                    saveBut.addActionListener(r -> {
                        list.get(table.getSelectedRow()).setIntentName(saveIntent.getText());
                        model.setDataVector(getTableData(), columnNames);
                        addDialog.dispose();
                        addDialog.setVisible(false);
                    });
                    intentBut.addActionListener(y -> {
                        // create a new dialog that allows the adding, and editing of intent answers.
                        JDialog answerDialog = new AnswersDialog(list.get(table.getSelectedRow()).getIntentAnswerList(), list.get(table.getSelectedRow()).getID());
                    });
                    cancelBut.addActionListener(t -> {
                        addDialog.dispose();
                        addDialog.setVisible(false);
                    });

                    addPanel.add(new JLabel("IntentName"));
                    addPanel.add(saveIntent);
                    addPanel.add(saveBut);
                    addPanel.add(cancelBut);
                    addPanel.add(intentBut);
                    addPanel.setVisible(true);

                    addDialog.setSize(new Dimension(500, 300));
                    addDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    addDialog.add(addPanel);
                }
            });

            deleteBut.addActionListener(e -> {
                LockoutCheck.lastButClick = System.currentTimeMillis();
                if (table.isRowSelected(table.getSelectedRow())) {
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
        for (AlexaIntent el : list)   {
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

        ApiClient apiClient = new ApiClient();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() throws Exception {
                for (IntentDetail el : apiClient.getIntents().getIntents()) {
                list.add(new AlexaIntent(el.getIntentID(), el.getName(), el.getDateAdded(), apiClient.getAnswers(el.getIntentID()).getAnswers()));
                }
                System.out.println("list size INSIDE worker methods" + list.size());

                return null;
            }
            @Override
            protected void done() {
                setCursor(Cursor.getDefaultCursor());
                model.setDataVector(getTableData(), columnNames);
                super.done();
            }
        };
        swingWorker.execute();
        System.out.println("list size outside worker methods" + list.size());
//        AlexaIntent intent0 = new AlexaIntent(0, "PullAltitude", formattedDateTime, new ArrayList());
//        AlexaIntent intent1 = new AlexaIntent(1, "SkydivingGear", formattedDateTime, new ArrayList());
//        AlexaIntent intent2 = new AlexaIntent(2, "CircleOfAwareness", formattedDateTime, new ArrayList());
//        AlexaIntent intent3 = new AlexaIntent(3, "SkydivingRush", formattedDateTime, new ArrayList());
//        AlexaIntent intent4 = new AlexaIntent(4, "DecisionAltitude", formattedDateTime, new ArrayList());
//        AlexaIntent intent5 = new AlexaIntent(5, "StayCalm", formattedDateTime, new ArrayList());
//        AlexaIntent intent6 = new AlexaIntent(6, "ParamountPartOfSkydiving", formattedDateTime, new ArrayList());
//        AlexaIntent intent7 = new AlexaIntent(7, "Jumprun", formattedDateTime, new ArrayList());
//        AlexaIntent intent8 = new AlexaIntent(8, "WindsAloft", formattedDateTime, new ArrayList());
//        AlexaIntent intent9 = new AlexaIntent(9, "Freefall", formattedDateTime, new ArrayList());
//
//        list.add(intent0);
//        list.add(intent1);
//        list.add(intent2);
//        list.add(intent3);
//        list.add(intent4);
//        list.add(intent5);
//        list.add(intent6);
//        list.add(intent7);
//        list.add(intent8);
//        list.add(intent9);
    }
}
