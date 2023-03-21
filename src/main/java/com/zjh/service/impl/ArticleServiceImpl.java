package com.zjh.service.impl;

import com.zjh.bean.Article;
import com.zjh.dao.ArticleDao;
import com.zjh.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 20:42
 * @Description: 文章实现类
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleDao articleDao;

    @Override
    public List<Article> findAllArticles() {
        return articleDao.selectArticles();
    }

    @Override
    public List<Article> findArticlesByPage(Integer page, Integer size) {
        int begin = (page - 1) * size;
        return articleDao.selectArticlesByLimit(begin,size);
    }

    @Override
    public int getArticlesCount() {
        return articleDao.getArticlesCount();
    }

    @Override
    public int deleteArticles(List<Article> articles) {
        return articleDao.deleteArticles(articles);
    }

    @Override
    public int addArticle(Article article) {
        return articleDao.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

    @Override
    public List<Article> searchArticles(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin = (page - 1) * size;
        //在搜索的基础上添加2个参数
        Map<String,Object> map = searchParam;
        map.put("begin", begin);
        map.put("size", size);
        List<Article> articles = articleDao.searchArticlesByLimit(map);
        return articles;
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return articleDao.getSearchCount(searchParam);
    }
}
