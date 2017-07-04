package com.qunar.echarts.service;

import com.qunar.echarts.model.FlightMinuteModel;

import java.util.List;

/**
 * @author shuai.lv
 * @date 2017/06/28.
 */
public interface FlightMinuteService {

    FlightMinuteModel getByDateTime(String datetime);

    List<FlightMinuteModel> listByDay(String date);

//    List<FlightMinuteModel> listAll();
//
//    int save(FlightMinuteModel flightMinuteModel);
//
//    int removeByDateTime(DateTime datetime);
//
//    int update(FlightMinuteModel flightMinuteModel);

    int batchUpdate(List<FlightMinuteModel> list);

}
