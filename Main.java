class Order {
    String lastName;
    int orderNumber;
    double orderTotal;

    public Order(String lastName, int orderNumber, double orderTotal) {
        this.lastName = lastName;
        this.orderNumber = orderNumber;
        this.orderTotal = orderTotal;
    }
}

class Display {
    Order[] orderByLastName;
    Order[] orderByOrderNumber;
    int size;

    public Display(int capacity) {
        orderByLastName = new Order[capacity];
        orderByOrderNumber = new Order[capacity];
        size = 0;
    }

    public void updateDisplay(Order order) {
        orderByLastName[size] = order;
        orderByOrderNumber[size] = order;
        size++;
        quickSort(orderByLastName, 0, size - 1, true);
        quickSort(orderByOrderNumber, 0, size - 1, false);
        displayOrders();
    }

    private void quickSort(Order[] array, int low, int high, boolean sortByLastName) {
        if (low < high) {
            int pi = partition(array, low, high, sortByLastName);
            quickSort(array, low, pi - 1, sortByLastName);
            quickSort(array, pi + 1, high, sortByLastName);
        }
    }

    private int partition(Order[] array, int low, int high, boolean sortByLastName) {
        Order pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if ((sortByLastName && array[j].lastName.compareTo(pivot.lastName) > 0)
                    || (!sortByLastName && array[j].orderNumber > pivot.orderNumber)) {
                i++;
                Order temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Order temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public void displayOrders() {
        System.out.println("Order list sorted by last name:");
        for (int i = 0; i < size; i++) {
            System.out.println(orderByLastName[i].lastName + " " + orderByLastName[i].orderNumber + " "
                    + orderByLastName[i].orderTotal);
        }
        System.out.println("\nOrder list sorted by order number:");
        for (int i = 0; i < size; i++) {
            System.out.println(orderByOrderNumber[i].lastName + " " + orderByOrderNumber[i].orderNumber + " "
                    + orderByOrderNumber[i].orderTotal);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Display display = new Display(100);
        Order order1 = new Order("Smith", 1001, 50.0);
        display.updateDisplay(order1);
        Order order2 = new Order("Johnson", 1002, 70.0);
        display.updateDisplay(order2);
        Order order3 = new Order("Doe", 1003, 60.0);
        display.updateDisplay(order3);
    }
}
