package Students;
public interface Learner { //学习者接口
    void register();  //注册
    void takeExam(String course);//参加考试
    default int queryExam(String course){  //查询成绩
        System.out.println("The function of querying scores not provided temporarily.");
        return 0;
    }
}
