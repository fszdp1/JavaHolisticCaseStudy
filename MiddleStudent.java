package Students;
import java.util.Scanner;
public class MiddleStudent extends Student {
	private int physicsScore;
	private int chemistryScore;
	public MiddleStudent(){} //�޲������췽��
	public MiddleStudent(String nm, String cls, char gender){
		super(nm,cls,gender);  //���û��๹�췽��
	}
	public MiddleStudent(String nm, String cls, char gender, int cScore, int eScore, int mScore){
		super(nm,cls,gender,cScore,eScore,mScore);//���û��๹�췽��
	}
	public MiddleStudent(String nm, String cls, char gender, int cScore, int eScore, int mScore, int pScore, int chScore){
		this(nm,cls,gender,cScore,eScore,mScore);//�����Լ��Ĺ��췽��
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
	public void dispStudent(){ //�˷�����д�򸲸ǻ��෽��
		super.dispStudent();   //���û��౻���Ƿ���
		System.out.println("Physics: "+this.physicsScore);
		System.out.println("Chemistry: "+this.chemistryScore);
	}
	public void inputStudent(Scanner sc){ //�˷�����д�򸲸ǻ��෽��
		super.inputStudent(sc);  //���û��౻���Ƿ���
		System.out.print("Physics: ");
		setPhysicsScore(sc.nextInt());//ȷ������Ϊ�Ǹ���
		System.out.print("Chemistry: ");
		setChemicalScore(sc.nextInt());//ȷ������Ϊ�Ǹ���
	}
	//�˷������ؿγ̳ɼ���courseΪ�γ����ƣ�������"����"��"��ѧ"��"Ӣ��"��"����"��"��ѧ"��
	public int queryExam(String course){//��д����ʵ�ֵĽӿڷ�����
		if(course.equals("Physics"))
			return this.physicsScore;
		else if(course.equals("Chemistry"))
			return this.chemistryScore;
		else
			return super.queryExam(course);//���û���ı���д��������super
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
