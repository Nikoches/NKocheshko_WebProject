package ServletExample.Servlets.TestInstance;

public class Test2 {
    private TestInterface test1 = Test1.getInstance();
    public String getInfo(){
        return test1.getInfo();
    }
}
