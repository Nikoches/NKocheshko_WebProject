package ServletExample.Servlets.TestInstance;

public class Test1 implements TestInterface {
    private static final Test1 test1 = new Test1();
    private Test1(){

    }
    public static TestInterface getInstance() {
        return test1;
    }
    public String getInfo(){
        return "info 0000";
    }
}
