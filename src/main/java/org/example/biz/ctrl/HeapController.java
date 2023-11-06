package org.example.biz.ctrl;

import org.example.biz.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/9/7 0007 9:41
 **/
@RestController
public class HeapController {

    @RequestMapping("/heap")
    public void heap() {
        List<Person> lists = new ArrayList<>();
        while (true) {
            int i = 0;
            lists.add(new Person(i++, UUID.randomUUID().toString()));
        }
    }
}
