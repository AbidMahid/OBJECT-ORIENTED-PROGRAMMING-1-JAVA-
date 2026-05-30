class BankAccount {
    private double balance;
    private final Object lock = new Object();
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
        synchronized (lock) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + 
                " deposited $" + amount + ". New balance: $" + balance);
        }
    }
    
    public void withdraw(double amount) {
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + 
                    " withdrew $" + amount + ". New balance: $" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + 
                    " insufficient funds! Balance: $" + balance);
            }
        }
    }
    
    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }
}

class Transaction implements Runnable {
    private BankAccount account;
    private boolean isDeposit;
    private double amount;
    
    public Transaction(BankAccount account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            if (isDeposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MultithreadingDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        
        Thread t1 = new Thread(new Transaction(account, true, 200), "Thread-1");
        Thread t2 = new Thread(new Transaction(account, false, 150), "Thread-2");
        Thread t3 = new Thread(new Transaction(account, true, 300), "Thread-3");
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final balance: $" + account.getBalance());
    }
}