package cli

import (
	"bufio"
	"fmt"
	"os"
	"strings"

	"github.com/VitJRBOG/TextChanger/censorship"
	"github.com/VitJRBOG/TextChanger/chars"
	"github.com/VitJRBOG/TextChanger/formatting"
	"github.com/VitJRBOG/TextChanger/keywords"
	"github.com/VitJRBOG/TextChanger/tools"
	"github.com/atotto/clipboard"
)

func ShowCLI() {
	initCLI()
}

func initCLI() {
	for {
		makeMainMenu()
		userAnswer := getMenuItemNumberFromUser()
		interpretationUserAnswer(userAnswer)
	}
}

func makeMainMenu() {
	fmt.Println("[MAIN MENU]")

	actions := []string{
		"Cyr to Lat (all)", "Cyr to Lat", "Lat to Cyr", "Censorship", "Formatting",
	}

	for i, item := range actions {
		fmt.Printf("%d == %s\n", i+1, item)
	}

	fmt.Println("0 == Settings")
	fmt.Println("00 == Exit")
}

func interpretationUserAnswer(userAnswer string) {
	if len(userAnswer) == 0 {
		fmt.Println("Your input is empty...")
		return
	}

	switch userAnswer {
	case "1":
		cyrToLatForAll()
	case "2":
		cyrToLatForKeywords()
	case "3":
		latToCyr()
	case "4":
		goCensorship()
	case "5":
		goFormatting()
	case "0":
		settingsMenu()
	case "00":
		os.Exit(0)
	default:
		fmt.Println("Unknown command...")
		return
	}
}

func cyrToLatForAll() {
	origText := getDataFromClipboard()
	changesNumber, changedText := chars.CyrToLatForAll(origText)
	setDataToClipboard(changedText)
	fmt.Printf("Changed chars: %d.\n"+
		"Result of CyrToLat(all)-replacing has been copied in clipboard...\n", changesNumber)
}

func cyrToLatForKeywords() {
	keywords := keywords.GetKeywords()
	if len(keywords) > 0 && keywords[0] != "" {
		origText := getDataFromClipboard()
		changesNumber, changedText := chars.CyrToLatForKeywords(origText, keywords)
		setDataToClipboard(changedText)
		fmt.Printf("Changed chars: %d.\n"+
			"Result of CyrToLat-replacing has been copied in clipboard...\n", changesNumber)
	} else {
		fmt.Println("The keywords file (keywords.txt) is empty. " +
			"Letters replacing in keywords is not possible.")
	}
}

func latToCyr() {
	origText := getDataFromClipboard()
	changesNumber, changedText := chars.LatToCyr(origText)
	setDataToClipboard(changedText)
	fmt.Printf("Changed chars: %d.\n"+
		"Result of LatToCyr-replacing has been copied in clipboard...\n", changesNumber)
}

func goCensorship() {
	obsceneWords := censorship.GetObsceneWords()
	if len(obsceneWords) > 0 && obsceneWords[0] != "" {
		origText := getDataFromClipboard()
		changesNumber, changedText := censorship.CensorText(origText, obsceneWords)
		setDataToClipboard(changedText)
		fmt.Printf("Censored words: %d\n"+
			"Result of censorship has been copied in clipboard...\n", changesNumber)
	} else {
		fmt.Println("The file with obscene words (obscene_words.txt) is empty. " +
			"Censorship is not possible.")
	}
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

func settingsMenu() {
	for {
		cfg := tools.GetCfgFile()
		makeSettingsMenu(cfg)
		userAnswer := getMenuItemNumberFromUser()
		ok := settingsMenuHandler(userAnswer, cfg)
		if ok {
			break
		}
	}
}

func makeSettingsMenu(cfg tools.Cfg) {
	fmt.Println("[SETTINGS]")

	if cfg.ConnectWebview {
		fmt.Println("1 == Disable \"Connect webview\"")
	} else {
		fmt.Println("1 == Enable \"Connect webview\"")
	}

	if cfg.ShowWindow {
		fmt.Println("2 == Disable \"Show window\"")
	} else {
		fmt.Println("2 == Enable \"Show window\"")
	}

	fmt.Println("00 == Back to Main menu")
}

func settingsMenuHandler(userAnswer string, cfg tools.Cfg) bool {
	if len(userAnswer) == 0 {
		fmt.Println("Your input is empty...")
		return false
	}

	switch userAnswer {
	case "1":
		toggleWebviewConnection(cfg)
	case "2":
		toggleWindowDisplay(cfg)
	case "00":
		return true
	default:
		fmt.Println("Unknown command...")
		return false
	}

	return true
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

func toggleWebviewConnection(cfg tools.Cfg) {
	if cfg.ConnectWebview {
		cfg.ConnectWebview = false
	} else {
		cfg.ConnectWebview = true
	}
	cfg.UpdateFile()
	fmt.Println("Config file successfully updated!")
}

func toggleWindowDisplay(cfg tools.Cfg) {
	if cfg.ShowWindow {
		cfg.ShowWindow = false
	} else {
		cfg.ShowWindow = true
	}
	cfg.UpdateFile()
	fmt.Println("Config file successfully updated!")
}
