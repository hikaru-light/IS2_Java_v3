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

  synchronized boolean reserve(int id, int num){
    int sum = 0;

    for(int i=0; i<seat.length; i++){
      for(int j=0; j<seat[i].length; j++){
        if(seat[i].length-j>=num){
          if(seat[i][j]==-1){
            for(int k=j; k<j+num; k++){
              sum += seat[i][k];
            }
            if(sum == -num){
              for(int l=j; l<j+num; l++){
                seat[i][l] = id;
                System.out.println("ID:"+id+"  ["+i+","+l+"]");
              }
              return true;
            }
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
    int thread_num = 5;
    Reservation rs = new Reservation(6,15);
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
    for(int i=0;i<10;i++){
      int num = (int)(Math.random()*6+1);
      if(rs.reserve(id, num)){
        System.out.println("ID:"+id+"  reserved "+num+" seats.");
      }else{
        System.out.println("FAILURE  ID:"+id+"  NUMBER:"+num);
      }
    }
  }
}
