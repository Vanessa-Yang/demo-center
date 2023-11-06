package org.example.design.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Prototype pattern provides a mechanism to copy the original object to a new object
 * and then modify it according to our needs.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 9:10
 **/
public class Employees implements Cloneable {

    private List<String> empList;

    public Employees() {
        empList = new ArrayList<>();
    }

    public Employees(List<String> list) {
        this.empList = list;
    }

    public void loadData() {
        // read all employees from database and put into the list
        empList.add("Pankaj");
        empList.add("Raj");
        empList.add("David");
        empList.add("Lisa");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<>();
        for (String s : this.empList) {
            temp.add(s);
        }
        return new Employees(temp);
    }
}
