package com.lin.controller;

import com.lin.dao.DepartmentDao;
import com.lin.dao.EmployeeDao;
import com.lin.entities.Department;
import com.lin.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao department;

    //查询所有员工，返回员工列表页面
//    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf默认拼串
        //classpath:/templates/xxx.html
        return "emp/list";
    }

    /**
     * 来到员工添加页面
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //先查出所有的部门，在页面显示
        Collection<Department> departments = department.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
}
