package RegexVsArray;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

public class RegexVsArray {

    private static final String ALPHA_NUMERIC_STRING = "X0123456";
    private static Map<String, String> cachedUmaskedCodes = new HashMap<>();
    private static Pattern PATTERN = Pattern.compile("X.*");

    private static List<String> listKodu1 = new ArrayList<>();
    private static List<String> listKodu2 = new ArrayList<>();
    private static List<String> listKodu3 = new ArrayList<>();
    private static List<String> listKodu4 = new ArrayList<>();
    private static List<String> listKodu5 = new ArrayList<>();

    private static List<String> listKodu1Res = new ArrayList<>();
    private static List<String> listKodu2Res = new ArrayList<>();
    private static List<String> listKodu3Res = new ArrayList<>();
    private static List<String> listKodu4Res = new ArrayList<>();
    private static List<String> listKodu5Res = new ArrayList<>();

    public static void main(String[] args) {
        int pocetPrvku = 10;
        for (int i = 0; i < 6; ++i) {
            pocetPrvku *= 10;
            provedBenchmark(pocetPrvku);
        }
    }

    private static void performTest(int pocetPrvku, List<String> kodyList, List<String> resultList, String reseniNazev, Method method) {
        long startTime = System.nanoTime();
        kodyList.forEach(s -> {
            try {
                resultList.add((String) method.invoke(s,s));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.nanoTime();
        System.out.println( String.format( "%04d" ,(endTime - startTime) / 1000000) + " ms - " +reseniNazev + "<br/>");  //divide by 1000000 to get milliseconds.
        System.gc();
    }

    private static void provedBenchmark(int pocetPrvku) {
        naplnitList(pocetPrvku);
        Class[] parameterTypes = new Class[1];
        parameterTypes[0] = String.class;

        try {
            System.out.println("\n------------------------------- Testovani pro " + pocetPrvku + " prvku: ");
            performTest(pocetPrvku, listKodu1, listKodu1Res, "Davida řešení s mapou na cachovaní.", RegexVsArray.class.getDeclaredMethod("removeMask", parameterTypes));
            performTest(pocetPrvku, listKodu2, listKodu2Res, "Davida řešení bez mapy.", RegexVsArray.class.getDeclaredMethod("removeMaskWithoutMap", parameterTypes));
           // performTest(pocetPrvku, listKodu3, listKodu3Res, "Regex", RegexVsArray.class.getDeclaredMethod("removeMaskRegex", parameterTypes));
            performTest(pocetPrvku, listKodu4, listKodu4Res, "Regex s připraveným patternem.", RegexVsArray.class.getDeclaredMethod("removeMaskRegexWithCompiledPattern", parameterTypes));
            performTest(pocetPrvku, listKodu5, listKodu5Res, "Další řešení pomocí substring.", RegexVsArray.class.getDeclaredMethod("removeMaskOneMoreTry", parameterTypes));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // naplni list kody o delce 4-13 znaku
    private static void naplnitList(int pocetPrvku) {
        listKodu1.clear();
        listKodu2.clear();
        listKodu3.clear();
        listKodu4.clear();
        listKodu5.clear();
        listKodu1Res.clear();
        listKodu2Res.clear();
        listKodu3Res.clear();
        listKodu4Res.clear();
        listKodu5Res.clear();

        cachedUmaskedCodes.clear();
        for (int i = 0; i < pocetPrvku; ++i) {
            String str = randomAlphaNumeric(new Random().nextInt(10) + 4);
            //System.out.println(str);
            listKodu1.add(str);
            listKodu2.add(str);
            listKodu3.add(str);
            listKodu4.add(str);
            listKodu5.add(str);
        }
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static String removeMaskRegex(String kod) {
        return kod.replaceAll("X.*", "");
    }

    private static String removeMaskRegexWithCompiledPattern(String kod) {
        return PATTERN.matcher(kod).replaceAll("");
    }

    /**
     * Odstraní znaky X na konci předaného kódu.
     * Již jednou odmaskované kódy kešuje pro větší rychlost.
     */
    private static String removeMask(String kod) {
        String res = cachedUmaskedCodes.get(kod);
        if (res != null)
            return res;

        StringBuilder resSb = new StringBuilder();
        for (char ch : kod.toCharArray()) {
            if (ch != 'X')
                resSb.append(ch);
            else
                break;
        }

        res = resSb.toString();
        cachedUmaskedCodes.put(kod, res);
        return res;
    }

    /**
     * Odstraní znaky X na konci předaného kódu.
     * Již jednou odmaskované kódy kešuje pro větší rychlost.
     */
    private static String removeMaskWithoutMap(String kod) {
        StringBuilder resSb = new StringBuilder();
        for (char ch : kod.toCharArray()) {
            if (ch != 'X')
                resSb.append(ch);
            else
                break;
        }
        return resSb.toString();
    }

    private static String removeMaskOneMoreTry(String kod) {
        return kod.substring(0, kod.indexOf('X') == -1 ? kod.length() : kod.indexOf('X'));
    }

}
