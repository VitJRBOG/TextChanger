package chars

import "strings"

var cyrChars = []string{"у", "е", "Е", "х", "Х", "о", "О", "р", "Р", "а", "А", "с", "С"}
var latChars = []string{"y", "e", "E", "x", "X", "o", "O", "p", "P", "a", "A", "c", "C"}

// CyrToLatForAll заменяет кириллические символы на латинские во всех словах
func CyrToLatForAll(origText string) string {
	changedText := origText
	for i, cyrChar := range cyrChars {
		changedText = strings.ReplaceAll(changedText, cyrChar, latChars[i])
	}

	return changedText
}

// CyrToLatForKeywords заменяет кириллические символы на латинские в словах из списка
func CyrToLatForKeywords(origText string, keywords []string) string {
	changedText := origText

	for _, keyword := range keywords {
		if len(keyword) > 0 {
			changedKeyword := keyword

			for i, cyrChar := range cyrChars {
				changedKeyword = strings.ReplaceAll(changedKeyword, cyrChar, latChars[i])
			}

			changedText = strings.ReplaceAll(changedText, keyword, changedKeyword)
		}
	}

	return changedText
}

// LatToCyr заменяет латинские символы на кириллические во всех словах
func LatToCyr(origText string) string {
	changedText := origText
	for i, latChar := range latChars {
		changedText = strings.ReplaceAll(changedText, latChar, cyrChars[i])
	}

	return changedText
}
