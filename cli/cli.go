package cli

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"

	"github.com/VitJRBOG/TextChanger/censorship"
	"github.com/VitJRBOG/TextChanger/chars"
	"github.com/VitJRBOG/TextChanger/formatting"
	"github.com/VitJRBOG/TextChanger/keywords"
	"github.com/atotto/clipboard"
)

func ShowCLI() {
	initCLI()
}

func initCLI() {
	numberMenuItems := makeMainMenu()
	for {
		userAnswer := getMenuItemNumberFromUser()
		interpretationUserAnswer(userAnswer, numberMenuItems)
	}
}

func makeMainMenu() int {
	actions := []string{
		"Cyr to Lat (all)", "Cyr to Lat", "Lat to Cyr", "Censorship", "Formatting",
	}

	for i, item := range actions {
		fmt.Printf("%d == %s\n", i+1, item)
	}

	fmt.Println("0 == Exit")

	return len(actions)
}

func getMenuItemNumberFromUser() string {
	fmt.Println("-- Enter the number of menu item and press «Enter» --")

	var userInput string
	in := bufio.NewReader(os.Stdin)
	userInput, err := in.ReadString('\n')
	if err != nil {
		panic(err.Error())
	}

	if len(userInput) > 0 {
		u := strings.Split(userInput, "")
		u = u[:len(u)-1]
		userInput = strings.Join(u, "")
	}

	return userInput
}

func interpretationUserAnswer(userAnswer string, numberMenuItems int) {
	if len(userAnswer) == 0 {
		fmt.Println("Your input is empty...")
		return
	}

	menuItemNumber, err := strconv.Atoi(userAnswer)
	if err != nil {
		if strings.Contains(strings.ToLower(err.Error()), "invalid syntax") {
			fmt.Println("Number of menu item must be integer...")
			return
		} else {
			panic(err.Error())
		}
	}

	if menuItemNumber < numberMenuItems+1 {
		switch menuItemNumber {
		case 1:
			cyrToLatForAll()
		case 2:
			cyrToLatForKeywords()
		case 3:
			latToCyr()
		case 4:
			goCensorship()
		case 5:
			goFormatting()
		case 0:
			os.Exit(0)
		}
	} else {
		fmt.Println("Menu item number out of range...")
		return
	}
}

func cyrToLatForAll() {
	origText := getDataFromClipboard()
	changedText := chars.CyrToLatForAll(origText)
	setDataToClipboard(changedText)
	fmt.Println("Result of CyrToLat(all)-replacing has been copied in clipboard...")
}

func cyrToLatForKeywords() {
	keywords := keywords.GetKeywords()
	if len(keywords) > 0 && keywords[0] != "" {
		origText := getDataFromClipboard()
		changedText := chars.CyrToLatForKeywords(origText, keywords)
		setDataToClipboard(changedText)
	} else {
		fmt.Println("The keywords file (keywords.txt) is empty. " +
			"Letters replacing in keywords is not possible.")
	}
	fmt.Println("Result of CyrToLat-replacing has been copied in clipboard...")
}

func latToCyr() {
	origText := getDataFromClipboard()
	changedText := chars.LatToCyr(origText)
	setDataToClipboard(changedText)
	fmt.Println("Result of LatToCyr-replacing has been copied in clipboard...")
}

func goCensorship() {
	obsceneWords := censorship.GetObsceneWords()
	if len(obsceneWords) > 0 && obsceneWords[0] != "" {
		origText := getDataFromClipboard()
		changedText := censorship.CensorText(origText, obsceneWords)
		setDataToClipboard(changedText)
	} else {
		fmt.Println("The file with obscene words (obscene_words.txt) is empty. " +
			"Censorship is not possible.")
	}
	fmt.Println("Result of censorship has been copied in clipboard...")
}

func goFormatting() {
	origText := getDataFromClipboard()
	changedText := formatting.FormatText(origText)
	setDataToClipboard(changedText)
	fmt.Println("Result of formatting has been copied in clipboard...")
}

func getDataFromClipboard() string {
	clipboardContent, err := clipboard.ReadAll()
	if err != nil {
		panic(err.Error())
	}
	return clipboardContent
}

func setDataToClipboard(contentForClipboard string) {
	err := clipboard.WriteAll(contentForClipboard)
	if err != nil {
		panic(err.Error())
	}
}
