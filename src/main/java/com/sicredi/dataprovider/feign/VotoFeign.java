package com.sicredi.dataprovider.feign;

import com.sicredi.dataprovider.fallback.VotoFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${feign.name}", url = "${feign.url}", fallback = VotoFallback.class)
public interface VotoFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/{cpf}")
    Object validaCpf(@PathVariable("cpf") Long cpf);


}