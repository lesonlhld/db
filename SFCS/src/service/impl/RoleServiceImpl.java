package service.impl;

import java.util.List;

import dao.RoleDao;
import dao.impl.RoleDaoImpl;
import model.Role;
import service.RoleService;

public class RoleServiceImpl implements RoleService {
	RoleDao roleDao = new RoleDaoImpl();

	@Override
	public void insert(Role role) {
		roleDao.insert(role);

	}

	@Override
	public void edit(Role newRole) {
		Role oldCate = roleDao.get(newRole.getId());
		oldCate.setName(newRole.getName());
		roleDao.edit(oldCate);

	}

	@Override
	public void delete(int id) {
		roleDao.delete(id);

	}

	@Override
	public Role get(int id) {
		return roleDao.get(id);
	}

	@Override
	public Role get(String name) {
		return roleDao.get(name);
	}

	@Override
	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Override
	public List<Role> search(String username) {
		return roleDao.search(username);
	}
}