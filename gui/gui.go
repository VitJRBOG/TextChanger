package gui

import "github.com/webview/webview"

func ShowGUI() {
	go initServer()
	// TODO: добавить возможность отключать отображение окна webview
	initWebview()
}

func initWebview() {
	w := webview.New(true)
	defer w.Destroy()
	w.SetTitle("TextChanger")
	w.SetSize(780, 340, webview.HintNone)
	w.Navigate("http://127.0.0.1:8080")
	w.Run()
}
