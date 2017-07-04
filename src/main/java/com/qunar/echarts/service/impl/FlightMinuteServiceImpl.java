package com.qunar.echarts.service.impl;

import com.qunar.echarts.dao.FlightMinuteDao;
import com.qunar.echarts.model.FlightMinuteModel;
import com.qunar.echarts.service.FlightMinuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuai.lv
 * @date 2017/06/28.
 */
@Service
public class FlightMinuteServiceImpl implements FlightMinuteService {

    //private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private FlightMinuteDao flightMinuteDao;

    @Override
    public FlightMinuteModel getByDateTime(String datetime) {
        return flightMinuteDao.getByDateTime(datetime);
    }

    @Override
    public List<FlightMinuteModel> listByDay(String date) {
        return flightMinuteDao.listByDay(date);
    }

    @Override
    public int batchUpdate(List<FlightMinuteModel> list) {
        return flightMinuteDao.batchUpdate(list);
    }
}
