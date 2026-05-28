abstract class Payment {
    protected double amount;
    
    public Payment(double amount) {
        this.amount = amount;
    }
    
    abstract void processPayment();
    abstract void generateReceipt();
}

class CreditCardPayment extends Payment {
    private String cardNumber;
    
    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
    
    @Override
    void processPayment() {
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Card: " + maskCardNumber());
    }
    
    @Override
    void generateReceipt() {
        System.out.println("Receipt: Credit Card Payment of $" + amount + " approved");
    }
    
    private String maskCardNumber() {
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }
    
    @Override
    void processPayment() {
        System.out.println("Processing cash payment of $" + amount);
    }
    
    @Override
    void generateReceipt() {
        System.out.println("Receipt: Cash Payment of $" + amount + " received");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(100.50, "1234567890123456");
        Payment p2 = new CashPayment(50.25);
        
        p1.processPayment();
        p1.generateReceipt();
        System.out.println();
        p2.processPayment();
        p2.generateReceipt();
    }
}