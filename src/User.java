import java.security.MessageDigest;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Account> accounts;


    public User(String firstName, String lastName, String pin, Bank theBank) {


        this.firstName = firstName;
        this.lastName = lastName;


        //хранение хэш PIN-кодов с помощью mds, а не исходное значение, по соображениям безопасности
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (Exception e) {
            System.err.println("error, caught exeption : " + e.getMessage());
            System.exit(1);
        }

        //получение ID
        this.uuid = theBank.getNewUserUUID();

        //список учетных записей
        this.accounts = new ArrayList<Account>();
        System.out.printf("New user %s, %s with ID %s created.\n",
                lastName, firstName, this.uuid);

    }

    //геттер
    public String getUUID() {
        return this.uuid;
    }

    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    //получение номера аккаунта клиента
    public int numAccounts() {
        return this.accounts.size();
    }

    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }


    //печать истории транзакции аккаунта
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }

    //метод проверки пин кода
    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (Exception e) {
            System.err.println("error, caught exeption : " + e.getMessage());
            System.exit(1);
        }

        return false;
    }


    //распечатать сводки для учетных записей этого пользователя
    public void printAccountsSummary() {

        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a + 1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();

    }
}