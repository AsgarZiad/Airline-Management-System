package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.sql.*;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JSpinner dcdate;

    public BookFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblaadhar = new JLabel("NID Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        add(tfaadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        labelgender = new JLabel("Gender");
        labelgender.setBounds(220, 280, 150, 25);
        add(labelgender);

        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);

        source = new Choice();
        source.setBounds(220, 330, 150, 25);
        add(source);

        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        add(destination);

        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220, 430, 150, 25);
        add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(220, 480, 150, 25);
        add(labelfcode);

        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);

        dcdate = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dcdate, "yyyy-MM-dd");
        dcdate.setEditor(dateEditor);
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);

        ImageIcon i1 = new ImageIcon("src/airlinemanagementsystem/icons/details.jpg");
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 410);
        add(lblimage);

        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);

        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String nid = tfaadhar.getText();

            if (nid.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter NID");
                return;
            }

            try {
                Conn conn = new Conn();

                String query = "select * from passenger where NID = ?";
                java.sql.PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, nid);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Passenger not found with NID: " + nid + ". Please enter correct NID");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                Conn conn = new Conn();

                String query = "select * from flight where source = ? and destination = ?";
                java.sql.PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, src);
                pst.setString(2, dest);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Random random = new Random();

            String nid = tfaadhar.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = new java.text.SimpleDateFormat("yyyy-MM-dd").format((java.util.Date) dcdate.getValue());

            if (nid.isEmpty() || name.isEmpty() || flightname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fetch passenger and flight details first");
                return;
            }

            try {
                Conn conn = new Conn();

                String pnr = "PNR-" + random.nextInt(1000000);
                String tic = "TIC-" + random.nextInt(10000);

                String query = "insert into reservation values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                java.sql.PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, pnr);
                pst.setString(2, tic);
                pst.setString(3, nid);
                pst.setString(4, name);
                pst.setString(5, nationality);
                pst.setString(6, flightname);
                pst.setString(7, flightcode);
                pst.setString(8, src);
                pst.setString(9, des);
                pst.setString(10, ddate);

                pst.executeUpdate();

                String successMessage = "Ticket Booked Successfully!\n\n" +
                        "PNR Number: " + pnr + "\n" +
                        "Ticket Number: " + tic + "\n" +
                        "Passenger: " + name + "\n" +
                        "Flight: " + flightname + "\n" +
                        "From: " + src + " To: " + des + "\n" +
                        "Date: " + ddate;

                // Create custom dialog with copy button
                JDialog confirmDialog = new JDialog();
                confirmDialog.setTitle("Booking Confirmation");
                confirmDialog.setSize(450, 300);
                confirmDialog.setLocationRelativeTo(null);
                confirmDialog.setModal(true);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setBackground(Color.WHITE);

                JTextArea messageArea = new JTextArea(successMessage);
                messageArea.setEditable(false);
                messageArea.setLineWrap(true);
                messageArea.setWrapStyleWord(true);
                messageArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
                messageArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                JScrollPane scrollPane = new JScrollPane(messageArea);
                panel.add(scrollPane, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                buttonPanel.setBackground(Color.WHITE);

                JButton copyButton = new JButton("Copy PNR");
                copyButton.setBackground(Color.BLACK);
                copyButton.setForeground(Color.WHITE);
                copyButton.addActionListener(e -> {
                    StringSelection stringSelection = new StringSelection(pnr);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(confirmDialog, "PNR copied to clipboard!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                });

                JButton okButton = new JButton("OK");
                okButton.setBackground(Color.BLACK);
                okButton.setForeground(Color.WHITE);
                okButton.addActionListener(e -> confirmDialog.dispose());

                buttonPanel.add(copyButton);
                buttonPanel.add(okButton);

                panel.add(buttonPanel, BorderLayout.SOUTH);

                confirmDialog.add(panel);
                confirmDialog.setVisible(true);

                setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Booking Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
