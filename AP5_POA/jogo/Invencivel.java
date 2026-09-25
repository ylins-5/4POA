package jogo;

import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Invencivel extends JLabel implements Runnable {

    private Thread invencivel;
    private static int pos = 0;

    private String cor;
    private int X;
    private int Y;

    public Invencivel(String cor, ImageIcon img, int X, int Y) {

    // Redimensiona a imagem para caber no quadrado
    Image imagem = img.getImage();
    Image imagemRedimensionada = imagem.getScaledInstance(100,100,Image.SCALE_SMOOTH);

    super.setIcon(new ImageIcon(imagemRedimensionada));

    this.cor = cor;
    this.X = X;
    this.Y = Y;

    this.setBounds(X, Y, 200, 150);
}

    public void iniciar() {

        invencivel = new Thread(this, cor);
        invencivel.start();
    }

    @Override
    public void run() {

        Random random = new Random();

        while (X < 850) {

            X += random.nextInt(4) + 1;

            this.setLocation(X, Y);

            try {

                Thread.sleep(50);

            } catch (InterruptedException e) {

                e.printStackTrace();
                return;
            }
        }

        pos++;

        JOptionPane.showMessageDialog(
            null,
            pos + "º lugar: " + cor
        );
    }
}