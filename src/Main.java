import java.util.*;

public class Main {
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in);
        addUsers();
        while (true){
            System.out.println(" 1. Метод получения всех пользователей \n" +
                    " 2. Метод получения пользователя по идентификатору (использовать минимум 3 метода класса Optional)\n" +
                    " 3. Метод добавления пользователя \n" +
                    " 4. Метод редактирования пользователя по идентификатору \n" +
                    " 5. Метод удаления пользователя по идентификатору \n" +
                    " 6. Метод получения только имён и возраста всех пользователей \n" +
                    " 7. Метод получения только имён пользователей и фильтрации имён по длине \n" +
                    " 8. Метод получения числа умноженных возростов пользователей (возраст 1 пользователя умножается на возраст 2 а возраст 2 умножается на возраст 3 и тд) чей возраст больше 20 \n" +
                    " 9. Метод получения минимального возраста \n" +
                    " 10. Метод получения максимального \n" +
                    " 11. Метод который проверят существует ли в коллекции хотя бы один элемент с именем начинающийся на букву J и если да то выводится результат логического типа данных\n" +
                    " 12. Метод который будет фильтровать всех пользователей\n"+
                    " 13. Выход \n"+
                    "Введите цыфру: "
            );
            int console = scanner.nextInt();
            switch (console){
                case 1:
                    getAllUsers();
                    System.out.println("\n \n");
                    break;

                case 2:
                    System.out.println("Введите id");
                    int ID = scanner.nextInt();
                    getUserById(ID);
                    System.out.println("\n \n");
                    break;

                case 3:
                    addUser(new User(11, "John", 25));
                    System.out.println("\n \n");
                    break;

                case 4:
                    editUserId(2, "Anna", 30);
                    System.out.println("\n \n");
                    break;

                case 5:
                    System.out.println("Введите id");
                    int IDDell = scanner.nextInt();
                    deleteUserId(IDDell);
                    System.out.println("\n \n");
                    break;

                case 6:
                    getNamesAndAges();
                    System.out.println("\n \n");
                    break;

                case 7:
                    System.out.println("Введите длинну");
                    int len = scanner.nextInt();
                    getFilteredNames(len);
                    System.out.println("\n \n");
                    break;

                case 8:
                    increaseAges();
                    System.out.println("\n \n");
                    break;

                case 9:
                    getMinAge();
                    System.out.println("\n \n");
                    break;

                case 10:
                    getMaxAge();
                    System.out.println("\n \n");
                    break;

                case 11:
                    checkNameJ();
                    System.out.println("\n \n");
                    break;

                case 12:
                    filterUsers(users);
                    System.out.println("\n \n");
                    break;

                case 13:
                    System.exit(0);
                    System.out.println("\n \n");
                    break;
                default:
                    break;
            }

        }

    }

    public static void addUsers() {
        users.add(new User(1, "John", 25));
        users.add(new User(2, "Alex", 30));
        users.add(new User(3, "Mary", 20));
        users.add(new User(4, "David", 35));
        users.add(new User(5, "Kate", 28));
        users.add(new User(6, "Jason", 22));
        users.add(new User(7, "Jessica", 32));
        users.add(new User(8, "Daniel", 19));
        users.add(new User(9, "Sarah", 27));
        users.add(new User(10, "Luke", 24));
    }

    public static void getAllUsers() {
        users.stream()
                .map(u-> "Id: " + u.getId() + " ,Name: " + u.getName() + ", Age: " + u.getAge())
                .forEach(System.out::println);
    }

    public static void getUserById(int id) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        user.ifPresentOrElse(
                u -> System.out.println(u.getName()),
                () -> System.out.println("Такого нет")
        );
    }

    public static void addUser(User user) {
        users.add(user);
        System.out.println("Добавлен: " + user.getName());
    }

    public static void editUserId(int id, String newName, int newAge) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        user.ifPresent(u -> {
            u.name = newName;
            u.age = newAge;
            System.out.println("Пользователь обновлен: " + u.getName());
        });


    }

    public static void deleteUserId(int id) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        user.ifPresent(u -> {
            users.remove(u);
            System.out.println("Пользователь удален: " + u.getName());
        });
    }

    public static void getNamesAndAges() {
        users.stream()
                .map(u -> "Name: " + u.getName() + ", Age: " + u.getAge())
                .forEach(System.out::println);
    }

    public static void getFilteredNames(int length) {
        users.stream()
                .map(User::getName)
                .filter(name -> name.length() == length)
                .forEach(System.out::println);
    }

    public static void increaseAges() {
        int result = users.stream()
                .filter(u -> u.getAge() > 20)
                .mapToInt(User::getAge)
                .reduce(1, (a, b) -> a * b);

        System.out.println("Результат: " + result);
    }

    public static void getMinAge() {
        OptionalInt minAge = users.stream()
                .mapToInt(User::getAge)
                .min();

        minAge.ifPresent(System.out::println);
    }

    public static void getMaxAge() {
        OptionalInt maxAge = users.stream()
                .mapToInt(User::getAge)
                .max();

        maxAge.ifPresent(System.out::println);
    }

    public static void checkNameJ() {
        boolean exists = users.stream()
                .map(User::getName)
                .anyMatch(name -> name.startsWith("J"));

        System.out.println("Ответ: " + exists);
    }

    public static void filterUsers(List<User> users) {

        for (User user : users) {
            if (user.getAge() > 18 && user.getAge() <= 25 && user.getId() % 3 == 0) {
                System.out.println(user.getId() + " " + user.getName());
            }
        }
    }

}