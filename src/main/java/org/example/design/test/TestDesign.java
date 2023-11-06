package org.example.design.test;

import org.example.design.Observer.MyTopic;
import org.example.design.Observer.MyTopicSubscriber;
import org.example.design.Observer.Observer;
import org.example.design.abstract_factory.ComputerFactory;
import org.example.design.abstract_factory.PCFactory;
import org.example.design.abstract_factory.ServerFactory;
import org.example.design.adapter.SocketAdapter;
import org.example.design.adapter.SocketClassAdapterImpl;
import org.example.design.adapter.SocketObjectAdapterImpl;
import org.example.design.adapter.Volt;
import org.example.design.bridge.GreenColor;
import org.example.design.bridge.RedColor;
import org.example.design.command.*;
import org.example.design.composite.Circle;
import org.example.design.composite.Drawing;
import org.example.design.composite.Triangle;
import org.example.design.decorator.BasicCar;
import org.example.design.decorator.LuxuryCar;
import org.example.design.decorator.SportsCar;
import org.example.design.facade.HelperFacade;
import org.example.design.facade.MySqlHelper;
import org.example.design.facade.OracleHelper;
import org.example.design.iterator.*;
import org.example.design.mediator.ChatMediator;
import org.example.design.mediator.ChatMediatorImpl;
import org.example.design.mediator.User;
import org.example.design.mediator.UserImpl;
import org.example.design.model.Computer;
import org.example.design.prototype.Employees;
import org.example.design.proxy.CommandExecutorProxy;
import org.example.design.state.TVContext;
import org.example.design.state.TVOffState;
import org.example.design.state.TVStartState;
import org.example.design.strategy.CreditCardStrategy;
import org.example.design.strategy.Item;
import org.example.design.strategy.PaypalStrategy;
import org.example.design.strategy.ShoppingCart;
import org.example.design.template.GlassHouse;
import org.example.design.template.HouseTemplate;
import org.example.design.template.WoodenHouse;
import org.example.design.visitor.*;

import java.sql.Connection;
import java.util.List;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/6 0006 17:52
 **/
public class TestDesign {

    public static void main(String[] args) throws CloneNotSupportedException {
        testIteratorPattern();

//        testVisitorPattern();

//    testStatePattern();


//        testCommandPattern();

//        testStrategyPattern();

//        testObserverPattern();

//        testMediatorPattern();

//        testTemplatePattern();

//        testDecoratorPattern();

//        testBridgePattern();

//        testFacadePattern();

//        testProxyPattern();

//        testComposite();

//        testAdapter();

//        testPrototypePattern();

//        testBuilderPattern();

//        testAbstractFactory();

//        testFactory();
    }

    private static void testIteratorPattern() {
        ChannelCollection channels = populateChannels();
        ChannelIterator baseIterator = channels.iterator(ChannelTypeEnum.ALL);
        while (baseIterator.hasNext()) {
            Channel c = baseIterator.next();
            System.out.println(c.toString());
        }
        System.out.println("******************");
        // Channel Type Iterator
        ChannelIterator englishIterator = channels.iterator(ChannelTypeEnum.ENGLISH);
        while (englishIterator.hasNext()) {
            Channel c = englishIterator.next();
            System.out.println(c.toString());
        }
    }

    private static ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(100.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(101.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));
        return channels;
    }

    private static void testVisitorPattern() {
        ItemElement[] items = {new Book(20, "1234"),
                new Book(100, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")};
        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        for (ItemElement item : items) {
            sum += item.accept(visitor);
        }
        return sum;
    }

    private static void testStatePattern() {
        TVContext context = new TVContext();
        TVStartState tvStartState = new TVStartState();
        TVOffState tvOffState = new TVOffState();

        context.setTvState(tvStartState);
        context.doAction();

        context.setTvState(tvOffState);
        context.doAction();
    }

    /**
     * Notice that client is responsible to create the appropriate type of command object.
     * Client program is also reponsible to attach receiver to the command and then command
     * to the invoker class.
     */
    private static void testCommandPattern() {
        // Creating the receiver object
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();

        // creating command and associating with receiver
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);

        // Creating invoker and associating with Command
        FileInvoker oFileInvoker = new FileInvoker(openFileCommand);

        // perform action on invoker object
        oFileInvoker.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        FileInvoker wFileInvoker = new FileInvoker(writeFileCommand);
        wFileInvoker.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        FileInvoker cFileInvoker = new FileInvoker(closeFileCommand);
        cFileInvoker.execute();
    }

    private static void testStrategyPattern() {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("1234", 10);
        Item item2 = new Item("5678", 40);

        cart.addItem(item1);
        cart.addItem(item2);

        // pay by paypal
        cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

        // pay by credit card
        cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "789", "12/15"));
    }

    /**
     * Observer design pattern is also called as publish-subscribe pattern.
     */
    private static void testObserverPattern() {
        // create subject
        MyTopic topic = new MyTopic();

        // create observers
        Observer obj1 = new MyTopicSubscriber("obj1");
        Observer obj2 = new MyTopicSubscriber("obj2");
        Observer obj3 = new MyTopicSubscriber("obj3");

        // register observers to the subject
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        // attach observer to subject
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        // check if any update is available
        obj1.update();

        // now send message to subject
        topic.postMessage("New Message");
    }

    private static void testMediatorPattern() {
        ChatMediator chatMediator = new ChatMediatorImpl();
        User user1 = new UserImpl(chatMediator, "Pankaj");
        User user2 = new UserImpl(chatMediator, "Lisa");
        User user3 = new UserImpl(chatMediator, "Saurabh");
        User user4 = new UserImpl(chatMediator, "David");
        chatMediator.addUser(user1);
        chatMediator.addUser(user2);
        chatMediator.addUser(user3);
        chatMediator.addUser(user4);

        user1.send("hi, guys.");
    }

    private static void testTemplatePattern() {
        HouseTemplate woodenHouse = new WoodenHouse();

        // using template method
        woodenHouse.buildHouse();
        System.out.println("******************");

        HouseTemplate glassHouse = new GlassHouse();
        glassHouse.buildHouse();
    }

    private static void testDecoratorPattern() {
        SportsCar sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        SportsCar sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }

    /**
     * Bridge design pattern can be used when both abstraction and implementation can have
     * different hierarchies independently and we want to hide the implementation from the
     * client application.
     */
    private static void testBridgePattern() {
        org.example.design.bridge.Shape triangle = new org.example.design.bridge.Triangle(new RedColor());
        triangle.applyColor();

        org.example.design.bridge.Shape pentagon = new org.example.design.bridge.Pentagon(new GreenColor());
        pentagon.applyColor();
    }

    private static void testFacadePattern() {
        String tableName = "Employee";

        // generating MySql HTML report and Oracle PDF report without using Facade
        Connection mySqlCon = MySqlHelper.getMySqlDBConnection();
        MySqlHelper mySqlHelper = new MySqlHelper();
        mySqlHelper.generateMySqlHTMLReport(tableName, mySqlCon);

        Connection oracleCon = OracleHelper.getOracleDBConnection();
        OracleHelper oracleHelper = new OracleHelper();
        oracleHelper.generateOraclePDFReport(tableName, oracleCon);

        // generate MySql HTML report and Oracle PDF report using Facade
        HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
        HelperFacade.generateReport(HelperFacade.DBTypes.ORACLE, HelperFacade.ReportTypes.PDF, tableName);
    }

    private static void testProxyPattern() {
        CommandExecutorProxy executor = new CommandExecutorProxy("Pankaj", "wrong_pwd");
        try {
            executor.runCommand("ls -ltr");
            executor.runCommand(" rm -rf abc.pdf");
        } catch (Exception e) {
            System.out.println("Exception Message::" + e.getMessage());
        }
    }

    private static void testComposite() {
        Triangle tri1 = new Triangle();
        Triangle tri2 = new Triangle();
        Circle cir = new Circle();

        Drawing drawing = new Drawing();
        drawing.add(tri1);
        drawing.add(tri2);
        drawing.add(cir);

        drawing.draw("Red");

        drawing.clear();

        drawing.add(tri1);
        drawing.add(cir);
        drawing.draw("Green");

    }

    private static void testAdapter() {
        testClassAdapter();

        testObjectAdapter();
    }

    private static void testObjectAdapter() {
        SocketObjectAdapterImpl objectAdapter = new SocketObjectAdapterImpl();
        Volt v3 = getVolt(objectAdapter, 3);
        Volt v12 = getVolt(objectAdapter, 12);
        Volt v120 = getVolt(objectAdapter, 120);
        System.out.println("v3 volts using Object Adapter = " + v3.getVolts());
        System.out.println("v12 volts using Object Adapter = " + v12.getVolts());
        System.out.println("v120 volts using Object Adapter = " + v120.getVolts());
    }


    private static void testClassAdapter() {
        SocketClassAdapterImpl classAdapter = new SocketClassAdapterImpl();
        Volt v3 = getVolt(classAdapter, 3);
        Volt v12 = getVolt(classAdapter, 12);
        Volt v120 = getVolt(classAdapter, 120);
        System.out.println("v3 volts using Class Adapter = " + v3.getVolts());
        System.out.println("v12 volts using Class Adapter = " + v12.getVolts());
        System.out.println("v120 volts using Class Adapter = " + v120.getVolts());

    }

    private static Volt getVolt(SocketAdapter socketAdapter, int divide) {
        switch (divide) {
            case 3:
                return socketAdapter.get3Volt();
            case 12:
                return socketAdapter.get12Volt();
            default:
                return socketAdapter.get120Volt();
        }
    }

    private static void testPrototypePattern() throws CloneNotSupportedException {
        Employees emps = new Employees();
        emps.loadData();

        // Use the clone method to get th eEmployee object
        Employees empsNew = (Employees) emps.clone();
        Employees empsNew2 = (Employees) emps.clone();
        List<String> list = empsNew.getEmpList();
        list.add("John");
        List<String> list2 = empsNew2.getEmpList();
        list2.remove("Pankaj");

        System.out.println("emps list: " + emps.getEmpList());
        System.out.println("empsNew list: " + list);
        System.out.println("empsNew2 list: " + list2);

    }

    private static void testBuilderPattern() {
        // Using builder to get the object in a single line of code and without any inconsistent state or arguments
        // management issues
        org.example.design.builder.Computer computer = new org.example.design.builder.Computer.ComputerBuilder("500 GB", "2 GB")
                .setBluetoothEnabled(true)
                .setGraphicsCardEnabled(true).build();
        System.out.println(computer);

    }

    private static void testAbstractFactory() {
        Computer pc = ComputerFactory.getComputer(new PCFactory("2 GB", "500 GB", "2.4 GHz"));
        Computer server = ComputerFactory.getComputer(new ServerFactory("16GB", "1 TB", "2.9 GHz"));
        System.out.println("AbstractFactory PC Config: " + pc);
        System.out.println("AbstractFactiry Server Config: " + server);
    }

    private static void testFactory() {
        Computer pc = org.example.design.factory.ComputerFactory.getComputer("pc", "2 GB", "500 GB", "2.4 GHz");
        Computer server = org.example.design.factory.ComputerFactory.getComputer("server", "16 GB", "1 TB", "2.9 GHz");
        System.out.println("Factory PC Config: " + pc);
        System.out.println("Factory Server Config: " + server);
    }
}
