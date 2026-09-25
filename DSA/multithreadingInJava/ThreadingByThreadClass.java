public class ThreadingByThreadClass {

  public static void main(String[] args) {

    Thread t1 = new ThreadClass();
    Thread t2 = new ThreadClass();
    Thread t3 = new ThreadClass();
    Thread t4 = new ThreadClass();

    t1.start();
    t2.start();

    // try {
    //   t1.join();
    //   t2.join();
    // } catch (InterruptedException e) {
    //   e.printStackTrace();
    // }

    System.out.println("Total Books: " + Book.numOfBooks);
  }
}

class ThreadClass extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      Book.incNumOfBooks(1);
      System.out.println("Total Books: " + Book.numOfBooks);
    }
  }
}

class Book {

  static int numOfBooks = 0;

  public static synchronized void incNumOfBooks(int value) {
    numOfBooks += value;
  }
}