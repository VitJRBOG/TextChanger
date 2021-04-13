package ui

import (
	"github.com/VitJRBOG/TextChanger/tools"
	"github.com/VitJRBOG/TextChanger/ui/cli"
	"github.com/VitJRBOG/TextChanger/ui/gui"
)

func InitUI() {
	tools.InitCfgFile()
	cfg := tools.GetCfgFile()

	if cfg.ConnectWebview {
		go cli.ShowCLI()
		gui.ShowGUI(cfg.ShowWindow)
	} else {
		cli.ShowCLI()
	}
}
