import java.util.ArrayList;

public class Queue<T> {

	ArrayList<T> data;
	
	public Queue() {
		data = new ArrayList<T>();
	}
	
	public void enqueue(T value) {
		data.add(value);
	}
	
	public T dequeue() {
		return data.remove(0);
	}
	
	public T peek(){
		return data.get(0);
	}
	public void clear() {
		
		data.clear();
	}
	
	public String toString() {
		String output = "";
		for (int x = 0; x < data.size(); x++) {
			output = data.get(x) + " "; 
		}
		return output;
	}
	
	public int size() {
		return data.size();
	} 
	
}