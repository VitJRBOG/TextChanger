package keywords

import (
	"github.com/VitJRBOG/TextChanger/file_manager"
	"strings"
)

// CheckFileWithKeywords проверяет существование файла keywords.txt,
// если файла нет, то создает его
func CheckFileWithKeywords() {
	file_manager.CheckTextFile(file_manager.GetPathToCurrentDir() + "/keywords.txt")
}

// GetKeywords получает из файла keywords.txt список слов для замены символов
func GetKeywords() []string {
	keywordsString := file_manager.GetTextFromFile(file_manager.GetPathToCurrentDir() + "/keywords.txt")
	keywordsList := parseKeywordsString(keywordsString)
	return keywordsList
}

func parseKeywordsString(keywordsString string) []string {
	keywordsList := strings.Split(keywordsString, "\n")
	return keywordsList
}
