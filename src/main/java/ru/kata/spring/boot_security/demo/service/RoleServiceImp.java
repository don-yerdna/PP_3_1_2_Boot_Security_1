//package ru.kata.spring.boot_security.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.dao.RoleDao;
//import ru.kata.spring.boot_security.demo.model.Role;
//
//@Service
//public class RoleServiceImp implements RoleService {
//    private final RoleDao roleDao;
//
//    @Autowired
//    public RoleServiceImp(RoleDao roleDao) {
//        this.roleDao = roleDao;
//    }
//
//    @Transactional
//    @Override
//    public void addRole(Role role) {
//        roleDao.addRole(role);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public Role findRoleByNameAndUserId(String roleName, Long userId) {
//        return roleDao.findRoleByNameAndUserId(roleName, userId);
//    }
//
//
//    @Transactional
//    @Override
//    public void deleteRole(String roleName, Long userId) {
//        roleDao.deleteRole(roleName, userId);
//    }
//
//    @Transactional
//    @Override
//    public void updateRole(Role role) {
//        roleDao.updateRole(role);
//    }
//}
