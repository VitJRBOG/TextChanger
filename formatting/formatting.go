package formatting

import (
	"regexp"
	"strings"
)

// FormatText форматирует текст: убирает капс, удаляет лишние пробелы,
// переводит буквы после знаков конца предложения и начала новой строки в верхний регистр
func FormatText(origText string) string {
	if len(origText) > 0 {
		changedText := toLowerCaseAllChars(origText)
		changedText = removeRedundantSpaces(changedText)

		charsFromText := strings.Split(changedText, "")

		charsFromText = addSpacesAfterPunctuationMarks(charsFromText)
		charsFromText = removeSpacesBeforePunctuationMarks(charsFromText)
		charsFromText = toUpperCaseFirstChar(charsFromText)
		charsFromText = toUpperCaseCharsAfterPunctuationMarksAndNewLine(charsFromText)
		changedText = strings.Join(charsFromText, "")

		return changedText
	}

	return origText
}

func toLowerCaseAllChars(text string) string {
	text = strings.ToLower(text)

	return text
}

func removeRedundantSpaces(text string) string {
	for strings.Contains(text, "  ") {
		text = strings.ReplaceAll(text, "  ", " ")
	}

	return text
}

func addSpacesAfterPunctuationMarks(chars []string) []string {
	for i := 0; i < len(chars)-1; i++ {
		match, err := regexp.MatchString("[.!?]", chars[i])
		if err != nil {
			panic(err.Error())
		}
		if match {
			match, err := regexp.MatchString("[.!?]", chars[i+1])
			if err != nil {
				panic(err.Error())
			}
			if match {
				continue
			} else {
				if chars[i+1] != " " {
					var newSlice []string
					newSlice = append(newSlice, chars[:i+1]...)
					newSlice = append(newSlice, " ")
					newSlice = append(newSlice, chars[i+1:]...)
					chars = newSlice
				}
			}
		}
	}

	return chars
}

func removeSpacesBeforePunctuationMarks(chars []string) []string {
	for i := 1; i < len(chars); i++ {
		match, err := regexp.MatchString("[.!?]", chars[i])
		if err != nil {
			panic(err.Error())
		}
		if match {
			if chars[i-1] == " " {
				chars[i-1] = ""
				continue
			}
		}
	}

	return chars
}

func toUpperCaseFirstChar(chars []string) []string {
	chars[0] = strings.ToUpper(chars[0])

	return chars
}

func toUpperCaseCharsAfterPunctuationMarksAndNewLine(chars []string) []string {
	for i := 0; i < len(chars); i++ {
		match, err := regexp.MatchString("[.!?\n]", chars[i])
		if err != nil {
			panic(err.Error())
		}
		if match {
			for n := 0; (n+i) < len(chars) && n < 3; n++ {
				match, err = regexp.MatchString("[а-яa-z]", chars[i+n])
				if err != nil {
					panic(err.Error())
				}
				if match {
					chars[i+n] = strings.ToUpper(chars[i+n])
					break
				}
			}
		}
	}

	return chars
}
