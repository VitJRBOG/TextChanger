package file_manager

import (
	"os"
	"testing"
)

func TestCheckTextFile(t *testing.T) {
	path := "test.txt"

	CheckTextFile(path)

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

func TestGetTextFromFile(t *testing.T) {
	path := "test.txt"
	s := "Test text\n"

	///
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
	///

	result := GetTextFromFile(path)
	e := "Test text\n"

	if result != e {
		t.Error("For:", path,
			"\ngot:", result,
			"\nexpected:", e)
	}

	///
	err = os.Remove(path)
	if err != nil {
		panic(err.Error())
	}
}
