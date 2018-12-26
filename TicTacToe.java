import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel {

    JButton[] buttons = new JButton[9];
    int alternate = 0;

    public TicTacToe() {
        setLayout(new GridLayout(3, 3));
        initializebuttons();
    }

    public static void main(String[] Args) {
        JFrame frame = new JFrame("Tic Tac Toe");

        TicTacToe content = new TicTacToe();
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(content);
    }

    public void initializebuttons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new buttonListener());

            add(buttons[i]);
        }
    }

    public void resetButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
    }

    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();
            if (buttonClicked.getText() != ""){
                JOptionPane.showConfirmDialog(null,"Sudah ada isinya","Oops",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
            }else {
                if (alternate % 2 == 0) {
                    buttonClicked.setText("X");
                } else {
                    buttonClicked.setText("O");
                }
                alternate++;
            }


            if (checkForWinner()) {
                finish();
            }else if (allchecked() == true){
                finish();
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

        public boolean threeline(int a, int b) {
            return buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("");
        }

        public boolean allchecked(){
            return buttons[0].getText() != "" && buttons[1].getText() != "" && buttons[2].getText() != "" && buttons[3].getText() != "" && buttons[4].getText() != "" && buttons[5].getText() != "" && buttons[6].getText() != "" && buttons[7].getText() != "" && buttons[8].getText() != "";
        }

        public void finish(){
            int gameover = JOptionPane.showConfirmDialog(null,"Permainan Selesai ,Ingin Ulangi Permainan ?","Permainan Selesai",JOptionPane.YES_NO_OPTION);

            System.out.println(gameover);

            if (gameover == JOptionPane.YES_OPTION){
                resetButtons();
            }else if(gameover == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }

    }

}

