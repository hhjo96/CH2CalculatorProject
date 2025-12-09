package step3;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


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

        while (true) {

            //숫자 입력받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            try {
                calculator.number1 = sc.nextDouble();
                //엔터 지우기
                sc.nextLine();
            } catch (Exception e) {
                printOnlyNumbers(sc);
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            try {
                calculator.number2 = sc.nextDouble();
            } catch (Exception e) {
                printOnlyNumbers(sc);
                continue;
            }

            //엔터 지우기
            sc.nextLine();

            System.out.print("사칙연산 기호를 입력하세요: ");
            operator = sc.nextLine();
            operator = operator.charAt(0) + "";

            //정해진 연산자가 아니라면 다시입력하라고 하기
            if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
                printOperatorType();
                continue;
            }

            //계산하기
            result = calculator.calc(calculator.number1, calculator.number2, operator);

            //나누기 0의 경우 따로 처리
            if (operator.equals("/") && Double.compare(calculator.number2, 0) == 0) {
                continue;
            } else {
                System.out.println("현재까지의 결과 값: " + calculator.getResultList());
            }

            //가장먼저 저장된값 삭제
            System.out.println("가장 먼저 저장된 값을 삭제하시겠습니까? (y or n)");
            String deleteMessage = sc.nextLine(); // 가장 먼저 저장된 데이터 삭제할지 입력받을 변수
            if (deleteMessage.equalsIgnoreCase("y")) {
                calculator.removeResultList();
                printRemoveList(calculator);
            }

            //입력받은 값보다 큰 결과 출력하기
            printBiggerNum(calculator, sc);

            //추가: 연산자별 결과 출력하기
            printByOp(calculator, sc);

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
    public static void printOnlyNumbers(Scanner sc) {

        System.out.println("숫자만 입력해주세요.");

    }

    //정의된 연산자만 출력하라고 하는 메서드
    public static void printOperatorType() {

        System.out.print("정의된 연산자는 ");

        for (OperatorType opType : OperatorType.values()) {
            System.out.print(opType.getOperator() + " ");
        }

        System.out.println("입니다. 해당 연산자 중에서 입력해주세요.");
    }

    //가장먼저 저장된 값 삭제
    public static void printRemoveList(Calculator<Double> calculator) {

        System.out.println("가장 먼저 저장된 값을 삭제했습니다.");
        System.out.println("현재까지의 결과 값: " + calculator.getResultList());
    }

    //입력받은 값보다 큰 결과만 출력
    public static void printBiggerNum(Calculator<Double> calculator, Scanner sc) {
        System.out.println("입력받은 값보다 큰 결과물을 출력하시겠습니까? (y or n)");
        String exitMessage = sc.nextLine();
        if (exitMessage.equalsIgnoreCase("y")) {
            while (true) {
                if (!calculator.getResultList().isEmpty()) {
                    System.out.print("값을 입력하세요: ");
                    try {
                        double temp = sc.nextDouble();
                        sc.nextLine();
                        List<Double> pickedResultList =
                                calculator.getResultList().stream().map(r -> r.split("=")[1].trim()).map(Double::parseDouble).toList();


                        System.out.println(temp + "보다 큰 값을 출력합니다.");
                        for (int i = 0; i < pickedResultList.size(); i++) {
                            if (pickedResultList.get(i) > temp) {
                                System.out.print(pickedResultList.get(i) + " ");
                            }
                        }
                        System.out.println();
                    } catch (Exception e) {
                        printOnlyNumbers(sc);
                        sc.nextLine();

                    }
                } else {
                    System.out.println("출력할 계산 이력이 없습니다.");
                }
                System.out.println("입력받은 값보다 큰 결과물 출력을 계속하시겠습니까? (exit 입력시 종료)");
                exitMessage = sc.nextLine();
                if (exitMessage.equalsIgnoreCase("exit")) {
                    break;
                }

            }

        }
    }


    //연산자를 입력받아 그 연산자로 계산한 계산식을 출력
    public static void printByOp(Calculator<Double> calculator, Scanner sc) {
        System.out.println("연산자 별로 출력하시겠습니까? (y or n)");
        String temp = sc.nextLine();
        if (temp.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("출력할 연산자의 종류를 입력해주세요.");
                String operator = sc.nextLine();
                switch (operator) {
                    case "+":
                        System.out.println("---덧셈---");
                        System.out.println(calculator.getResultList().stream().filter(r -> r.contains(operator)).collect(Collectors.toList()));
                        break;
                    case "-":
                        System.out.println("---뺄셈---");
                        System.out.println(calculator.getResultList().stream().filter(r -> r.contains(operator)).collect(Collectors.toList()));
                        break;
                    case "*":
                        System.out.println("---곱셈---");
                        System.out.println(calculator.getResultList().stream().filter(r -> r.contains(operator)).collect(Collectors.toList()));
                        break;
                    case "/":
                        System.out.println("---나눗셈---");
                        System.out.println(calculator.getResultList().stream().filter(r -> r.contains(operator)).collect(Collectors.toList()));
                        break;
                    default:
                        System.out.println("잘못된 연산자를 입력했습니다. + - * / 중에 입력해주세요.");
                }
                System.out.println("연산자별 출력을 계속하시겠습니까? (exit 입력 시 종료)");
                String exitMessage = sc.nextLine();
                if (exitMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        }
    }
}
