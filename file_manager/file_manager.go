package file_manager

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
)

func CheckFileExistence(path string) bool {
	path = filepath.FromSlash(path)
	if _, err := os.Stat(path); os.IsNotExist(err) {
		return false
	}
	return true
}

func WriteToFile(path string, data []byte) error {
	err := ioutil.WriteFile(path, data, 0644)
	if err != nil {
		return err
	}

	return nil
}

func ReadJSON(path string) ([]byte, error) {
	rawData, err := ioutil.ReadFile(path)
	if err != nil {
		return nil, err
	}

	return rawData, nil
}

// CheckTextFile проверяет текстовый файл по указанному пути,
// если файл отсутствует, то функция создает его
func CheckTextFile(path string) {
	path = filepath.FromSlash(path)
	if _, err := os.Stat(path); os.IsNotExist(err) {
		b := []byte("")
		err = ioutil.WriteFile(path, b, 0644)
		if err != nil {
			panic(err.Error())
		}
	}
}

func GetTextFromFile(path string) string {
	path = filepath.FromSlash(path)
	file, err := os.Open(path)
	defer func() {
		err := file.Close()
		if err != nil {
			panic(err.Error())
		}
	}()
	if err != nil {
		panic(err.Error())
	}

	scanner := bufio.NewScanner(file)

	var textFromFile string
	for scanner.Scan() {
		textFromFile += fmt.Sprintf("%v\n", scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		panic(err.Error())
	}

	return textFromFile
}

func GetPathToCurrentDir() string {
	path, err := filepath.Abs(filepath.Dir(os.Args[0]))
	if err != nil {
		panic(err.Error())
	}
	return path
}
