package com.zjh.controller;

import com.zjh.bean.Approval;
import com.zjh.bean.Student;
import com.zjh.service.ApprovalService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-12-01 14:23
 * @Description: 审批
 **/
@Slf4j
@RestController
@RequestMapping("/approval")
public class ApprovalController {
    @Resource
    ApprovalService approvalService;

    /**
     * 查询审批
     * @param page  当前页码
     * @param limit  每页大小
     * @return 审批信息
     */
    @RequestMapping("queryApprovals.do")
    public PageResult queryApprovals(Integer page,Integer limit){
        //获取审批数量
        int count = approvalService.getApprovalsCount();
        //获取数据
        List<Approval> approvals=approvalService.findApprovalsByPage(page,limit);
        //返回结果
        return PageResult.success(
                count,approvals);
    }

    /**
     * 查询审批（未审批的）
     * @param page
     * @param limit
     * @return 审批信息
     */
    @RequestMapping("queryApprovalsByWait.do")
    public PageResult queryApprovalsByWait(Integer page,Integer limit){
        //获取审批数量
        int count = approvalService.getApprovalsCountByWait();
        //获取数据
        List<Approval> approvals = approvalService.findApprovalsByPageByWait(page, limit);
        //返回结果
        return PageResult.success(count,approvals);
    }

    /**
     * 根据学生id查询审批
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping({"queryApprovalsBySid.do", "student/queryApprovalsBySid.do"})
    public PageResult queryApprovalsBySid(Integer page, Integer limit, HttpServletRequest request){
        //获取当前账号信息
        Student loginStudent = (Student) request.getSession().getAttribute("loginObj");
        Integer sid = loginStudent.getSid();
        //获取审批数量
        int count = approvalService.getApprovalsCountBySid(sid);
        //获取数据
        List<Approval> approvals= approvalService.findApprovalsByPageBySid(page, limit, sid);
        //返回结果
        return PageResult.success(count,approvals);
    }

    /**
     * 查询所有审批
     * @return
     */
    @RequestMapping("queryAllApprovals.do")
    public PageResult queryAllApprovals(){
        //审批数量
        int count = approvalService.getApprovalsCount();
        //数据
        List<Approval> approvals = approvalService.findAllApprovals();
        //返回结果
        return PageResult.success(count,approvals);
    }

    /**
     * 删除审批
     * @param json 审批对象的json
     * @return 成功行数
     */
    @RequestMapping({"deleteApprovals.do", "student/deleteApprovals.do"})
    public Integer deleteApprovals(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';//如果不是数组形式，就变成数组形式
        }
        List<Approval> approvals = JsonUtil.parseList(json, Approval.class);
        return approvalService.deleteApprovals(approvals);
    }

    /**
     * 添加一个审批
     * @param json  审批对象的json
     * @return  成功标志1
     */
    @RequestMapping({"addApproval.do", "student/addApproval.do"})
    public Integer addApproval(String json){
        /**
         * 注意：使用lombok的时候，其中某些字段，比如eDte，sDate。生成的getter，setter方法好像不一样，
         * 导致json格式的字符串转换为实体类的时候不能赋值成功,所以改Approval实体类，并没有使用lombok。
         */
        Approval approval = JsonUtil.parseObject(json, Approval.class);
        return approvalService.addApproval(approval);
    }

    /**
     * 修改一个审批
     * @param json 审批的json对象
     * @return  成功标志1
     */
    @RequestMapping({"updateApproval.do", "student/updateApproval.do"})
    public Integer updateApproval(String json){
        Approval approval = JsonUtil.parseObject(json, Approval.class);
        return approvalService.updateApproval(approval);
    }

    /**
     * 获取审批总数
     * @return
     */
    @RequestMapping({"getAmount.do","student/getAmount.do"})
    public Integer getAmount(){
        return approvalService.getApprovalsCount();
    }

    @RequestMapping("student/getAmountByStudent.do")
    public Integer getAmountByStudent(String json){
        Integer sid=JsonUtil.parseObject(json,Integer.class);
        log.info("学生Id:"+sid);
        return approvalService.getApprovalsCountBySid(sid);
    }

    /**
     * 获取未处理的审批的总数
     * @return
     */
    @RequestMapping("getAmountUntreated.do")
    public Integer getAmountUntreated(){
        return approvalService.getApprovalsCountUntreated();
    }
}
