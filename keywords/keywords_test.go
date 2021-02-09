package keywords

import (
	"os"
	"strings"
	"testing"
)

// WARNING! WARNING! WARNING!
//
// Этот тест удаляет ресурсный файл со списком ключевых слов!
// Перед запуском необходимо сделать копию файла "keywords.txt"
func TestCheckFileWithKeywords(t *testing.T) {
	path := "../keywords.txt"
	//

	CheckFileWithKeywords()

	if _, err := os.Stat(path); os.IsNotExist(err) {
		t.Error("For:", path,
			"\nfile was no created")
	}

	///
	err := os.Remove(path)
	if err != nil {
		panic(err.Error())
	}
}

// WARNING! WARNING! WARNING!
//
// Этот тест удаляет ресурсный файл со списком ключевых слов!
// Перед запуском необходимо сделать копию файла "keywords.txt"
func TestGetKeywords(t *testing.T) {
	path := "../keywords.txt"
	s := "Test\nlist\nwith\nwords\n"

	f, err := os.Create(path)
	if err != nil {
		panic(err.Error())
	}

	defer func() {
		err := f.Close()
		if err != nil {
			panic(err.Error())
		}
	}()

	_, err = f.WriteString(s)

	if err != nil {
		panic(err.Error())
	}
	//

	r := GetKeywords()
	result := strings.Join(r, "\n")

	e := "Test\nlist\nwith\nwords\n"
	if result != e {
		t.Error("For:", path,
			"\ngot:", result,
			"\nexpected:", e)
	}

	//
	err = os.Remove(path)
	if err != nil {
		panic(err.Error())
	}
}
