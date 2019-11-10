package com.lin.controller;

import com.lin.dao.DepartmentDao;
import com.lin.dao.EmployeeDao;
import com.lin.entities.Department;
import com.lin.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加员工
     * SpringMVC自动将请求参数和入参对象的属性一一绑定：要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
//        System.out.println(employee);
        employeeDao.save(employee);
        //redirect:表示重定向到一个地址   /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/emps";
    }

//    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = department.getDepartments();
        model.addAttribute("depts",departments);
        model.addAttribute("emp",employee);
        //回到修改页面（add是一个添加修改二合一）
        return "emp/add";
    }
}
