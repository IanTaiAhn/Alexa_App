package com.weber.cs3230.adminapp;

import com.weber.cs3230.adminapp.api.MetricUI;
import com.weber.cs3230.adminapp.api.MetricUIList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MetricsDialog extends  JDialog{
        private DefaultTableModel model;
        private JTable table;
        private List<MetricUI> metricList;
        private final String[] columnNames = {"EventName", "EventCount", "Date Added"};

        public MetricsDialog(List list) {
            this.metricList = list;
            setPreferredSize(new Dimension(600, 400));
            setModalityType(ModalityType.APPLICATION_MODAL);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            add(mainMetricsPanel(), BorderLayout.NORTH);
//            add(mainMetricsPanel());
            add(createTablePanel(), BorderLayout.CENTER);
//            add(createTablePanel());
//            add(butPanel(), BorderLayout.SOUTH);
            pack();
            setVisible(true);
        }

        private JPanel mainMetricsPanel()    {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Metrics"));

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
/*
        private JPanel butPanel()   {
            JPanel panel = new JPanel();
            ApiClient apiClient = new ApiClient();
            JButton addBut = new JButton("Add");

/*
            addBut.addActionListener(e -> {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Object doInBackground() throws Exception {

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
            panel.add(addBut);
            return panel;
        }
        */
        private Object[][] getTableData() {
            java.util.List<Object[]> rows = new ArrayList<>();
            for (MetricUI el : metricList)   {
                Object[] row = new Object[1];
                row[0] = el.getEventName();
                row[1] = el.getCount();
                row[2] = el.getMostRecentDate();
                rows.add(row);
            }
            return rows.toArray(new Object[0][0]);
        }



}
