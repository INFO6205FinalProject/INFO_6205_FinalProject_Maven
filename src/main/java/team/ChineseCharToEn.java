package team;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.HashSet;
import java.util.Set;

public class ChineseCharToEn {
    public static class CharacterPinYinConvert
    {
        /** set output format **/
        HanyuPinyinOutputFormat format = null;

        /**
         * constructor
         */
        public CharacterPinYinConvert()
        {
            // set output format
            format = new HanyuPinyinOutputFormat();

            // no tone set
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            //
            // fmt.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
            // fmt.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

            //
            format.setVCharType(HanyuPinyinVCharType.WITH_V);
            //
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }

        /**
         * chinese letter to pinyin
         *
         * @param text
         *
         * @return allPinYin
         */
        public String toPinYin(String text)
        {
            StringBuilder allPinYin = new StringBuilder();
            for (int i = 0; i < text.length(); i++)
            {
                char wordChar = text.charAt(i);
                // 如果为汉字
                if (Character.toString(wordChar).matches("[\\u4E00-\\u9FA5]"))
                {
                    try
                    {

                        String[] pinYinArray = PinyinHelper.toHanyuPinyinStringArray(
                                wordChar, format);

                        String pinYin = pinYinArray[0];
                        if (pinYin != null)
                        {
                            allPinYin.append(pinYin);
                            allPinYin.append(' ');
                        }
                    }
                    catch (Exception e)
                    {
                    }
                }
                else if (((int) wordChar >= 65 && (int) wordChar <= 90)
                        || ((int) wordChar >= 97 && (int) wordChar <= 122))
                {
                    allPinYin.append(wordChar);
                    allPinYin.append(' ');
                }
            }
            return allPinYin.toString();
        }

        /**
         * chinese letter to pinyin
         *
         * @param text
         *
         * @return pinYinSet
         */
        public Set<String> toPinYins(String text)
        {
            char[] srcChar = text.toCharArray();

            String[][] temp = new String[text.length()][];
            for (int i = 0; i < srcChar.length; i++)
            {
                char c = srcChar[i];
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]"))
                {
                    try
                    {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i],
                                format);
                    }
                    catch (Exception e)
                    {
                    }
                }
                else if (((int) c >= 65 && (int) c <= 90)
                        || ((int) c >= 97 && (int) c <= 122))
                {
                    temp[i] = new String[] { String.valueOf(srcChar[i]) };
                }
                else
                {
                    temp[i] = new String[] { "" };
                }
            }
            String[] pingyinArray = doExchange(temp)[0];
            Set<String> pinyinSet = new HashSet<String>();
            for (int i = 0; i < pingyinArray.length; i++)
            {
                pinyinSet.add(pingyinArray[i]);
            }
            return pinyinSet;
        }

        /**
         * 处理转换
         *
         * @return
         */
        private String[][] doExchange(String[][] strJaggedArray)
        {
            int len = strJaggedArray.length;
            if (len >= 2)
            {
                int len1 = strJaggedArray[0].length;
                int len2 = strJaggedArray[1].length;
                int newlen = len1 * len2;
                String[] temp = new String[newlen];
                int index = 0;
                for (int i = 0; i < len1; i++)
                {
                    for (int j = 0; j < len2; j++)
                    {
                        temp[index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                        index++;
                    }
                }
                String[][] newArray = new String[len - 1][];
                for (int i = 2; i < len; i++)
                {
                    newArray[i - 1] = strJaggedArray[i];
                }
                newArray[0] = temp;
                return doExchange(newArray);
            }
            else
            {
                return strJaggedArray;
            }
        }

    }
}
