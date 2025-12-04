package step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

List<String> resultList = new ArrayList<String>();

    public int calc(int num1, int num2, String operator) {
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
