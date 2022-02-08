import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;


    public Bank(String name) {

        this.name = name;
        users = new ArrayList<User>();
        accounts = new ArrayList<Account>();

    }


    //генерация универсального ID для User
    public String getNewUserUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 6; //длина ряда символов
        boolean nonUnique;

        //продолжаем цикл пока не получим уникальный ID
        do {

            //генерация числа
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            //проверка на уникальность
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }


    //генерация учетной записи для аккаунта
    public String getNewAccountUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;  //предпологаем что учетных записей будет больше чем пользователей
        boolean nonUnique = false;

        //продолжаем цикл пока не получим уникальный ID
        do {

            //генерация числа
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            //проверка на уникальность
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    public User addUser(String firstName, String lastName, String pin) {

        //создаение объекта User и добавление его в список
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //создание сберегательного счета и добавление User и Bank в lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;

    }


    //добавление учетной записи  для банка
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }



    //способ входа в банк
    public User userLogin(String userID, String pin) {

        //просмотр списка User и поиск подходящего
        for (User u : this.users) {

            //проверка ID
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        //если не найден User с таким UUID и Pin
        return null;

    }

    public String getName() {
        return this.name;
    }

}