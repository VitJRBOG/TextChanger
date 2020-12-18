package main

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
	"strings"
)

func checkFileWithKeywords() {
	path := filepath.FromSlash(getPathToCurrentDir() + "/keywords.txt")
	if _, err := os.Stat(path); os.IsNotExist(err) {
		b := []byte("")
		err = ioutil.WriteFile(path, b, 0644)
		if err != nil {
			panic(err.Error())
		}
	}
}

func getKeywords() []string {
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
