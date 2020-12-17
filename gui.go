package main

import (
	"github.com/andlabs/ui"
	_ "github.com/andlabs/ui/winmanifest"
)

func showGUI() {
	err := ui.Main(initGUI)
	if err != nil {
		panic(err.Error())
	}
}

func initGUI() {
	var m mainWindow
	m.initWindow()
	m.initMainBox()
	m.initInputTextArea()
	m.initButtons()
	m.initOutputTextArea()
}

type mainWindow struct {
	window                 *ui.Window
	mainBox                *ui.Box
	inputTextArea          *ui.MultilineEntry
	outputTextArea         *ui.MultilineEntry
	btnCyrToLatForAll      *ui.Button
	btnCyrToLatForKeywords *ui.Button
	btnLatToCyr            *ui.Button
}

func (m *mainWindow) initWindow() {
	m.window = ui.NewWindow("CharChanger", 300, 500, true)
	m.window.SetMargined(true)
	m.window.OnClosing(func(*ui.Window) bool {
		ui.Quit()
		return true
	})
	ui.OnShouldQuit(func() bool {
		m.window.Destroy()
		return true
	})
	m.window.Show()
}

func (m *mainWindow) initMainBox() {
	m.mainBox = ui.NewVerticalBox()
	m.mainBox.SetPadded(true)
	m.window.SetChild(m.mainBox)
}

func (m *mainWindow) initInputTextArea() {
	m.inputTextArea = ui.NewMultilineEntry()
	box := ui.NewHorizontalBox()
	box.Append(m.inputTextArea, true)
	m.mainBox.Append(box, true)
}

func (m *mainWindow) initButtons() {
	m.btnCyrToLatForAll = ui.NewButton("Кириллицу в латиницу для всех слов")
	m.btnCyrToLatForAll.OnClicked(func(*ui.Button) {
		changerCyrToLatForAll(m)
	})
	m.btnCyrToLatForKeywords = ui.NewButton("Кириллицу в латиницу для ключевых слов")
	m.btnCyrToLatForKeywords.OnClicked(func(*ui.Button) {
		changerCyrToLatForKeywords(m)
	})
	m.btnLatToCyr = ui.NewButton("Латиницу в кириллицу")
	m.btnLatToCyr.OnClicked(func(*ui.Button) {
		changerLatToCyr(m)
	})

	box := ui.NewVerticalBox()
	box.Append(m.btnCyrToLatForAll, false)
	box.Append(m.btnCyrToLatForKeywords, false)
	box.Append(m.btnLatToCyr, false)

	m.mainBox.Append(box, false)
}

func (m *mainWindow) initOutputTextArea() {
	m.outputTextArea = ui.NewMultilineEntry()
	m.outputTextArea.SetReadOnly(true)
	box := ui.NewHorizontalBox()
	box.Append(m.outputTextArea, true)
	m.mainBox.Append(box, true)
}

func changerCyrToLatForAll(m *mainWindow) {
	origText := m.inputTextArea.Text()
	changedText := cyrToLatForAll(origText)
	m.outputTextArea.SetText(changedText)
}

func changerCyrToLatForKeywords(m *mainWindow) {
	keywords := getKeywords()
	origText := m.inputTextArea.Text()
	changedText := cyrToLatForKeywords(origText, keywords)
	m.outputTextArea.SetText(changedText)
}

func changerLatToCyr(m *mainWindow) {
	origText := m.inputTextArea.Text()
	changedText := latToCyr(origText)
	m.outputTextArea.SetText(changedText)
}
