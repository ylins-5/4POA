package jogo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tela extends JFrame implements ActionListener {

    private JButton botaoComeca;
    private JButton botaoPara;

    private JLabel pista;

    private ImageIcon invencivel1;
    private ImageIcon invencivel2;
    private ImageIcon invencivel3;

    private Invencivel corredor1;
    private Invencivel corredor2;
    private Invencivel corredor3;

    public Tela() {

        this.setLayout(null);

        this.setSize(1000, 650);
        this.setLocation(50, 50);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        this.setTitle("Corrida");

        // Pista
        this.pista = new JLabel();
        this.pista.setBounds(0, 0, 1000, 650);

        this.add(pista);

        // Botões
        this.botaoComeca = new JButton("Começa");
        this.botaoPara = new JButton("Para");

        this.botaoComeca.setBounds(400, 530, 120, 50);
        this.botaoPara.setBounds(540, 530, 120, 50);

        // Imagens
        invencivel1 = new ImageIcon("./images/iv1.png");

        invencivel2 = new ImageIcon("./images/ev.png");

        invencivel3 = new ImageIcon("./images/om.png");

        // Personagens
        corredor1 = new Invencivel("Corredor 1", invencivel1, 20, 100);

        corredor2 = new Invencivel("Corredor 2", invencivel2, 20, 220);

        corredor3 = new Invencivel("Corredor 3", invencivel3, 20, 340);

        // Adiciona na pista
        this.pista.add(corredor1);
        this.pista.add(corredor2);
        this.pista.add(corredor3);

        this.pista.add(botaoComeca);
        this.pista.add(botaoPara);

        // Eventos
        botaoComeca.addActionListener(this);
        botaoPara.addActionListener(this);

        this.setVisible(true);

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoComeca) {

            corredor1.iniciar();
            corredor2.iniciar();
            corredor3.iniciar();
    }

        if (e.getSource() == botaoPara) {
            
            System.exit(0);
        }
    }
}