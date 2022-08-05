package com.weber.cs3230.adminapp;

import com.weber.cs3230.adminapp.api.ApiClient;
import com.weber.cs3230.adminapp.api.IntentAnswer;
import com.weber.cs3230.adminapp.api.IntentDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlexaMainPanel extends JPanel{
    private JTable table;
    private final String[] columnNames = {"ID", "Intent Names", "Date Added"};
    private List<AlexaIntent> list = new ArrayList<>();
    private DefaultTableModel model;

    public AlexaMainPanel() {
        add(createTitle(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        populateTable();
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
        ApiClient apiClient = new ApiClient();
        JPanel buttons = new JPanel(new GridLayout());
        JButton addBut = new JButton("Add");
        JButton editBut = new JButton("Edit");
        JButton deleteBut = new JButton("Delete");
        JButton metricsBut = new JButton("Metrics");

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

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Boolean, Object> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        long alexaIntentID = apiClient.saveNewIntent(saveIntent.getText()).getIntentID();
                        AlexaIntent intent = new AlexaIntent(alexaIntentID, saveIntent.getText(), formattedDateTime, apiClient.getAnswers(alexaIntentID).getAnswers());
                        list.add(intent);
                        return true;
                    }
                    @Override
                    protected void done() {
                        try {
                            if (get() == true) {
                                setCursor(Cursor.getDefaultCursor());
                                model.setDataVector(getTableData(), columnNames);
                                addDialog.dispose();
                                addDialog.setVisible(false);
                                super.done();
                            }
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        } catch (ExecutionException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                };
                swingWorker.execute();
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

                    addDialog.setVisible(true);
                    saveBut.addActionListener(r -> {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        SwingWorker<Boolean, Object> swingWorker = new SwingWorker<>() {
                            @Override
                            protected Boolean doInBackground() throws Exception {
                                apiClient.updateIntent(list.get(table.getSelectedRow()).getID(), saveIntent.getText());
                                list.get(table.getSelectedRow()).setIntentName(saveIntent.getText());
                                list.set(table.getSelectedRow(), list.get(table.getSelectedRow()));
                                return true;
                            }
                            @Override
                            protected void done() {
                                try {
                                    if (get() == true) {
                                        setCursor(Cursor.getDefaultCursor());
                                        model.setDataVector(getTableData(), columnNames);
                                        addDialog.dispose();
                                        addDialog.setVisible(false);
                                        super.done();
                                    }
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                } catch (ExecutionException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        };
                        swingWorker.execute();
                    });
                    intentBut.addActionListener(y -> {
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
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    SwingWorker<Boolean, Object> swingWorker = new SwingWorker<>() {
                        @Override
                        protected Boolean doInBackground() throws Exception {
                            apiClient.deleteIntent(list.get(table.getSelectedRow()).getID());
                            list.remove(table.getSelectedRow());
                            // I'm assuming since we dont create a new GSON on the deleteIntent method call,
                            // that is why I get the failed to parse response into JSON.
                            // same thing happens when i delete an intent answer. so it must be normal?!
                            return true;
                        }
                        @Override
                        protected void done() {
                            try {
                                if (get() == true)  {
                                    setCursor(Cursor.getDefaultCursor());
                                    model.setDataVector(getTableData(), columnNames);
                                    super.done();
                                }
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            } catch (ExecutionException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    };
                    swingWorker.execute();
                }
            });

        metricsBut.addActionListener(e -> {
            LockoutCheck.lastButClick = System.currentTimeMillis();
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Boolean, Object> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        JDialog metricsDialog = new MetricsDialog(apiClient.getMetrics().getMetrics());
                        return true;
                    }
                    @Override
                    protected void done() {
                        try {
                            if (get() == true) {
                                setCursor(Cursor.getDefaultCursor());
                                model.setDataVector(getTableData(), columnNames);
                                super.done();
                            }
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        } catch (ExecutionException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                };
                swingWorker.execute();
        });

        buttons.add(addBut);
        buttons.add(editBut);
        buttons.add(deleteBut);
        buttons.add(metricsBut);
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
        ApiClient apiClient = new ApiClient();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SwingWorker<Boolean, Object> swingWorker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                for (IntentDetail el : apiClient.getIntents().getIntents()) {
                list.add(new AlexaIntent(el.getIntentID(), el.getName(), el.getDateAdded(), apiClient.getAnswers(el.getIntentID()).getAnswers()));
                }
                return true;
            }
            @Override
            protected void done() {
                try {
                    if (get() == true) {
                        setCursor(Cursor.getDefaultCursor());
                        model.setDataVector(getTableData(), columnNames);
                        super.done();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        swingWorker.execute();
    }
}
