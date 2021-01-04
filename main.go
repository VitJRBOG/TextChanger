package main

import (
	"github.com/VitJRBOG/CharChanger/gui"
	"github.com/VitJRBOG/CharChanger/keywords"
	_ "github.com/andlabs/ui/winmanifest"
)

func main() {
	keywords.CheckFileWithKeywords()
	gui.ShowGUI()
}
