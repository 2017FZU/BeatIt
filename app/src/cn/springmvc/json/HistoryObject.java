package cn.springmvc.json;

import java.util.List;

public class HistoryObject {
	private int score;
	private String comment;
	private List<?>workimg;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<?> getWorkimg() {
		return workimg;
	}
	public void setWorkimg(List<?> workimg) {
		this.workimg = workimg;
	}
}
