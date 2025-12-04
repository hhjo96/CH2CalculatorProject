package Step1;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //숫자 입력받기
        System.out.print("첫 번째 숫자를 입력하세요: ");
        int number1 = sc.nextInt();

        System.out.print("두 번째 숫자를 입력하세요: ");
        int number2 = sc.nextInt();

        //엔터 지우기
        sc.nextLine();
        System.out.print("사칙연산 기호를 입력하세요: ");
        String operator = sc.nextLine();
        operator = operator.charAt(0)+ "";

        System.out.println("operator = " + operator);

    }
}
