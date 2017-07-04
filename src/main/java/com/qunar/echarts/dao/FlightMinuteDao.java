package com.qunar.echarts.dao;

import com.qunar.echarts.model.FlightMinuteModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuai.lv
 * @date 2017/06/28.
 */
@Repository
public interface FlightMinuteDao {

    FlightMinuteModel getByDateTime(String datetime);

    List<FlightMinuteModel> listByDay(String date);

    List<FlightMinuteModel> listAll();

    int save(FlightMinuteModel flightMinuteModel);

    int removeByDateTime(FlightMinuteModel flightMinuteModel);

    int update(FlightMinuteModel flightMinuteModel);

    int batchUpdate(List<FlightMinuteModel> list);

}
