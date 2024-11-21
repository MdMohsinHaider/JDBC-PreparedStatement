import com.infosys.jdbc_prepared_statetment_curd.dao.LaptopDao;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LaptopController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Insert\n2. Delete\n3. Update\n4. Display \n5. Display by Id");
        System.out.println("Please Enter Choice");
        int choice = scanner.nextInt();

        LaptopDao dao = new LaptopDao();

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

//				LocalDate date = LocalDate.parse(mfd);
//				Laptop laptop = new Laptop(id,name,color,price,LocalDate.parse(mfd));
                Laptop laptop = dao.saveLaptopDao(new Laptop(id,name,color,price, LocalDate.parse(mfd)));

                String msg = laptop!=null?"Success":"data not store";
                System.out.println(msg);

            }break;
            case 2: {
                System.out.println("Enter laptop Id");
                int id = scanner.nextInt();
                int a = dao.deleteLaptopByIdDao(id);
                String msgString = a!=0?"deleted":"given id is not present";
                System.out.println(msgString);
            }break;
            case 3:{
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

                Laptop laptop1 = dao.updateLaptopByIdDao(id,laptop);
                System.out.println(laptop1);

            }break;
            case 4: {
                List<Laptop> laptops = dao.getAllLaptopDao();
                for(Laptop laptop:laptops){
                    System.out.println(laptop);
                }
            }break;
            case 5: {
                System.out.print("Enter laptop id to find laptop : ");
                int id = scanner.nextInt();
                Laptop laptop = dao.getLaptopByIdDao(id);
                System.out.println(laptop);
            }
        }
        scanner.close();
    }
}