package gui

import (
	"html/template"
	"net/http"

	"github.com/VitJRBOG/TextChanger/censorship"
	"github.com/VitJRBOG/TextChanger/chars"
	"github.com/VitJRBOG/TextChanger/formatting"
	"github.com/VitJRBOG/TextChanger/keywords"
	"github.com/VitJRBOG/TextChanger/tools"
	"github.com/gorilla/mux"
)

func initServer() {
	rtr := mux.NewRouter()
	rtr.HandleFunc("/", homePageHandler).Methods("GET")
	rtr.HandleFunc("/form/cyr_to_lat_for_all", cyrToLatForAllFormHandler).Methods("GET")
	rtr.HandleFunc("/form/cyr_to_lat_for_keywords", cyrToLatForKeywordsFormHandler).Methods("GET")
	rtr.HandleFunc("/form/lat_to_cyr", latToCyrFormHandler).Methods("GET")
	rtr.HandleFunc("/form/censorship", censorshipFormHandler).Methods("GET")
	rtr.HandleFunc("/form/formatting", formattingFormHandler).Methods("GET")
	rtr.HandleFunc("/change/cyr_to_lat_for_all", cyrToLatForAllHandler).Methods("POST")
	rtr.HandleFunc("/change/cyr_to_lat_for_keywords", cyrToLatForKeywordsHandler).Methods("POST")
	rtr.HandleFunc("/change/lat_to_cyr", latToCyrHandler).Methods("POST")
	rtr.HandleFunc("/change/censorship", censorshipHandler).Methods("POST")
	rtr.HandleFunc("/change/formatting", formattingHandler).Methods("POST")
	rtr.HandleFunc("/keywords_are_missing", keywordsAreMissingHandler).Methods("GET")
	rtr.HandleFunc("/obscenewords_are_missing", obsceneWordsAreMissingHandler).Methods("GET")

	pathToCurrentDir := tools.GetPathToCurrentDir() + "/"

	http.Handle("/static/", http.StripPrefix("/static/",
		http.FileServer(http.Dir(pathToCurrentDir+"ui/gui/static/"))))
	http.Handle("/", rtr)
	err := http.ListenAndServe(":8080", nil)
	if err != nil {
		panic(err.Error())
	}
}

func homePageHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "index", nil)
	if err != nil {
		panic(err.Error())
	}
}

func cyrToLatForAllFormHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "cyr_to_lat_for_all", nil)
	if err != nil {
		panic(err.Error())
	}
}
func cyrToLatForKeywordsFormHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "cyr_to_lat_for_keywords", nil)
	if err != nil {
		panic(err.Error())
	}
}

func latToCyrFormHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "lat_to_cyr", nil)
	if err != nil {
		panic(err.Error())
	}
}

func censorshipFormHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "censorship", nil)
	if err != nil {
		panic(err.Error())
	}
}

func formattingFormHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "formatting", nil)
	if err != nil {
		panic(err.Error())
	}
}

type Text struct {
	OrigText, ChangedText string
}

var text Text

func cyrToLatForAllHandler(w http.ResponseWriter, r *http.Request) {
	text.OrigText = r.FormValue("input_area")

	_, text.ChangedText = chars.CyrToLatForAll(text.OrigText)

	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "cyr_to_lat_for_all", text)
	if err != nil {
		panic(err.Error())
	}
}

func cyrToLatForKeywordsHandler(w http.ResponseWriter, r *http.Request) {
	text.OrigText = r.FormValue("input_area")

	keywords := keywords.GetKeywords()
	if len(keywords) > 0 && keywords[0] != "" {
		_, text.ChangedText = chars.CyrToLatForKeywords(text.OrigText, keywords)

		tmplt := parseHtmlFiles()

		err := tmplt.ExecuteTemplate(w, "cyr_to_lat_for_keywords", text)
		if err != nil {
			panic(err.Error())
		}
	} else {
		http.Redirect(w, r, "/keywords_are_missing", http.StatusSeeOther)
	}
}

func latToCyrHandler(w http.ResponseWriter, r *http.Request) {
	text.OrigText = r.FormValue("input_area")

	_, text.ChangedText = chars.LatToCyr(text.OrigText)

	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "lat_to_cyr", text)
	if err != nil {
		panic(err.Error())
	}
}

func censorshipHandler(w http.ResponseWriter, r *http.Request) {
	text.OrigText = r.FormValue("input_area")

	obsceneWords := censorship.GetObsceneWords()
	if len(obsceneWords) > 0 && obsceneWords[0] != "" {
		_, text.ChangedText = censorship.CensorText(text.OrigText, obsceneWords)

		tmplt := parseHtmlFiles()

		err := tmplt.ExecuteTemplate(w, "censorship", text)
		if err != nil {
			panic(err.Error())
		}
	} else {
		http.Redirect(w, r, "/obscenewords_are_missing", http.StatusSeeOther)
	}
}

func formattingHandler(w http.ResponseWriter, r *http.Request) {
	text.OrigText = r.FormValue("input_area")

	text.ChangedText = formatting.FormatText(text.OrigText)

	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "formatting", text)
	if err != nil {
		panic(err.Error())
	}
}

func keywordsAreMissingHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "keywords_are_missing", text)
	if err != nil {
		panic(err.Error())
	}
}

func obsceneWordsAreMissingHandler(w http.ResponseWriter, r *http.Request) {
	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "obscenewords_are_missing", text)
	if err != nil {
		panic(err.Error())
	}
}

func parseHtmlFiles() *template.Template {
	pathToCurrentDir := tools.GetPathToCurrentDir() + "/"

	tmplt, err := template.ParseFiles(
		pathToCurrentDir+"ui/gui/html/header.html",
		pathToCurrentDir+"ui/gui/html/index.html",
		pathToCurrentDir+"ui/gui/html/keywords_are_missing.html",
		pathToCurrentDir+"ui/gui/html/obscenewords_are_missing.html",
		pathToCurrentDir+"ui/gui/html/cyr_to_lat_for_all.html",
		pathToCurrentDir+"ui/gui/html/cyr_to_lat_for_keywords.html",
		pathToCurrentDir+"ui/gui/html/lat_to_cyr.html",
		pathToCurrentDir+"ui/gui/html/censorship.html",
		pathToCurrentDir+"ui/gui/html/formatting.html",
		pathToCurrentDir+"ui/gui/html/footer.html")
	if err != nil {
		panic(err.Error())
	}
	return tmplt
}
