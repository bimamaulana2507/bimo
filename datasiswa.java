import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormDataMahasiswa extends JFrame {
    private JTextField txtNama, txtNPM, txtUmur, txtNoHP, txtAlamat;
    private JRadioButton rbLaki, rbPerempuan;
    private ButtonGroup bgJenisKelamin;
    private JButton btnSimpan, btnHapus, btnBersihkan;
    private JTable tabelMahasiswa;
    private DefaultTableModel modelTabel;
    
    public FormDataMahasiswa() {
        setTitle("Form Input Data Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        // Panel Utama
        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel Form Input
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Input Data Mahasiswa"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Nama
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtNama = new JTextField(20);
        panelForm.add(txtNama, gbc);
        
        // NPM
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelForm.add(new JLabel("NPM:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtNPM = new JTextField(20);
        panelForm.add(txtNPM, gbc);
        
        // Jenis Kelamin
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelForm.add(new JLabel("Jenis Kelamin:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JPanel panelJK = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");
        bgJenisKelamin = new ButtonGroup();
        bgJenisKelamin.add(rbLaki);
        bgJenisKelamin.add(rbPerempuan);
        panelJK.add(rbLaki);
        panelJK.add(rbPerempuan);
        panelForm.add(panelJK, gbc);
        
        // Umur
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelForm.add(new JLabel("Umur:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtUmur = new JTextField(20);
        panelForm.add(txtUmur, gbc);
        
        // No HP
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        panelForm.add(new JLabel("No HP:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtNoHP = new JTextField(20);
        panelForm.add(txtNoHP, gbc);
        
        // Alamat
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        panelForm.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtAlamat = new JTextField(20);
        panelForm.add(txtAlamat, gbc);
        
        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSimpan = new JButton("Simpan");
        btnHapus = new JButton("Hapus");
        btnBersihkan = new JButton("Bersihkan");
        
        panelTombol.add(btnSimpan);
        panelTombol.add(btnHapus);
        panelTombol.add(btnBersihkan);
        
        // Panel Tabel
        String[] kolom = {"Nama", "NPM", "Jenis Kelamin", "Umur", "No HP", "Alamat"};
        modelTabel = new DefaultTableModel(kolom, 0);
        tabelMahasiswa = new JTable(modelTabel);
        JScrollPane scrollPane = new JScrollPane(tabelMahasiswa);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data Mahasiswa"));
        
        // Tambahkan komponen ke panel utama
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        
        panelUtama.add(panelAtas, BorderLayout.NORTH);
        panelUtama.add(scrollPane, BorderLayout.CENTER);
        
        add(panelUtama);
        
        // Event Handler
        btnSimpan.addActionListener(e -> simpanData());
        btnHapus.addActionListener(e -> hapusData());
        btnBersihkan.addActionListener(e -> bersihkanForm());
    }
    
    private void simpanData() {
        // Validasi input
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtNPM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NPM harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!rbLaki.isSelected() && !rbPerempuan.isSelected()) {
            JOptionPane.showMessageDialog(this, "Jenis kelamin harus dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtUmur.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Umur harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Ambil data dari form
        String nama = txtNama.getText().trim();
        String npm = txtNPM.getText().trim();
        String jenisKelamin = rbLaki.isSelected() ? "Laki-laki" : "Perempuan";
        String umur = txtUmur.getText().trim();
        String noHP = txtNoHP.getText().trim();
        String alamat = txtAlamat.getText().trim();
        
        // Tambahkan ke tabel
        modelTabel.addRow(new Object[]{nama, npm, jenisKelamin, umur, noHP, alamat});
        
        // Bersihkan form
        bersihkanForm();
        
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void hapusData() {
        int selectedRow = tabelMahasiswa.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            modelTabel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void bersihkanForm() {
        txtNama.setText("");
        txtNPM.setText("");
        bgJenisKelamin.clearSelection();
        txtUmur.setText("");
        txtNoHP.setText("");
        txtAlamat.setText("");
        txtNama.requestFocus();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormDataMahasiswa().setVisible(true);
        });
    }
}