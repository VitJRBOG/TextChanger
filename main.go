package main

import (
	"github.com/VitJRBOG/TextChanger/censorship"
	"github.com/VitJRBOG/TextChanger/keywords"
	"github.com/VitJRBOG/TextChanger/ui"
)

func main() {
	keywords.CheckFileWithKeywords()
	censorship.CheckFileWithObsceneWords()
	ui.InitUI()
}
