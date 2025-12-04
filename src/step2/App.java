package step2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int number1 = 0;
        int number2 = 0;
        int result = 0;

        String operator = "";
        String message = ""; // 결과 식을 String으로 저장
        String exitMessage = ""; // exit인지를 입력받을 변수

        Calculator calculator = new Calculator();

        while(true) {

            //숫자 입력받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            try {
                number1 = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자만 입력해주세요.");
                //엔터 지우기
                sc.nextLine();
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            try {
                number2 = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자만 입력해주세요.");
                //엔터 지우기
                sc.nextLine();
                continue;
            }

            //엔터 지우기
            sc.nextLine();

            System.out.print("사칙연산 기호를 입력하세요: ");
            operator = sc.nextLine();
            operator = operator.charAt(0) + "";

            if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
                System.out.println("정의된 연산자는 + - * / 입니다. 해당 연산자 중에서 입력해주세요.");
                continue;
            }

//        System.out.println("operator = " + operator);

            //계산하기. 추후 결과를 저장해야 하므로 message 에 저장했다.
            result = calculator.calc(number1, number2, operator);

            //나누기 0의 경우 따로 처리
            if (operator.equals("/") && number2 == 0) {
                continue;
            } else {
                message = number1 + operator + number2 + " = " + result;
                System.out.println(message);
            }
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            exitMessage = sc.nextLine();
            if (exitMessage.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

        }

    }

}
