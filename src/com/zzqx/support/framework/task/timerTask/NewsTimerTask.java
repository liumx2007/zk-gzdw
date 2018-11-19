package com.zzqx.support.framework.task.timerTask;

import java.util.List;

import com.zzqx.mvc.javabean.NewsCategory;
import com.zzqx.mvc.javabean.NewsInfo;
import com.zzqx.Global;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.News;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NewsTimerTask {
	
	public void doTask() {
		PropertiesHelper props = new PropertiesHelper("config");
		String autoScan = props.readValueTrim("timer.news.163");
		List<String> filterCategories = props.readList("interface.news.filter.category", ",");
		List<String> filterKeywords = props.readList("interface.news.filter.title", ",");
		Integer number = props.readInteger("interface.news.category.number");
		if("on".equalsIgnoreCase(autoScan)) {
			System.out.println("正在获取网易新闻数据...");
			News news = new News();
			String newsDataString = news.get();
			JSONObject newsDataObject = JSONObject.fromObject(newsDataString);
			String newsCategoriesString = newsDataObject.getString("category");
			String newsInfoString = newsDataObject.getString("news");
			Global.newsCategories.clear();
			Global.news.clear();
			JSONArray newsCategoriesArray = JSONArray.fromObject(newsCategoriesString);
			for(int i = 0; i < newsCategoriesArray.size(); i++) {
				NewsCategory newsCategory = new NewsCategory();
				JSONObject newsCategoryObject = newsCategoriesArray.getJSONObject(i);
				newsCategory.setCategoryName(newsCategoryObject.getString("n").toLowerCase());
				newsCategory.setIndex(i);
				newsCategory.setUrl(newsCategoryObject.getString("l"));
				Global.newsCategories.add(newsCategory);
			}
			JSONArray newsInfoByCategoryArray = JSONArray.fromObject(newsInfoString);
			for(int i = 0; i < newsInfoByCategoryArray.size(); i++) {
				//过滤新闻类别开始
				boolean a = false;
				for(String filterCategory : filterCategories) {
					for(NewsCategory newsCategory : Global.newsCategories) {
						if(newsCategory.getCategoryName().equalsIgnoreCase(filterCategory.trim().toLowerCase())) {
							a = true;
							break;
						}
					}
					if(a) break;
				}
				if(a) continue;
				//过滤新闻类别结束
				NewsCategory newsCategory = Global.newsCategories.get(i);
				JSONArray newsInfoArray = JSONArray.fromObject(newsInfoByCategoryArray.get(i));
				int num = 0;
				for(int k = 0; k < newsInfoArray.size(); k++) {
					if(number != null && number > 0 && number == num) break;
					NewsInfo newsInfo = new NewsInfo();
					JSONObject newsInfoObject = newsInfoArray.getJSONObject(k);
					newsInfo.setTitle(newsInfoObject.getString("t"));
					newsInfo.setUrl(newsInfoObject.getString("l"));
					newsInfo.setTime(newsInfoObject.getString("p"));
					newsInfo.setCategory(newsCategory);
					//过滤标题关键字开始
					boolean b = false;
					for(String filterKeyword : filterKeywords) {
						if(newsInfo.getTitle().toLowerCase().contains(filterKeyword.toLowerCase())) {
							b = true;
							break;
						}
					}
					if(b) continue;
					//过滤标题关键字结束
					//通过URL过滤图片新闻开始
					if(newsInfo.getUrl().contains("/photoview/")) continue;
					//通过URL过滤图片新闻结束
					Global.news.add(newsInfo);
					num++;
				}
			}
			System.out.println("成功获取网易新闻数据！");
		}
	}
}