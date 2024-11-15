import javax.swing.*;  
import java.awt.*;     
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingExample {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(700, 400); 
        frame.setTitle("Aplikasi Pemesanan Makanan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selamat Datang di Aplikasi Pemesanan Makanan"));
        frame.add(panel, BorderLayout.NORTH);

        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400); 
        
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10)); 
        
        panelKiri.add(new JLabel("Nama      :"));
        JTextField Nama = new JTextField(20);
        panelKiri.add(Nama);
        
        panelKiri.add(new JLabel("Alamat    :"));
        JTextField Alamat = new JTextField(20);
        panelKiri.add(Alamat);
        
        panelKiri.add(new JLabel("No.Telp   :"));
        JTextField Telp = new JTextField(20);
        panelKiri.add(Telp);
        
        JPanel panelPilihan = new JPanel();
        panelPilihan.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
        JCheckBox Rendang = new JCheckBox("Rendang");
        JCheckBox Indomie = new JCheckBox("Indomie");
        JCheckBox Air = new JCheckBox("Air ");
        
        panelPilihan.add(Rendang);
        panelPilihan.add(Indomie);
        panelPilihan.add(Air);
        
        panelKiri.add(panelPilihan); 
        
        panelKiri.add(new JLabel("Total Harga:"));
        JTextField HargaField = new JTextField(10);
        HargaField.setEditable(false); 
        HargaField.setText("0"); 
        panelKiri.add(HargaField);

        JButton submitButton = new JButton("Kirim Data");
        panelKiri.add(submitButton); 
        
        JPanel panelKanan = new JPanel();
        panelKanan.setLayout(new BorderLayout());
        
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panelKanan.add(new JLabel("Data yang telah dipilih:"), BorderLayout.NORTH);
        panelKanan.add(scrollPane, BorderLayout.CENTER);

        splitPane.setLeftComponent(panelKiri);
        splitPane.setRightComponent(panelKanan);
        
        frame.add(splitPane, BorderLayout.CENTER);
        splitPane.setEnabled(false); 
        
        ActionListener updateHargaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalHarga = 0;
                
                if (Rendang.isSelected()) {
                    totalHarga += 10000;  
                }
                if (Indomie.isSelected()) {
                    totalHarga += 5000;   
                }
                if (Air.isSelected()) {
                    totalHarga += 2000;  
                }
                
                HargaField.setText(String.valueOf(totalHarga));
            }
        };

        Rendang.addActionListener(updateHargaListener);
        Indomie.addActionListener(updateHargaListener);
        Air.addActionListener(updateHargaListener);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Nama.getText().isEmpty() || Alamat.getText().isEmpty() || Telp.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Harap isi semua data sebelum mengirim.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!Rendang.isSelected() && !Indomie.isSelected() && !Air.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Harap pilih setidaknya satu menu.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String NamaText = Nama.getText();
                String AlamatText = Alamat.getText();
                String TelpText = Telp.getText();
                
                String menuPilihan = "";
                if (Rendang.isSelected()) menuPilihan += "Rendang, ";
                if (Indomie.isSelected()) menuPilihan += "Indomie, ";
                if (Air.isSelected()) menuPilihan += "Air, ";
                
                String message = "Nama: " + NamaText + "\n"
                        + "Alamat: " + AlamatText + "\n"
                        + "No.Telp: " + TelpText + "\n"
                        + "Pilihan Menu: " + menuPilihan + "\n"
                        + "Total Harga: Rp " + HargaField.getText() + "\n\n";
                
                JOptionPane.showMessageDialog(frame, "Data berhasil terkirim!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                textArea.append(message);
                
                Nama.setText("");
                Alamat.setText("");
                Telp.setText("");
                Rendang.setSelected(false);
                Indomie.setSelected(false);
                Air.setSelected(false);
                HargaField.setText("0");
            }
        });
        
        frame.setVisible(true);
    }
}

