package com.zjh.dao;

import com.zjh.bean.Approval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 13:05
 * @Description: 审批
 **/
public interface ApprovalDao {
    /**
     * 添加审批
     * @param approval
     * @return
     */
    int insertApproval(Approval approval);

    /**
     * 删除审批
     * @param approvals
     * @return
     */
    int deleteApprovals(List<Approval> approvals);

    /**
     * 修改审批
     * @param approval
     * @return
     */
    int updateApproval(Approval approval);

    /**
     * 查询所有审批
     * @return
     */
    List<Approval> selectApprovals();

    /**
     * 根据id查询审批
     * @param aid
     * @return
     */
    Approval selectApproval(Integer aid);

    /**
     * 分页查询审批
     * @param begin 起始索引，从0开始
     * @param size  每页大小
     * @return
     */
    List<Approval> selectApprovalsByLimit(@Param("begin") int begin, @Param("size") int size);

    /**
     * 获取审批数量
     * @return
     */
    int getApprovalsCount();

    /**
     * 根据学生获取审批数量
     * @param sid
     * @return
     */
    int getApprovalsCountBySid(int sid);

    /**
     * 根据学生进行分页查询
     * @param begin
     * @param size
     * @param sid
     * @return
     */
    List<Approval> selectApprovalsByLimitBySid(@Param("begin") int begin, @Param("size") Integer size, @Param("sid") Integer sid);

    /**
     * 获取等待审批的数量
     * @return
     */
    int getApprovalsCountByWait();

    /**
     * 分页查询等待审批
     * @param begin
     * @param size
     * @return
     */
    List<Approval> selectApprovalsByLimitByWait(@Param("begin") int begin, @Param("size") int size);

    /**
     * 未处理的，和前面的Wait相同
     * @return
     */
    Integer getApprovalsCountUntreated();
}
