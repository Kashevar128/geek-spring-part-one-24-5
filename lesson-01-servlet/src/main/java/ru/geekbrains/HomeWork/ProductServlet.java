package ru.geekbrains.HomeWork;

import ru.geekbrains.HomeWork.products.Product;
import ru.geekbrains.HomeWork.products.ProductRepository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        productRepository.insert(new Product("Milk"));
        productRepository.insert(new Product("Meat"));
        productRepository.insert(new Product("Carrot"));
        productRepository.insert(new Product("Apple"));
        productRepository.insert(new Product("Egg"));
        productRepository.insert(new Product("Butter"));
        productRepository.insert(new Product("Fish"));
        productRepository.insert(new Product("Pear"));
        productRepository.insert(new Product("Lemon"));
        productRepository.insert(new Product("Banana"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");

        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>ProductName</th>");
        wr.println("</tr>");

        String strIndex = req.getPathInfo().substring(1);
        long index;
        if(isNumber(strIndex)) {
            index = Long.parseLong(strIndex);
        } else {
            wr.println("<p>Invalid number format, enter /number </p>");
            return;
        }

        try{
            if(index == 0) {
                for (Product product : productRepository.findAll()) printProduct(wr, product);
            } else {
                printProduct(wr, productRepository.findById(index));
            }
        }catch (Exception e) {
            wr.println("<p>The index does not exist</p>");
            return;
        }

        wr.println("</table>");

    }

    private void printProduct(PrintWriter writer, Product prd) {
        writer.println("<tr>");
        String strId = "<td>" + prd.getId() + "</td>";
        String strName = "<td>" + prd.getProductName() + "</td>";
        writer.println(strId);
        writer.println(strName);
        writer.println("<tr>");
    }

    private boolean isNumber(String strIndex) {
        try{
            Long.parseLong(strIndex);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

