package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbPlanGroup<M extends BaseTbPlanGroup<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setPlanId(java.lang.Long planId) {
		set("plan_id", planId);
		return (M)this;
	}
	
	public java.lang.Long getPlanId() {
		return getLong("plan_id");
	}

	public M setIsScheduled(java.lang.String isScheduled) {
		set("is_scheduled", isScheduled);
		return (M)this;
	}
	
	public java.lang.String getIsScheduled() {
		return getStr("is_scheduled");
	}

	public M setPlanGroupName(java.lang.String planGroupName) {
		set("plan_group_name", planGroupName);
		return (M)this;
	}
	
	public java.lang.String getPlanGroupName() {
		return getStr("plan_group_name");
	}

	public M setGroupStudentCount(java.lang.String groupStudentCount) {
		set("group_student_count", groupStudentCount);
		return (M)this;
	}
	
	public java.lang.String getGroupStudentCount() {
		return getStr("group_student_count");
	}

	public M setGroupTarget(java.lang.String groupTarget) {
		set("group_target", groupTarget);
		return (M)this;
	}
	
	public java.lang.String getGroupTarget() {
		return getStr("group_target");
	}

	public M setStatisMonth(java.lang.String statisMonth) {
		set("statis_month", statisMonth);
		return (M)this;
	}
	
	public java.lang.String getStatisMonth() {
		return getStr("statis_month");
	}

	public M setHostDepart(java.lang.String hostDepart) {
		set("host_depart", hostDepart);
		return (M)this;
	}
	
	public java.lang.String getHostDepart() {
		return getStr("host_depart");
	}

	public M setSecondDepart(java.lang.String secondDepart) {
		set("second_depart", secondDepart);
		return (M)this;
	}
	
	public java.lang.String getSecondDepart() {
		return getStr("second_depart");
	}

	public M setShortMessage(java.lang.String shortMessage) {
		set("short_message", shortMessage);
		return (M)this;
	}
	
	public java.lang.String getShortMessage() {
		return getStr("short_message");
	}

	public M setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
		return (M)this;
	}
	
	public java.lang.String getRemarks() {
		return getStr("remarks");
	}

}