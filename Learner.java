package Students;
public interface Learner { //ѧϰ�߽ӿ�
    void register();  //ע��
    void takeExam(String course);//�μӿ���
    default int queryExam(String course){  //��ѯ�ɼ�
        System.out.println("The function of querying scores not provided temporarily.");
        return 0;
    }
}
