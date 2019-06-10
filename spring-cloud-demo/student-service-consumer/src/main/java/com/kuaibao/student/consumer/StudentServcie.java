package com.kuaibao.student.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "student-service-provider")
public interface StudentServcie {
    @RequestMapping(value = "/stu/getName" ,method = RequestMethod.GET)
    String getName();

    @RequestMapping(value = "/stu/getAge" ,method = RequestMethod.GET)
    Integer getAge();
}
