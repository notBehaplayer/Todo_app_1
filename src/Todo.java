import java.util.*;

public class Todo {
    TodoInterface todoInterface = new TodoInterface() {
        private final ArrayList<String[]> arrayList = new ArrayList<>();
        private final Scanner sc = new Scanner(System.in);
        private final TodoEnum[] todoEnum = TodoEnum.values();

        private void addDefaultTasks() {
            if (arrayList.isEmpty()) {
                arrayList.add(new String[]{"Пойти погулять", "Я очень хотел пойти погулять после школы вместе с друзьями", TodoEnum.MEDIUM.name(), UUID.randomUUID().toString()});
                arrayList.add(new String[]{"Купить продукты", "Нужно купить хлеб, молоко и яйца", TodoEnum.HIGH.name(), UUID.randomUUID().toString()});
                arrayList.add(new String[]{"Сделать домашку", "Подготовиться к экзаменам, решить задачи по математике", TodoEnum.LOW.name(), UUID.randomUUID().toString()});
            }
        }

        @Override
        public void addTask() {
            addDefaultTasks();
            String[] task = createTask(null, null, null, null);
            arrayList.add(task);
            sortTasks();
            System.out.println("Ваша задача успешно добавлена!");
        }

        @Override
        public void removeTask() {
            if (!arrayList.isEmpty()) {
                System.out.println("Для удаления задачи введите её ID: ");
                String UTId = sc.nextLine();

                if (removeTaskById(UTId)) {
                    System.out.println("Ваша задача была удалена!");
                } else {
                    System.out.println("Задача не найдена!");
                }
            } else {
                System.out.println("У вас нет задач для удаления!");
            }
        }

        @Override
        public void editTask() {
            if (!arrayList.isEmpty()) {
                System.out.println("Для редактирования задачи введите её ID: ");
                String UTId = sc.nextLine();

                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i)[3].equals(UTId)) {
                        String[] oldTask = arrayList.get(i);
                        arrayList.remove(i);
                        String[] updatedTask = createTask(oldTask[0], oldTask[1], oldTask[2], oldTask[3]);
                        arrayList.add(updatedTask);
                        sortTasks();
                        System.out.println("Ваша задача была успешно отредактирована!");
                        return;
                    }
                }

                System.out.println("Задача не найдена!");
            } else {
                System.out.println("У вас нет задач для редактирования!");
            }
        }

        @Override
        public void viewTasks() {
            if (!arrayList.isEmpty()) {
                for (int i = 0; i < arrayList.size(); i++) {
                    String[] task = arrayList.get(i);
                    System.out.println((i + 1) + ". Заголовок: " + task[0] +
                            ". Описание: " + task[1] +
                            ". Приоритет: " + task[2] +
                            ". ID: " + task[3] + ".");
                }
            } else {
                System.out.println("У вас нет задач для отображения!");
            }
        }

        @Override
        public void sortTasks() {
            arrayList.sort(Comparator.comparing(task -> TodoEnum.valueOf(task[2])));
        }

        private String[] createTask(String oldTitle, String oldDesc, String oldPriority, String oldId) {
            System.out.println("Введите заголовок задачи (оставьте пустым, чтобы оставить текущий):");
            String UTTitle = sc.nextLine();
            if (UTTitle.isBlank()) {
                UTTitle = oldTitle != null ? oldTitle : "Без заголовка";
            }

            System.out.println("Введите описание задачи (оставьте пустым, чтобы оставить текущее):");
            String UTDesc = sc.nextLine();
            if (UTDesc.isBlank()) {
                UTDesc = oldDesc != null ? oldDesc : "Без описания";
            }

            String UTImp = "";
            while (true) {
                System.out.println("Введите приоритет задачи " + Arrays.toString(todoEnum) +
                        " (оставьте пустым, чтобы оставить текущий или дефолтный LOW):");
                UTImp = sc.nextLine().toUpperCase();

                if (UTImp.isBlank()) {
                    UTImp = oldPriority != null ? oldPriority : TodoEnum.LOW.name();
                    break;
                }

                try {
                    TodoEnum.valueOf(UTImp);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Некорректный приоритет. Пожалуйста, введите один из " + Arrays.toString(todoEnum));
                }
            }

            String id = oldId != null ? oldId : UUID.randomUUID().toString();

            return new String[]{UTTitle, UTDesc, UTImp, id};
        }

        private boolean removeTaskById(String id) {
            return arrayList.removeIf(task -> task[3].equals(id));
        }
    };
}
