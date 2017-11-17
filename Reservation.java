public class Reservation{
    int seat[][];
    public Reservation(int n, int m){
	seat = new int[n][m];
	for(int i=0;i<n;i++){
	    for(int j=0;j<m;j++){
		seat[i][j]=-1;
	    }
	}
    }
    
    boolean reserve(int id, int num){
	for(int i=0;i<seat.length;i++){
	    for(int j=0;j<seat[i].length;j++){
		if(seat[i][j]==-1){
		    for(int k=j; k<j+num; k++){
			if(seat[i][k]==-1){
			}
			seat[i][k]=id;
		    }
		}
	    }
	}
	return false;
    }
    
    void printSeat(){
	for(int i=0;i<seat.length;i++){
	    for(int j=0;j<seat[i].length;j++){
		System.out.print(seat[i][j]+" ");
	    }
	    System.out.println();
	}
    }
    public static void main(String args[]){
	int thread_num = 5;      //予約を取りに来る顧客(窓口)数
	Reservation rs = new Reservation(10,10); //6,15は座席数
	Passengers ps[] = new Passengers[thread_num];
	for(int i=0;i<thread_num;i++){
	    ps[i] = new Passengers(i,rs);
	}
	for(int i=0;i<thread_num;i++){
	    try{
		ps[i].join();
	    }catch(InterruptedException e){
	    }
	}
	rs.printSeat();
    }
}


class Passengers extends Thread{
    int id;
    Reservation rs;
    public Passengers(int n, Reservation rs){
	this.id = n;
	this.rs = rs;
	this.start();
    }

    public void run(){
	for(int i=0;i<10;i++){ //10回行う
	    int num = (int)(Math.random()*6+1);
	    if(rs.reserve(id, num)){
		System.out.println("ID:"+id+"  reserved "+num+" seats.");
	    }
	}
    }
}
