import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel {

    JButton[] buttons = new JButton[9];  //inisialisasi button
    int alternate = 0;

    public TicTacToe() {
        setLayout(new GridLayout(3, 3));  //membuat layout 3x3
        initializebuttons();  //inisialisasi button 3x3
    }

    public static void main(String[] Args) {
        JFrame frame = new JFrame("Tic Tac Toe");  // menentukan judul / title

        TicTacToe content = new TicTacToe(); //instansiasi tictactoe
        frame.setSize(200, 200);  //menentukan ukuran frame
        frame.setLocationRelativeTo(null);  //membuat frame ditengah
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //membuat close button
        frame.setVisible(true); //membuat terlihat
        frame.add(content); //memberi isi konten tictactoe
    }

    public void initializebuttons() {
        for (int i = 0; i < 9; i++) {                           //perulangan 9 button
            buttons[i] = new JButton();                         //instansiasi button
            buttons[i].setText("");                             //membuat tulisan di button menjadi kosong
            buttons[i].addActionListener(new buttonListener()); //memberi aksi pada button ketika diklik

            add(buttons[i]); //membuat button
        }
    }

    public void resetButtons() {        //mereset semua button
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
    }

    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();
            if (buttonClicked.getText() != ""){                                                                                                                                     // apabila button tidak kosong atau berisi x / o ,muncul pesan
                JOptionPane.showConfirmDialog(null,"Sudah ada isinya","Oops",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);                   // error sudah ada isinya
            }else {
                if (alternate % 2 == 0) {               //menentukan giliran siapa
                    buttonClicked.setText("X");         //giliran pertama berisi x
                } else {
                    buttonClicked.setText("O");         //giliran kedua berisi o
                }
                alternate++;
            }


            if (checkForWinner()) {                 //akses method checkForWinner() atau cek pemenang apabila true
                finish();                           //akses method finish() atau permainan selesai
            }else if (allChecked() == true){        //akses method allchecked() atau semua button sudah terisi
                finish();                           //akses method finish() atau permainan selesai
            }


        }

        public boolean checkForWinner() {
            // Menang Mendatar
            if (threeline(0, 1) && threeline(1, 2)) {
                return true;
            } else if (threeline(3, 4) && threeline(4, 5)) {
                return true;
            } else if (threeline(6, 7) && threeline(7, 8)) {
                return true;
            }

            //Menang Menurun

            else if (threeline(0, 3) && threeline(3, 6)) {
                return true;
            } else if (threeline(1, 4) && threeline(4, 7)) {
                return true;
            } else if (threeline(2, 5) && threeline(5, 8)) {
                return true;
            }

            //Menang Diagonal

            else if (threeline(0, 4) && threeline(4, 8)) {
                return true;
            } else return threeline(2, 4) && threeline(4, 6);
        }

        public boolean threeline(int a, int b) {                                                                //menentukan 3jadi / untuk mengecek  a dan b sama dan tidak kosong
            return buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("");
        }

        public boolean allChecked(){                                                                            //menentukan apakah semua telah terisi
            return buttons[0].getText() != "" && buttons[1].getText() != "" && buttons[2].getText() != "" && buttons[3].getText() != "" && buttons[4].getText() != "" && buttons[5].getText() != "" && buttons[6].getText() != "" && buttons[7].getText() != "" && buttons[8].getText() != "";
        }

        public void finish(){                                                                                   //permainan selesai
            int gameover = JOptionPane.showConfirmDialog(null,"Permainan Selesai ,Ingin Ulangi Permainan ?","Permainan Selesai",JOptionPane.YES_NO_OPTION);     //memunculkan option permainan selesai , ingin lanjut.

            System.out.println(gameover);                                  // print option

            if (gameover == JOptionPane.YES_OPTION){                       // apabila diklik yes
                resetButtons();                                            //reset permainan
            }else if(gameover == JOptionPane.NO_OPTION) {                  // apabila klik no
                System.exit(0);                                     //keluar permainan
            }
        }

    }

}

