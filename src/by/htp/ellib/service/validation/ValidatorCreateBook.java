package by.htp.ellib.service.validation;

public class ValidatorCreateBook {

	public static boolean  isCorrect (String author, String name, int year, String genre, int price) {
		if ((genre.equals("novel")!=true ) && (genre.equals("comedy")!=true) &&(genre.equals("story")!=true)){
			return false;
		}else {

			return isLogicCorrect(author) && isIntCorrect(year)&&isLogicCorrect(name) && isIntCorrect(price)&& isLogicCorrect(genre);

		}
	}

	private static boolean isLogicCorrect (String str) {
		if (str.length()<=2) {
			return false;
		}
		return true;
	}

	private static boolean isIntCorrect (int num) {
		if (num<0) {
			return false;
		}
		return true;
	}

}
