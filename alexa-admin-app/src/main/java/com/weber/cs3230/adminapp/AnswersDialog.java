package com.weber.cs3230.adminapp;

import com.weber.cs3230.adminapp.api.ApiClient;
import com.weber.cs3230.adminapp.api.IntentAnswer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnswersDialog extends JDialog {
    private DefaultTableModel model;
    private JTable table;
    private List<IntentAnswer> answersList;
    private long alexaIntentID;
    private final String[] columnNames = {"Intent Answers"};

    public AnswersDialog(List list, long alexaId) {
        this.answersList = list;
        this.alexaIntentID = alexaId;
        setPreferredSize(new Dimension(600, 400));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(mainAnswerPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(butPanel(), BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private JPanel mainAnswerPanel()    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Answers"));

        return panel;
    }
    private JComponent createTablePanel() {
        model = new DefaultTableModel(getTableData(), columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setVisible(true);

        model.setDataVector(getTableData(), columnNames);
        return scrollPane;
    }

    private JPanel butPanel()   {
        JPanel panel = new JPanel();
        ApiClient apiClient = new ApiClient();
        JTextField answerText = new JTextField("add here!", 10);
        JTextField editAnswerText = new JTextField("edit here!", 10);
        JButton addBut = new JButton("Add");
        JButton editBut = new JButton("Edit");
        JButton deleteBut = new JButton("Delete");

        addBut.addActionListener(e -> {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
                @Override
                protected Object doInBackground() throws Exception {
                    IntentAnswer newAnswer = new IntentAnswer();
                    newAnswer.setIntentID(alexaIntentID);
                    newAnswer.setText(answerText.getText());
                    apiClient.saveNewAnswer(alexaIntentID, answerText.getText());
                    answersList.add(newAnswer);
                    return null;
                }
                @Override
                protected void done() {
                    setCursor(Cursor.getDefaultCursor());
                    answerText.setText("");
                    model.setDataVector(getTableData(), columnNames);
                    super.done();
                }
            };
            swingWorker.execute();
        });
        editBut.addActionListener(e -> {
            if (table.getSelectedRow() > -1)    {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        long answerID = apiClient.getAnswers(alexaIntentID).getAnswers().get(table.getSelectedRow()).getAnswerID();
                        answersList.set(table.getSelectedRow(), apiClient.updateAnswer(alexaIntentID, answerID, editAnswerText.getText()));
                        return null;
                    }
                    @Override
                    protected void done() {
                        setCursor(Cursor.getDefaultCursor());
                        editAnswerText.setText("");
                        model.setDataVector(getTableData(), columnNames);
                        super.done();
                    }
                };
                swingWorker.execute();
            }
        });
        deleteBut.addActionListener(u -> {
            if (table.getSelectedRow() > -1)    {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        // Idk why, but this delete method triggers a raw response to return,
                        // and I'm wondering if that is because we don't actually need to create a GSON object?
                        // so I think that it's okay for now.
                        long answerID = apiClient.getAnswers(alexaIntentID).getAnswers().get(table.getSelectedRow()).getAnswerID();
                        apiClient.deleteAnswer(alexaIntentID, answerID);
                        answersList.remove(table.getSelectedRow());
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
            }
        });
        answerText.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        panel.add(answerText);
        panel.add(addBut);
        editAnswerText.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        panel.add(editAnswerText);
        panel.add(editBut);
        panel.add(deleteBut);
        return panel;
    }

    private Object[][] getTableData() {
        java.util.List<Object[]> rows = new ArrayList<>();
        for (IntentAnswer el : answersList)   {
            Object[] row = new Object[1];
            row[0] = el.getText();
            rows.add(row);
        }
        return rows.toArray(new Object[0][0]);
    }
}

