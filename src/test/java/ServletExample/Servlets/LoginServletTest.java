package ServletExample.Servlets;


import ServletExample.Logic.DbStore;
import ServletExample.Logic.Store;
import ServletExample.Logic.Validate;
import ServletExample.Logic.ValidateService;
import ServletExample.Servlets.TestInstance.DbStoreTest;
import org.junit.jupiter.api.Test;
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
@PrepareForTest(DbStore.class)
public class LoginServletTest {
    @Test
    public void Test1() throws ServletException, IOException {
        Store dbstore = new DbStoreTest();
        PowerMockito.mockStatic(DbStore.class);
        Mockito.when(DbStore.getInstance()).thenReturn(dbstore);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("user")).thenReturn("admin");
        when(req.getParameter("pwd")).thenReturn("0000");
        new LoginServlet().doPost(req, resp);
        //assertThat(resp.)
        for (Cookie x : req.getCookies()) {
            System.out.println("Name = " + x.getName() + " Value = "+ x.getValue());
        }
        System.out.println();
        //assertThat(validate.getAll().iterator().next().getName(), is("Petr Arsentev"));

    }
}
