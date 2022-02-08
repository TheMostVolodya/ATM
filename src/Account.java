import java.util.ArrayList;

public class Account {
    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions; // список транзакций по этому счету


    public Account(String name, User holder, Bank theBank) {

        //установка имяни аккаунта и владельца
        this.name = name;
        this.holder = holder;

        // получение UUID для учетной записи
        this.uuid = theBank.getNewAccountUUID();

        // инициализация транзакций в пустой список
        this.transactions = new ArrayList<Transaction>();

    }

    //геттер для account ID
    public String getUUID() {
        return this.uuid;
    }

    public void addTransaction(double amount) {

        // создать новую транзакцию и добавить ее в наш список
        Transaction newTrans = new Transaction(amount, this);
        this.transactions.add(newTrans);

    }

    public void addTransaction(double amount, String memo) {

        // создать новую транзакцию и добавить ее в наш список
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);

    }

    public double getBalance() {

        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;

    }

    //получить сводную строку для учетной записи
    public String getSummaryLine() {

        //найти баланс
        double balance = this.getBalance();

        // форматирование сводной строки если баланс отрицателен
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance,
                    this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance,
                    this.name);
        }

    }

   //Распечатать историю транзакций для аккаунта
    public void printTransHistory() {

        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();

    }

}