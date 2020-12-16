package main

import "strings"

var cyrChars = []string{"у", "е", "Е", "х", "Х", "о", "О", "р", "Р", "а", "А", "с", "С"}
var latChars = []string{"y", "e", "E", "x", "X", "o", "O", "p", "P", "a", "A", "c", "C"}

func cyrToLatForAll(origText string) string {
	changedText := origText
	for i, cyrChar := range cyrChars {
		changedText = strings.ReplaceAll(changedText, cyrChar, latChars[i])
	}

	return changedText
}

func cyrToLatForKeywords(origText string) string {
	var changedText string

	// TODO

	return changedText
}

func latToCyr(origText string) string {
	changedText := origText
	for i, latChar := range latChars {
		changedText = strings.ReplaceAll(changedText, latChar, cyrChars[i])
	}

	return changedText
}
