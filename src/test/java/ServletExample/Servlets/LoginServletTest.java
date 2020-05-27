package ServletExample.Servlets;


import ServletExample.Logic.DbStore;
import ServletExample.Logic.Store;
import ServletExample.Logic.Validate;
import ServletExample.Logic.ValidateService;
import ServletExample.Model.User;
import ServletExample.Servlets.TestInstance.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class LoginServletTest {
    public Validate validate;
    public AddUserServlet addUserServlet;
    @Before
    public void checkAdd() throws IOException, ServletException {
        validate = new ValidateTester();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest reqToAdd = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(reqToAdd.getParameter("name")).thenReturn("Vova Vova");
        when(reqToAdd.getParameter("login")).thenReturn("vova12");
        when(reqToAdd.getParameter("email")).thenReturn("vova12@log.com");
        addUserServlet = new AddUserServlet() {
            public ServletContext getServletContext(){
                return mock(ServletContext.class);
            }
        };
        addUserServlet.doPost(reqToAdd, resp);
    }

    @Test
    public void whenAddUserThenStoreIt() {
        HttpServletRequest reqToGet = mock(HttpServletRequest.class);
        List<User> userList = (List) validate.process(reqToGet,"all").get();
        assertThat(userList.iterator().next().getName(), is("Vova Vova"));
    }

    @Test
    public void whenRemoveUser() throws ServletException, IOException {
        HttpServletRequest reqToGet = mock(HttpServletRequest.class);
        RemoveUserServlet removeUserServlet = new RemoveUserServlet(){
            public ServletContext getServletContext() {
                return mock(ServletContext.class);
            };
        };
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<User> userList = (List) validate.process(reqToGet,"all").get();
        assertThat(userList.iterator().next().getName(), is("Vova Vova"));
        removeUserServlet.doGet(reqToGet,resp);
        assertThat(userList.iterator().hasNext(), is(false));
    }

    @Test
    public void whenUpdateUser() throws ServletException, IOException {
        HttpServletRequest reqToGet = mock(HttpServletRequest.class);
        when(reqToGet.getParameter("name")).thenReturn("Andy Andy");
        when(reqToGet.getParameter("login")).thenReturn("vova12");
        when(reqToGet.getParameter("email")).thenReturn("vova12@log.com");
        when(reqToGet.getParameter("id")).thenReturn("0");
        when(reqToGet.getParameter("key")).thenReturn("update");
        HttpServletResponse resp = mock(HttpServletResponse.class);
        UpdateUserServlet updateUserServlet = new UpdateUserServlet();
        List<User> userList = (List) validate.process(reqToGet,"all").get();
        assertThat(userList.iterator().next().getName(), is("Vova Vova"));
        updateUserServlet.doPost(reqToGet,resp);
        assertThat(userList.iterator().next().getName(), is("Andy Andy"));
    }


}