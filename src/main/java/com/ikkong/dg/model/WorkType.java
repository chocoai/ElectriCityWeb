package com.ikkong.dg.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import com.ikkong.core.annotation.BindID;
import com.ikkong.core.model.BaseModel;

/**
 * Generated by Blade.
 * 2017-09-24 12:44:38
 */
@Table(name = "dg_worktype")
@BindID(name = "id")
@SuppressWarnings("serial")
public class WorkType extends BaseModel {
    	private Integer id;
    	private Integer check; //工种审核需要，0不需要审核，1需要审核
    	private Integer order_flag; //发单标记
    	private Integer version; //版本记录号
    	private String mark; //工种描述,100个汉字以内
    	private String name; //工种名称
    	private Integer times;// 选择次数
    	private Double over_money; //加班酬劳
    	private Double salary; //工种每天酬劳，整数表示每天多少钱，如果是百分比表示占总额比例

    	@AutoID
    	public Integer getId() {
    		return id;
    	}

    	public void setId(Integer id) {
    		this.id = id;
    	}

    	public Integer getCheck() {
    		return check;
    	}

    	public void setCheck(Integer check) {
    		this.check = check;
    	}

    	public Integer getOrder_flag() {
    		return order_flag;
    	}

    	public void setOrder_flag(Integer order_flag) {
    		this.order_flag = order_flag;
    	}

    	public Integer getVersion() {
    		return version;
    	}

    	public void setVersion(Integer version) {
    		this.version = version;
    	}

    	public String getMark() {
    		return mark;
    	}

    	public void setMark(String mark) {
    		this.mark = mark;
    	}

    	public String getName() {
    		return name;
    	}

    	public void setName(String name) {
    		this.name = name;
    	}

    	public Double getOver_money() {
    		return over_money;
    	}

    	public void setOver_money(Double over_money) {
    		this.over_money = over_money;
    	}

    	public Double getSalary() {
    		return salary;
    	}

    	public void setSalary(Double salary) {
    		this.salary = salary;
    	}

		public Integer getTimes() {
			return times;
		}

		public void setTimes(Integer times) {
			this.times = times;
		}
}
