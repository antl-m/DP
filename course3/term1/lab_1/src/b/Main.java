package b;

import javax.swing.*;

class MyThread extends Thread {

    int Increment = 0;

    public MyThread(int _Increment) {
        Increment = _Increment;
    }

    @Override
    public void run() {
        while (Main.Semaphore != 0);

        Main.Semaphore = 1;
        Main.Label.setText("Section is used!!!");

        while(!interrupted()) {
            int Value = Main.Slider.getValue();
            if ((Value > 10 && Increment < 0) || (Value < 90 && Increment > 0))
                Main.Slider.setValue(Value + Increment);
        }

        Main.Semaphore = 0;
        Main.Label.setText("Section is free");
    }
}

public class Main {

    static volatile int Semaphore = 0;
    static volatile JSlider Slider;
    static volatile JLabel Label;

    static private MyThread Thread1;
    static private MyThread Thread2;

    static private JButton Thread1StartBt;
    static private JButton Thread1StopBt;
    static private JButton Thread2StartBt;
    static private JButton Thread2StopBt;

    public static void main(String[] args) {

        JFrame MainWindow = new JFrame();
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setSize(600, 400);

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));

        Slider = new JSlider(0, 100);
        MainPanel.add(Slider);

        Label = new JLabel("Section is free");
        MainPanel.add(Label);

        JPanel Thread1Panel = new JPanel();
        Thread1StartBt = new JButton("START 1");
        Thread1StopBt = new JButton("STOP 1");
        Thread1StopBt.setEnabled(false);
        Thread1StartBt.addActionListener(e -> {
            Thread1 = new MyThread(+1);
            Thread1.setPriority(Thread.MIN_PRIORITY);
            Thread1StopBt.setEnabled(true);
            Thread1StartBt.setEnabled(false);
            Thread1.start();
        });
        Thread1StopBt.addActionListener(e -> {
            Thread1.interrupt();
            Thread1StopBt.setEnabled(false);
            Thread1StartBt.setEnabled(true);
        });
        Thread1Panel.add(Thread1StartBt);
        Thread1Panel.add(Thread1StopBt);

        JPanel Thread2Panel = new JPanel();
        Thread2StartBt = new JButton("START 2");
        Thread2StopBt = new JButton("STOP 2");
        Thread2StopBt.setEnabled(false);
        Thread2StartBt.addActionListener(e -> {
            Thread2 = new MyThread(-1);
            Thread2.setPriority(Thread.MAX_PRIORITY);
            Thread2StopBt.setEnabled(true);
            Thread2StartBt.setEnabled(false);
            Thread2.start();
        });
        Thread2StopBt.addActionListener(e -> {
            Thread2.interrupt();
            Thread2StopBt.setEnabled(false);
            Thread2StartBt.setEnabled(true);
        });
        Thread2Panel.add(Thread2StartBt);
        Thread2Panel.add(Thread2StopBt);

        MainPanel.add(Thread1Panel);
        MainPanel.add(Thread2Panel);

        MainWindow.setContentPane(MainPanel);
        MainWindow.setVisible(true);
    }
}
