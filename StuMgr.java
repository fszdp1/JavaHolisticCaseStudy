package StudentsManager;
import java.util.*;
import java.util.regex.*;
import Students.*;
import java.io.*;
public class StuMgr {
	static final String FILE = ".\\Students.data";//存放学生数据的文件路径
	static int selection;
	static Scanner sc = new Scanner(System.in);
	static ArrayList learners = new ArrayList(); //使用ArrayList集合存放学习者，代码大大简化
	//static Students learners = new Students();  //使用自定义集合类Students
	
	public static void main(String[] args) {
		//如果存在存放数据的文件，则先从文件中读取数据
		File f = new File(FILE);
		if(f.canRead()){
			try{
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				learners = (ArrayList)ois.readObject();  //使用ArrayList集合类
				//learners = (Students)ois.readObject(); //使用自定义Students集合类
				ois.close();
				fis.close();
			}
			catch(Exception e){
				System.out.println("Reading exception："+e.getMessage());
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
		    System.out.println("Input your selection：");
		    selection = inputInt();//确保输入整数
		    if(selection>=1&&selection<=9)
		    	break;
		    else
		    	System.out.println("Invalid input！");
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
				for(Object o : learners){  //集合遍历
					((Learner)o).register();  //强制类型转换
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
		for(Object o : learners){ //集合遍历
			((Learner)o).takeExam(course);//这里实际执行的是学生的方法,体现接口多态
		}
	}
	private static void queryScores(String course){
	    for(Object o : learners){  //集合遍历
	    	int t = ((Student)o).queryExam(course);
	    	if(t>=0)
	    	    System.out.println("Name: "+((Student)o).getName()
	    			+"  Score: "+((Student)o).queryExam(course));//这里会体现出接口多态
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
			((Student)o).dispStudent();   //此处表现出多态性。注意将集合元素转化为Student类型
			System.out.print("\n");
		}
	}
	private static void inputStudents() {
		int stype;
		for(;;){
			while(true){
				System.out.print("Select student type(1-Primary School; 2-Middle School; 0-End Input):");
				stype = inputInt();  //确保输入整数
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
				s = new MiddleStudent(); //MiddleStudent对象赋值给Student数组元素
			try{
				s.inputStudent(sc);  //此处表现出多态性
				learners.add(s);    //向ArrayList集合添加元素
			}
			catch(InputMismatchException ime){
				System.out.println("Content or format wrong!");
				sc.nextLine();//此行代码消耗掉输入缓冲的一行信息，以免后面输入再次引发异常
			}
			catch(ScoreException se){
				System.out.println(se.getMessage()+"——"+se.getCourseName()+":"+se.getErrorValue());
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
			((Student)learners.get(sno-1)).modifyStudent(sc);//利用多态性避免了选择结构
		}
		catch(ScoreException se){
			System.out.println(se.getMessage()+"——"+se.getCourseName()+":"+se.getErrorValue());
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
			System.out.printf("Input student order(1-%d)：",learners.size());
			sno = inputInt();//确保输入一个整数值
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
	
	//以下函数确保输入一个整数。使用正则表达式验证输入的字符串是否为一个合法的正整数
	private static int inputInt(){
		int result;
		String intPattern = "^\\d+$"; //正整数模式字符串
		Pattern p = Pattern.compile(intPattern);  //正则表达式模式
		String s;
		while(true){
			s = sc.next();
			if(p.matcher(s).matches()) //字符串s如果符合模式，即为合法正整数
				break;
			else
				System.out.println("Input is not digits, please input again!");
		}
		result = Integer.parseInt(s);//将字符串变成整数
		return result;
	}
}
