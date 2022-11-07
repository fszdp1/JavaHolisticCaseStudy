package Students;
import java.util.*;
import java.io.*;
public class Students implements Collection, Serializable{
	private static final long serialVersionUID = 19928390L;
	private Student[] students = new Student[10];
	private int capacity = 10;
	private int increment = 10;
	private int size = 0;
	public Students(){  }
	public Students(int initCapacity){
		this.capacity = initCapacity;
		students = new Student[initCapacity];
	}
	public Students(int initCapacity, int increment){
		this.capacity = initCapacity;
		this.increment = increment;
		students = new Student[initCapacity];
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		if(o==null)
			return true;
		if(o instanceof Student){
			for(int i=0;i<size;i++){
				if(students[i].equals(o))
					return true;
			}
		}
		return false;
	}
    //�˷�����Iterable�ӿ��ж���ģ�Collection�ӿڼ̳���Iterable�ӿڣ�����Ҫʵ��
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		//����ʹ���ڲ���ĵ������ӡ���������ڲ��࣬����͸��Ӻܶ�
		return new Iterator(){  //�������������ʵ�֡����ڲ���Ҳһ�����ԡ�
			int index=0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index<size;
			}
			@Override
			public Object next() {
				// TODO Auto-generated method stub
				return students[index++];
			}
			@Override
			public void remove(){
				size--;
				for(int j=index;j<size;j++){
					students[j]=students[j+1];
				}
			}
		};
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(students, size);
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (Object[]) Arrays.copyOf(students, size, a.getClass());
        System.arraycopy(students, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;

	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		if(!(e instanceof Student))
			return false;
		if(size == this.capacity){
			Student[] tmp = Arrays.copyOf(students, size);
			this.capacity += this.increment;
			students = new Student[this.capacity];
			System.arraycopy(tmp, 0, students, 0, size);
		}
		students[size++]=(Student)e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if(!(o instanceof Student))
			return false;
		for(int i=0;i<size;i++){
			if(students[i].equals((Student)o)){
				size--;
				for(int j=i;j<size;j++){
					students[j]=students[j+1];
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		for(Object o : c){
			if(!(o instanceof Student))
				return false;
		}
		if(c.size()>this.capacity-size){
			Student[] tmp = Arrays.copyOf(students, size);
			this.capacity += c.size();
			students = new Student[this.capacity];
			System.arraycopy(tmp, 0, students, 0, size);
			System.arraycopy(c, 0,students, size, c.size());
			size += c.size();
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }
        return modified;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(c);
        boolean modified = false;
        Iterator it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.size=0;
	}
	//���·�������Collection�ӿ��й涨�ģ����ǳ�������Ҫ�õ���
	public Object get(int index) {
		// TODO Auto-generated method stub
		if(index>=size || index <0)
			throw new IndexOutOfBoundsException();
		return students[index];
	}
}
