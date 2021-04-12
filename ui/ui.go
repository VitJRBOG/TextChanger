package ui

import (
	"github.com/VitJRBOG/TextChanger/file_manager"
	"github.com/VitJRBOG/TextChanger/ui/cli"
	"github.com/VitJRBOG/TextChanger/ui/gui"
)

func InitUI() {
	file_manager.InitCfgFile()
	cfg := file_manager.GetCfgFile()

	if cfg.ConnectWebview {
		go cli.ShowCLI()
		gui.ShowGUI(cfg.ShowWindow)
	} else {
		cli.ShowCLI()
	}
}
