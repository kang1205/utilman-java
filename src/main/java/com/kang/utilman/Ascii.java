package com.kang.utilman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bradly
 * @version 1.0.0
 * @date 11/19/18 2:57 PM
 */
class Ascii {

    static final Map<String, List<String>> ASCII;

    static {
        ASCII = new HashMap<>(115);

        ASCII.put("0", Arrays.asList("°", "₀", "۰"));
        ASCII.put("1", Arrays.asList("¹", "₁", "۱"));
        ASCII.put("2", Arrays.asList("²", "₂", "۲"));
        ASCII.put("3", Arrays.asList("³", "₃", "۳"));
        ASCII.put("4", Arrays.asList("⁴", "₄", "۴", "٤"));
        ASCII.put("5", Arrays.asList("⁵", "₅", "۵", "٥"));
        ASCII.put("6", Arrays.asList("⁶", "₆", "۶", "٦"));
        ASCII.put("7", Arrays.asList("⁷", "₇", "۷"));
        ASCII.put("8", Arrays.asList("⁸", "₈", "۸"));
        ASCII.put("9", Arrays.asList("⁹", "₉", "۹"));
        ASCII.put("a", Arrays.asList("à", "á", "ả", "ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ", "â", "ấ", "ầ", "ẩ", "ẫ", "ậ",
                "ā", "ą", "å", "α", "ά", "ἀ", "ἁ", "ἂ", "ἃ", "ἄ", "ἅ", "ἆ", "ἇ", "ᾀ", "ᾁ", "ᾂ", "ᾃ",
                "ᾄ", "ᾅ", "ᾆ", "ᾇ", "ὰ", "ά", "ᾰ", "ᾱ", "ᾲ", "ᾳ", "ᾴ", "ᾶ", "ᾷ", "а", "أ", "အ", "ာ",
                "ါ", "ǻ", "ǎ", "ª", "ა", "अ", "ا"));
        ASCII.put("b", Arrays.asList("б", "β", "Ъ", "Ь", "ب", "ဗ", "ბ"));
        ASCII.put("c", Arrays.asList("ç", "ć", "č", "ĉ", "ċ"));
        ASCII.put("d", Arrays.asList("ď", "ð", "đ", "ƌ", "ȡ", "ɖ", "ɗ", "ᵭ", "ᶁ", "ᶑ", "д", "δ", "د", "ض", "ဍ", "ဒ", "დ"));
        ASCII.put("e", Arrays.asList("é", "è", "ẻ", "ẽ", "ẹ", "ê", "ế", "ề", "ể", "ễ", "ệ", "ë", "ē", "ę", "ě", "ĕ", "ė",
                "ε", "έ", "ἐ", "ἑ", "ἒ", "ἓ", "ἔ", "ἕ", "ὲ", "έ", "е", "ё", "э", "є", "ə", "ဧ", "ေ",
                "ဲ", "ე", "ए", "إ", "ئ"));
        ASCII.put("f", Arrays.asList("ф", "φ", "ف", "ƒ", "ფ"));
        ASCII.put("g", Arrays.asList("ĝ", "ğ", "ġ", "ģ", "г", "ґ", "γ", "ဂ", "გ", "گ"));
        ASCII.put("h", Arrays.asList("ĥ", "ħ", "η", "ή", "ح", "ه", "ဟ", "ှ", "ჰ"));
        ASCII.put("i", Arrays.asList("í", "ì", "ỉ", "ĩ", "ị", "î", "ï", "ī", "ĭ", "į", "ı", "ι", "ί", "ϊ", "ΐ", "ἰ", "ἱ",
                "ἲ", "ἳ", "ἴ", "ἵ", "ἶ", "ἷ", "ὶ", "ί", "ῐ", "ῑ", "ῒ", "ΐ", "ῖ", "ῗ", "і", "ї", "и",
                "ဣ", "ိ", "ီ", "ည်", "ǐ", "ი", "इ", "ی"));
        ASCII.put("j", Arrays.asList("ĵ", "ј", "Ј", "ჯ", "ج"));
        ASCII.put("k", Arrays.asList("ķ", "ĸ", "к", "κ", "Ķ", "ق", "ك", "က", "კ", "ქ", "ک"));
        ASCII.put("l", Arrays.asList("ł", "ľ", "ĺ", "ļ", "ŀ", "л", "λ", "ل", "လ", "ლ"));
        ASCII.put("m", Arrays.asList("м", "μ", "م", "မ", "მ"));
        ASCII.put("n", Arrays.asList("ñ", "ń", "ň", "ņ", "ŉ", "ŋ", "ν", "н", "ن", "န", "ნ"));
        ASCII.put("o", Arrays.asList("ó", "ò", "ỏ", "õ", "ọ", "ô", "ố", "ồ", "ổ", "ỗ", "ộ", "ơ", "ớ", "ờ", "ở", "ỡ", "ợ",
                "ø", "ō", "ő", "ŏ", "ο", "ὀ", "ὁ", "ὂ", "ὃ", "ὄ", "ὅ", "ὸ", "ό", "о", "و", "θ", "ို",
                "ǒ", "ǿ", "º", "ო", "ओ"));
        ASCII.put("p", Arrays.asList("п", "π", "ပ", "პ", "پ"));
        ASCII.put("q", Arrays.asList("ყ"));
        ASCII.put("r", Arrays.asList("ŕ", "ř", "ŗ", "р", "ρ", "ر", "რ"));
        ASCII.put("s", Arrays.asList("ś", "š", "ş", "с", "σ", "ș", "ς", "س", "ص", "စ", "ſ", "ს"));
        ASCII.put("t", Arrays.asList("ť", "ţ", "т", "τ", "ț", "ت", "ط", "ဋ", "တ", "ŧ", "თ", "ტ"));
        ASCII.put("u", Arrays.asList("ú", "ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử", "ữ", "ự", "û", "ū", "ů", "ű", "ŭ", "ų",
                "µ", "у", "ဉ", "ု", "ူ", "ǔ", "ǖ", "ǘ", "ǚ", "ǜ", "უ", "उ"));
        ASCII.put("v", Arrays.asList("в", "ვ", "ϐ"));
        ASCII.put("w", Arrays.asList("ŵ", "ω", "ώ", "ဝ", "ွ"));
        ASCII.put("x", Arrays.asList("χ", "ξ"));
        ASCII.put("y", Arrays.asList("ý", "ỳ", "ỷ", "ỹ", "ỵ", "ÿ", "ŷ", "й", "ы", "υ", "ϋ", "ύ", "ΰ", "ي", "ယ"));
        ASCII.put("z", Arrays.asList("ź", "ž", "ż", "з", "ζ", "ز", "ဇ", "ზ"));
        ASCII.put("aa", Arrays.asList("ع", "आ", "آ"));
        ASCII.put("ae", Arrays.asList("ä", "æ", "ǽ"));
        ASCII.put("ai", Arrays.asList("ऐ"));
        ASCII.put("at", Arrays.asList("@"));
        ASCII.put("ch", Arrays.asList("ч", "ჩ", "ჭ", "چ"));
        ASCII.put("dj", Arrays.asList("ђ", "đ"));
        ASCII.put("dz", Arrays.asList("џ", "ძ"));
        ASCII.put("ei", Arrays.asList("ऍ"));
        ASCII.put("gh", Arrays.asList("غ", "ღ"));
        ASCII.put("ii", Arrays.asList("ई"));
        ASCII.put("ij", Arrays.asList("ĳ"));
        ASCII.put("kh", Arrays.asList("х", "خ", "ხ"));
        ASCII.put("lj", Arrays.asList("љ"));
        ASCII.put("nj", Arrays.asList("њ"));
        ASCII.put("oe", Arrays.asList("ö", "œ", "ؤ"));
        ASCII.put("oi", Arrays.asList("ऑ"));
        ASCII.put("oii", Arrays.asList("ऒ"));
        ASCII.put("ps", Arrays.asList("ψ"));
        ASCII.put("sh", Arrays.asList("ш", "შ", "ش"));
        ASCII.put("shch", Arrays.asList("щ"));
        ASCII.put("ss", Arrays.asList("ß"));
        ASCII.put("sx", Arrays.asList("ŝ"));
        ASCII.put("th", Arrays.asList("þ", "ϑ", "ث", "ذ", "ظ"));
        ASCII.put("ts", Arrays.asList("ц", "ც", "წ"));
        ASCII.put("ue", Arrays.asList("ü"));
        ASCII.put("uu", Arrays.asList("ऊ"));
        ASCII.put("ya", Arrays.asList("я"));
        ASCII.put("yu", Arrays.asList("ю"));
        ASCII.put("zh", Arrays.asList("ж", "ჟ", "ژ"));
        ASCII.put("(c)", Arrays.asList("©"));
        ASCII.put("A", Arrays.asList("Á", "À", "Ả", "Ã", "Ạ", "Ă", "Ắ", "Ằ", "Ẳ", "Ẵ", "Ặ", "Â", "Ấ", "Ầ", "Ẩ", "Ẫ", "Ậ", "Å",
                "Ā", "Ą", "Α", "Ά", "Ἀ", "Ἁ", "Ἂ", "Ἃ", "Ἄ", "Ἅ", "Ἆ", "Ἇ", "ᾈ", "ᾉ", "ᾊ", "ᾋ", "ᾌ", "ᾍ",
                "ᾎ", "ᾏ", "Ᾰ", "Ᾱ", "Ὰ", "Ά", "ᾼ", "А", "Ǻ", "Ǎ"));
        ASCII.put("B", Arrays.asList("Б", "Β", "ब"));
        ASCII.put("C", Arrays.asList("Ç", "Ć", "Č", "Ĉ", "Ċ"));
        ASCII.put("D", Arrays.asList("Ď", "Ð", "Đ", "Ɖ", "Ɗ", "Ƌ", "ᴅ", "ᴆ", "Д", "Δ"));
        ASCII.put("E", Arrays.asList("É", "È", "Ẻ", "Ẽ", "Ẹ", "Ê", "Ế", "Ề", "Ể", "Ễ", "Ệ", "Ë", "Ē", "Ę", "Ě", "Ĕ", "Ė", "Ε",
                "Έ", "Ἐ", "Ἑ", "Ἒ", "Ἓ", "Ἔ", "Ἕ", "Έ", "Ὲ", "Е", "Ё", "Э", "Є", "Ə"));
        ASCII.put("F", Arrays.asList("Ф", "Φ"));
        ASCII.put("G", Arrays.asList("Ğ", "Ġ", "Ģ", "Г", "Ґ", "Γ"));
        ASCII.put("H", Arrays.asList("Η", "Ή", "Ħ"));
        ASCII.put("I", Arrays.asList("Í", "Ì", "Ỉ", "Ĩ", "Ị", "Î", "Ï", "Ī", "Ĭ", "Į", "İ", "Ι", "Ί", "Ϊ", "Ἰ", "Ἱ", "Ἳ", "Ἴ",
                "Ἵ", "Ἶ", "Ἷ", "Ῐ", "Ῑ", "Ὶ", "Ί", "И", "І", "Ї", "Ǐ", "ϒ"));
        ASCII.put("K", Arrays.asList("К", "Κ"));
        ASCII.put("L", Arrays.asList("Ĺ", "Ł", "Л", "Λ", "Ļ", "Ľ", "Ŀ", "ल"));
        ASCII.put("M", Arrays.asList("М", "Μ"));
        ASCII.put("N", Arrays.asList("Ń", "Ñ", "Ň", "Ņ", "Ŋ", "Н", "Ν"));
        ASCII.put("O", Arrays.asList("Ó", "Ò", "Ỏ", "Õ", "Ọ", "Ô", "Ố", "Ồ", "Ổ", "Ỗ", "Ộ", "Ơ", "Ớ", "Ờ", "Ở", "Ỡ", "Ợ", "Ø",
                "Ō", "Ő", "Ŏ", "Ο", "Ό", "Ὀ", "Ὁ", "Ὂ", "Ὃ", "Ὄ", "Ὅ", "Ὸ", "Ό", "О", "Θ", "Ө", "Ǒ", "Ǿ"));
        ASCII.put("P", Arrays.asList("П", "Π"));
        ASCII.put("R", Arrays.asList("Ř", "Ŕ", "Р", "Ρ", "Ŗ"));
        ASCII.put("S", Arrays.asList("Ş", "Ŝ", "Ș", "Š", "Ś", "С", "Σ"));
        ASCII.put("T", Arrays.asList("Ť", "Ţ", "Ŧ", "Ț", "Т", "Τ"));
        ASCII.put("U", Arrays.asList("Ú", "Ù", "Ủ", "Ũ", "Ụ", "Ư", "Ứ", "Ừ", "Ử", "Ữ", "Ự", "Û", "Ū", "Ů", "Ű", "Ŭ", "Ų", "У",
                "Ǔ", "Ǖ", "Ǘ", "Ǚ", "Ǜ"));
        ASCII.put("V", Arrays.asList("В"));
        ASCII.put("W", Arrays.asList("Ω", "Ώ", "Ŵ"));
        ASCII.put("X", Arrays.asList("Χ", "Ξ"));
        ASCII.put("Y", Arrays.asList("Ý", "Ỳ", "Ỷ", "Ỹ", "Ỵ", "Ÿ", "Ῠ", "Ῡ", "Ὺ", "Ύ", "Ы", "Й", "Υ", "Ϋ", "Ŷ"));
        ASCII.put("Z", Arrays.asList("Ź", "Ž", "Ż", "З", "Ζ"));
        ASCII.put("AE", Arrays.asList("Ä", "Æ", "Ǽ"));
        ASCII.put("CH", Arrays.asList("Ч"));
        ASCII.put("DJ", Arrays.asList("Ђ"));
        ASCII.put("DZ", Arrays.asList("Џ"));
        ASCII.put("GX", Arrays.asList("Ĝ"));
        ASCII.put("HX", Arrays.asList("Ĥ"));
        ASCII.put("IJ", Arrays.asList("Ĳ"));
        ASCII.put("JX", Arrays.asList("Ĵ"));
        ASCII.put("KH", Arrays.asList("Х"));
        ASCII.put("LJ", Arrays.asList("Љ"));
        ASCII.put("NJ", Arrays.asList("Њ"));
        ASCII.put("OE", Arrays.asList("Ö", "Œ"));
        ASCII.put("PS", Arrays.asList("Ψ"));
        ASCII.put("SH", Arrays.asList("Ш"));
        ASCII.put("SHCH", Arrays.asList("Щ"));
        ASCII.put("SS", Arrays.asList("ẞ"));
        ASCII.put("TH", Arrays.asList("Þ"));
        ASCII.put("TS", Arrays.asList("Ц"));
        ASCII.put("UE", Arrays.asList("Ü"));
        ASCII.put("YA", Arrays.asList("Я"));
        ASCII.put("YU", Arrays.asList("Ю"));
        ASCII.put("ZH", Arrays.asList("Ж"));
        ASCII.put(" ", Arrays.asList("\\xC2\\xA0", "\\xE2\\x80\\x80", "\\xE2\\x80\\x81", "\\xE2\\x80\\x82", "\\xE2\\x80\\x83",
                "\\xE2\\x80\\x84", "\\xE2\\x80\\x85", "\\xE2\\x80\\x86", "\\xE2\\x80\\x87", "\\xE2\\x80\\x88",
                "\\xE2\\x80\\x89", "\\xE2\\x80\\x8A", "\\xE2\\x80\\xAF", "\\xE2\\x81\\x9F", "\\xE3\\x80\\x80"));
    }

}
