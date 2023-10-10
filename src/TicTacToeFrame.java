import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    Border empty;
    JPanel mainPnl;

    JPanel titlePnl;
    JLabel title;
    ImageIcon img;

    JPanel msgPnl;
    JLabel msg;

    JPanel gamePnl;

    JPanel cntPnl;
    JLabel oWins;
    JLabel xWins;
    JLabel tieCntLbl;
    JTextField xWinCntField;
    JTextField oWinCntField;
    JTextField tieCntField;
    int xWinCnt = 0;
    int oWinCnt = 0;
    int tieCnt = 0;
    int numTurns = 0;

    JPanel controlPnl;
    JButton quitBtn;
    JButton clearBtn;

    private static final int ROW = 3;
    private static final int COL = 3;
    public static String[][] board = new String[ROW][COL];
    private static final JButton[][] GUIboard = new JButton[ROW][COL];
    String player = "X";
    boolean again = false;


    public TicTacToeFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(5, 1));

        createTitlePnl();
        mainPnl.add(titlePnl);

        createMsgPnl();
        mainPnl.add(msg);

        createDisplay();
        mainPnl.add(gamePnl);

        createCntPnl();
        mainPnl.add(cntPnl);

        createControlPnl();
        mainPnl.add(controlPnl);

        add(mainPnl);
        mainPnl.setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
        setLocationRelativeTo(null);



    }
    private void createTitlePnl(){
        titlePnl = new JPanel();
        titlePnl.setBackground(Color.WHITE);
        title = new JLabel("Tic Tac Toe", img, JLabel.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 45));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        titlePnl.add(title);
    }
    private void createDisplay(){
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3, 3));
        gamePnl.setBackground(Color.WHITE);
        CreateGame();
    }
    private void createMsgPnl(){
        msgPnl = new JPanel();
        msgPnl.setBackground(Color.WHITE);
        msg = new JLabel("Player " + player + ", it is your turn.", null, JLabel.CENTER);
        msg.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        msg.setHorizontalTextPosition(JLabel.CENTER);
        msg.setVerticalTextPosition(JLabel.BOTTOM);
        msgPnl.add(msg);
    }
    private void createCntPnl(){
        cntPnl = new JPanel();
        cntPnl.setBackground(Color.WHITE);
        cntPnl.setLayout(new GridLayout(0, 1));

        xWins = new JLabel("Player X Win Count", JLabel.CENTER);
        xWins.setFont(new Font("Dialog", Font.PLAIN, 16));
        xWins.setVerticalTextPosition(JLabel.BOTTOM);
        xWins.setHorizontalTextPosition(JLabel.CENTER);

        xWinCntField = new JTextField();
        xWinCntField.setHorizontalAlignment(JTextField.CENTER);
        empty = BorderFactory.createEmptyBorder();
        xWinCntField.setBorder(empty);
        xWinCntField.setFont(new Font("Dialog", Font.PLAIN, 18));
        xWinCntField.setEditable(false);

        tieCntLbl = new JLabel("Tie Count", JLabel.CENTER);
        tieCntLbl.setFont(new Font("Dialog", Font.PLAIN, 16));
        tieCntLbl.setVerticalTextPosition(JLabel.BOTTOM);
        tieCntLbl.setHorizontalTextPosition(JLabel.CENTER);

        tieCntField = new JTextField();
        tieCntField.setHorizontalAlignment(JTextField.CENTER);
        tieCntField.setBorder(empty);
        tieCntField.setFont(new Font("Dialog", Font.PLAIN, 18));
        tieCntField.setEditable(false);

        oWins = new JLabel("Player O Win Count", JLabel.CENTER);
        oWins.setFont(new Font("Dialog", Font.PLAIN, 16));
        oWins.setVerticalTextPosition(JLabel.BOTTOM);
        oWins.setHorizontalTextPosition(JLabel.CENTER);

        oWinCntField = new JTextField();
        oWinCntField.setHorizontalAlignment(JTextField.CENTER);
        oWinCntField.setBorder(empty);
        oWinCntField.setFont(new Font("Dialog", Font.PLAIN, 18));
        oWinCntField.setEditable(false);

        cntPnl.add(xWins);
        cntPnl.add(xWinCntField);
        cntPnl.add(tieCntLbl);
        cntPnl.add(tieCntField);
        cntPnl.add(oWins);
        cntPnl.add(oWinCntField);

        oWinCntField.setText("0");
        xWinCntField.setText("0");
        tieCntField.setText("0");
    }
    private void createControlPnl(){
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2));
        controlPnl.setBackground(Color.WHITE);
        clearBtn = new JButton("Reset");
        clearBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        clearBtn.addActionListener((ActionEvent ae) ->{
            int res = JOptionPane.showOptionDialog(null, "Are you sure you want to reset game board and scores?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                clearBoard();
                for (int row1 = 0; row1 <= 2; row1++) {
                    for (int col1 = 0; col1 <= 2; col1++) {
                        GUIboard[row1][col1].setText(" ");
                    }
                }
                oWinCntField.setText("0");
                xWinCntField.setText("0");
                tieCntField.setText("0");
                oWinCnt = 0;
                xWinCnt = 0;
                tieCnt = 0;
                JOptionPane.showMessageDialog(null, "Board and scores reset", "Message", JOptionPane.INFORMATION_MESSAGE, img);
            }
            else if(res == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Canceled reset request", "Message", JOptionPane.INFORMATION_MESSAGE, img);
            }
            else if(res == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Canceled reset request", "Message", JOptionPane.INFORMATION_MESSAGE, img);
            }
        });
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        quitBtn.addActionListener((ActionEvent ae) ->{
            int res = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
            if(res == JOptionPane.YES_OPTION){
                System.exit(0);
            }
            else if(res == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null, "Canceled quit request", "Message", JOptionPane.INFORMATION_MESSAGE, img);
            }
            else if(res == JOptionPane.CLOSED_OPTION){
                JOptionPane.showMessageDialog(null, "Canceled reset request", "Message", JOptionPane.INFORMATION_MESSAGE, img);
            }
        });
        controlPnl.add(quitBtn);
        controlPnl.add(clearBtn);
    }
    public static void validMove(String player, int row, int col){
        board[row][col] = player;
    }

    public static void clearBoard(){
        for(int row = 0; row < ROW; row++){
            for(int col = 0; col < COL; col++) {
                board[row][col] = " ";

            }
        }
    }

    public static boolean isTie(){
        boolean xFlag = false;
        boolean oFlag = false;
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X")) {
                xFlag = true;
            }if(board[row][0].equals("O") ||
                board[row][1].equals("O") ||
                board[row][2].equals("O")) {
            oFlag = true;
        }if(! (xFlag && oFlag) )
        {
            return false;
        }
            xFlag = oFlag = false;
        }
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X")) {
                xFlag = true;
            }if(board[0][col].equals("O") ||
                board[1][col].equals("O") ||
                board[2][col].equals("O"))
        {
            oFlag = true;
        } if(! (xFlag && oFlag) )
        {
            return false;
        }
        }

        xFlag = oFlag = false;
        if(board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") )
        {
            oFlag = true;
        } if(! (xFlag && oFlag) )
        {
            return false;
        }
        xFlag = oFlag = false;
        if(board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") )
        {
            xFlag = true;
        } if(board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") )
        {
            oFlag = true;
        } if(! (xFlag && oFlag) )
        {
            return false;
        }
        return true;
    }

    public static boolean isWin(String player){
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }
    private static boolean isRowWin(String player){
        for(int row = 0; row < ROW; row++){
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player){
        for(int col = 0; col < COL; col++){
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player){
        for(int row = 0; row < ROW; row++){
            for(int col = 0; col < COL; col++){
                if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)){
                    return true;
                }
                if(board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)){
                    return true;
                }
            }
        }
        return false;
    }
    private void CreateGame() {
        do {
            clearBoard();
            numTurns = 0;
            for (int row = 0; row <= 2; row++) {
                for (int col = 0; col <= 2; col++) {
                    GUIboard[row][col] = new TicTacToeButton(row, col);
                    GUIboard[row][col].setBorder(new LineBorder(Color.BLUE));
                    GUIboard[row][col].setForeground(Color.BLACK);
                    GUIboard[row][col].setFont(new Font("Times New Roman", Font.ITALIC, 25));
                    gamePnl.add(GUIboard[row][col]);
                    GUIboard[row][col].setText(" ");
                    GUIboard[row][col].addActionListener(e -> {
                        TicTacToeButton clicked = (TicTacToeButton) e.getSource();
                        JFrame frame = new JFrame("JOptionPane");
                        numTurns++;

                        if (!clicked.getText().isBlank()) {
                            JOptionPane.showMessageDialog(frame, "Already taken. Make a valid move.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        clicked.setText(String.valueOf(player));
                        validMove(player, clicked.getRow(), clicked.getCol());


                        System.out.println(numTurns);
                        if (numTurns > 5 ) {

                            if (isWin(player)) {
                                int res = JOptionPane.showOptionDialog(frame, "Player " + player + " Wins!\nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    again = true;
                                } else if (res == JOptionPane.NO_OPTION) {
                                    clearBoard();
                                    numTurns = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                } else if (res == JOptionPane.CLOSED_OPTION) {
                                    clearBoard();
                                    numTurns = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                }
                                if (player.equals("X")) {
                                    xWinCnt = xWinCnt + 1;
                                    xWinCntField.setText(String.valueOf(xWinCnt));
                                }
                                if (player.equals("O")) {
                                    oWinCnt = oWinCnt + 1;
                                    oWinCntField.setText(String.valueOf(oWinCnt));
                                }
                                clearBoard();
                                numTurns = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                player = "O";
                                msg.setText("Player " + player + ", it is your turn.");

                            }
                        }  if (numTurns > 7 ) {

                            if (isWin(player)) {
                                int res = JOptionPane.showOptionDialog(frame, "Player " + player + " Wins!\nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    again = true;
                                } else if (res == JOptionPane.NO_OPTION) {
                                    clearBoard();
                                    numTurns = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                } else if (res == JOptionPane.CLOSED_OPTION) {
                                    clearBoard();
                                    numTurns = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                }
                                if (player.equals("X")) {
                                    xWinCnt = xWinCnt + 1;
                                    xWinCntField.setText(String.valueOf(xWinCnt));
                                }
                                if (player.equals("O")) {
                                    oWinCnt = oWinCnt + 1;
                                    oWinCntField.setText(String.valueOf(oWinCnt));
                                }
                                clearBoard();
                                numTurns = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                player = "O";
                                msg.setText("Player " + player + ", it is your turn.");

                            }
                            if (isTie()) {
                                int res = JOptionPane.showOptionDialog(frame, "Tie Game! \nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    again = true;
                                } else if (res == JOptionPane.NO_OPTION) {
                                    clearBoard();
                                    numTurns = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                } else if (res == JOptionPane.CLOSED_OPTION) {
                                    clearBoard();
                                    numTurns = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                }
                                tieCnt = tieCnt + 1;
                                tieCntField.setText(String.valueOf(tieCnt));
                                clearBoard();
                                numTurns = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                player = "O";
                                msg.setText("Player " + player + ", it is your turn.");
                            }
                        }
                        if (player.equals("X")) {
                            player = "O";
                        } else {
                            player = "X";
                        }
                        msg.setText("Player " + player + ", it is your turn.");
                    });
                }
            }
        }while(again);
    }
}
