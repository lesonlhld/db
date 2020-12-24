package controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Role;
import model.Payment;
import model.User;
import service.RoleService;
import service.UserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/role/edit" })
public class RoleEditController extends HttpServlet {
	RoleService cateService = new RoleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		Role role = cateService.get(Integer.parseInt(id));
		
		req.setAttribute("role", role);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/edit-role.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Role role = new Role();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		
		try {
			List<FileItem> items = servletFileUpload.parseRequest(req);

			for (FileItem item : items) {
				if (item.getFieldName().equals("id")) {
					role.setId(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("name")) {
					role.setName(item.getString("UTF-8"));
				}		
			}

			cateService.edit(role);

			resp.sendRedirect(req.getContextPath() + "/admin/role/list");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
