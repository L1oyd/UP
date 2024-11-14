package org.example;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UniversityAIS extends JFrame {

    private JTable vehicleTable;
    private JTable serviceTypeTable;
    private JTable partTable;
    private JTable workTable;
    private JTable invoiceTable;

    private DefaultTableModel vehicleTableModel;
    private DefaultTableModel serviceTypeTableModel;
    private DefaultTableModel partTableModel;
    private DefaultTableModel workTableModel;
    private DefaultTableModel invoiceTableModel;

    private Connection connection;

    public UniversityAIS() {
        super("АИС Рекламные агенства");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        vehicleTable = new JTable();
        serviceTypeTable = new JTable();
        partTable = new JTable();
        workTable = new JTable();
        invoiceTable = new JTable();

        vehicleTableModel = new DefaultTableModel();
        serviceTypeTableModel = new DefaultTableModel();
        partTableModel = new DefaultTableModel();
        workTableModel = new DefaultTableModel();
        invoiceTableModel = new DefaultTableModel();

        vehicleTable.setModel(vehicleTableModel);
        serviceTypeTable.setModel(serviceTypeTableModel);
        partTable.setModel(partTableModel);
        workTable.setModel(workTableModel);
        invoiceTable.setModel(invoiceTableModel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Рекламные кампании", new JScrollPane(vehicleTable));
        tabbedPane.addTab("Клиенты", new JScrollPane(serviceTypeTable));
        tabbedPane.addTab("Медиа-каналы", new JScrollPane(partTable));
        tabbedPane.addTab("Бюджеты", new JScrollPane(workTable));
        tabbedPane.addTab("Контракты", new JScrollPane(invoiceTable));

        JButton updateButton = new JButton("Обновить");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTables();
            }
        });

        JToolBar toolBar = new JToolBar();
        toolBar.add(updateButton);

        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        connectToDatabase();

        updateTables();

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "567890");
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при подключении к базе данных: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTables() {
        try {
            updateVehicleTable();

            updateServiceTypeTable();

            updatePartTable();

            updateWorkTable();

            updateInvoiceTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при обновлении данных: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateVehicleTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Рекламные компании");
        vehicleTableModel.setDataVector(getResultSetData(rs), new String[]{"ID_кампании", "ID_канала", "ID_клиента", "Название кампании", "Дата начала", "Дата окончания"});
        rs.close();
        stmt.close();
    }

    private void updateServiceTypeTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Клиенты");
        serviceTypeTableModel.setDataVector(getResultSetData(rs), new String[]{"ID_клиента", "Название компании", "Контактные данные"});
        rs.close();
        stmt.close();
    }

    private void updatePartTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Контракты");
        partTableModel.setDataVector(getResultSetData(rs), new String[]{"ID_контракта", "ID_кампании", "Сумма контракта", "Дата подписания"});
        rs.close();
        stmt.close();
    }

    private void updateWorkTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Медиа-каналы");
        workTableModel.setDataVector(getResultSetData(rs), new String[]{"ID_канала", "Название канала", "Тип канала"});
        rs.close();
        stmt.close();
    }

    private void updateInvoiceTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Бюджеты");
        invoiceTableModel.setDataVector(getResultSetData(rs), new String[]{"ID_бюджета", "ID_кампании", "Сумма бюджета"});
        rs.close();
        stmt.close();
    }

    private Object[][] getResultSetData(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        rs.last();
        int rowCount = rs.getRow();
        rs.beforeFirst();

        Object[][] data = new Object[rowCount][columnCount];

        int row = 0;
        while (rs.next()) {
            for (int col = 0; col < columnCount; col++) {
                data[row][col] = rs.getObject(col + 1);
            }
            row++;
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversityAIS();
            }
        });
    }
}
