import java.awt.*; //import library untuk mengatur layout dan GUI
import java.util.ArrayList; //import library untuk mengatur array list
import javax.swing.*; //import library untuk komponen GUI

import java.awt.event.MouseAdapter; // Import adapter untuk menangani event mouse
import java.awt.event.MouseEvent; // Import event untuk mendeteksi klik mouse

public class PapanGame {
    int jumlahBaris;
    int jumlahKolom;
    int jumlahBom = 10;
    int ukuranUbin;
    int tilesClicked = 0; //jumlah ubin yang diklik
    boolean gameOver = false; //kondisi permainan selesai atau belum

    MineTile[][] papan; //array untuk menyimpan ubin
    ArrayList<MineTile> mineList; //array list untuk menyimpan daftar bom
    JPanel boardPanel; //panel untuk papan
    JLabel textLabel; //label untuk teks

    public PapanGame(int jumlahBaris, int jumlahKolom, int jumlahBom, int ukuranUbin, JLabel textLabel) {

        this.jumlahBaris = jumlahBaris;
        this.jumlahKolom = jumlahKolom;
        this.jumlahBom = jumlahBom;
        this.ukuranUbin = ukuranUbin;
        this.textLabel = textLabel;

        papan = new MineTile[jumlahBaris][jumlahKolom]; //64 ubin
        mineList = new ArrayList<>(); //daftar bom

        // Buat panel untuk papan
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(jumlahBaris, jumlahKolom)); //atur layout grid 8x8
        boardPanel.setPreferredSize(new Dimension(jumlahKolom * ukuranUbin, jumlahBaris * ukuranUbin)); //ukuran papan 560x560 pixel

        //generate ubin di papan
        for (int baris = 0; baris < jumlahBaris; baris++) {
            for (int kolom = 0; kolom < jumlahKolom; kolom++) {
                MineTile ubin = new MineTile(baris, kolom);
                ubin.setPreferredSize(new Dimension(ukuranUbin, ukuranUbin));
                ubin.setFocusable(false);
                ubin.setMargin(new Insets(0, 0, 0, 0));
                ubin.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                boardPanel.add(ubin);
                papan[baris][kolom] = ubin; //menambahkan ubin ke array papan
                mineList.add(ubin); //menambahkan ubin ke array list mineList

            }
        }
    }
}
