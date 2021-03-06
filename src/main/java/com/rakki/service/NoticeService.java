package com.rakki.service;

import java.util.List;

import com.rakki.domain.Criteria;
import com.rakki.domain.NoticeVo;

public interface NoticeService {
	List<NoticeVo> getList();
	void register(NoticeVo noticeVo);
	NoticeVo get(Long nno);
	boolean modify(NoticeVo noticeVo);
	boolean remove(Long nno);
	List<NoticeVo> getList(Criteria cri);
	int getTotal(Criteria cri);
}