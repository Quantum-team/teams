package com.first.web; /**
 * ProjectName:   JavaWeb
 * PackageName:   ${PACKAGE_NAME}
 * ClassName:     ${NAME}
 * Description:  ${description}
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 9:50 AM
 */

import com.first.pojo.Brand;
import com.first.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "selectAllServlet", value = "/selectAllServlet")
public class selectAllServlet extends HttpServlet {
    private BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用BrandService完成查询
        List<Brand> brands = service.selectAll();
        System.out.println(brands);
        //2.存入request域中
        request.setAttribute("brands",brands);
        //3.转发到brand.jsp
        request.getRequestDispatcher("/brand.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
