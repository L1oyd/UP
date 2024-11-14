import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatematikoGame extends JFrame implements ActionListener {
    private Deck deck;
    private PlayerGrid playerGrid;
    private PlayerGrid computerGrid;
    private JLabel currentNumberLabel;
    private JButton nextNumberButton;
    protected int currentNumber;
    private ComputerPlayer computerPlayer;
    private boolean gameEnded;

    public MatematikoGame() {
        setTitle("Математико");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        deck = new Deck();
        playerGrid = new PlayerGrid("Ваше поле", this);
        computerGrid = new PlayerGrid("Поле компьютера", this);
        computerPlayer = new ComputerPlayer();

        // Верхняя панель с текущим числом и кнопкой
        JPanel topPanel = new JPanel();
        currentNumberLabel = new JLabel("");
        currentNumberLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nextNumberButton = new JButton("Начать");
        nextNumberButton.addActionListener(this);
        topPanel.add(currentNumberLabel);
        topPanel.add(nextNumberButton);

        // Центральная панель с полями игрока и компьютера
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(playerGrid);
        centerPanel.add(computerGrid);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        gameEnded = false;
    }


    public void start() {
        if (!gameEnded) {
            if (deck.hasNext()) {
                currentNumber = deck.drawCard();
                currentNumberLabel.setText("Текущее число: " + currentNumber);

                // Ход компьютера
                boolean computerMoved = computerPlayer.makeMove(computerGrid, currentNumber);

                // Активируем возможность игроку сделать ход
                playerGrid.enableCells(currentNumber);


                // Если компьютер не смог сделать ход (поле заполнено)
                if (!computerMoved) {
                    playerMadeMove();
                }
            }
        }
    }

    public void proceedToNextNumber() {
        playerGrid.disableCells();

        // Проверяем, заполнены ли поля
        if (playerGrid.isFull() && computerGrid.isFull()) {
            gameEnded = true;
            currentNumberLabel.setText("Игра окончена!");

            // Подсчет очков
            int playerScore = Scoring.calculateScore(playerGrid);
            int computerScore = Scoring.calculateScore(computerGrid);

            // Отображение результатов
            JOptionPane.showMessageDialog(this,
                    "Ваши очки: " + playerScore + "\nОчки компьютера: " + computerScore,
                    "Результаты игры",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void playerMadeMove() {
        // Этот метод вызывается после того, как игрок сделал ход
        proceedToNextNumber();
        start();
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatematikoGame game = new MatematikoGame();
            game.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextNumberButton.setVisible(false);
        start();
    }
}

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Integer> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(i);
            }
        }
        Collections.shuffle(cards);
    }

    public int drawCard() {
        return cards.remove(0);
    }

    public boolean hasNext() {
        return !cards.isEmpty();
    }
}

import java.util.*;

public class Scoring {
    public static int calculateScore(PlayerGrid grid) {
        int[][] numbers = grid.getNumbers();
        int totalScore = 0;

        // Проверка строк и столбцов
        for (int i = 0; i < 5; i++) {
            int[] row = numbers[i];
            int[] column = new int[5];
            for (int j = 0; j < 5; j++) {
                column[j] = numbers[j][i];
            }
            totalScore += calculateLineScore(row, false);
            totalScore += calculateLineScore(column, false);
        }

        // Проверка диагоналей
        int[] diag1 = new int[5];
        int[] diag2 = new int[5];
        for (int i = 0; i < 5; i++) {
            diag1[i] = numbers[i][i];
            diag2[i] = numbers[i][4 - i];
        }
        totalScore += calculateLineScore(diag1, true);
        totalScore += calculateLineScore(diag2, true);

        return totalScore;
    }

    private static int calculateLineScore(int[] line, boolean isDiagonal) {
        int score = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : line) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Список чисел в линии
        List<Integer> numbersList = new ArrayList<>();
        for (int num : line) {
            numbersList.add(num);
        }

        // Флаг, чтобы избежать двойного начисления очков за одну линию
        boolean scored = false;

        // 1. За 4 единицы
        if (!scored && Collections.frequency(numbersList, 1) == 4) {
            score += isDiagonal ? 210 : 200;
            scored = true;
        }

        // 2. За числа 1, 13, 12, 11 и 10
        if (!scored && numbersList.containsAll(Arrays.asList(1, 10, 11, 12, 13))) {
            score += isDiagonal ? 160 : 150;
            scored = true;
        }

        // 3. За три раза по 1 и два раза по 13
        if (!scored && Collections.frequency(numbersList, 1) == 3 && Collections.frequency(numbersList, 13) == 2) {
            score += isDiagonal ? 110 : 100;
            scored = true;
        }

        // 4. За 5 последовательных чисел
        if (!scored && isSequence(numbersList)) {
            score += isDiagonal ? 60 : 50;
            scored = true;
        }

        // 5. За 4 одинаковых числа
        if (!scored && counts.containsValue(4)) {
            score += isDiagonal ? 170 : 160;
            scored = true;
        }

        // 6. За 3 одинаковых числа и два других одинаковых числа
        if (!scored && counts.size() == 2 && counts.containsValue(3) && counts.containsValue(2)) {
            score += isDiagonal ? 90 : 80;
            scored = true;
        }

        // 7. За 3 одинаковых числа
        if (!scored && counts.containsValue(3)) {
            score += isDiagonal ? 50 : 40;
            scored = true;
        }

        // 8. За 2 пары одинаковых чисел
        if (!scored && counts.size() == 3 && Collections.frequency(new ArrayList<>(counts.values()), 2) == 2) {
            score += isDiagonal ? 30 : 20;
            scored = true;
        }

        // 9. За 2 одинаковых числа
        if (!scored && counts.containsValue(2)) {
            score += isDiagonal ? 20 : 10;
            scored = true;
        }

        return score;
    }

    // Метод для проверки, являются ли числа последовательными
    private static boolean isSequence(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 5) {
            return false;
        }
        List<Integer> sortedNumbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedNumbers);
        for (int i = 0; i < sortedNumbers.size() - 1; i++) {
            if (sortedNumbers.get(i + 1) - sortedNumbers.get(i) != 1) {
                return false;
            }
        }
        return true;
    }
}

import javax.swing.*;
import java.awt.*;

public class PlayerGrid extends JPanel {
    protected JButton[][] cells;
    private String name;
    private int[][] numbers;


    public PlayerGrid(String name, MatematikoGame gameInstance) {
        this.name = name;
        this.numbers = new int[5][5];
        setLayout(new BorderLayout());
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(nameLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        cells = new JButton[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new JButton("");
                cells[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                cells[i][j].setEnabled(false);
                gridPanel.add(cells[i][j]);

                int row = i;
                int col = j;

                // Для игрока добавляем ActionListener
                if (name.equals("Ваше поле")) {
                    cells[i][j].addActionListener(e -> {
                        if (cells[row][col].isEnabled()) {
                            int currentNumber = gameInstance.getCurrentNumber();
                            cells[row][col].setText(String.valueOf(currentNumber));
                            numbers[row][col] = currentNumber;
                            cells[row][col].setEnabled(false);
                            gameInstance.playerMadeMove(); // Вызываем метод после хода игрока
                        }
                    });
                }
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    public void enableCells(int number) {
        if (name.equals("Ваше поле")) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (cells[i][j].getText().equals("")) {
                        cells[i][j].setEnabled(true);
                    }
                }
            }
        }
    }

    public void disableCells() {
        if (name.equals("Ваше поле")) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    cells[i][j].setEnabled(false);
                }
            }
        }
    }

    public void setNumber(int row, int col, int number) {
        cells[row][col].setText(String.valueOf(number));
        numbers[row][col] = number;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public boolean isFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cells[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    public boolean makeMove(PlayerGrid grid, int number) {
        Random random = new Random();
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid.cells[i][j].getText().equals("")) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        if (emptyCells.isEmpty()) {
            return false; // Нет доступных клеток
        } else {
            int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
            grid.setNumber(cell[0], cell[1], number);
            return true;
        }
    }
}
