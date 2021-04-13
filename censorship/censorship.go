package censorship

import (
	"strings"

	"github.com/VitJRBOG/TextChanger/tools"
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
	tools.CheckTextFile(tools.GetPathToCurrentDir() + "/obscene_words.txt")
}

// GetObsceneWords получает из файла obscene_words.txt список слов для замены символов
func GetObsceneWords() []string {
	obsceneWordsString := tools.GetTextFromFile(tools.GetPathToCurrentDir() + "/obscene_words.txt")
	obsceneWordsList := parseObsceneWordsString(obsceneWordsString)
	return obsceneWordsList
}

func parseObsceneWordsString(obsceneWordsString string) []string {
	obsceneWordsList := strings.Split(obsceneWordsString, "\n")
	return obsceneWordsList
}
