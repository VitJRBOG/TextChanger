class Censorship {

    char[] simpleWords(char[] arrayCharText) {

        try {
            String[] arrayStringObsceneWords = {
                    "хуй", "пиз", "хре",
                    "хер", "шлю", "муд",
                    "еба", "бля", "хуе",
                    "сук", "жоп", "пид",
                    "чле", "ебл", "уеб",
                    "секс", "трах", "ваги",
                    "блеа"
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                if (i < arrayCharText.length - 2) {
                    for (int j = 0; j < arrayStringObsceneWords.length; j++) {
                        String varStringCondition = arrayStringObsceneWords[j];
                        if (j < arrayStringObsceneWords.length - 5) {
                            if (arrayCharText[i] == varStringCondition.charAt(0) &&
                                    arrayCharText[i + 1] == varStringCondition.charAt(1) &&
                                    arrayCharText[i + 2] == varStringCondition.charAt(2)) {
                                arrayCharText[i + 1] = '*';
                                arrayCharText[i + 2] = '*';
                            }
                        } else {
                            if (arrayCharText[i] == varStringCondition.charAt(0) &&
                                    arrayCharText[i + 1] == varStringCondition.charAt(1) &&
                                    arrayCharText[i + 2] == varStringCondition.charAt(2) &&
                                    arrayCharText[i + 3] == varStringCondition.charAt(3)) {
                                arrayCharText[i + 1] = '*';
                                arrayCharText[i + 2] = '*';
                                arrayCharText[i + 3] = '*';
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [simpleWords] Error. " + e.getMessage() + ".");
        }

        return arrayCharText;
    }

}
