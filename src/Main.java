import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Todo todo = new Todo();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Добро пожаловать в Todo\n" +
                    "1. Добавить задачу\n" +
                    "2. Удалить задачу\n" +
                    "3. Редактировать задачу\n" +
                    "4. Просмотр задач\n" +
                    "5. Выход");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    todo.todoInterface.addTask();
                    break;
                case 2:
                    todo.todoInterface.removeTask();
                    break;
                case 3:
                    todo.todoInterface.editTask();
                    break;
                case 4:
                    todo.todoInterface.viewTasks();
                    break;
                case 5:
                    System.out.println("Спасибо, что используете Todo! До свидания!");
                    System.exit(0);
                    break;
            }
        }
    }
}