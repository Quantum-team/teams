package com.first.web; /**
 * ProjectName:   JavaWeb
 * PackageName:   ${PACKAGE_NAME}
 * ClassName:     ${NAME}
 * Description:  ${description}
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 7:18 PM
 */

import com.first.pojo.Brand;
import com.first.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "selectByIdServlet", value = "/selectByIdServlet")
public class selectByIdServlet extends HttpServlet {
    private BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Brand brand = service.selectById(Integer.parseInt(id));
        request.setAttribute("brand",brand);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
