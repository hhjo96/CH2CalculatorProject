package Step1;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int number1 = 0;
        int number2 = 0;
        int result = 0;
        String message = ""; // 결과 식을 String으로 저장
        String exitMessage = ""; // exit인지를 입력받을 변수

        while(true) {

            //숫자 입력받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            number1 = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            number2 = sc.nextInt();

            //엔터 지우기
            sc.nextLine();

            System.out.print("사칙연산 기호를 입력하세요: ");
            String operator = sc.nextLine();
            operator = operator.charAt(0) + "";

//        System.out.println("operator = " + operator);

            //계산하기. 추후 결과를 저장해야 하므로 message 에 저장했다.
            result = calc(number1, number2, operator);

            //나누기 0의 경우 따로 처리
            if (operator.equals("/") && number2 == 0) {
                continue;
            } else {
                message = number1 + operator + number2 + " = " + result;
                System.out.println(message);
            }
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            exitMessage = sc.nextLine();
            if (exitMessage.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

        }

    }

    public static int calc(int num1, int num2, String operator) {
        int result = -1;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                try {
                    if (num2 == 0){
                        throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                    } else {
                        result = num1 / num2;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default: result = 0;
        }
        return result;
    }
}
