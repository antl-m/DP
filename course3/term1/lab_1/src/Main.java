import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.annotation.IncompleteAnnotationException;

class MyTh extends Thread {
    private int Increment;
    private JSlider Slider;
    private int Counter = 0;
    private static int BOUND = 1000000;
    private static int THREAD_COUNTER = 0;
    private int CurrentNumber = 0;

    public MyTh(JSlider _Slider, int _Increment, int _Priority) {
        Slider = _Slider;
        Increment = _Increment;
        CurrentNumber = ++THREAD_COUNTER;
        setPriority(_Priority);
    }

    @Override
    public void run() {
        while (!interrupted()) {
            synchronized (Slider) {
                ++Counter;
                if (Counter > BOUND) {
                    Slider.setValue((int)(Slider.getValue()) + Increment);
                    Counter = 0;
                }
            }
        }
    }

    public JPanel GetJPanel() {
        JPanel Panel = new JPanel();
        JLabel Label = new JLabel("Thread #" + CurrentNumber + ", Increment: " + Increment);
        SpinnerModel Model = new SpinnerNumberModel(
                getPriority(),
                Thread.MIN_PRIORITY,
                Thread.MAX_PRIORITY,
                1
            );
        JSpinner Spinner = new JSpinner(Model);
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

        MyTh Thread1 = new MyTh(Slider, +1, Thread.NORM_PRIORITY);
        MyTh Thread2 = new MyTh(Slider, -1, Thread.NORM_PRIORITY);

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
