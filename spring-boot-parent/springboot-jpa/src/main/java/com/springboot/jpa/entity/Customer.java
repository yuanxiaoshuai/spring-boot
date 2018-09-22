package com.springboot.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @NoArgsConstructor ： 生成一个无参数的构造方法
 * @AllArgsContructor： ?会生成一个包含所有变量
* @ClassName: Customer 
* @Description:  
* @author Alex-晓帅
* @date 2018年9月21日 下午4:14:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "b_customer")
public class Customer extends BastEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}