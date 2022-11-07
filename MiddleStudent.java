package Students;
import java.util.Scanner;
public class MiddleStudent extends Student {
	private int physicsScore;
	private int chemistryScore;
	public MiddleStudent(){} //无参数构造方法
	public MiddleStudent(String nm, String cls, char gender){
		super(nm,cls,gender);  //调用基类构造方法
	}
	public MiddleStudent(String nm, String cls, char gender, int cScore, int eScore, int mScore){
		super(nm,cls,gender,cScore,eScore,mScore);//调用基类构造方法
	}
	public MiddleStudent(String nm, String cls, char gender, int cScore, int eScore, int mScore, int pScore, int chScore){
		this(nm,cls,gender,cScore,eScore,mScore);//调用自己的构造方法
		this.physicsScore = pScore;
		this.chemistryScore = chScore;
	}
	public void setPhysicsScore(int pScore){
		if(pScore>=0)
			this.physicsScore = pScore;
		else
			throw new ScoreException("Physics", pScore);
	}
	public void setChemicalScore(int chScore){
		if(chScore>=0)
			this.chemistryScore = chScore;
		else
			throw new ScoreException("Chemistry", chScore);
	}
	public void dispStudent(){ //此方法重写或覆盖基类方法
		super.dispStudent();   //调用基类被覆盖方法
		System.out.println("Physics: "+this.physicsScore);
		System.out.println("Chemistry: "+this.chemistryScore);
	}
	public void inputStudent(Scanner sc){ //此方法重写或覆盖基类方法
		super.inputStudent(sc);  //调用基类被覆盖方法
		System.out.print("Physics: ");
		setPhysicsScore(sc.nextInt());//确保输入为非负数
		System.out.print("Chemistry: ");
		setChemicalScore(sc.nextInt());//确保输入为非负数
	}
	//此方法返回课程成绩。course为课程名称，可以是"语文"、"数学"、"英语"、"物理"、"化学"。
	public int queryExam(String course){//重写基类实现的接口方法。
		if(course.equals("Physics"))
			return this.physicsScore;
		else if(course.equals("Chemistry"))
			return this.chemistryScore;
		else
			return super.queryExam(course);//调用基类的被重写方法，用super
	}
	public void modifyStudent(Scanner sc){
		super.modifyStudent(sc);
		String stringIn;
		System.out.print("Physics: "+this.physicsScore);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.setPhysicsScore(Integer.parseInt(stringIn));
		System.out.print("Chemistry: "+this.chemistryScore);
		stringIn = sc.nextLine();
		if(!stringIn.equals(""))
			this.setChemicalScore(Integer.parseInt(stringIn));
	}
	@Override
	public String toString() {
		String str = super.toString() + "\t" + this.queryExam("Physics") + "\t" + this.queryExam("Chemistry");
		return str;
	}
}
