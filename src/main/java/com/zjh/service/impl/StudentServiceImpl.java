package com.zjh.service.impl;

import com.zjh.bean.Clazz;
import com.zjh.bean.Student;
import com.zjh.dao.ClazzDao;
import com.zjh.dao.StudentDao;
import com.zjh.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 21:55
 * @Description: StudentService
 **/
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentDao studentDao;
    @Resource
    ClazzDao clazzDao;

    /**
     * 为学生添加班级名字信息
     *
     * @param student
     */
    private void addClazzNameForStudent(Student student) {
        Integer cid = student.getCid();
        if (cid != null) {
            Clazz clazz = clazzDao.selectClazz(cid);
            if (clazz != null) {
                student.setCname(clazz.getCname());
            }
        }
    }

    /**
     * 为班级列表添加班级名字信息
     *
     * @param students
     */
    private void addClazzNameForStudents(List<Student> students) {
        for (Student student : students) {
            addClazzNameForStudent(student);
        }
    }

    @Override
    public List<Student> findStudentsByPage(int page, int size) {
        int begin = (page - 1) * size;
        List<Student> students = studentDao.selectStudentsByLimit(begin, size);
        //为学生添加班级名字信息，Student相比于表多了一个班级名字
        addClazzNameForStudents(students);
        return students;
    }


    @Override
    public int getStudentsCount() {
        return studentDao.getStudentsCount();
    }

    @Override
    public int deleteStudents(List<Student> students) {
        return studentDao.deleteStudents(students);
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public List<Student> searchStudents(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin = (page - 1) * size;
        //在传进来的搜索参数的基础上面，添加两个参数
        Map<String,Object> map=searchParam;
        map.put("begin",begin);
        map.put("size",size);
        List<Student> students = studentDao.searchStudentsByLimit(map);
        addClazzNameForStudents(students);
        return students;
    }

    @Override
    public List<Student> searchStudentsByTeacher(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin = (page - 1) * size;
        //添加三个参数，与上面不同的的是，还要添加老师id(searchParams里面有)
        Map<String,Object> map=searchParam;
        map.put("begin",begin);
        map.put("size",size);
        List<Student> students = studentDao.searchStudentsByLimitByTeacher(map);
        //添加班级信息
        addClazzNameForStudents(students);
        return students;
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return studentDao.getSearchCount(searchParam);
    }

    @Override
    public int getSearchCountByTeacher(Map<String, Object> searchParam) {
        return studentDao.getSearchCountByTeacher(searchParam);
    }

    @Override
    public int getStudentsCountByTeacher(int tid) {
        return studentDao.getStudentsCountByTeacher(tid);
    }

    @Override
    public List<Student> findStudentsByPageByTeacher(Integer page, Integer size, int tid) {
        int begin=(page - 1)*size;
        List<Student> students = studentDao.selectStudentsByLimitByTeacher(begin, size, tid);
        //为学生添加班级信息
        addClazzNameForStudents(students);
        return students;
    }

    @Override
    public List<Student> findStudentsByOid(int oid) {
        List<Student> students = studentDao.selectStudentsByOid(oid);
        addClazzNameForStudents(students);
        return students;
    }

    @Override
    public Student findStudentBySid(Integer sid) {
        Student student = studentDao.selectStudent(sid);
        addClazzNameForStudent(student);
        return student;
    }

    @Override
    public String print(HttpServletRequest req) {
        //获取全部学生信息
        List<Student> students = studentDao.selectStudents();

        FileWriter fw = null;
        String fileName=null;
        try {
            //获取地址.来获取网站的物理路径。例如myWeb项目的物理路径被配置在E：/aaa下，那么我们使用getRealPath()得到的就是“E：/aaa”。
            String path=req.getSession().getServletContext().getRealPath("report");
            File pathFile=new File(path);
            if (!pathFile.exists()){
                //创建目录
                boolean flag=pathFile.mkdirs();
                if (!flag){
                    throw new RuntimeException("创建文件失败");
                }
            }
            //文件地址，文件名是当前的时间戳
            Long curTime=System.currentTimeMillis();
            fileName=curTime+".csv";
            String filePath=path+"/"+fileName;
            //创建文件
            File file=new File(filePath);
            if (!file.exists()){
                boolean flag=file.createNewFile();
                if (!flag){
                    throw new RuntimeException("创建文件失败");
                }
            }
            //写文件,不覆盖之前的内容，追加内容
            fw =new FileWriter(file,true);
            fw = new FileWriter(file, true);
            fw.write("序号,姓名,学号,性别,年龄,班级,状态,备注,身份证号,电话,地址,进校时间,密码,照片\n");
            // 遍历学生信息
            for (Student student : students) {
                fw.write(student.getSid() + ",");
                fw.write(student.getSname() + ",");
                fw.write(student.getSnum() + ",");
                fw.write(student.getSsex() + ",");
                fw.write(student.getSage() + ",");
                fw.write(student.getCname() + ",");
                fw.write(student.getSstatus() + ",");
                fw.write(student.getSremark() + ",");
                fw.write(student.getIdcard() + ",");
                fw.write(student.getPhone() + ",");
                fw.write(student.getAddress() + ",");
                fw.write(student.getEntime() + ",");
                fw.write(student.getPswd() + ",");
                fw.write(student.getPic() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //url
        return "report/"+fileName;
    }
}
