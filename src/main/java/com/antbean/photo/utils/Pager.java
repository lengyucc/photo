package com.antbean.photo.utils;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {
	/** 总记录数 */
	private long totalRecd;
	/** 起始记录 */
	private int startPageIdx;
	/** 结束记录 */
	private int endPageIdx;
	/** 当前第一页 */
	private int currPageIdx;
	/** 每页显示的记录数 */
	private int pageSize = 10;
	/** 总页数 */
	private int totalPageNum;
	/** 数据集合 */
	private List<T> entityList = new ArrayList<T>();

	public Pager(long totalRecd, int currPageIdx, List<T> entityList, int pageSize) {
		this.totalRecd = totalRecd;
		this.currPageIdx = currPageIdx;
		this.pageSize = pageSize;
		this.entityList = entityList;

		setOtherParams();
	}

	private void setOtherParams() {
		this.totalPageNum = (int) Math.ceil(1.0 * totalRecd / pageSize);
		this.totalPageNum = this.totalPageNum == 0 ? 1 : this.totalPageNum;

		this.startPageIdx = this.currPageIdx - (this.pageSize / 2 - 1);
		this.endPageIdx = this.currPageIdx + this.pageSize / 2;
		if (this.startPageIdx < 1) {
			this.startPageIdx = 1;
			this.endPageIdx = pageSize;
			if (this.endPageIdx > this.totalPageNum) {
				this.endPageIdx = this.totalPageNum;
			}
		}
		if (this.endPageIdx > this.totalPageNum) {
			this.endPageIdx = this.totalPageNum;
			this.startPageIdx = endPageIdx - pageSize + 1;
			if (this.startPageIdx < 1) {
				this.startPageIdx = 1;
			}
		}
	}

	public void setTotalRecd(int totalRecd) {
		this.totalRecd = totalRecd;
	}

	public void setCurrPageIdx(int currPageIdx) {
		this.currPageIdx = currPageIdx;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	public long getTotalRecd() {
		return totalRecd;
	}

	public int getStartPageIdx() {
		return startPageIdx;
	}

	public int getEndPageIdx() {
		return endPageIdx;
	}

	public int getCurrPageIdx() {
		return currPageIdx;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public List<T> getEntityList() {
		return entityList;
	}
}