package gui

import (
	"github.com/VitJRBOG/TextChanger/chars"
	"github.com/VitJRBOG/TextChanger/keywords"
	"github.com/gorilla/mux"
	"html/template"
	"net/http"
)

func initServer() {
	rtr := mux.NewRouter()
	rtr.HandleFunc("/", homePageHandler).Methods("GET")
	rtr.HandleFunc("/form/cyr_to_lat_for_all", cyrToLatForAllFormHandler).Methods("GET")
	rtr.HandleFunc("/form/cyr_to_lat_for_keywords", cyrToLatForKeywordsFormHandler).Methods("GET")
	rtr.HandleFunc("/form/lat_to_cyr", latToCyrFormHandler).Methods("GET")
	rtr.HandleFunc("/change/cyr_to_lat_for_all", cyrToLatForAllHandler).Methods("POST")
	rtr.HandleFunc("/change/cyr_to_lat_for_keywords", cyrToLatForKeywordsHandler).Methods("POST")
	rtr.HandleFunc("/change/lat_to_cyr", latToCyrHandler).Methods("POST")
	rtr.HandleFunc("/keywords_are_missing", keywordsAreMissingHandler).Methods("GET")

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

type Text struct {
	OrigText, ChangedText string
}

var text Text

func cyrToLatForAllHandler(w http.ResponseWriter, r *http.Request) {
	text.OrigText = r.FormValue("input_area")

	text.ChangedText = chars.CyrToLatForAll(text.OrigText)

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
		text.ChangedText = chars.CyrToLatForKeywords(text.OrigText, keywords)

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

	text.ChangedText = chars.LatToCyr(text.OrigText)

	tmplt := parseHtmlFiles()

	err := tmplt.ExecuteTemplate(w, "lat_to_cyr", text)
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

func parseHtmlFiles() *template.Template {
	tmplt, err := template.ParseFiles(
		"gui/html/header.html",
		"gui/html/index.html",
		"gui/html/keywords_are_missing.html",
		"gui/html/cyr_to_lat_for_all.html",
		"gui/html/cyr_to_lat_for_keywords.html",
		"gui/html/lat_to_cyr.html",
		"gui/html/footer.html")
	if err != nil {
		panic(err.Error())
	}
	return tmplt
}
