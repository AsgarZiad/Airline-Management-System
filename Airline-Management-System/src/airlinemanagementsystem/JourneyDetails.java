package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;

    public JourneyDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);

        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        add(pnr);

        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.WHITE);
        add(jsp);

        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String pnrText = pnr.getText();

        if (pnrText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter PNR");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "select * from reservation where PNR = ?";
            java.sql.PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, pnrText);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(convertResultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        new JourneyDetails();
    }
}
