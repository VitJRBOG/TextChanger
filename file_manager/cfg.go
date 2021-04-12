package file_manager

import "encoding/json"

type Cfg struct {
	ConnectWebview bool `json:"connect_webview"`
	ShowWindow     bool `json:"show_window"`
}

func (c *Cfg) UpdateFile() {
	data, err := json.Marshal(c)
	if err != nil {
		panic(err.Error())
	}

	path := GetPathToCurrentDir() + "/config.json"
	err = WriteToFile(path, data)
	if err != nil {
		panic(err.Error())
	}
}

func NewCfg() *Cfg {
	return &Cfg{
		ConnectWebview: true,
		ShowWindow:     true,
	}
}

func InitCfgFile() {
	// TODO: сделать проверку соответствия содержимого текущего cfg с требуемым
	ok := checkCfgFile()
	if !ok {
		createCfgFile()
	}
}

func checkCfgFile() bool {
	return CheckFileExistence(GetPathToCurrentDir() + "/config.json")
}

func createCfgFile() {
	c := NewCfg()
	data, err := json.Marshal(&c)
	if err != nil {
		panic(err.Error())
	}

	path := GetPathToCurrentDir() + "/config.json"
	err = WriteToFile(path, data)
	if err != nil {
		panic(err.Error())
	}
}

func GetCfgFile() Cfg {
	path := GetPathToCurrentDir() + "/config.json"

	data, err := ReadJSON(path)
	if err != nil {
		panic(err.Error())
	}

	var c Cfg
	err = json.Unmarshal(data, &c)
	if err != nil {
		panic(err.Error())
	}

	return c
}
