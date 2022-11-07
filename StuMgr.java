package StudentsManager;
import java.util.*;
import java.util.regex.*;
import Students.*;
import java.io.*;
public class StuMgr {
	static final String FILE = ".\\Students.data";//���ѧ�����ݵ��ļ�·��
	static int selection;
	static Scanner sc = new Scanner(System.in);
	static ArrayList learners = new ArrayList(); //ʹ��ArrayList���ϴ��ѧϰ�ߣ��������
	//static Students learners = new Students();  //ʹ���Զ��弯����Students
	
	public static void main(String[] args) {
		//������ڴ�����ݵ��ļ������ȴ��ļ��ж�ȡ����
		File f = new File(FILE);
		if(f.canRead()){
			try{
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				learners = (ArrayList)ois.readObject();  //ʹ��ArrayList������
				//learners = (Students)ois.readObject(); //ʹ���Զ���Students������
				ois.close();
				fis.close();
			}
			catch(Exception e){
				System.out.println("Reading exception��"+e.getMessage());
			}
		}
		for(;;){
			menuLoop();
			if(selection==9)
				break;
			switchFunc();
		}
		System.out.println("Operation ended. Bye!");
		sc.close();
	}
	private static void menuLoop() {
		System.out.println("Operation Menu:");
		System.out.println("1.Input Student Information;");
		System.out.println("2.Display Student Information;");
		System.out.println("3.Take Exam;");
		System.out.println("4.Query Scores;");
		System.out.println("5.Student Registering;");
		System.out.println("6.Modify Information;");
		System.out.println("7.Delete a Student;");
		System.out.println("8.Save Student Information;");
		System.out.println("9.Quit!");
		while(true){
		    System.out.println("Input your selection��");
		    selection = inputInt();//ȷ����������
		    if(selection>=1&&selection<=9)
		    	break;
		    else
		    	System.out.println("Invalid input��");
		}
	}
	private static void switchFunc() {
		String c;
		switch(selection){
			case 1:  
				inputStudents();
				break;
			case 2:
				listScores();
				break;
			case 3:
				System.out.println("Exam course: ");
				c = sc.next();
				takeExam(c);
				break;
			case 4:
				System.out.println("Course: ");
				c = sc.next();
				queryScores(c);
				break;
			case 5:
				for(Object o : learners){  //���ϱ���
					((Learner)o).register();  //ǿ������ת��
				}
				break;
			case 6:
				modifyStudent();
				break;
			case 7:
				deleteStudent();
				break;
			case 8:
				saveStudents();
				break;
			default:
				break;
		}
	}
	private static void takeExam(String course){
		for(Object o : learners){ //���ϱ���
			((Learner)o).takeExam(course);//����ʵ��ִ�е���ѧ���ķ���,���ֽӿڶ�̬
		}
	}
	private static void queryScores(String course){
	    for(Object o : learners){  //���ϱ���
	    	int t = ((Student)o).queryExam(course);
	    	if(t>=0)
	    	    System.out.println("Name: "+((Student)o).getName()
	    			+"  Score: "+((Student)o).queryExam(course));//��������ֳ��ӿڶ�̬
		}
	}
	private static void listScores(){
		System.out.println("\t\t\tStudent Scores List");
		System.out.println("\t\t\t===================");
		System.out.println("Name\tGender\tClass\tChinese\tMaths\tEnglish\tPhysics\tChemistry");
		for(Object o : learners){
			Student s = (Student)o;
			System.out.print(s.getName()+"\t");
			System.out.print(s.getSex()+"\t");
			System.out.print(s.getClsName()+"\t");
			System.out.print(s.queryExam("Chinese")+"\t");
			System.out.print(s.queryExam("Maths")+"\t");
			System.out.print(s.queryExam("English")+"\t");
			int t = s.queryExam("Physics");
			System.out.print((t >= 0 ? t : "--") + "\t");
			t = s.queryExam("Chemistry");
			System.out.print((t >= 0 ? t : "--") + "\t");
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	private static void listStudents() {
		int i=0;
		for(Object o : learners){
			System.out.printf("Display No.%d Student Infomation--\n",++i);
			((Student)o).dispStudent();   //�˴����ֳ���̬�ԡ�ע�⽫����Ԫ��ת��ΪStudent����
			System.out.print("\n");
		}
	}
	private static void inputStudents() {
		int stype;
		for(;;){
			while(true){
				System.out.print("Select student type(1-Primary School; 2-Middle School; 0-End Input):");
				stype = inputInt();  //ȷ����������
				if(stype>=0&&stype<=2)
					break;
				else
					System.out.println("Selection invalid, please select again!");
			}
			if(stype==0)
				break;
			Student s;
			if(stype==1)
			    s = new Student(); 
			else
				s = new MiddleStudent(); //MiddleStudent����ֵ��Student����Ԫ��
			try{
				s.inputStudent(sc);  //�˴����ֳ���̬��
				learners.add(s);    //��ArrayList�������Ԫ��
			}
			catch(InputMismatchException ime){
				System.out.println("Content or format wrong!");
				sc.nextLine();//���д������ĵ����뻺���һ����Ϣ��������������ٴ������쳣
			}
			catch(ScoreException se){
				System.out.println(se.getMessage()+"����"+se.getCourseName()+":"+se.getErrorValue());
			}
			catch(Exception e){
				System.out.println(e);
			}
			System.out.print("\n");
		}
	}
	private static void modifyStudent(){
		int sno;
		while(true){
			System.out.printf("Input the student order(1-%d): ",learners.size());
			sno=inputInt();
			if(sno>0&&sno<=learners.size())
				break;
			else
				System.out.println("Input invalid!");
		}
		try{
			((Student)learners.get(sno-1)).modifyStudent(sc);//���ö�̬�Ա�����ѡ��ṹ
		}
		catch(ScoreException se){
			System.out.println(se.getMessage()+"����"+se.getCourseName()+":"+se.getErrorValue());
		}
		catch(NumberFormatException nfe){
			System.out.println("Input is not digits!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void deleteStudent(){
		int sno;
		while(true){
			System.out.printf("Input student order(1-%d)��",learners.size());
			sno = inputInt();//ȷ������һ������ֵ
			if(sno>0&&sno<=learners.size())
				break;
			else
				System.out.println("Input invalid!");
		}
		learners.remove(learners.get(sno-1));
	}
	private static void saveStudents(){
		try{
			FileOutputStream fos = new FileOutputStream(FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(learners);
			oos.close();
			fos.close();
			System.out.println("Saving data successful!");
		}
		catch(IOException e){
			System.out.println("Saving data error:"+e.getMessage());
		}
	}
	
	//���º���ȷ������һ��������ʹ��������ʽ��֤������ַ����Ƿ�Ϊһ���Ϸ���������
	private static int inputInt(){
		int result;
		String intPattern = "^\\d+$"; //������ģʽ�ַ���
		Pattern p = Pattern.compile(intPattern);  //������ʽģʽ
		String s;
		while(true){
			s = sc.next();
			if(p.matcher(s).matches()) //�ַ���s�������ģʽ����Ϊ�Ϸ�������
				break;
			else
				System.out.println("Input is not digits, please input again!");
		}
		result = Integer.parseInt(s);//���ַ����������
		return result;
	}
}
