package pg.share;

import java.text.NumberFormat;
import java.util.Scanner;

public class NumberToWordsConverter {
	public static final String[] units = { "", "এক", "দুই", "তিন", "চার",
			"পাচ", "ছয়", "সাত", "আট", "নয়", "দশ", "এগারো", "বার",
			"তের", "চৌদ্দ", "পনের", "যোল", "সতের",
			"আঠারো", "উনিশ"}; 

			public static final String[] tens = { 
		
					"টুয়েন্টি", 	// 2
					"একোশ", 	// 21
					"বাইশ", 	// 22
					"তেইশ", 	// 23
					"চব্বিশ", 	// 24
					"পঁচিশ", 	// 25
					"ছাব্বিশ", 	// 26
					"সাতাশ", 	// 27
					"আঠাশ", 	// 28
					"ঊনত্রিশ", // 29
					"ত্রিশ", 	// 30
					"একত্রিশ", 	// 31
					"বত্রিশ", 	// 32
					"তেত্রিশ", 	// 33
					"চৌত্রিশ", 	// 34
					"পয়ত্রিশ", 	// 35
					"ছত্রিশ", 	// 36
					"সাইত্রিশ", 	// 37
					"আটত্রিশ", 	// 38
					"ঊনচল্লিশ", 	// 39
					"চল্লিশ", 	// 40
					"একচল্লিশ", 	// 41
					"বেয়াল্লিশ", 	// 42
					"তেতাল্লিশ", 	// 43
					"চোয়াল্লিশ", 	// 44
					"পঁয়তাল্লিশ", 	// 45
					"ছেচল্লিশ", 	// 46
					"সতচল্লিশ", 	// 47
					"আটচল্লিশ", 	// 48
					"ঊনপঞ্চাশ", 	// 49
					"পঞ্চাশ", 	// 50
					"একান্ন", 	// 51
					"বাহান্ন", 	// 52
					"তেপান্ন", 	// 53
					"চোয়ান্ন", 	// 54
					"পঁঞ্চান্ন", 	// 55
					"ছাপ্পান্ন", 	// 56
					"সাতান্ন", 	// 57
					"আটান্ন", 	// 58
					"ঊনষাট", 	// 59
					"ষাট", 	// 60
					"একষট্টি", 	// 61
					"বাষট্টি", 	// 62
					"তেষট্টি", 	// 63
					"চৌষট্টি", 	// 64
					"পঁয়ষট্টি", 	// 65
					"ছেষট্টি", 	// 66
					"সতাষট্টি", 	// 67
					"আটষট্টি", 	// 68
					"ঊনসত্তর", 	// 69
					"সত্তর",// 70
					"একাত্তর",// 71
					"বাহাত্তর",// 72
					"তেহাত্তর",// 73
					"চোয়াত্তর",// 74
					"পঁচাত্তর",// 75
					"ছিয়াত্তর",// 76
					"সাতাত্তর",// 77
					"আটাত্তর",// 78
					"ঊনআশি",// 79
					"আশি", 	// 80
					"একাশি", 	// 81
					"বিরাশি", 	// 82
					"তিরাশি", 	// 83
					"চোরাশি", 	// 84
					"পঁচাশি", 	// 85
					"ছিয়াশি", 	// 86
					"সাতাশি", 	// 87
					"অটাশি", 	// 88
					"ঊননব্বই", 	// 89
					"নব্বই", 	// 90
					"একানব্বই", 	// 91
					"বিরানব্বই", 	// 92
					"তিরানব্বই", 	// 93
					"চুরানব্বই", 	// 94
					"পঁচানব্বই", 	// 95
					"ছিয়ানব্বই", 	// 96
					"সাতানব্বই", 	// 97
					"আটানব্বই", 	// 98
					"নিরানব্বই", 	// 99
					"একশ" 	// 100
			};

			public static String convert(final int n) {
				if (n < 0) {
					return "Minus " + convert(-n);
				}

				if (n < 20) {
					return units[n];
				}

				if (n < 100) {
					System.out.println("n-20 "+(n-20));
					return tens[n-20];
					//return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
				}

				if (n < 1000) {
					return units[n / 100] + " শত" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
				}

				if (n < 100000) {
					return convert(n / 1000) + " হাজার" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
				}

				if (n < 10000000) {
					return convert(n / 100000) + " লাখ" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
				}

				return convert(n / 10000000) + " কোটি" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
			}

			public String getWord(int n) {

				System.out.println(NumberFormat.getInstance().format(n) + "='" + convert(n) + "'");
				
				return NumberFormat.getInstance().format(n) + "='" + convert(n) + "'";
			}

}
