# INFO_6205_FinalProject_Maven
maven struct

Sort result is under /src/main/resources

## New Model
All tests in thread pool(we use test as our main run method).

TXT read model to read txt file on disk.

MingZi model to storage and use for sort.

GenerateName model to generate Chinese name randomly, use to test sort result.

ChineseCharToEn model base on PinYin4j, transform Chinese character to English character according to pinyin.

Benchmark model to test time duration of sort. MyHuskySortBenchmark Special customized benchmark for husky sort.

## Changes on existing code
Modify TImsort, LSDSort, MSDSort, QuickSort and Huskysort to our structure.

Replace default Collator for Huskysort with IBM Collator, make it suitable for more Chinese characters.

## Attention！
The main method(Main class) is only used to generate sort result under /src/main/resources

If you want to run it, please make sure you deleted all the file named like "***Result.txt"under this folder.
