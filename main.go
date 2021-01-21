package main

import (
	"github.com/VitJRBOG/TextChanger/gui"
	"github.com/VitJRBOG/TextChanger/keywords"
)

func main() {
	keywords.CheckFileWithKeywords()
	gui.ShowGUI()
}
