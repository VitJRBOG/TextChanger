package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func getKeywords() []string {
	keywordsString := readKeywordsFile()
	keywordsList := parseKeywordsString(keywordsString)
	return keywordsList
}

func readKeywordsFile() string {
	file, err := os.Open("keywords.txt")
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
