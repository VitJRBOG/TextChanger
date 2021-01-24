package censorship

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
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
	path := filepath.FromSlash(getPathToCurrentDir() + "/obscene_words.txt")
	if _, err := os.Stat(path); os.IsNotExist(err) {
		b := []byte("")
		err = ioutil.WriteFile(path, b, 0644)
		if err != nil {
			panic(err.Error())
		}
	}
}

// GetObsceneWords получает из файла obscene_words.txt список слов для замены символов
func GetObsceneWords() []string {
	obsceneWordsString := readObsceneWordsFile()
	obsceneWordsList := parseObsceneWordsString(obsceneWordsString)
	return obsceneWordsList
}

func readObsceneWordsFile() string {
	path := filepath.FromSlash(getPathToCurrentDir() + "/obscene_words.txt")
	file, err := os.Open(path)
	defer file.Close()
	if err != nil {
		panic(err.Error())
	}

	scanner := bufio.NewScanner(file)

	var obsceneWordsString string
	for scanner.Scan() {
		obsceneWordsString += fmt.Sprintf("%v\n", scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		panic(err.Error())
	}

	return obsceneWordsString
}

func parseObsceneWordsString(obsceneWordsString string) []string {
	obsceneWordsList := strings.Split(obsceneWordsString, "\n")
	return obsceneWordsList
}

func getPathToCurrentDir() string {
	path, err := filepath.Abs(filepath.Dir(os.Args[0]))
	if err != nil {
		panic(err.Error())
	}
	return path
}
