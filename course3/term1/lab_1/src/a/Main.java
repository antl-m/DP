package a;

import javax.swing.*;

class MyTh extends Thread {
    private int Increment;
    private Integer Shared;
    private JSlider Slider;
    private int Counter = 0;
    private static int BOUND = 100000;
    private static int THREAD_COUNTER = 0;
    private int CurrentNumber = 0;

    public MyTh(Integer _Shared, JSlider _Slider, int _Increment) {
        Increment = _Increment;
        Shared = _Shared;
        Slider = _Slider;
        CurrentNumber = ++THREAD_COUNTER;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            synchronized (Shared) {
                if ((Increment > 0 && Shared < 90) || (Increment < 0 && Shared > 10)) {
                    Shared += Increment;
                }
            }
            ++Counter;
            if (Counter > BOUND) {
                Slider.setValue((int)(Slider.getValue()) + Increment);
                Counter = 0;
            }
        }
    }

    public JPanel GetJPanel() {
        JPanel Panel = new JPanel();
        JLabel Label = new JLabel("Thread #" + CurrentNumber + ", Increment: " + Increment);
        JSpinner Spinner = new JSpinner(new SpinnerNumberModel(
                getPriority(),
                Thread.MIN_PRIORITY,
                Thread.MAX_PRIORITY,
                1
            ));
        Spinner.addChangeListener(e -> {
                setPriority((int)(Spinner.getValue()));
            });
        Panel.add(Label);
        Panel.add(Spinner);

        return Panel;
    }
}

public class Main {

    static Thread th1, th2;

    public static void main(String[] args) {
        JFrame win = new JFrame();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(500, 400);

        JSlider Slider = new JSlider(0, 100);
        Integer Shared = 50;

        MyTh Thread1 = new MyTh(Shared, Slider, +1);
        MyTh Thread2 = new MyTh(Shared, Slider, -1);

        JButton StartBt = new JButton("Start");

        StartBt.addActionListener(e -> {
                Thread1.start();
                Thread2.start();
                StartBt.setEnabled(false);
            });

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));

        MainPanel.add(Thread1.GetJPanel());
        MainPanel.add(Thread2.GetJPanel());
        MainPanel.add(Slider);

        JPanel Panel = new JPanel();
        Panel.add(StartBt);
        MainPanel.add(Panel);

        win.setContentPane(MainPanel);
        win.setVisible(true);
    }
}
