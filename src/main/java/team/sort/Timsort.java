package team.sort;

import team.MingZi;

public class Timsort implements Sort {

    static int MIN_MERGE = 32;

    private MingZi[] data;
    public static int minRunLength(int n)
    {
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void insertionSort(MingZi[] arr, int left, int right)
    {
        for (int i = left + 1; i <= right; i++)
        {
            MingZi temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j].compareTo(temp) > 0 )
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
    public static void merge(MingZi[] arr, int l, int m, int r)
    {
        int len1 = m - l + 1, len2 = r - m;
        MingZi[] left = new MingZi[len1];
        MingZi[] right = new MingZi[len2];
        for (int x = 0; x < len1; x++)
        {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++)
        {
            right[x] = arr[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;
        while (i < len1 && j < len2)
        {
            if(left[i].compareTo(right[j]) <= 0)
            {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < len1)
        {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < len2)
        {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void TimSort(MingZi[] arr) {
        int n = arr.length;
        int minRun = minRunLength(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
        }

        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n;
                 left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                if (mid < right)
                    merge(arr, left, mid, right);
            }
        }
    }

    @Override
    public void setData(MingZi[] data){
        this.data = data;
    }
    @Override
    public void run() {
        TimSort(this.data);
    }

    @Override
    public MingZi[] getData(){
        return this.data;
    }
    @Override
    public void preWork() {
        for(MingZi n:this.data){
            n.toPinYin();
        }
    }
}
