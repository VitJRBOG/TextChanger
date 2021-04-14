package chars

import "strings"

var cyrChars = []string{"у", "е", "Е", "х", "Х", "о", "О", "р", "Р", "а", "А", "с", "С"}
var latChars = []string{"y", "e", "E", "x", "X", "o", "O", "p", "P", "a", "A", "c", "C"}

// CyrToLatForAll заменяет кириллические символы на латинские во всех словах
func CyrToLatForAll(origText string) (int, string) {
	changesNumber := 0

	chars := strings.Split(origText, "")

	for i := 0; i < len(cyrChars); i++ {
		for n := 0; n < len(chars); n++ {
			if chars[n] == cyrChars[i] {
				chars[n] = latChars[i]
				changesNumber++
			}
		}
	}

	changedText := strings.Join(chars, "")

	return changesNumber, changedText
}

// CyrToLatForKeywords заменяет кириллические символы на латинские в словах из списка
func CyrToLatForKeywords(origText string, keywords []string) (int, string) {
	changesNumber := 0

	words := strings.Split(origText, " ")

	for i := 0; i < len(words); i++ {
		for _, keyword := range keywords {
			if len(keyword) > 0 {
				if strings.Contains(words[i], keyword) {
					x := 0
					x, words[i] = CyrToLatForAll(words[i])
					changesNumber += x
				}
			}
		}
	}

	changedText := strings.Join(words, " ")

	return changesNumber, changedText
}

// LatToCyr заменяет латинские символы на кириллические во всех словах
func LatToCyr(origText string) (int, string) {
	changesNumber := 0

	chars := strings.Split(origText, "")

	for i := 0; i < len(latChars); i++ {
		for n := 0; n < len(chars); n++ {
			if chars[n] == latChars[i] {
				chars[n] = cyrChars[i]
				changesNumber++
			}
		}
	}

	changedText := strings.Join(chars, "")

	return changesNumber, changedText
}
