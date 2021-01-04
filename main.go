package main

import (
	"github.com/VitJRBOG/TextChanger/gui"
	"github.com/VitJRBOG/TextChanger/keywords"
	_ "github.com/andlabs/ui/winmanifest"
)

func main() {
	keywords.CheckFileWithKeywords()
	gui.ShowGUI()
}
