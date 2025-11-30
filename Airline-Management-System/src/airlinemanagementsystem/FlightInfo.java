package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FlightInfo extends JFrame {

    public FlightInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();

        try {
            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery("select * from flight");
            table.setModel(convertResultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    private javax.swing.table.TableModel convertResultSetToTableModel(ResultSet rs) throws Exception {
        java.util.Vector<String> columnNames = new java.util.Vector<>();
        java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<>();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            columnNames.addElement(metaData.getColumnName(i));
        }

        while (rs.next()) {
            java.util.Vector<Object> row = new java.util.Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.addElement(rs.getObject(i));
            }
            data.addElement(row);
        }

        return new javax.swing.table.DefaultTableModel(data, columnNames);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
