package view;

import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraditionalModeFrame extends JFrame {
    private ButtonGroup difficultyGroup;
    private int difficulty;
    private ModeSelectingFrame.TraditionalModeCallback callback;
    public TraditionalModeFrame(ModeSelectingFrame selectingFrame) {
        this.selectingFrame=selectingFrame;
        setTitle("classic mode-decided difficulty");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 使用现代外观
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 设置应用程序图标
        setIconImage(new ImageIcon("icon.png").getImage());

        // 添加难度选择的组件
        addDifficultySelectionComponents();
    }
    private ModeSelectingFrame selectingFrame;

    private void addDifficultySelectionComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.setBackground(Color.WHITE);

        difficultyGroup = new ButtonGroup();

        JRadioButton easyButton = createStyledRadioButton("Easy");
        JRadioButton normalButton = createStyledRadioButton("Normal");
        JRadioButton hardButton = createStyledRadioButton("Hard");

        panel.add(easyButton);
        panel.add(normalButton);
        panel.add(hardButton);

        add(panel, BorderLayout.CENTER);
    }

    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Arial", Font.BOLD, 14));
        radioButton.setForeground(Color.BLACK);
        radioButton.setBackground(Color.WHITE);
        difficultyGroup.add(radioButton);

        // 添加悬停效果
        radioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                radioButton.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                radioButton.setBackground(Color.WHITE);
            }
        });

        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSubDifficultySelection(text);
            }
        });

        return radioButton;
    }

    private void showSubDifficultySelection(String difficulty) {
        int difficultyValue = getDifficultyValue(difficulty);
        // 在这里实现弹出子难度选择窗口的逻辑
        // 例如，使用 JOptionPane.showInputDialog 显示输入对话框
        String[] options = {"1", "2", "3"};
        String subDifficulty = (String) JOptionPane.showInputDialog(
                this,
                "Choose sub-difficulty for " + difficulty,
                "Sub-Difficulty Selection",
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("sub_difficulty_icon.png"),
                options,
                options[0]);

        if (subDifficulty != null) {
            // 用户选择了子难度，可以在这里处理逻辑
            int subDifficultyValue = Integer.parseInt(subDifficulty);
             this.difficulty= difficultyValue + subDifficultyValue;
            System.out.println("Selected difficulty: " + this.difficulty);
            updateDifficulty(this.difficulty);
//            startGameWithSubDifficulty(this.difficulty);

            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(),this.difficulty);
            mainFrame.setGameController(gameController);
            gameController.setStatusLabel(mainFrame.getStatusLabel());
            this.dispose();
            mainFrame.setVisible(true);
        }

    }

    private void startGameWithSubDifficulty(int difficulty) {

    }


    private int getDifficultyValue(String difficulty) {
        // 映射关系，你可以根据实际需求进行调整
        switch (difficulty) {
            case "Easy":
                return 0;
            case "Normal":
                return 3;
            case "Hard":
                return 6;
            default:
                return -1;
        }
    }

    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficultyCallback(ModeSelectingFrame.TraditionalModeCallback callback) {
        this.callback=callback;
    }
    public void updateDifficulty(int newDifficulty) {
        this.difficulty = newDifficulty;

        // 执行回调
        if (callback != null) {
            callback.onReceiveDifficulty(this.difficulty);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TraditionalModeFrame difficultyFrame = new TraditionalModeFrame();
//            difficultyFrame.setVisible(true);
//        });
//    }
}
