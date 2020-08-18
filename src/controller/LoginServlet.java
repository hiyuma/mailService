package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import domain.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/view/login.jsp")
    .forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String loginId = request.getParameter("loginId");
      String loginPass = request.getParameter("loginPass");

      request.setAttribute("loginId", loginId);

      // バリデーション
      boolean isError = false;
      if (loginId.isEmpty()) {
        isError = true;
        request.setAttribute("loginIdError", "※ログインIDが未入力です。");
      }
      if (loginPass.isEmpty()) {
        isError = true;
        request.setAttribute("loginPassError", "※パスワードが未入力です。");
      }
      if (isError) {
          request.getRequestDispatcher("/WEB-INF/view/login.jsp")
              .forward(request, response);
          return;
      }

      UserDao userDao = DaoFactory.createUserDao();
      User user = userDao.findByLoginIdAndLoginPass(loginId, loginPass);
      if (user != null) {
        request.getSession().setAttribute("userName", user.getName());
        response.sendRedirect("listItem");
      } else {
        request.setAttribute("error", true);
        request.getRequestDispatcher("/WEB-INF/view/login.jsp")
          .forward(request, response);
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
