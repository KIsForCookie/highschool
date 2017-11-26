
public class temp {
	public static void main(String[] args){
		int k = 2;
		for(int j = 0; j < 5; j++){
			k *= 2;
		}
		for(int j = 0; j < k; j++){
			for(int i = 0; i < k; i++){
				System.out.print("[" + (i + 1) + ", " + (j + 1) + "]");
			}
			System.out.print("\n\n");
		}
	}
	
	
}
