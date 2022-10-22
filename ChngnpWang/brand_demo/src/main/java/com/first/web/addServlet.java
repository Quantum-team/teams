package com.first.web; /**
 * ProjectName:   JavaWeb
 * PackageName:   ${PACKAGE_NAME}
 * ClassName:     ${NAME}
 * Description:  ${description}
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 3:39 PM
 */

import com.first.pojo.Brand;
import com.first.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "addServlet", value = "/addServlet")
public class addServlet extends HttpServlet {
    private BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String brand_name = request.getParameter("brand_name");
        String company_name = request.getParameter("company_name");
        String ordered = request.getParameter("ordered");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        Brand brand = new Brand();
        brand.setBrand_name(brand_name);
        brand.setCompany_name(company_name);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));

        service.add(brand);

        request.getRequestDispatcher("/selectAllServlet").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
