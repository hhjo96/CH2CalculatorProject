package step3;

import java.util.ArrayList;
import java.util.List;

public class Calculator<T extends Number>{

    T number1;
    T number2;

    private String message = "";
    private List<String> resultList = new ArrayList<String>();

    public Calculator() {

    }

    public List<String> getResultList() {
        return resultList;
    }

    public String getMessage() {
        return message;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public double calc(T num1, T num2, String operator) {
        double result = -1;

        OperatorType op = OperatorType.PLUS; // null 로 초기화했더니 널처리를 해야 하는 거 같아서 plus로 초기화

        for(OperatorType type : OperatorType.values()) {
            if(type.getOperator().equals(operator)) {
                op = type;
            }
        }

        switch (op.getOperator()) {
            case "+":
                result = num1.doubleValue() + num2.doubleValue();
                break;
            case "-":
                result = num1.doubleValue() - num2.doubleValue();
                break;
            case "*":
                result = num1.doubleValue() * num2.doubleValue();
                break;
            case "/":
                try {
                    if (Double.compare(num2.doubleValue(), 0.0) == 0) {
                        throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                    } else {
                        result = num1.doubleValue() / num2.doubleValue();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                result = 0;
        }

        message = num1 + " " + operator + " " + num2 + " = " + result;
        resultList.add(message);

        return result;
    }

    public void removeResultList() {
        resultList.remove(0);
    }
}
