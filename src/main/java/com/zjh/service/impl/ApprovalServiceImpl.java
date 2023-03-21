package com.zjh.service.impl;

import com.zjh.bean.Approval;
import com.zjh.bean.Student;
import com.zjh.dao.ApprovalDao;
import com.zjh.dao.StudentDao;
import com.zjh.service.ApprovalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 20:04
 * @Description: 审批实现类,他的bean比表多了一个学生姓名
 **/
@Service
public class ApprovalServiceImpl implements ApprovalService {
    @Resource
    ApprovalDao approvalDao;
    @Resource
    StudentDao studentDao;


    //为单个审批添加学生姓名
    private void addStudentNameForApproval(Approval approval) {
        Integer sid = approval.getSid();
        if (sid!=null){
            Student student = studentDao.selectStudent(sid);
            if (student!=null){
                approval.setSname(student.getSname());
            }
        }
    }

    //为审批列表添加学生姓名
    private void addStudentNameForApprovals(List<Approval> approvals) {
        for (Approval approval:approvals){
            //为单个审批添加学生姓名
            addStudentNameForApproval(approval);
        }
    }

    @Override
    public List<Approval> findAllApprovals() {
        List<Approval> approvals = approvalDao.selectApprovals();
        //为审批列表添加学生姓名
        addStudentNameForApprovals(approvals);
        return approvals;
    }

    @Override
    public List<Approval> findApprovalsByPage(int page, int size) {
        int begin = (page-1) * size;
        List<Approval> approvals = approvalDao.selectApprovalsByLimit(begin, size);
        addStudentNameForApprovals(approvals);
        return approvals;
    }

    @Override
    public int getApprovalsCount() {
        return approvalDao.getApprovalsCount();
    }

    @Override
    public int deleteApprovals(List<Approval> approvals) {
        return approvalDao.deleteApprovals(approvals);
    }

    @Override
    public int addApproval(Approval approval) {
        return approvalDao.insertApproval(approval);
    }

    @Override
    public int updateApproval(Approval approval) {
        return approvalDao.updateApproval(approval);
    }

    @Override
    public int getApprovalsCountBySid(Integer sid) {
        return approvalDao.getApprovalsCountBySid(sid);
    }

    @Override
    public List<Approval> findApprovalsByPageBySid(Integer page, Integer size, Integer sid) {
        int begin=(page-1) * size;
        List<Approval> approvals = approvalDao.selectApprovalsByLimitBySid(begin, size, sid);
        addStudentNameForApprovals(approvals);
        return approvals;
    }

    @Override
    public int getApprovalsCountByWait() {
        return approvalDao.getApprovalsCountByWait();
    }

    @Override
    public List<Approval> findApprovalsByPageByWait(Integer page, Integer size) {
        int begin=(page-1)*size;
        List<Approval> approvals = approvalDao.selectApprovalsByLimitByWait(begin, size);
        addStudentNameForApprovals(approvals);
        return approvals;
    }

    @Override
    public Integer getApprovalsCountUntreated() {
        return approvalDao.getApprovalsCountUntreated();
    }
}
