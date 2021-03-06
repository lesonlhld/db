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
import model.User;
import service.RoleService;
import service.UserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/role/list" })
public class RoleListController extends HttpServlet {
	RoleService roleService = new RoleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		List<Role> roleList = roleService.getAll();
		req.setAttribute("roleList", roleList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/list-role.jsp");
		dispatcher.forward(req, resp);
	}

}
