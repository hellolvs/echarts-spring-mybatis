package com.qunar.echarts.controller;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.qunar.echarts.model.FlightMinuteModel;
import com.qunar.echarts.service.FlightMinuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qunar.web.spring.annotation.JsonBody;

import java.util.List;

/**
 * @author shuai.lv
 * @date 2017/06/28.
 */
@Controller
@RequestMapping("/flight_minute_orders")
public class FlightMinuteController {

    private static final Logger LOG = LoggerFactory.getLogger(FlightMinuteController.class);

    @Autowired
    private FlightMinuteService flightMinuteService;

    @RequestMapping(value = "/datetime/{datetime}", method = { RequestMethod.GET })
    @JsonBody
    public FlightMinuteModel getByDateTime(@PathVariable("datetime") String datetime){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(datetime));
        return flightMinuteService.getByDateTime(datetime);
    }

    @RequestMapping(value = "/date/{date}", method = { RequestMethod.GET })
    @JsonBody
    public List<FlightMinuteModel> listByDay(@PathVariable("date") String date){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(date));
        return flightMinuteService.listByDay(date);
    }

    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    @JsonBody
    public int batchUpdate(@RequestBody List<FlightMinuteModel> list){
        Preconditions.checkNotNull(list);
        return flightMinuteService.batchUpdate(list);
    }

    /* 异常处理，输出异常信息 */
    @ExceptionHandler(RuntimeException.class)
    @JsonBody
    public void handlerRuntimeException(RuntimeException e) {
        LOG.error("Controller运行时异常：{}", e.getMessage(), e);
        throw e;
    }
}
