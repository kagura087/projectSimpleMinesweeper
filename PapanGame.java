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

<<<<<<< HEAD
                ubin.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver) {
                            return; //jika game over, tidak bisa klik ubin
                        }

                        MineTile ubin = (MineTile) e.getSource(); //mengambil ubin yang diklik

                        // Klik kiri
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (ubin.getText() == "") {
                                if (mineList.contains(ubin)) {
                                    //jika klik bom, tampilkan semua bom
                                    revealMines();
                                } else {
                                    checkMine(ubin.baris, ubin.kolom); //mengecek bom disekitar ubin
                                }
                            }
                        }
                        // Klik kanan
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (ubin.getText() == "" && ubin.isEnabled()) {
                                ubin.setText("🚩"); //tandai bom
                            } else if (ubin.getText().equals("🚩")) {
                                ubin.setText(""); //hapus tanda bom
                            }
                        }
                    }
                });
            }
        }

        setRandomMines(); // Atur bom secara acak di papan
    }
    
    // Menempatkan bom secara acak di papan
    void setRandomMines() {
        mineList = new ArrayList<>(); //reset mineList
        // mineList.add(papan[2][2]);
        // mineList.add(papan[0][0]);
        // mineList.add(papan[1][1]);
        // mineList.add(papan[3][3]);
        // mineList.add(papan[4][4]);

        int sisaBom = jumlahBom; //hitung jumlah bom yang tersisa
        while (sisaBom > 0) {
            int baris = (int) (Math.random() * jumlahBaris); //0-7
            int kolom = (int) (Math.random() * jumlahKolom); //0-7

            MineTile ubin = papan[baris][kolom];
            if (!mineList.contains(ubin)) {
                mineList.add(ubin);
                sisaBom--;
            }
        }
    }

    // Menampilkan semua bom di papan
    void revealMines() {
        for (int i = 0; i < mineList.size(); i++) {
            MineTile tile = mineList.get(i);
            tile.setText("💣");
        }

        gameOver = true;
        textLabel.setText("Game Over!");
    }

    // Periksa apakah ada bom di sekitar ubin
    void checkMine(int baris, int kolom) {
        if (baris < 0 || baris >= jumlahBaris || kolom < 0 || kolom >= jumlahKolom) {
            return;
        }

        MineTile ubin = papan[baris][kolom];

        if (!ubin.isEnabled()) {
            return;
        }
        ubin.setEnabled(false);
        tilesClicked++;

        int minesFound = 0;

        // Top 3
        minesFound += countMine(baris - 1, kolom - 1);  // Top left
        minesFound += countMine(baris - 1, kolom);      // Top
        minesFound += countMine(baris - 1, kolom + 1);  // Top right

        // Left and Right
        minesFound += countMine(baris, kolom - 1);      // Left
        minesFound += countMine(baris, kolom + 1);      // Right

        // Bottom 3
        minesFound += countMine(baris + 1, kolom - 1);  // Bottom left
        minesFound += countMine(baris + 1, kolom);      // Bottom
        minesFound += countMine(baris + 1, kolom + 1);  // Bottom right

        if (minesFound > 0) {
            ubin.setText(Integer.toString(minesFound));
        } else {
            ubin.setText("");

            checkMine(baris - 1, kolom - 1); //top left
            checkMine(baris - 1, kolom); //top
            checkMine(baris - 1, kolom + 1); //top right

            //left and right
            checkMine(baris, kolom - 1); //left
            checkMine(baris, kolom + 1); //right

            //bottom 3
            checkMine(baris + 1, kolom - 1); //bottom left
            checkMine(baris + 1, kolom); //bottom
            checkMine(baris + 1, kolom + 1); //bottom right
        }
        //kondisi menang
        if(tilesClicked == (jumlahBaris * jumlahKolom) - mineList.size()){
            textLabel.setText("You Win!");
            gameOver = true;
        }
    }

    int countMine(int baris, int kolom) {
        if (baris < 0 || baris >= jumlahBaris || kolom < 0 || kolom >= jumlahKolom) {
            return 0;
        }
        if (mineList.contains(papan[baris][kolom])) {
            return 1;
        }
        return 0;
    }
}
=======
            }
        }
    }
}
>>>>>>> 71b613001a0c9c3342cc7cfc26e7a26d181045cc
