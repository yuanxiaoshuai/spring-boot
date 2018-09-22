package com.springboot.jpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.dao.CustomerRepository;
import com.springboot.jpa.entity.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
* @ClassName: CustmoerController 
* @Description: 用户管理控制层
* @author Alex-晓帅
* @date 2018年9月21日 下午4:47:19
 */
@Api(value="用户管理",tags={"用户管理接口"})
@RestController
@Repository(value="/custmoer")
public class CustmoerController {

    @Autowired
    private CustomerRepository repository;

    /**
     * 初始化数据
     */
    @ApiOperation(value="添加初始化值")
    @PostMapping("/index")
    public void index() {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
        repository.save(new Customer("Bauer", "Dessler"));
    }

    /**
     * 查询所有
     */
    @ApiOperation(value="查询所有")
    @PostMapping("/findAll")
    public @ResponseBody Object  findAll(){
        List<Customer> result = repository.findAll();
        return result;
    }

    /**
     * 查询ID为1的数据
     */
    @ApiOperation(value="删除数据")
    @PostMapping("/delete")
    public void delete(){

        System.out.println("删除前数据：");
        List<Customer> customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }

        System.out.println("删除ID=3数据：");
        repository.deleteById(3l);

        System.out.println("删除后数据：");
        customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 查询ID为1的数据
     */
    @ApiOperation(value="查询ID=1的数据")
    @PostMapping("/findOne")
    public  @ResponseBody Object  findOne(){
        Optional<Customer> result = repository.findById(1L);
        return result;
    }
    /**
     * 查询lastName为指定用户昵称
     */
    @ApiOperation(value="查询lastName为指定用户昵称")
    @PostMapping("/findByLastName")
    public @ResponseBody Object findByLastName(){
        List<Customer> result = repository.findByLastName("Bauer");
        return result;
    }

    /**
     * 查询FirstName为指定用户昵称
     */
    @ApiOperation(value="查询FirstName为指定用户昵称")
    @PostMapping("/findByFirstName")
    public @ResponseBody Object findByFirstName(){
        Customer customer = repository.findByFirstName("Bauer");
        return customer;
    }

    /**
     * @Query注解方式查询
     * 查询FirstName为指定字符串
     */
    @ApiOperation(value="查询FirstName为指定字符串")
    @PostMapping("/findByFirstName2")
    public  @ResponseBody Object findByFirstName2(){
        Customer customer = repository.findByFirstName2("Bauer");
        return customer;
    }

    /**
     * @Query注解方式查询
     * 查询LastName为指定字符串
     */
    @ApiOperation(value="查询LastName为指定字符串")
    @PostMapping("/findByLastName2")
    public @ResponseBody Object findByLastName2(){
        List<Customer> result = repository.findByLastName2("Bauer");
        return result;
    }

    /**
     * @Query注解方式查询,
     * 用@Param指定参数，匹配firstName和lastName
     */
    @ApiOperation(value="用@Param指定参数，匹配firstName和lastName")
    @PostMapping("/findByName")
    public @ResponseBody Object  findByName(){
        List<Customer> result = repository.findByName("Bauer");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        return result;
    }

    /**
     * @Query注解方式查询,使用关键词like
     * 用@Param指定参数，firstName的结尾为e的字符串
     */
    @ApiOperation(value="用@Param指定参数，firstName的结尾为e的字符串")
    @PostMapping("/findByName2")
    public @ResponseBody Object  findByName2(){
        List<Customer> result = repository.findByName2("e");
        return result;
    }

    /**
     * @Query注解方式查询，模糊匹配关键字e
     */
    @ApiOperation(value="模糊匹配关键字e")
    @PostMapping("/findByName3")
    public @ResponseBody Object  findByName3(){
        List<Customer> result = repository.findByName3("e");
        return result;
    }

    /**
     * @Query注解方式查询,
     * 用@Param指定参数，匹配firstName和lastName
     */
    @ApiOperation(value="匹配firstName和lastName")
    @PostMapping("/findByName4")
    public  @ResponseBody Object  findByName4(){
        //按照ID倒序排列
        System.out.println("直接创建sort对象，通过排序方法和属性名");
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Customer> result = repository.findByName4("Bauer",sort);
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
        //按照ID倒序排列
        System.out.println("通过Sort.Order对象创建sort对象");
        Sort sortx = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        List<Customer> resultx = repository.findByName4("Bauer",sort);
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");

        System.out.println("通过排序方法和属性List创建sort对象");
        List<String> sortProperties = new ArrayList<>();
        sortProperties.add("id");
        sortProperties.add("firstName");
        Sort sort2 = new Sort(Sort.Direction.DESC,sortProperties);
        List<Customer> result2 = repository.findByName4("Bauer",sort2);
        for (Customer customer:result2){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");

        System.out.println("通过创建Sort.Order对象的集合创建sort对象");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"firstName"));
        List<Customer> result3 = repository.findByName4("Bauer",new Sort(orders));
        for (Customer customer:result3){
            System.out.println(customer.toString());
        }
        return result3;
    }

    /**
     * 根据FirstName进行修改
     */
    @ApiOperation(value="根据FirstName进行修改")
    @PostMapping("/modifying")
    public @ResponseBody Object   modifying(){
        Integer result = repository.setFixedFirstnameFor("Bauorx","Bauer");
        return result;
    }
    /**
     * 分页
     * 应用查询提示@QueryHints，这里是在查询的适合增加了一个comment
     * 查询结果是lastName和firstName都是bauer这个值的数据
     */
    @ApiOperation(value="分页")
    @PostMapping("/pageable")
    public @ResponseBody Object   pageable(){
        //Pageable是接口，PageRequest是接口实现
        //PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，后两个参数参考Sort对象的构造方法
        Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
        Page<Customer> page = repository.findByName("bauer",pageable);
        //查询结果总行数
        System.out.println(page.getTotalElements());
        //按照当前分页大小，总页数
        System.out.println(page.getTotalPages());
        //按照当前页数、分页大小，查出的分页结果集合
        for (Customer customer: page.getContent()) {
            System.out.println(customer.toString());
        }
        return page;
    }

}
