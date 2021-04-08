package main

import (
	"github.com/VitJRBOG/TextChanger/censorship"
	"github.com/VitJRBOG/TextChanger/cli"
	"github.com/VitJRBOG/TextChanger/gui"
	"github.com/VitJRBOG/TextChanger/keywords"
)

func main() {
	keywords.CheckFileWithKeywords()
	censorship.CheckFileWithObsceneWords()
	go cli.ShowCLI()
	gui.ShowGUI()
}
