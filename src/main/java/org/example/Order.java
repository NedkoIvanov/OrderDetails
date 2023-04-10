package org.example;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
public class Order {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Product a = new Product("A", 0.52, "80%", "none");
        Product b = new Product("B", 0.38, "120%", "30% off");
        Product c = new Product("C", 0.41, "0.9 EUR/unit", "none");
        Product d = new Product("D", 0.60, "1 EUR/unit", "Buy 2, get 3rd free");

        menu(scanner,a,b,c,d);

    }

   public static double additionalClientDiscount(int clientId,double total){

        double additionalDiscount = 0.0;
       switch (clientId){
            case 1:
                if(total>10000 && total<=30000){
                    additionalDiscount = 0.0;
                }else if(total>30000){
                    additionalDiscount = 0.02;
                }
                break;
            case 2:
                if(total>10000 && total<=30000){
                    additionalDiscount = 0.01;
                }else if(total > 30000){
                    additionalDiscount = 0.02;
                }
                break;
            case 3:
                if(total>10000 && total<=30000){
                    additionalDiscount = 0.01;
                }else if(total>30000){
                    additionalDiscount = 0.01;
                }
                break;
            case 4:
                if(total>10000 && total<=30000){
                    additionalDiscount = 0.03;
                }else if(total>30000){
                    additionalDiscount = 0.05;
                }
                break;
            case 5:
                if(total>10000 && total<=30000){
                    additionalDiscount = 0.05;
                }else if(total > 30000){
                    additionalDiscount = 0.07;
                }
                break;
        }

        double totalPriceDiscount = total * additionalDiscount;
        return totalPriceDiscount;
   }

    public static double basicClientDiscount(int clientId,double productPrice){

        double basicDiscount = 0.0;

        switch (clientId){
            case 1:
                basicDiscount = 0.05;
                break;
            case 2:
                basicDiscount = 0.04;
                break;
            case 3:
                basicDiscount = 0.03;
                break;
            case 4:
                basicDiscount = 0.02;

                break;
            case 5:
                basicDiscount = 0.00;
                break;
        }

        double productPriceDiscount = productPrice * basicDiscount;
        return productPriceDiscount;
    }


    private static void printInfo(int clientId, int quantityA,
                                 int quantityB, int quantityC,
                                 int quantityD, Product a,
                                 Product b, Product c,
                                 Product d){
       double aPrice = a.getProductPrice(quantityA);
       double bPrice = b.getProductPrice(quantityB);
       double cPrice = c.getProductPrice(quantityC);
       double dPrice = d.getProductPrice(quantityD);

       //total price without client discounts
       double totalWithoutClient = aPrice + bPrice + cPrice + dPrice;

       //applying basic client discount
       double basicClientDiscountSum = (aPrice - basicClientDiscount(clientId,aPrice))
               + (bPrice - basicClientDiscount(clientId,bPrice))
               + (cPrice - basicClientDiscount(clientId,cPrice))
               + (dPrice - basicClientDiscount(clientId,dPrice));



       DecimalFormat df = new DecimalFormat("0.00");
       System.out.println("Order Summary");
       System.out.println("-------------");
       System.out.println("Product:A Quantity:" + quantityA + " Unit price:" + a.getUnitCost() +
               " Promo price(with markup):" + (quantityA == 0 ? "0.00" : df.format(aPrice/quantityA)) + " Total price:" + df.format(aPrice));
       System.out.println("Product:B Quantity:" + quantityB + " Unit price:" + b.getUnitCost() +
               " Promo price(with markup):" + (quantityB == 0 ? "0.00" : df.format(bPrice/quantityB)) + " Total price:" + df.format(bPrice));
       System.out.println("Product:C Quantity:" + quantityC + " Unit price:" + c.getUnitCost() +
               " Promo price(with markup):" + (quantityC == 0 ? "0.00" : df.format(cPrice/quantityC)) + " Total price:" + df.format(cPrice));

       String[] dPromotion = d.getPromotion().split(" ");
       System.out.println("Product:D Quantity:" + quantityD + " Unit price:" + d.getUnitCost() +
               " Promo price(with markup):" + (d.getPaidQuantity(quantityD,dPromotion) == 0 ? "0.00" : df.format(dPrice/ d.getPaidQuantity(quantityD,dPromotion))) + " Total price:" + df.format(dPrice));

       System.out.println("------------");
       System.out.println("Total (before client discount):" + df.format(totalWithoutClient) + " EUR");
       System.out.println("------------");
       System.out.println("Basic client discounts for each product for client with id=" + clientId);
       System.out.println("------------");
       System.out.println("Product A:" + df.format(basicClientDiscount(clientId,aPrice)));
       System.out.println("Product B:" + df.format(basicClientDiscount(clientId,bPrice)));
       System.out.println("Product C:" + df.format(basicClientDiscount(clientId,cPrice)));
       System.out.println("Product D:" + df.format(basicClientDiscount(clientId,dPrice)));
       System.out.println("------------");
       System.out.println("Additional client discount:" + df.format(additionalClientDiscount(clientId,basicClientDiscountSum)) + " EUR.");
       System.out.println("------------");
       System.out.println("Total (with client discount):" + df.format(basicClientDiscountSum - additionalClientDiscount(clientId,basicClientDiscountSum)) + " EUR.");

   }


   private static void menu(Scanner scanner,Product a,Product b,Product c,Product d){
       System.out.println("--------------Hello!--------------");
       System.out.println("Type 1 to log as admin.");
       System.out.println("Type 2 to log as user.");
       System.out.println("Type 3 to exit.");
       int choose = Integer.parseInt(scanner.nextLine());

       while(choose != 3) {

           switch (choose) {

               //admin
               case 1:
                   System.out.print("Enter password:");
                   String password = scanner.nextLine();
                   //just test pass
                   while (!password.equals("123456789")) {
                       if (!password.equals("123456789")) {
                           System.out.println("Wrong pass!Try again.");
                           System.out.print("Enter password:");
                       }
                       password = scanner.nextLine();
                   }


                   HashMap<String, Product> products = new HashMap<>();
                   products.put(a.getName(), a);
                   products.put(b.getName(), b);
                   products.put(c.getName(), c);
                   products.put(d.getName(), d);
                   System.out.println("Now you can change Unit cost,Markup and Product Promotion.");
                   System.out.print("Write the name of the product on which you want to make changes:");
                   String productName = scanner.nextLine();
                   Product productForChange = null;
                   while (productForChange == null) {

                       for (Product p : products.values()) {
                           if (p.getName().equals(productName)) {
                               productForChange = p;
                               break;
                           }
                       }

                       if (productForChange == null) {
                           System.out.println("There is no product with that name.");
                           System.out.print("Enter name again:");
                           productName = scanner.nextLine();
                       }


                   }
                   System.out.println("Type 1 for Unit cost change/ 2 for Markup change / 3 for Product Promotion change / 4 to exit.");
                   int change = Integer.parseInt(scanner.nextLine());
                   while (change != 4) {

                       double unitCost = 0.0;
                       String markup = "";
                       String promotion = "";

                       switch (change) {
                           case 1:
                               System.out.print("Enter unit cost(current unit cost:" +  productForChange.getUnitCost() + "):");
                               unitCost = Double.parseDouble(scanner.nextLine());
                               productForChange.setUnitCost(unitCost);
                               break;
                           case 2:
                               System.out.print("Enter product markup(current product markup:" + productForChange.getMarkup() + "):");
                               markup = scanner.nextLine();
                               productForChange.setMarkup(markup);
                               break;
                           case 3:
                               System.out.print("Enter product promotion(current promotion:" + productForChange.getPromotion() + "):");
                               promotion = scanner.nextLine();
                               productForChange.setPromotion(promotion);
                               break;
                           default:
                               System.out.println("Wrong input.");
                               break;
                       }
                       if (change != 4) {
                           System.out.println("Type 1 for Unit cost change/ 2 for Markup change / 3 for Product Promotion change / 4 to exit.");
                       }
                       change = Integer.parseInt(scanner.nextLine());
                   }
                   break;

               //user
               case 2:
                   int clientId = 0;
                   int quantityA = 0;
                   int quantityB = 0;
                   int quantityC = 0;
                   int quantityD = 0;

                   while (clientId < 1 || clientId > 5 || quantityA < 0 || quantityB < 0 || quantityC < 0 || quantityD < 0) {
                       System.out.println("Enter client ID (1-5),quantity for A,B,C and D.");
                       String input = scanner.nextLine();
                       String[] fromUser = input.split(" ");

                       try {
                           if (fromUser.length != 5) {
                               System.out.println("Invalid input.Please enter values for all fields.");
                               continue;
                           }
                           clientId = Integer.parseInt(fromUser[0]);
                           quantityA = Integer.parseInt(fromUser[1]);
                           quantityB = Integer.parseInt(fromUser[2]);
                           quantityC = Integer.parseInt(fromUser[3]);
                           quantityD = Integer.parseInt(fromUser[4]);

                           if (clientId < 1 || clientId > 5 || quantityA < 0 || quantityB < 0 || quantityC < 0 || quantityD < 0) {
                               System.out.println("Invalid input. Please enter a valid client ID and positive integer quantities for A, B, C, D.");
                           }

                       } catch (NumberFormatException e) {
                           System.out.println("Invalid input format. Please enter integers only.");
                       }
                   }

                   printInfo(clientId, quantityA, quantityB, quantityC, quantityD, a, b, c, d);
                   break;
               default:
                   System.out.println("Invalid input!");
                   break;
           }

           if (choose != 3) {
               System.out.println("Type 1 to log as admin.");
               System.out.println("Type 2 to log as user.");
               System.out.println("Type 3 to exit.");
           }
           choose = Integer.parseInt(scanner.nextLine());
       }

   }


}