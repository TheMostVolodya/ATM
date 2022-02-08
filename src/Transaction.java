import java.util.Date;

public class Transaction {

    private double amount;
    private Date timestamp;
    private String memo;  // памятка о транзакциях
    private Account inAccount;


    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();  //создание оъекта Data (метка времени)
        this.memo = "";
    }

    public Transaction(double amount, String memo, Account inAccount) {
        //вызов-обращение к первому конструктору
        //если произойдут изменения в 1ом конструкторе, они произойдут и сдесь
        this(amount, inAccount);
        this.memo = memo;
    }


    //получение количества транзакций
    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {

        if (this.amount >= 0) {
            return String.format("%s, $%.02f : %s",
                    this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s, $(%.02f) : %s",
                    this.timestamp.toString(), -this.amount, this.memo);
        }
    }

}