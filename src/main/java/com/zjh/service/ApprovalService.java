package com.zjh.service;

import com.zjh.bean.Approval;

import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 20:00
 * @Description: 排课Service
 **/
public interface ApprovalService {
    /**
     * 找所有的审批
     * @return
     */
    List<Approval> findAllApprovals();

    /**
     * 分页找所有审批
     * @param page
     * @param size
     * @return
     */
    List<Approval> findApprovalsByPage(int page, int size);

    /**
     * 获取审批总数
     * @return
     */
    int getApprovalsCount();

    /**
     * 删除指定审批
     * @param approvals
     * @return 删除成功的数量
     */
    int deleteApprovals(List<Approval> approvals);

    /**
     * 添加一个审批
     * @param approval
     */
    int addApproval(Approval approval);

    /**
     * 修改一个审批
     * @param approval
     * @return
     */
    int updateApproval(Approval approval);

    /**
     * 得到某个学生的审批数量
     * @param sid
     * @return
     */
    int getApprovalsCountBySid(Integer sid);

    /**
     * 分页得到某个学生的审批
     * @param page
     * @param size
     * @param sid
     * @return
     */
    List<Approval> findApprovalsByPageBySid(Integer page, Integer size, Integer sid);

    /**
     * 得到等待审批的审批的数量
     * @return
     */
    int getApprovalsCountByWait();

    /**
     * 分页得到等待审批的审批数量
     * @param page
     * @param size
     * @return
     */
    List<Approval> findApprovalsByPageByWait(Integer page, Integer size);

    /**
     * 得到未处理的审批的审批数量
     * @return
     */
    Integer getApprovalsCountUntreated();
}
