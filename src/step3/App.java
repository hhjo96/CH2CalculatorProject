package step3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        int number1 = 0;
//        int number2 = 0;
        double result = 0;

        String operator = "";
        String exitMessage = ""; // exit인지를 입력받을 변수

        OperatorType op = null; // enum으로 바꾸기

        Calculator<Double> calculator = new Calculator<>();

        while(true) {

            //숫자 입력받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            try {
                calculator.number1 = sc.nextDouble();
            } catch (Exception e) {
                printOnlyNumbersAndDeleteEnters(sc);
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            try {
                calculator.number2 = sc.nextDouble();
            } catch (Exception e) {
                printOnlyNumbersAndDeleteEnters(sc);
                continue;
            }

            //엔터 지우기
            sc.nextLine();

            System.out.print("사칙연산 기호를 입력하세요: ");
            operator = sc.nextLine();
            operator = operator.charAt(0) + "";

            if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
                printOperatorType();
                continue;
            }

            //계산하기.
            result = calculator.calc(calculator.number1, calculator.number2, operator);

            //나누기 0의 경우 따로 처리
            if (operator.equals("/") && Double.compare(calculator.number2, 0) == 0) {
                continue;
            } else {
                System.out.println("현재까지의 결과 값: " + calculator.getResultList());
            }

            System.out.println("가장 먼저 저장된 값을 삭제하시겠습니까? (y or n)");
            String deleteMessage = sc.nextLine(); // 가장 먼저 저장된 데이터 삭제할지 입력받을 변수
            if (deleteMessage.equalsIgnoreCase("y")) {
                calculator.removeResultList();
                printRemoveList(calculator);
            }

            printBiggerNum(calculator, sc);

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            exitMessage = sc.nextLine();
            if (exitMessage.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }


        }
        sc.close();
    }

    //메인 함수를 깔끔하게 만들기 위해 별도의 함수로 만들어 보았다.
    public static void printOnlyNumbersAndDeleteEnters(Scanner sc) {

        System.out.println("숫자만 입력해주세요.");
        //엔터 지우기
        sc.nextLine();

    }

    public static void printOperatorType() {

        System.out.print("정의된 연산자는 ");

        for (OperatorType opType : OperatorType.values()) {
            System.out.print(opType.getOperator() + " ");
        }

        System.out.println("입니다. 해당 연산자 중에서 입력해주세요.");
    }

    public static void printRemoveList(Calculator<Double> calculator){

        System.out.println("가장 먼저 저장된 값을 삭제했습니다.");
        System.out.println("현재까지의 결과 값: " + calculator.getResultList());
    }

    public static void printBiggerNum(Calculator<Double> calculator, Scanner sc) {
        System.out.println("입력받은 값보다 큰 결과물을 출력하시겠습니까? (y or n)");
        String bigMessage = sc.nextLine();
        if (bigMessage.equalsIgnoreCase("y")) {
            System.out.print("값을 입력하세요: ");
            double temp = sc.nextDouble();
            sc.nextLine();
            List<Double> pickedResultList =
                    calculator.getResultList().stream().map(r->r.split("=")[1].trim()).map(Double::parseDouble).toList();


            System.out.println(temp + "보다 큰 값을 출력합니다.");
            for(int i = 0; i < pickedResultList.size(); i++) {
                if(pickedResultList.get(i) > temp) {
                    System.out.print(pickedResultList.get(i)+ " ");
                }
            }
            System.out.println();
        }
    }
}
