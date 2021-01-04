package keywords

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
	"strings"
)

// CheckFileWithKeywords проверяет существование файла keywords.txt,
// если файла нет, то создает его
func CheckFileWithKeywords() {
	path := filepath.FromSlash(getPathToCurrentDir() + "/keywords.txt")
	if _, err := os.Stat(path); os.IsNotExist(err) {
		b := []byte("")
		err = ioutil.WriteFile(path, b, 0644)
		if err != nil {
			panic(err.Error())
		}
	}
}

// GetKeywords получает из файла keywords.txt список слов для замены символов
func GetKeywords() []string {
	keywordsString := readKeywordsFile()
	keywordsList := parseKeywordsString(keywordsString)
	return keywordsList
}

func readKeywordsFile() string {
	path := filepath.FromSlash(getPathToCurrentDir() + "/keywords.txt")
	file, err := os.Open(path)
	defer file.Close()
	if err != nil {
		panic(err.Error())
	}

	scanner := bufio.NewScanner(file)

	var keywordsString string
	for scanner.Scan() {
		keywordsString += fmt.Sprintf("%v\n", scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		panic(err.Error())
	}

	return keywordsString
}

func parseKeywordsString(keywordsString string) []string {
	keywordsList := strings.Split(keywordsString, "\n")
	return keywordsList
}

func getPathToCurrentDir() string {
	path, err := filepath.Abs(filepath.Dir(os.Args[0]))
	if err != nil {
		panic(err.Error())
	}
	return path
}
