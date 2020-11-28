package baseball;

import java.util.Scanner;

public class GameManager {
    final Scanner scanner;

    public GameManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void game() {
        do {
            gaming();
        } while (continueGame());
    }

    private void gaming() {
        Balls botBalls;
        Balls myBalls;
        GameResult gameResult;

        botBalls = Balls.generateRandomBalls();                 // 랜덤 생성 balls
        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            myBalls = getMyBalls();                             // 사용자 balls
            gameResult = myBalls.judgeResult(botBalls);         // 결과 계산
            System.out.println(gameResult.getResult());         // 결과 출력

            if (gameResult.isThreeStrikes()) {                  // 3스트라이크: 루프탈출
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
        }
    }

    private Balls getMyBalls() {
        Balls myBalls = null;
        while (myBalls == null) {
            try {
                myBalls = Balls.stringToBalls(scanner.next());  // 사용자 생성 balls
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return myBalls;
    }

    private Boolean continueGame() {
        GameStatus gameStatus = null;
        while (gameStatus == null) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String s = "";
            try {
                s = scanner.next().trim();
                gameStatus = GameStatus.valueOf(s);
            } catch (IllegalArgumentException e) {
                System.out.println("1, 2만 입력 가능합니다. 다시 입력해주세요. 입력값:" + s);
            }
        }
        return gameStatus.isContinue();
    }
}

enum GameStatus {
    CONTINUE("1"),
    QUIT("2");

    String input;

    GameStatus(String input) {
        this.input = input;
    }

    Boolean isContinue() {
        if (this == GameStatus.CONTINUE) {
            return true;
        } else if (this == GameStatus.QUIT) {
            return false;
        }

        return false;
    }
}