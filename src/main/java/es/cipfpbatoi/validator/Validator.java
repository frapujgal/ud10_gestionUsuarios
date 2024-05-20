package es.cipfpbatoi.validator;

public class Validator {

	private static final String NUMERIC_REGEXP = "\\d+";
	
	private static final String ALFABETIC_FIRST_UPPERCASE_REGEXP = "^[A-Z][a-zA-Z ]*$";
	
	private static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	private static final String POSTALCODE_REGEXP = "^(0[1-9]|[1-4][0-9]|5[0-2])[0-9]{3}$";
	
	private static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z\\d!@#$%^&*(),.?\":{}|<>]{5,20}$";
	
	private static final String PHONE_REGEXP = "^(0034|\\+34|34)[67][0-9]{8}$";
	
	public static boolean isValidPassword(String param) {
		return isNotEmptyOrNull(param) && param.matches(PASSWORD_REGEXP);
	}
	
	public static boolean isValidDni(String param) {
		if(!isNotEmptyOrNull(param) || !isValidNumberOfLength(param, 9)) {
			return false;
		}
		
		char[] LETTERS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
		
        String numberPart = param.substring(0, 8);
        char letterPart = param.charAt(8);

        // Verificar que los primeros 8 caracteres sean dígitos
        if (!numberPart.matches("\\d{8}")) {
            return false;
        }

        // Convertir la parte numérica a un entero
        int number = Integer.parseInt(numberPart);

        // Calcular la letra correspondiente
        char expectedLetter = LETTERS[number % 23];

        // Comparar la letra calculada con la letra proporcionada
        return Character.toUpperCase(expectedLetter) == letterPart;
		//return true;
	}
	
	public static boolean isValidEmail(String param) {
		return isNotEmptyOrNull(param) && param.matches(EMAIL_REGEXP);
	}
	
	public static boolean isValidPhone(String param) {
		return isNotEmptyOrNull(param) && 
				param.matches(PHONE_REGEXP);
	}
	
	public static boolean isValidPostalCode(String param) {
		return isNotEmptyOrNull(param) && 
				param.matches(POSTALCODE_REGEXP) &&
				isValidNumberOfLength(param, 5);
	}
	
	public static boolean isValidNumberOfLength(String param, int minLength, int maxLength) {
		return isNotEmptyOrNull(param) && 
				param.length() <= maxLength && 
				param.length() >= minLength;
	}
	
	public static boolean isValidNumberOfLength(String param, int length) {
		return isNotEmptyOrNull(param) && 
				param.length() == length;
	}
	
	public static boolean isValidText(String param) {
		return isNotEmptyOrNull(param) && param.matches(ALFABETIC_FIRST_UPPERCASE_REGEXP);
	}
	
	private static boolean isNotEmptyOrNull(String param) {
        return param != null && !param.isEmpty();
    }

}


