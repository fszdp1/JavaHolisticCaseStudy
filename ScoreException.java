package Students;

public class ScoreException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String courseName;
	private float errorValue;
	public ScoreException(String course, float score){
		super("Score Exception");
		courseName = course;
		errorValue = score;
	}
	public String getCourseName(){
		return courseName;
	}
	public float getErrorValue(){
		return errorValue;
	}
}
