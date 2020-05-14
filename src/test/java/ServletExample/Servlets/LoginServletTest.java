package ServletExample.Servlets;


import ServletExample.Logic.DbStore;
import ServletExample.Logic.Store;
import ServletExample.Logic.Validate;
import ServletExample.Logic.ValidateService;
import ServletExample.Servlets.TestInstance.*;
import org.junit.Test;
import org.junit.runner.RunWith;;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest(Test1.class)
public class LoginServletTest {
    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        TestInterface testInterface= new Test3();
       // PowerMockito.mockStatic(Test1.class);
       // Mockito.when(Test1.getInstance()).thenReturn(testInterface);

    }


}