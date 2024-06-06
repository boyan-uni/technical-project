package com.boyan.controller.json;

import com.boyan.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * JSON 参数 接收
 */
@Controller
@RequestMapping("json")
@ResponseBody
public class JsonController {

    @RequestMapping("person")
    public String addPerson(@RequestBody Person person) {
        return person.toString();
    }

}
