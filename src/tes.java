import javax.swing.*;  
import java.awt.*;     
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tes {
    public static void main(String[] args) {
        // Membuat frame atau jendela utama
        JFrame frame = new JFrame();
        frame.setSize(700, 400); 
        frame.setTitle("Aplikasi Pemesanan Makanan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selamat Datang di Aplikasi Pemesanan Makanan"));
        frame.add(panel, BorderLayout.NORTH);

        // Menggunakan JSplitPane untuk membagi jendela menjadi 2 bagian
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400); // Membagi secara horizontal dengan posisi pembagi di tengah
        
        // Panel kiri untuk form data pembeli
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10)); // Menggunakan FlowLayout di panelKiri
        
        panelKiri.add(new JLabel("Nama      :"));
        JTextField nama = new JTextField(20);
        panelKiri.add(nama);
        
        panelKiri.add(new JLabel("Alamat    :"));
        JTextField Alamat = new JTextField(20);
        panelKiri.add(Alamat);
        
        panelKiri.add(new JLabel("No.Telp   :"));
        JTextField Telp = new JTextField(20);
        panelKiri.add(Telp);
        
        // Panel kedua untuk pilihan menu
        JPanel panelKiri2 = new JPanel();
        panelKiri2.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
        JCheckBox Rendang = new JCheckBox("Rendang");
        JCheckBox Indomie = new JCheckBox("Indomie");
        JCheckBox Air = new JCheckBox("Air ");
        
        panelKiri2.add(Rendang);
        panelKiri2.add(Indomie);
        panelKiri2.add(Air);
        
        panelKiri.add(panelKiri2); // Menambahkan panelKiri2 ke panelKiri
        
        panelKiri.add(new JLabel("Total Harga:"));
        JTextField hargaField = new JTextField(10);
        hargaField.setEditable(false); // Tidak bisa diedit oleh pengguna
        hargaField.setText("0"); // Inisialisasi dengan 0
        panelKiri.add(hargaField);

        // Menambahkan tombol untuk mengirimkan data
        JButton submitButton = new JButton("Kirim Data");
        panelKiri.add(submitButton); // Menambahkan tombol ke panelKiri
        
        // Panel kanan untuk menampilkan hasil
        JPanel panelKanan = new JPanel();
        panelKanan.setLayout(new BorderLayout());
        
        // JTextArea untuk menampilkan hasil
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panelKanan.add(new JLabel("Data yang telah dipilih:"), BorderLayout.NORTH);
        panelKanan.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan panel kiri dan kanan ke split pane
        splitPane.setLeftComponent(panelKiri);
        splitPane.setRightComponent(panelKanan);
        
        // Menambahkan split pane ke frame
        frame.add(splitPane, BorderLayout.CENTER);
        splitPane.setEnabled(false); 
        
        ActionListener updateHargaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalHarga = 0;
                
                if (Rendang.isSelected()) {
                    totalHarga += 10000;  // Harga Rendang
                }
                if (Indomie.isSelected()) {
                    totalHarga += 5000;   // Harga Indomie
                }
                if (Air.isSelected()) {
                    totalHarga += 2000;  // Harga Air
                }
                
                // Update harga yang ditampilkan di text field
                hargaField.setText(String.valueOf(totalHarga));
            }
        };

        // Menambahkan listener pada checkbox untuk mengupdate harga secara live
        Rendang.addActionListener(updateHargaListener);
        Indomie.addActionListener(updateHargaListener);
        Air.addActionListener(updateHargaListener);

        // Aksi tombol kirim data
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mengambil data dari text field
                String namaText = nama.getText();
                String AlamatText = Alamat.getText();
                String TelpText = Telp.getText();
                
                // Mengambil data dari checkbox
                String menuPilihan = "";
                if (Rendang.isSelected()) menuPilihan += "Rendang, ";
                if (Indomie.isSelected()) menuPilihan += "Indomie, ";
                if (Air.isSelected()) menuPilihan += "Air, ";
                
                // Menampilkan data yang dikirim di panel kanan
                String message = "Nama: " + namaText + "\n"
                        + "Alamat: " + AlamatText + "\n"
                        + "No.Telp: " + TelpText + "\n"
                        + "Pilihan Menu: " + menuPilihan + "\n"
                        + "Total Harga: Rp " + hargaField.getText() + "\n\n";
                
                // Menambahkan data ke JTextArea (append untuk menambah di bawah)
                textArea.append(message);
                
                // Mengosongkan form
                nama.setText("");
                Alamat.setText("");
                Telp.setText("");
                Rendang.setSelected(false);
                Indomie.setSelected(false);
                Air.setSelected(false);
                hargaField.setText("0");
            }
        });
        
        // Menampilkan frame
        frame.setVisible(true);
    }
}
