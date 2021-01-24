package censorship

import (
	"github.com/VitJRBOG/TextChanger/file_manager"
	"strings"
)

func CensorText(origText string, obsceneWords []string) string {
	changedText := origText

	for _, obsceneWord := range obsceneWords {
		if len(obsceneWord) > 1 {
			charsFromObsceneWord := strings.Split(obsceneWord, "")
			censoredObsceneWord := charsFromObsceneWord[0]
			for i := 1; i < len(charsFromObsceneWord); i++ {
				censoredObsceneWord += "*"
			}
			changedText = strings.ReplaceAll(changedText, obsceneWord, censoredObsceneWord)
		}
	}

	return changedText
}

// CheckFileWithKeywords проверяет существование файла obscene_words.txt,
// если файла нет, то создает его
func CheckFileWithObsceneWords() {
	file_manager.CheckTextFile(file_manager.GetPathToCurrentDir() + "/obscene_words.txt")
}

// GetObsceneWords получает из файла obscene_words.txt список слов для замены символов
func GetObsceneWords() []string {
	obsceneWordsString := file_manager.GetTextFromFile(file_manager.GetPathToCurrentDir() + "/obscene_words.txt")
	obsceneWordsList := parseObsceneWordsString(obsceneWordsString)
	return obsceneWordsList
}

func parseObsceneWordsString(obsceneWordsString string) []string {
	obsceneWordsList := strings.Split(obsceneWordsString, "\n")
	return obsceneWordsList
}
