package com.robot.api.entity.weather;

/**
 * @author zhang.yu
 * @date 2022-09-11 23:30:03
 * @description
 */
public class List
{
    private String name;

    private int hot;

    private String url;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setHot(int hot){
        this.hot = hot;
    }
    public int getHot(){
        return this.hot;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
