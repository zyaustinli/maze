import java.util.ArrayList;

public class Stack <T>{

	private ArrayList<T> data;	
	private int size;
	
	public Stack() {
		data = new ArrayList<T>();
		size = 0;
	}

	public int size() {
		return size;
	}
		
	public void push(T element) {
		data.add(0, element);
		size++;
	}


	public T pop() {
		size--;
		return data.remove(0);
	}
	
	public T peek() {
		return data.get(0);
	}
	
	public String toString() {
		String output = "";
		for (T value : data) {
			output += value + ", ";
		}
		output = output.substring(0, output.length() - 2);
		return output;
	}

	public void clear() {
		// TODO Auto-generated method stub
		data.clear();
	}
}