package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class billetGUI {
    private JPanel panel1;

    private JLabel balance;
    private JTextField inPenge;
    private JButton indsatPengeKnap;
    private JButton returKnap;
    private JLabel antalCykelKøbt;
    private JLabel antalVoksenKøbt;
    private JLabel antalBørneKøbt;
    private JLabel totalBørnPris;
    private JLabel totalVoksenPris;
    private JLabel totalCykelPris;
    private JLabel totalKurv;
    private JTextField inBørn;
    private JTextField inVoksen;
    private JTextField inCykel;
    private JButton tilføjBilletter;
    private JButton gennemførKøbKnap;
    private JButton tømKurvKnap;
    Billetautomat billetautomat = new Billetautomat();
    IndkøbsKurv kurv = new IndkøbsKurv();

    public billetGUI() {

        tilføjBilletter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!inBørn.getText().isEmpty()) {

                    kurv.addBillet('b', Integer.parseInt(inBørn.getText()));
                }

                if (!inVoksen.getText().isEmpty()) {
                    kurv.addBillet('v', Integer.parseInt(inVoksen.getText()));
                }

                if (!inCykel.getText().isEmpty()) {
                    kurv.addBillet('c', Integer.parseInt(inCykel.getText()));
                }
                int antalBørn = kurv.getBilletAntal('b');
                int antalVoksne = kurv.getBilletAntal('v');
                int antalCykler = kurv.getBilletAntal('c');
                int børnePris = kurv.getBørneBilletPris();
                int voksenPris = kurv.getVoksenBilletPris();
                int cykelPris = kurv.getCykelBilletPris();

                int samletBørnPris = børnePris * antalBørn;
                int samletVoksenPris = voksenPris * antalVoksne;
                int samletCykelPris = cykelPris * antalCykler;

                antalBørneKøbt.setText(Integer.toString(antalBørn));
                antalVoksenKøbt.setText(Integer.toString(antalVoksne));
                antalCykelKøbt.setText(Integer.toString(antalCykler));

                totalBørnPris.setText(Integer.toString(samletBørnPris) + " Kr.");
                totalVoksenPris.setText(Integer.toString(samletVoksenPris) + " Kr.");
                totalCykelPris.setText(Integer.toString(samletCykelPris) + " Kr.");
                totalKurv.setText(Integer.toString(kurv.getKurvTotalPris()) + " Kr.");
            }
        });
        indsatPengeKnap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!inPenge.getText().isEmpty()){
                    int pengeIndsat = Integer.parseInt(inPenge.getText());
                    billetautomat.indsætPenge(pengeIndsat);
                    balance.setText(Integer.toString(billetautomat.getBalance()) + " Kr.");
                }

            }
        });
        returKnap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(billetautomat.getBalance() > 0) {
                    billetautomat.udskrivReturPenge();
                    balance.setText("0 Kr.");
                }
            }
        });
        gennemførKøbKnap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(billetautomat.getBalance() >= kurv.getKurvTotalPris() && kurv.getKurvTotalPris() > 0) {
                    int antalBørn = kurv.getBilletAntal('b');
                    int antalVoksne = kurv.getBilletAntal('V');
                    int antalCykler = kurv.getBilletAntal('c');
                    int total = kurv.getKurvTotalPris();

                    billetautomat.udskrivBillet(antalVoksne, antalBørn, antalCykler, total);

                    int nybalance = billetautomat.getBalance();
                    balance.setText(Integer.toString(nybalance) + " Kr.");

                    kurv.tømKurv();
                    antalBørneKøbt.setText("0");
                    antalVoksenKøbt.setText("0");
                    antalCykelKøbt.setText("0");

                    totalBørnPris.setText("0 Kr.");
                    totalVoksenPris.setText("0 Kr.");
                    totalCykelPris.setText("0 Kr.");
                    totalKurv.setText("0 Kr.");
                }

            }
        });
        tømKurvKnap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                kurv.tømKurv();
                antalBørneKøbt.setText("0");
                antalVoksenKøbt.setText("0");
                antalCykelKøbt.setText("0");

                totalBørnPris.setText("0 Kr.");
                totalVoksenPris.setText("0 Kr.");
                totalCykelPris.setText("0 Kr.");
                totalKurv.setText("0 Kr.");
            }
        });


    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("billetGUI");
        frame.setContentPane(new billetGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
