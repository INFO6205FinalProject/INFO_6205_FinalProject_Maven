import org.junit.Assert;
import org.junit.Test;
import team.GenerateName;
import team.MingZi;
import team.TXT;
import team.sort.*;
import team.sort.HuskySortDir.HuskySortBenchmark;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionTest {
    int arrSize = 10;

    @Test
    public void MsdTest() {
        MingZi[] arr = new MingZi[arrSize];
        MingZi[] test = new MingZi[arrSize];
        boolean flag = true;
        int loop = 50;
        while(loop>0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new MingZi(GenerateName.randomName(),true);
                test[i] = arr[i];
            }

            MSD.MSDSort(arr);
            Arrays.sort(test);

            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].getHanZi().equals(test[i].getHanZi())) {
                    flag = false;
                    break;
                }

            }
            if(!flag) break;
            loop--;
        }

        Assert.assertEquals(true,flag);
    }

    @Test
    public void TimSortTest() {
        MingZi[] arr = new MingZi[arrSize];
        MingZi[] test = new MingZi[arrSize];
        boolean flag = true;
        int loop = 50;
        while(loop>0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new MingZi(GenerateName.randomName(),true);
                test[i] = arr[i];
            }

            Timsort.TimSort(arr);
            Arrays.sort(test);

            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].getHanZi().equals(test[i].getHanZi())) {
                    flag = false;
                    break;
                }

            }
            if(!flag) break;
            loop--;
        }
        Assert.assertEquals(true,flag);
    }
    @Test
    public void HuskySortTest() {


        boolean flag = true;
        int loop = 1;
        while(loop>0) {
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = GenerateName.randomName();
//                test[i] = new MingZi(arr[i],true);
//
            ArrayList<String> hz;
            String addr = "src/main/resources/shuffledChinese.txt";
            hz = TXT.read_txt(addr);
            String[] arr = new String[hz.size()];
            MingZi[] test = new MingZi[hz.size()];

            for(int i = 0;i<hz.size();i++){
                arr[i] = hz.get(i);
                test[i] = new MingZi(arr[i],true);
            }

            HuskySortBenchmark hs = new HuskySortBenchmark();
            hs.setData(arr);  //husky sort
            hs.runBenchmark();

            Arrays.sort(test); //system sort

            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].equals(test[i].getHanZi())) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
            loop--;
        }

        Assert.assertEquals(true,flag);
    }
    @Test
    public void LsdTest() {

        MingZi[] arr = new MingZi[arrSize];
        MingZi[] test = new MingZi[arrSize];
        boolean flag = true;
        int loop = 10;
        while(loop>0){
            for(int i = 0;i<arr.length;i++){
                arr[i] = new MingZi(GenerateName.randomName(),true);
                test[i] = arr[i];
            }

            LSD.LSDSort(arr);
            Arrays.sort(test);

            for(int i = 0;i<arr.length;i++){
                if(!arr[i].getHanZi().equals(test[i].getHanZi())){
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
            loop--;
        }

        Assert.assertEquals(true,flag);

    }
    @Test
    public void DualQuicksortTest() {
        MingZi[] arr = new MingZi[arrSize];
        MingZi[] test = new MingZi[arrSize];
        boolean flag = true;

        int loop = 50;
        while(loop>0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new MingZi(GenerateName.randomName(),true);
                test[i] = arr[i];
            }

            DualQuicksort.DualPivotQuickSort(arr, 0, arr.length - 1);

            Arrays.sort(test);

            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].getHanZi().equals(test[i].getHanZi())) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
            loop--;
        }
        Assert.assertEquals(true,flag);
    }
}

