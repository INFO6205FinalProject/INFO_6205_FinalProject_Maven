package team.sort;

import team.MingZi;

public class MSD implements Sort {

    private MingZi[] data;
    private static int R=256;
    private static MingZi[] aux;
    public static int charAt(String s,int d){
        if(d<s.length()){
            return s.charAt(d);
        }else{
            return -1;
        }
    }
    public static void MSD_sort(MingZi[] a){
        int N=a.length;
        aux=new MingZi[N];
        MSD_sort1(a,0,N-1,0);
    }

    private static void MSD_sort1(MingZi[] a, int lo, int hi, int d){
        if(lo>=hi){
            return;
        }
        int[] count=new int[R+2];
        for(int i=lo;i<=hi;i++){
            count[charAt(a[i].getPinYin(),d)+2]++;
        }
        for(int r=0;r<R+1;r++){
            count[r+1]+=count[r];
        }
        for(int i=lo;i<=hi;i++){
            aux[count[charAt(a[i].getPinYin(),d)+1]++]=a[i];
        }
        for(int i=lo;i<=hi;i++){
            a[i]=aux[i - lo];
        }
        for(int r=0;r<R;r++){
            MSD_sort1(a,lo+count[r],lo+count[r+1]-1,d+1);
        }
    }

    // Driver Code

    public static void main(String[] args) {

        // Input String
        String str[] = { "midnight", "badge",  "bag", "worker",   "banner", "wander" };

        System.out.print("Unsorted array : ");

        // Print the unsorted array
        for(String i: str){
            System.out.print(i + " ");
        }

        // Function Call
        //MSD_sort(str);

        System.out.print("Sorted array : ");

        // Print the sorted array
        for(String i: str){
            System.out.print(i + " ");
        }
    }

    @Override
    public void setData(MingZi[] data){
        this.data = data;
    }
    @Override
    public void run() {
        MSD_sort(this.data);
    }
}
