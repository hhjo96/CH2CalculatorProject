package step3;

public enum OperatorType {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

    private String operator;

    OperatorType(String operator) {
        this.operator = operator;
    }
    public String getOperator() {
        return operator;
    }
}
