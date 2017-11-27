public class Sort{
  private int array[];
  public Sort(int n){
    array = new int[n];
    for(int i=0;i<n;i++){
      array[i] = (int)(Math.random()*Integer.MAX_VALUE);
    }

    long start = System.currentTimeMillis();
    QuickSort qs = new QuickSort(array);
    array = qs.getArray();

    //printArray(array);

    long end = System.currentTimeMillis();
    System.out.println("sort?: "+sortCheck(array)+
    ", Processing time: "+(end-start)+"ms");
  }

  public static boolean sortCheck(int array[]){
    for(int i=0;i<array.length-1;i++){
      if(array[i]>array[i+1])return false;
    }
    return true;
  }

  public static void printArray(int array[]){
    for(int i=0;i<array.length;i++){
      System.out.print(array[i]+" ");
    }
    System.out.println();
  }

  public static void main(String args[]){
    new Sort(Integer.parseInt(args[0]));
  }
}

class QuickSort extends Thread{
  private int array[];
  QuickSort(int[] n){
    array = n;
    sort(array, 0, array.length-1);
  }

  private void sort(int[] d, int left, int right) {
    if (left>=right) {
      return;
    }
    int p = d[(left+right)/2];
    int l = left, r = right, tmp;
    while(l<=r) {
      while(d[l] < p) { l++; }
      while(d[r] > p) { r--; }
      if (l<=r) {
        tmp = d[l]; d[l] = d[r]; d[r] = tmp;
        l++; r--;
      }
    }
    sort(d, left, r);
    sort(d, l, right);
  }

  public int[] getArray(){
    return array;
  }
}
