import java.util.ArrayList;

class Bank {

  private static ArrayList<Integer> account = new ArrayList<>();

  // synchronized = mutex lock
  public static synchronized void deposit(int amount) {

    System.out.println(Thread.currentThread().getName() + (" entered " + "deposit()"));

    try {
      Thread.sleep(100); // sleep() does NOT release the synchronized lock.
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // Critical section
    account.add(amount);

    System.out.println(Thread.currentThread().getName() + " deposited ₹" + amount);
  }

  public static void showBalance() {

    int total = 0;

    for (int amount : account) {
      total += amount;
    }

    System.out.println("Total Balance: ₹" + total);
    System.out.println("Total Transactions: " + account.size());
  }
}

class BankThread extends Thread {

  private int amount;

  BankThread(String name, int amount) {
    super(name);
    this.amount = amount;
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      Bank.deposit(amount);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}

public class ThreadingInBankingProgram {

  public static void main(String[] args) {

    Thread t1 = new BankThread("Customer-1", 100);
    Thread t2 = new BankThread("Customer-2", 200);
    Thread t3 = new BankThread("Customer-3", 300);

    t1.start();
    t2.start();
    t3.start();

    // Wait for all threads to finish
    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    Bank.showBalance();
  }
}