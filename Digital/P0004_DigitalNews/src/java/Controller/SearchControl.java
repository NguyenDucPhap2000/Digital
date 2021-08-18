/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Context.DBContext;
import DAO.DigitalDAO;
import Entity.Digital;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class SearchControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DBContext bContext = new DBContext();
            String imagePath = bContext.getImagePath();
            request.setAttribute("imagePath", imagePath);

            String txtSearch = request.getParameter("txtSearch");
            if (txtSearch.isEmpty()) {
                response.sendRedirect("SearchControl?index=1&txtSearch=null");
            }
            DigitalDAO digitalDAO = new DigitalDAO();
            int count = digitalDAO.count(txtSearch);
            int pageSize = 3;
            int endPage = count / pageSize;

            //number of news divide pagesize is not equal 0 , number of pages increase 1
            if (count % pageSize != 0) {
                endPage++;
            }
            int index = 0;
            //check index take from SearchResultPage is number or characters
            try {
                index = Integer.parseInt(request.getParameter("index"));
            } catch (Exception e) {
                response.sendRedirect("SearchControl?index=" + (endPage + 1) + "&txtSearch=" + txtSearch + "");
            }

            // List searched in database pageSize is each page there are 3 news, index is positon of page default = 1
            List<Digital> listDigital = digitalDAO.search(txtSearch, pageSize, index);

            //up data to jsp page
            request.setAttribute("endpage", endPage);
            request.setAttribute("list", listDigital);
            request.setAttribute("save", txtSearch);
            request.setAttribute("index", index);

            Digital top1 = digitalDAO.getTop1();
            request.setAttribute("top1", top1);
            List<Digital> listTop5 = digitalDAO.getTop5();

            // set data to right.jsp
            request.setAttribute("top5", listTop5);
            request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
