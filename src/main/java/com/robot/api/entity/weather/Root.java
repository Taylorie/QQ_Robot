package com.robot.api.entity.weather;

/**
 * @author zhang.yu
 * @date 2022-09-11 23:30:38
 * @description
 */

import java.util.ArrayList;
import com.robot.api.entity.weather.List;

public class Root {
    private int data;

    private ArrayList<List> list;

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

//    public void setList(List<List> list) {
//        this.list = list;
//    }
//
//    public List<List> getList() {
//        return this.list;
//    }
}

