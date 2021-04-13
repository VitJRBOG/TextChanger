package keywords

import (
	"strings"

	"github.com/VitJRBOG/TextChanger/tools"
)

// CheckFileWithKeywords проверяет существование файла keywords.txt,
// если файла нет, то создает его
func CheckFileWithKeywords() {
	tools.CheckTextFile(tools.GetPathToCurrentDir() + "/keywords.txt")
}

// GetKeywords получает из файла keywords.txt список слов для замены символов
func GetKeywords() []string {
	keywordsString := tools.GetTextFromFile(tools.GetPathToCurrentDir() + "/keywords.txt")
	keywordsList := parseKeywordsString(keywordsString)
	return keywordsList
}

func parseKeywordsString(keywordsString string) []string {
	keywordsList := strings.Split(keywordsString, "\n")
	return keywordsList
}
