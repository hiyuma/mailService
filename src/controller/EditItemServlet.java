package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ItemDao;
import dao.LocationDao;
import domain.Item;
import domain.Location;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/editItem")
public class EditItemServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String strId = request.getParameter("id");
      Integer id = Integer.parseInt(strId);
      ItemDao itemDao = DaoFactory.createItemDao();
      Item item = itemDao.findById(id);
      request.setAttribute("name", item.getName());
      request.setAttribute("amount", item.getAmount());
      request.setAttribute("locationId", item.getLocationId());
      request.setAttribute("note", item.getNote());

      LocationDao locationDao = DaoFactory.createLocationDao();
      List<Location> locationList = locationDao.findAll();
      request.setAttribute("locationList", locationList);
      request.getRequestDispatcher("/WEB-INF/view/editItem.jsp")
          .forward(request, response);
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      LocationDao locationDao = DaoFactory.createLocationDao();
      List<Location> locationList = locationDao.findAll();
      request.setAttribute("locationList", locationList);

      String name = request.getParameter("name");
      String strAmount = request.getParameter("amount");
      Integer amount = null;
      Integer locationId = Integer.parseInt(request.getParameter("locationId"));
      String note = request.getParameter("note");

      request.setAttribute("name", name);
      request.setAttribute("amount", strAmount);
      request.setAttribute("locationId", locationId);
      request.setAttribute("note", note);

      // バリデーション
      boolean isError = false;
      if (name.isEmpty()) {
        isError = true;
        request.setAttribute("nameError", "※品名が未入力です。");
      }
      if (name.length() > 50) {
        isError = true;
        request.setAttribute("nameError", "※50文字以下で入力してください。");
      }

      if (strAmount.isEmpty()) {
        isError = true;
        request.setAttribute("amountError", "※数量が未入力です。");
      } else {
        try {
          amount = Integer.parseInt(strAmount);
          if (amount <= 0) {
            isError = true;
            request.setAttribute("amountError", "※1以上の数を入力してください。");
          }
        } catch (NumberFormatException e) {
          isError = true;
          request.setAttribute("amountError", "※整数を入力してください。");
        }
      }

      if (isError) {
        request.getRequestDispatcher("/WEB-INF/view/editItem.jsp")
            .forward(request, response);
        return;
      }

      // データの更新
      Integer id = Integer.parseInt(request.getParameter("id"));
      ItemDao itemDao = DaoFactory.createItemDao();
      Item item = itemDao.findById(id);
      item.setName(name);
      item.setAmount(amount);
      item.setLocationId(locationId);
      item.setNote(note);
      itemDao.update(item);

      // 完了ページへフォワード
      request.getRequestDispatcher("/WEB-INF/view/editItemDone.jsp")
          .forward(request, response);
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
