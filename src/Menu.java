import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static Battlefield field = new Battlefield();

    private static void printAbilityMenu(){
        System.out.println("Для добавления лёгкого танка введите (1)");
        System.out.println("Для добавления тяжёлого танка введите (2)");
        System.out.println("Для вывода всех танков введите (3)");
        System.out.println("Для удаления танка с поля боя введите (4)");
        System.out.println("Для использования возможностей всех танков введите (5)");
        System.out.println("Для выхода из программы введите (0)");
    }
    public static void printMenu(){
        int choice;
        boolean runMenu = true;
        while (runMenu){
            printAbilityMenu();
            System.out.print("Введите вашу команду: ");
            choice = Menu.readChoice();
            switch (choice){
                case 1:
                    addLightTank();
                    break;
                case 2:
                    addHeavyTank();
                    break;
                case 3:
                    field.displayTanks();
                    break;
                case 4:
                    removeTank();
                    break;
                case 5:
                    field.performAbilityAllTanks();
                    break;
                case 0:
                    runMenu=false;
                    System.out.print("Вы вышли из программы :)");
                    break;
                default:
                    System.out.println("Команды с таким номером нет. Введите команду заново.");
            }
        }
    }

    private static int readIntNum(){
        Scanner scanner = new Scanner(System.in);
        int readed;
        try {
            readed = Integer.parseInt(scanner.nextLine());
            if (readed<=0){
                System.out.print("Ошибка: Танк не может иметь отрицательные или нулевые параметры. Введите параметр заново: ");
                readed = readIntNum();
            }
        }
        catch (NumberFormatException e){
            System.out.print("Ошибка: Похоже, вы ввели не число или его неправильный формат. Введите число заново: ");
            readed = readIntNum();
        }
        return readed;
    }

    private static int readChoice(){
        Scanner scanner = new Scanner(System.in);
        int readed;
        try {
            readed = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.print("Ошибка: Похоже, вы ввели не число или его неправильный формат. Введите число заново: ");
            readed = readChoice();
        }
        return readed;
    }

    private static String readStringName() {
        while(true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Строка не может быть пустой.");
                }
                return input;
            } catch (IllegalArgumentException var2) {
                System.out.println(var2.getMessage());
            }
        }
    }

    private static void addLightTank() {
        System.out.print("Введите название танка: ");
        String name = readStringName();
        System.out.print("Введите кол-во очков прочности танка: ");
        int HP = readIntNum();
        System.out.print("Введите дальность обзора танка: ");
        int viewRange = readIntNum();
        field.addTank(new LightTank(name, HP, viewRange));
    }

    private static void addHeavyTank() {
        System.out.print("Введите название танка: ");
        String name = readStringName();
        System.out.print("Введите очки прочности танка: ");
        int HP = readIntNum();
        System.out.print("Введите толщину брони танка: ");
        int armorThickness = readIntNum();
        field.addTank(new HeavyTank(name, HP, armorThickness));
    }

    private static void removeTank() {
        System.out.print("Введите название танка для удаления с поля боя: ");
        String nameToRemove = readStringName();
        if (field.removeTank(nameToRemove)) {
            System.out.println("Танк " + nameToRemove + " удалён с поля боя.");
        } else {
            System.out.println("Танка " + nameToRemove + " нет на поле боя.");
        }
    }
}
