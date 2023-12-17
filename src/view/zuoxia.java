//package view;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class zuoxia {
//    private JFrame frame;
//    private JPanel mainPanel;
//    private JPanel statusBar;
//
//    public zuoxia() {
//        frame = new JFrame("Status Bar Example");
//        frame.setSize(400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        mainPanel = new JPanel(new BorderLayout());
//        statusBar = new JPanel(new BorderLayout());
//        statusBar.setBackground(Color.LIGHT_GRAY);
//
//        mainPanel.add(new JButton(new AbstractAction("Perform Action") {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                performAction();
//            }
//        }), BorderLayout.CENTER);
//
//        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
//        frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
//
//        frame.setVisible(true);
//    }
//
//    private void performAction() {
//        // 模拟执行某个操作
//        boolean operationSuccessful = performSomeOperation();
//
//        // 根据操作结果显示不同的消息
//        if (operationSuccessful) {
//            showStatusMessage("Operation successful!");
//        } else {
//            showErrorMessage("Invalid operation!");
//        }
//    }
//
//    // 模拟执行某个操作
//    private boolean performSomeOperation() {
//        // 这里可以是你的业务逻辑
//        // 返回操作是否成功的标志
//        return true;
//    }
//
//    // 显示普通消息
//    private void showStatusMessage(String message) {
//        showMessage(message, Color.BLACK);
//    }
//
//    // 显示错误消息
//    private void showErrorMessage(String errorMessage) {
//        showMessage(errorMessage, Color.RED);
//    }
//
//    private void showMessage(String message, Color textColor) {
//        JLabel label = new JLabel(message);
//        label.setForeground(textColor);
//        statusBar.removeAll();
//        statusBar.add(label, BorderLayout.CENTER);
//        statusBar.revalidate();
//        statusBar.repaint();
//
//        // 3秒后自动清空消息
//        Timer timer = new Timer(3000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                statusBar.removeAll();
//                statusBar.revalidate();
//                statusBar.repaint();
//            }
//        });
//        timer.setRepeats(false);
//        timer.start();
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new zuoxia();
//            }
//        });
//    }
//}
