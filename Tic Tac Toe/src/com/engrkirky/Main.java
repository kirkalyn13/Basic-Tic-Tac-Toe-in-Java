package com.engrkirky;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'_', '+', '_', '+', '_'},
                {' ', '|', ' ', '|', ' '},
                {'_', '+', '_', '+', '_'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Placement (1-9): ");
            int playerPos = scanner.nextInt();
            //System.out.println(pos);

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Postion taken, enter another position.");
                playerPos = scanner.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");

            String results = checkWinner();

            Random rand = new Random();
            int cpuPos = rand.nextInt(9 + 1);
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9 + 1);
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);
            results = checkWinner();
            if(results.length()>0){
                System.out.println(results);
                break;
            }

        }
    }

    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = 'X';

        if (user.equals("player")) {
            playerPositions.add(pos);
            symbol = 'X';
        } else if (user.equals("cpu")) {
            cpuPositions.add(pos);
            symbol = 'O';
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("Wrong Input.");
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 2, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diagPos = Arrays.asList(7, 5, 3);
        List diagNeg = Arrays.asList(1, 5, 9);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(diagPos);
        winningConditions.add(diagNeg);

        for (List l : winningConditions) {
            if (playerPositions.containsAll(l)) {
                return "VICTORY";
            } else if (cpuPositions.containsAll(l)) {
                return "DEFEAT";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "CAT";
            }

        }
        return "";
    }
}
