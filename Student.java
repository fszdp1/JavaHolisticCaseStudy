package Students;
//import java.time.LocalTime;
import java.util.*;
import java.io.*;
public class Student implements Learner, Serializable{
	private String name;
	private String clsName;
	private char sex;
	private int chineseScore;
	private int englishScore;
	private int mathScore;
	public Student(){
		
	}
	public Student(String nm, String cls, char gender){
		this.name = nm;
		this.clsName = cls;
		this.sex = gender;
	}
	public Student(String nm, String cls, char gender, int cScore, int eScore, int mScore){
		this(nm, cls, gender);
		this.chineseScore = cScore;
		this.englishScore = eScore;
		this.mathScore = mScore;
	}
	public String getName(){return name;}
	public String getClsName(){return clsName;}
	public char getSex(){return sex;}
	public void setChineseScore(int cScore){
		if(cScore >= 0){
			this.chineseScore = cScore;
		}
		else{
			throw new ScoreException("Chinese", cScore);
		}
	}
	public void setEnglishScore(int eScore){
		if(eScore>=0){
			this.englishScore = eScore;
		}
		else{
			throw new ScoreException("English", eScore);
		}
	}
	public void setMathScore(int mScore){
		if(mScore>=0){
			this.mathScore = mScore;
		}
		else{
			throw new ScoreException("Maths", mScore);
		}
	}
	public void dispStudent(){
		System.out.println("Name: "+this.name);
		System.out.println("Class: "+this.clsName);
		System.out.println("Gender: "+this.sex);
		System.out.println("Chinese: "+this.chineseScore);
		System.out.println("Maths: "+this.mathScore);
		System.out.println("English: "+this.englishScore);
	}
	public void inputStudent(Scanner sc){
		System.out.print("Name: ");
		this.name = sc.next();
		System.out.print("Class: ");
		this.clsName = sc.next();
		System.out.print("Gender: ");
		this.sex = sc.next().charAt(0);
		System.out.print("Chinese: ");
		setChineseScore(sc.nextInt());//确保输入为非负数
		System.out.print("Maths: ");
		setMathScore(sc.nextInt());//确保输入为非负数
		System.out.print("English: ");
		setEnglishScore(sc.nextInt());//确保输入为非负数
	}
	public void register(){
		System.out.println(this.name+" registered at " + Calendar.getInstance().getTime());
	}
	public void takeExam(String course){
		if(queryExam(course)>=0)
		    System.out.println(this.name+" is taking " + course + " exam.");
	}
	public int queryExam(String course){//实现接口方法
		if(course.equals("Chinese"))
			return this.chineseScore;
		else if(course.equals("Maths"))
			return this.mathScore;
		else if(course.equals("English"))
			return this.englishScore;
		else
			return -1;
	}
	protected int getNonNegative(Scanner sc){
		int result = sc.nextInt();
		while(result<0){
			System.out.println("Please input an nonnegative integer：");
			result = sc.nextInt();
		}
		return result;
	}
	public void modifyStudent(Scanner sc){
		String stringIn;
		sc.nextLine();//此行代码目的是消除缓冲区现有空字符对当前输入的影响
		System.out.println("Modify student information(Reenter or directly press enter keeping unchanged)——");
		System.out.print("Name: "+this.name);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.name = stringIn;
		System.out.print("Class: "+this.clsName);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.clsName = stringIn;
		System.out.print("Gender: "+this.sex);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.sex = stringIn.charAt(0);
		System.out.print("Chinese: "+this.chineseScore);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.setChineseScore(Integer.parseInt(stringIn));
		System.out.print("Maths: "+this.mathScore);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.setMathScore(Integer.parseInt(stringIn));
		System.out.print("English: "+this.englishScore);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.setEnglishScore(Integer.parseInt(stringIn));
	}
}
