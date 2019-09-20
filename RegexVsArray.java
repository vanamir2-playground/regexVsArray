package RegexVsArray;

import java.util.*;
import java.util.regex.Pattern;

public class RegexVsArray {

    private static Map<String, String> cachedUmaskedCodes = new HashMap<>();

    private static final String ALPHA_NUMERIC_STRING = "X0123456789";

    private static Pattern PATTERN = Pattern.compile("X.*");

    private static List<String> listKodu1 = new ArrayList<>();
    private static List<String> listKodu2 = new ArrayList<>();
    private static List<String> listKodu3 = new ArrayList<>();

    private static List<String> listKodu1Res = new ArrayList<>();
    private static List<String> listKodu2Res = new ArrayList<>();
    private static List<String> listKodu3Res = new ArrayList<>();

    public static void main ( String [] args ){
        int pocetPrvku = 10;
        for( int i = 0 ; i < 6; ++i){
            pocetPrvku *= 10;
            provedBenchmark(pocetPrvku);
        }
    }

    private static void provedBenchmark(int pocetPrvku){
        naplnitList(pocetPrvku);

        long startTime = System.nanoTime();
        listKodu1.forEach( s -> {
            listKodu1Res.add(removeMask(s));
        });
        long endTime = System.nanoTime();
        System.out.println( "David reseni trvalo " + (endTime - startTime)/1000000 + " ms pro " + pocetPrvku + " prvku." );  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        listKodu2.forEach( s -> {
            listKodu2Res.add(removeMaskRegex(s));
        });
        endTime = System.nanoTime();
        System.out.println( "Regex reseni trvalo " + (endTime - startTime)/1000000 + " ms pro " + pocetPrvku + " prvku." );  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        listKodu3.forEach( s -> {
            listKodu3Res.add(removeMaskRegexWithCompiledPattern(s));
        });
        endTime = System.nanoTime();
        System.out.println( "Match reseni trvalo " + (endTime - startTime)/1000000 + " ms pro " + pocetPrvku + " prvku.\n" );  //divide by 1000000 to get milliseconds.
    }

    // naplni list kody o delce 4-13 znaku
    private static void naplnitList(int pocetPrvku){
        listKodu1.clear();
        listKodu2.clear();
        listKodu3.clear();
        listKodu1Res.clear();
        listKodu2Res.clear();
        listKodu3Res.clear();

        cachedUmaskedCodes.clear();
        for(int i = 0; i < pocetPrvku; ++i){
            String str = randomAlphaNumeric(new Random().nextInt(10) + 4);
             // System.out.println(new Random().nextInt(10) + 4);
            listKodu1.add( str);
            listKodu2.add( str);
            listKodu3.add( str);
        }
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static String removeMaskRegex( String kod ) {
        return kod.replaceAll("X.*", "");
    }

    private static String removeMaskRegexWithCompiledPattern( String kod ) {
        return PATTERN.matcher(kod).replaceAll("");
    }

    /**
     * Odstraní znaky X na konci předaného kódu.
     * Již jednou odmaskované kódy kešuje pro větší rychlost.
     */
    private static String removeMask( String kod ) {
        String res = cachedUmaskedCodes.get( kod );
        if ( res != null )
            return res;

        StringBuilder resSb = new StringBuilder();
        for ( char ch : kod.toCharArray() ) {
            if ( ch != 'X' )
                resSb.append( ch );
            else
                break;
        }

        res = resSb.toString();
        cachedUmaskedCodes.put( kod, res );
        return res;
    }
}
