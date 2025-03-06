import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Pizzeria {
    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;
    private static final long BLACKLISTED_NUMBER = 12345678901234L; 

    public String storeName;
    public String storeAddress;
    public String storeEmail;
    public String storePhone;
    public List<String> storeMenu;
    public List<String> pizzaIngredients;
    public double pizzaPrice;
    public List<String> sides;
    public List<String> drinks;
    private String orderID;
    private double orderTotal;
    private String specialPizza;
    private String specialSide;
    private double specialPrice;

    public Pizzeria() {
        this.storeName = "Slice-o-Heaven";
        this.storeAddress = "Unknown";
        this.storeEmail = "N/A";
        this.storePhone = "N/A";
        this.storeMenu = new ArrayList<>();
        this.pizzaIngredients = new ArrayList<>();
        this.pizzaIngredients.add(DEF_PIZZA_INGREDIENTS);
        this.pizzaPrice = 0.0;
        this.sides = new ArrayList<>();
        this.sides.add("None");
        this.drinks = new ArrayList<>();
        this.drinks.add("None");
        this.orderID = DEF_ORDER_ID;
        this.orderTotal = DEF_ORDER_TOTAL;
    }

    public Pizzeria(String storeName, String storeAddress, String storeEmail, String storePhone) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeEmail = storeEmail;
        this.storePhone = storePhone;
        this.storeMenu = new ArrayList<>();
        this.pizzaIngredients = new ArrayList<>();
        this.pizzaPrice = 0.0;
        this.sides = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.orderID = DEF_ORDER_ID;
        this.orderTotal = DEF_ORDER_TOTAL;
    }

    public Pizzeria(String orderID, List<String> pizzaIngredients, double orderTotal) {
        this.storeName = "Slice-o-Heaven";
        this.storeAddress = "Unknown";
        this.storeEmail = "N/A";
        this.storePhone = "N/A";
        this.storeMenu = new ArrayList<>();
        this.pizzaIngredients = pizzaIngredients;
        this.pizzaPrice = 0.0;
        this.sides = new ArrayList<>();
        this.sides.add("None");
        this.drinks = new ArrayList<>();
        this.drinks.add("None");
        this.orderID = orderID;
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        String ing1 = "", ing2 = "", ing3 = "";
        boolean validIngredients = false;
        while (!validIngredients) {
            System.out.println("Please pick any three of the following ingredients:\n" +
                               "1. Mushroom\n2. Paprika\n3. Sun-dried tomatoes\n4. Chicken\n5. Pineapple\n" +
                               "Enter any three choices (1, 2, 3,…) separated by spaces:");
            String[] choices = scanner.nextLine().split("\\s+");
            if (choices.length != 3) {
                System.out.println("Please enter exactly three choices.");
                continue;
            }

            try {
                int ingChoice1 = Integer.parseInt(choices[0]);
                int ingChoice2 = Integer.parseInt(choices[1]);
                int ingChoice3 = Integer.parseInt(choices[2]);

                if (ingChoice1 < 1 || ingChoice1 > 5 || ingChoice2 < 1 || ingChoice2 > 5 || ingChoice3 < 1 || ingChoice3 > 5) {
                    System.out.println("Invalid choice(s). Please pick only from the given list:");
                    continue;
                }

                ing1 = switch (ingChoice1) {
                    case 1 -> "Mushroom";
                    case 2 -> "Paprika";
                    case 3 -> "Sun-dried tomatoes";
                    case 4 -> "Chicken";
                    case 5 -> "Pineapple";
                    default -> "None";
                };
                ing2 = switch (ingChoice2) {
                    case 1 -> "Mushroom";
                    case 2 -> "Paprika";
                    case 3 -> "Sun-dried tomatoes";
                    case 4 -> "Chicken";
                    case 5 -> "Pineapple";
                    default -> "None";
                };
                ing3 = switch (ingChoice3) {
                    case 1 -> "Mushroom";
                    case 2 -> "Paprika";
                    case 3 -> "Sun-dried tomatoes";
                    case 4 -> "Chicken";
                    case 5 -> "Pineapple";
                    default -> "None";
                };
                validIngredients = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice(s). Please enter numbers only:");
            }
        }
        this.pizzaIngredients = new ArrayList<>();
        this.pizzaIngredients.add(ing1);
        this.pizzaIngredients.add(ing2);
        this.pizzaIngredients.add(ing3);

        String pizzaSize = "";
        boolean validSize = false;
        while (!validSize) {
            System.out.println("What size should your pizza be?\n1. Large\n2. Medium\n3. Small\nEnter only one choice (1, 2, or 3):");
            String sizeChoice = scanner.nextLine();
            try {
                int choice = Integer.parseInt(sizeChoice);
                pizzaSize = switch (choice) {
                    case 1 -> "Large";
                    case 2 -> "Medium";
                    case 3 -> "Small";
                    default -> {
                        System.out.println("Invalid choice. Please pick only from the given list:");
                        yield "";
                    }
                };
                if (!pizzaSize.isEmpty()) {
                    validSize = true;
                    switch (pizzaSize.toLowerCase()) {
                        case "small": this.pizzaPrice = 8.00; break;
                        case "medium": this.pizzaPrice = 10.00; break;
                        case "large": this.pizzaPrice = 12.00; break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number:");
            }
        }

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.nextLine();
        if (extraCheese.equalsIgnoreCase("Y")) {
            this.pizzaPrice += 2.00;
            this.pizzaIngredients.add("Extra Cheese");
        }

        String sideDish = "";
        boolean validSide = false;
        while (!validSide) {
            System.out.println("Following are the side dish that go well with your pizza:\n" +
                               "1. Calzone\n2. Garlic bread\n3. Chicken puff\n4. Muffin\n5. Nothing for me\n" +
                               "What would you like? Pick one (1, 2, 3,…):");
            String sideChoice = scanner.nextLine();
            try {
                int choice = Integer.parseInt(sideChoice);
                sideDish = switch (choice) {
                    case 1 -> "Calzone";
                    case 2 -> "Garlic bread";
                    case 3 -> "Chicken puff";
                    case 4 -> "Muffin";
                    case 5 -> "Nothing for me";
                    default -> {
                        System.out.println("Invalid choice. Please pick only from the given list:");
                        yield "";
                    }
                };
                if (!sideDish.isEmpty()) validSide = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number:");
            }
        }
        this.sides = new ArrayList<>();
        this.sides.add(sideDish);

        String drink = "";
        boolean validDrink = false;
        while (!validDrink) {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:\n" +
                               "1. Coca Cola\n2. Cold coffee\n3. Cocoa Drink\n4. No drinks for me\n" +
                               "Enter your choice:");
            String drinkChoice = scanner.nextLine();
            try {
                int choice = Integer.parseInt(drinkChoice);
                drink = switch (choice) {
                    case 1 -> "Coca Cola";
                    case 2 -> "Cold coffee";
                    case 3 -> "Cocoa Drink";
                    case 4 -> "No drinks for me";
                    default -> {
                        System.out.println("Invalid choice. Please pick only from the given list:");
                        yield "";
                    }
                };
                if (!drink.isEmpty()) validDrink = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number:");
            }
        }
        this.drinks = new ArrayList<>();
        this.drinks.add(drink);

        this.orderID = generateOrderID();
        calculatedOrderTotal();

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.nextLine();
        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
    }

    private String generateOrderID() {
        return "ORD" + System.currentTimeMillis();
    }

    private void calculatedOrderTotal() {
        this.orderTotal = pizzaPrice;
        for (String side : sides) {
            if (!side.equalsIgnoreCase("Nothing for me")) this.orderTotal += 5.00;
        }
        for (String drink : drinks) {
            if (!drink.equalsIgnoreCase("No drinks for me")) this.orderTotal += 3.00;
        }
    }

    private void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        LocalDate birthdate = null;
        LocalDate today = LocalDate.now();
        boolean validDate = false;

        while (!validDate) {
            System.out.println("Enter your birthday (YYYY-MM-DD):");
            try {
                String birthdateInput = scanner.nextLine();
                birthdate = LocalDate.parse(birthdateInput);
                int years = Period.between(birthdate, today).getYears();
                if (years < 5 || years > 120) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                } else {
                    validDate = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD:");
            }
        }

        Period age = Period.between(birthdate, today);
        int years = age.getYears();
        boolean isBirthdayToday = birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth();
        if (years < 18 && isBirthdayToday) {
            System.out.println("Congratulations! You pay only half the price for your order");
            this.orderTotal /= 2;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
    }

    private void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();
        scanner.nextLine(); 

        boolean validExpiry = false;
        String expiryDate = "";
        while (!validExpiry) {
            System.out.println("Enter card expiry date (MM/YY):");
            expiryDate = scanner.nextLine();
            try {
                YearMonth expiry = YearMonth.parse(expiryDate, DateTimeFormatter.ofPattern("MM/yy"));
                YearMonth now = YearMonth.now();
                if (expiry.isBefore(now)) {
                    System.out.println("Invalid expiry date. The card has expired. Please enter a future date:");
                } else {
                    validExpiry = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid format. Please use MM/YY:");
            }
        }

        System.out.println("Enter CVV (3 digits):");
        int cvv = scanner.nextInt();
        processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        Scanner scanner = new Scanner(System.in);
        boolean validCard = false;

        while (!validCard) {
            String cardNumberStr = Long.toString(cardNumber);
            int cardLength = cardNumberStr.length();
            if (cardLength != 14) {
                System.out.println("Invalid card number length. Please enter a 14-digit number:");
                cardNumber = scanner.nextLong();
                continue;
            }
            if (cardNumber == BLACKLISTED_NUMBER) {
                System.out.println("Card is blacklisted. Please use another card:");
                cardNumber = scanner.nextLong();
                continue;
            }
            validCard = true;

            System.out.println("Card accepted");

            int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
            System.out.println("First digit of card: " + firstCardDigit);

            int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));
            System.out.println("Last four digits: " + lastFourDigits);

            StringBuilder cardNumberToDisplay = new StringBuilder(cardNumberStr);
            for (int i = 1; i < cardLength - 4; i++) {
                cardNumberToDisplay.setCharAt(i, '*');
            }
            System.out.println("Card number to display: " + cardNumberToDisplay.toString());
        }
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, double specialPrice) {
        this.specialPizza = pizzaOfTheDay;
        this.specialSide = sideOfTheDay;
        this.specialPrice = specialPrice;
        StringBuilder specialInfo = new StringBuilder();
        specialInfo.append("Today's Special: ")
                   .append(pizzaOfTheDay)
                   .append(" with ")
                   .append(sideOfTheDay)
                   .append(" for $")
                   .append(String.format("%.2f", specialPrice));
        System.out.println(specialInfo.toString());
    }

    @Override
    public String toString() {
        return String.format("\nReceipt for Order ID: %s\n" +
                             "Pizzeria: %s\n" +
                             "Address: %s\n" +
                             "Email: %s\n" +
                             "Phone: %s\n" +
                             "Pizza Ingredients: %s\n" +
                             "Sides: %s\n" +
                             "Drinks: %s\n" +
                             "Order Total: $%.2f",
                             orderID, storeName, storeAddress, storeEmail, storePhone,
                             String.join(", ", pizzaIngredients),
                             String.join(", ", sides),
                             String.join(", ", drinks),
                             orderTotal);
    }

    public void makePizza() {
        System.out.println("Making a pizza with the following ingredients: " + String.join(", ", pizzaIngredients));
    }

    public static void main(String[] args) {
        Pizzeria sliceOHeaven = new Pizzeria("Slice-o-Heaven", "123 Pizza St, Pizzaville", "contact@sliceoheaven.com", "123-456-7890");
        System.out.println("测试 takeOrder 方法：");
        sliceOHeaven.takeOrder();
        sliceOHeaven.makePizza();
        System.out.println(sliceOHeaven); 

        System.out.println("\n测试每日特价：");
        sliceOHeaven.specialOfTheDay("Margherita Pizza", "Fries", 12.99);
    }
}