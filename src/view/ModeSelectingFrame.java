package view;

import view.TraditionalModeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ModeSelectingFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    private TraditionalModeFrame traditionalModeFrame;

    public void setTraditionalModeFrame(TraditionalModeFrame traditionalModeFrame) {
        this.traditionalModeFrame = traditionalModeFrame;
    }
//    private DifficultySelectedListener difficultySelectedListener;

    public ModeSelectingFrame(int width, int height) {
        setTitle("SelectingMode");
        this.WIDTH = width;
        this.HEIGHT = height;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        addLabel();
        addTraditionalModeButton();
        addNewModeButton();
    }

    // ... 其他代码 ...

    // 设置难度选中的监听器
    /*public void setDifficultySelectedListener(DifficultySelectedListener listener) {
        this.difficultySelectedListener = listener;
    }

    public interface DifficultySelectedListener {
        void onDifficultySelected(int difficulty);
    }
    private void someButtonActionPerformed(ActionEvent evt) {
        int selectedDifficulty = traditionalModeFrame.getDifficulty();// 根据你的实现获取选中的难度
        onDifficultySelected(selectedDifficulty);
    }
    public void onDifficultySelected(int difficulty) {
        if (difficultySelectedListener != null) {
            // 将整数转换为字符串再传递给监听器
            difficultySelectedListener.onDifficultySelected(difficulty);
        }
    }*/



    //

    private void addTraditionalModeButton() {
        JButton traditionalModeButton = new JButton("Classic Mode");
        traditionalModeButton.setPreferredSize(new Dimension(250, 80));
        traditionalModeButton.setFont(new Font("Rockwell", Font.BOLD, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        traditionalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                openTraditionalMode();
            }
        });
        add(traditionalModeButton, gbc);
    }

    private void addNewModeButton() {
        JButton newModeButton = new JButton("New Mode");
        newModeButton.setPreferredSize(new Dimension(250, 80));
        newModeButton.setFont(new Font("Rockwell", Font.BOLD, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        newModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewMode();
            }
        });
        add(newModeButton, gbc);
    }

    private void openTraditionalMode() {
        SwingUtilities.invokeLater(() -> {
            TraditionalModeFrame difficultyFrame = new TraditionalModeFrame(this);
            difficultyFrame.setVisible(true);
//            difficultyFrame.setDifficultyCallback(difficulty -> {
//                // 将从 TraditionalModeFrame 获取的 difficulty 信息传递给 Main 方法
//                if (traditionalModeCallback != null) {
//                    traditionalModeCallback.onReceiveDifficulty(difficulty);
//                }
////                difficultyFrame.setVisible(true);
//            });
        });
        this.dispose();

    }
    public interface TraditionalModeCallback {
        void onReceiveDifficulty(int difficulty);
    }

    private TraditionalModeCallback traditionalModeCallback;

    // 设置回调函数
    public void setTraditionalModeCallback(TraditionalModeCallback callback) {
        this.traditionalModeCallback = callback;
    }
    //above is for callback

    private void openNewMode() {
        // 实现新模式的逻辑
        JOptionPane.showConfirmDialog(this,"Sorry,the e-cigarette haven't been made yet.");
    }

    private void addLabel() {
        JLabel label = new JLabel("Selecting Mode");
        label.setFont(new Font("Rockwell", Font.BOLD, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(label, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ModeSelectingFrame frame = new ModeSelectingFrame(600, 500);
                frame.setVisible(true);
            }
        });
    }

    /*public interface DifficultySelectedListener {
        void onDifficultySelected(int difficulty);
    }
    public void setDDifficultySelectedListener(DifficultySelectedListener listener){
        this.difficultySelectedListener=listener;
    }*/
}
