import com.infosys.jdbc_prepared_statetment_curd.dao.LaptopDao;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;
import com.infosys.jdbc_prepared_statetment_curd.service.LaptopService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LaptopController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char ch;

        do {
            System.out.println("1. Insert\n2. Delete\n3. Update\n4. Display \n5. Display by Id\n6. Laptop Filter by price");
            System.out.println("Please Enter Choice");
            int choice = scanner.nextInt();

            LaptopDao dao = new LaptopDao();
            LaptopService laptopService = new LaptopService();

            switch (choice) {
                case 1: {
                    System.out.println("Enter Laptop id : ");
                    int id = scanner.nextInt();
                    System.out.println("Enter Laptop name : ");
                    String name = scanner.next();
                    System.out.println("Enter Laptop color : ");
                    String color = scanner.next();
                    System.out.println("Enter Laptop price : ");
                    double price = scanner.nextDouble();
                    // pass mfd (yyy-mm-dd)
                    System.out.println("Enter laptop manufacturing date : ");
                    String mfd = scanner.next();

//				    LocalDate date = LocalDate.parse(mfd);
				    Laptop laptop = new Laptop(id,name,color,price,LocalDate.parse(mfd));
                    Laptop laptop2 = laptopService.saveLaptopService(laptop);

                    String msg = laptop2 != null ? "Successfully data Store" : "data not store";
                    System.out.println(msg);

                }
                break;
                case 2: {
                    System.out.println("Enter laptop Id");
                    int id = scanner.nextInt();
                    int a = dao.deleteLaptopByIdDao(id);
                    String msgString = a != 0 ? "deleted" : "given id is not present";
                    System.out.println(msgString);
                }
                break;
                case 3: {
                    System.out.print("Enter laptop Id : ");
                    int id = scanner.nextInt();

                    // take input from user
                    System.out.println("Enter Laptop name : ");
                    String name = scanner.next();
                    System.out.println("Enter Laptop color : ");
                    String color = scanner.next();
                    System.out.println("Enter Laptop price : ");
                    double price = scanner.nextDouble();
                    // pass mfd (yyy-mm-dd)
                    System.out.println("Enter laptop manufacturing date : ");
                    String mfd = scanner.next();

                    // create a laptop object and set the desire value or filed
                    Laptop laptop = new Laptop();
                    laptop.setName(name);
                    laptop.setColor(color);
                    laptop.setPrice(price);
                    laptop.setMfd(LocalDate.parse(mfd));

                    Laptop laptop1 = dao.updateLaptopByIdDao(id, laptop);
                    System.out.println(laptop1);

                }
                break;
                case 4: {
                    List<Laptop> laptops = dao.getAllLaptopDao();
                    for (Laptop laptop : laptops) {
                        System.out.println(laptop);
                    }
                }
                break;
                case 5: {
                    System.out.print("Enter laptop id to find laptop : ");
                    int id = scanner.nextInt();
                    Laptop laptop = dao.getLaptopByIdDao(id);
                    System.out.println(laptop);
                }
                break;
                case 6: {
                    System.out.print("Enter laptop price which is less than : ");
                    double user_filter_price = scanner.nextDouble();
                    List<Laptop> laptops = laptopService.filterLaptopByPriceService(user_filter_price);
                    if (laptops.isEmpty()) {
                        System.out.println("Item Not Found");
                    } else {
                        for (Laptop laptop : laptops) {
                            System.out.println(laptop);
                        }
                    }
                }
            }

            // for continuity
            System.out.print("Are You continue Y or y :  ");
            ch = scanner.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
        scanner.close();
    }
}