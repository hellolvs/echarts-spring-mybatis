package com.qunar.echarts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author shuai.lv
 * @date 2017/06/28.
 */
public class FlightMinuteModel {

    private Long id;
//    @JsonSerialize(using = CustomDateTimeSerializer.class)
//    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
//    private DateTime datetime;
    private String datetime;
    private Integer orderNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
