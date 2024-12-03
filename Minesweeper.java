import javax.swing.*;
import java.awt.*;

//Program utama menjalankan game minesweeper
public class Minesweeper {
    int ukuranUbin = 70; //ukuran ubin 70x70 pixel
    int jumlahBaris = 8; //Buat 8
    int jumlahKolom = jumlahBaris; //Buat 8x8
    int lebarPapan = jumlahKolom * ukuranUbin; //70x8 = 560pixel
    int tinggiPapan = jumlahBaris * ukuranUbin; //70x8 = 560pixel
    int jumlahBom = 10;

    JFrame frame = new JFrame("Minesweeper"); //buat frame minesweeper
    JLabel textLabel = new JLabel(); // Label teks di atas papan.
    JPanel textPanel = new JPanel(); // Panel untuk teks.
    // JPanel boardPanel = new JPanel(); // Panel untuk papan.
    JButton tombolRestart = new JButton("Restart Game");
    
    PapanGame papanGame;

    //Konstruktor Minesweeper
    Minesweeper() {
        frame.setSize(lebarPapan, tinggiPapan + 50); //mengatur ukuran dan tinggi papan + 50 untuk restart button
        frame.setLocationRelativeTo(null); //buat bingkai dibuka ada ditengah layar
        frame.setResizable(false); //bingkai frame tidak bisa diubah ukuran
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //menutup bingkai ketika di close
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 25)); //membuat font jadi arial bold size 25
        textLabel.setHorizontalAlignment(JLabel.CENTER); //atur text ke tengah
        textLabel.setText("Minesweeper " + Integer.toString(jumlahBom)); //menampilkan teks mineswpeer ke window
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel); //menambahkan teks ke panel
        frame.add(textPanel, BorderLayout.NORTH); //menampilkan teks mineswpeer ke window dan membuat menempel diatas layout

        papanGame = new PapanGame(jumlahBaris, jumlahKolom, 10, ukuranUbin, textLabel);
        frame.add(papanGame.boardPanel, BorderLayout.CENTER); //menampilkan papan minesweeper ke window
        textPanel.add(tombolRestart, BorderLayout.SOUTH);  // Tambahkan tombol restart ke panel teks

        papanGame = new PapanGame(jumlahBaris, jumlahKolom, jumlahBom, ukuranUbin, textLabel);
        frame.add(papanGame.boardPanel, BorderLayout.CENTER);

        tombolRestart.addActionListener(e -> restartGame());

        frame.setVisible(true); //buat bingkai bisa dilihat di window
    }

    private void restartGame() {
        frame.remove(papanGame.boardPanel);  // Hapus panel lama
        papanGame = new PapanGame(jumlahBaris, jumlahKolom, jumlahBom, ukuranUbin, textLabel);  // Buat panel baru
        frame.add(papanGame.boardPanel, BorderLayout.CENTER);  // Tambahkan panel baru
        frame.revalidate();  // Perbarui tampilan
        frame.repaint();
        textLabel.setText("Minesweeper " + Integer.toString(jumlahBom));
    }
    
    public static void main(String[] args) {
        new Minesweeper(); //menjalankan program minesweeper
    }
}