package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Answer;

public interface AnswerService extends BaseService<Answer> {
	
	int getMinSort(String subjectId);
	int getMaxSort(String subjectId);
	Answer getCloseSmall(String id);
	Answer getCloseLarge(String id);
	
}