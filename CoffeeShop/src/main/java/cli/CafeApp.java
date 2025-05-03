package cli;

import model.Drink;
import model.Food;
import model.MenuItem;
import model.Order;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafeApp {
    private final Scanner scanner = new Scanner(System.in);
    private final List<MenuItem> menu = new ArrayList<>();
    private final OrderService orderService = new OrderService(new Order());

    public void run() {
        fillMenu();
        boolean running = true;
        while (running) {
            System.out.println("1. Добавить товар в заказ");
            System.out.println("2. Применить промокод");
            System.out.println("3. Просмотр заказа");
            System.out.println("4. Завершить заказ и выйти");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> handleAddToOrder();
                case "2" -> handlePromoCode();
                case "3" -> printOrder();
                case "4" -> {
                    printOrder();
                    running = false;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void fillMenu() {
        menu.add(new Drink("Капучино", 200, 250));
        menu.add(new Drink("Чай", 100, 300));
        menu.add(new Food("Бургер", 300, 800));
        menu.add(new Food("Салат", 250, 350));
    }

    private void showMenu() {
        System.out.println("--- Меню ---");
        int index = 1;
        for (MenuItem item : menu) {
            System.out.printf("%d. %s - %.2f руб.\n", index++, item.getName(), item.getPrice());
        }
    }

    private void handleAddToOrder() {
        showMenu();
        System.out.print("Введите номер товара: ");
        int itemNum = Integer.parseInt(scanner.nextLine());
        if (itemNum < 1 || itemNum > menu.size()) {
            System.out.println("Неверный номер");
            return;
        }
        orderService.addItem(menu.get(itemNum - 1));
        System.out.println("Товар добавлен.");
    }

    private void handlePromoCode() {
        System.out.print("Введите промокод: ");
        String code = scanner.nextLine();
        orderService.applyPromoCode(code);
    }

    private void printOrder() {
        System.out.println("--- Заказ ---");
        for (MenuItem item : orderService.getItems()) {
            System.out.printf("%s - %.2f руб.\n", item.getName(), item.getPrice());
        }
        System.out.printf("Итого: %.2f руб.\n", orderService.getTotalPrice());
    }
}
