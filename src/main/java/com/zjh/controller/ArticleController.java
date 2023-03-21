package com.zjh.controller;

import com.zjh.bean.Article;
import com.zjh.service.ArticleService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 12:47
 * @Description: 文章
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    ArticleService articleService;

    /**
     * 分页查询文章
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping({"queryArticles.do", "student/queryArticles.do","teacher/queryArticles.do"})
    public PageResult queryArticles(Integer page,Integer limit){
        //获取文章数量
        int count= articleService.getArticlesCount();
        //获取数据
        List<Article> articles = articleService.findArticlesByPage(page, limit);
        //返回数据
        return PageResult.success(count,articles);
    }

    /**
     * 查询所有文章
     * @return  文章信息
     */
    @RequestMapping("queryAllArticles.do")
    public List<Article> queryAllArticles(){
        return articleService.findAllArticles();
    }

    /**
     * 删除文章
     * @param json  文章对象的json
     * @return 成功行数
     */
    @RequestMapping("deleteArticles.do")
    public Integer deleteArticles(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';
        }
        List<Article> articles = JsonUtil.parseList(json, Article.class);
        return articleService.deleteArticles(articles);
    }

    /**
     * 添加一个文章
     * @param json 文章对象的json
     * @return 成功为1
     */
    @RequestMapping("addArticle.do")
    public Integer addArticle(String json){
        Article article = JsonUtil.parseObject(json, Article.class);
        return articleService.addArticle(article);
    }

    /**
     * 修改一个文章
     * @param json 文章对象的json
     * @return 成功为1
     */
    @RequestMapping("updateArticle.do")
    public Integer updateArticle(String json){
        Article article = JsonUtil.parseObject(json, Article.class);
        return articleService.updateArticle(article);
    }

    /**
     * 获取文章总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return articleService.getArticlesCount();
    }

    /**
     * 搜索文章
     * @param page
     * @param limit
     * @param json 搜索参数的json （"title":文章标题,"date":日期）
     * @return 文章信息
     */
    @RequestMapping({"searchArticles.do", "student/searchArticles.do", "teacher/searchArticles.do"})
    public PageResult searchArticles(Integer page,Integer limit,String json){
        //获取查询参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class,Object.class);
        //获取查询个数
        int count = articleService.getSearchCount(searchParam);
        //获取数据
        List<Article> articles = articleService.searchArticles(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,articles);
    }

}
