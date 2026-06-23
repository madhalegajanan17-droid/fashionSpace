package com.fashionspace.controller;

import java.io.IOException;
import java.util.List;

import com.fashionspace.dao.CategoryDAO;
import com.fashionspace.dao.ProductDAO;
import com.fashionspace.daoimpl.CategoryDAOImpl;
import com.fashionspace.daoimpl.ProductDAOImpl;
import com.fashionspace.model.Category;
import com.fashionspace.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String categoryId = request.getParameter("category");
        String keyword = request.getParameter("search");
        String sort = request.getParameter("sort");

        List<Product> products;

        if (keyword != null && !keyword.trim().isEmpty()) {

            products = productDAO.searchProducts(keyword.trim());

        } else if (categoryId != null && !categoryId.trim().isEmpty()) {

            products = productDAO.getProductsByCategory(
                    Integer.parseInt(categoryId));

        } else {

            products = productDAO.getActiveProducts();
        }

        if (sort != null && !sort.trim().isEmpty()) {

            if ("newest".equals(sort)) {

                products.sort((p1, p2) ->
                        Integer.compare(p2.getProductId(), p1.getProductId()));

            } else if ("lowToHigh".equals(sort)) {

                products.sort((p1, p2) ->
                Double.compare(p1.getPrice(), p2.getPrice()));

            } else if ("highToLow".equals(sort)) {

                products.sort((p1, p2) ->
                Double.compare(p2.getPrice(), p1.getPrice()));
            }
        }

        List<Category> categories =
                categoryDAO.getAllCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("search", keyword);
        request.setAttribute("sort", sort);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}