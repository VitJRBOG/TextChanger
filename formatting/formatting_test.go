package formatting

import (
	"strings"
	"testing"
)

func TestFormatText(t *testing.T) {
	s := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "Как быстро меняется мир! Казалось бы, совсем недавно получал аттестат и диплом, а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый.! И многое из тех времен уже давно ушло."

	result := FormatText(s)

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}

func TestToLowerCaseAllChars(t *testing.T) {
	s := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "как быстро меняется мир! казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."

	result := toLowerCaseAllChars(s)

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}

func TestRemoveRedundantSpaces(t *testing.T) {
	s := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."

	result := removeRedundantSpaces(s)

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}

func TestAddSpacesAfterPunctuationMarks(t *testing.T) {
	s := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы, совсем недавно получал аттестат и диплом  , а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."

	r := addSpacesAfterPunctuationMarks(strings.Split(s, ""))
	result := strings.Join(r, "")

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}

func TestRemoveSpacesBeforePunctuationMarks(t *testing.T) {
	s := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "КАК БЫСТРО МЕНЯЕТСЯ МИР! Казалось бы,совсем недавно получал аттестат и диплом ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый.! и многое из тех времен уже давно ушло."

	r := removeSpacesBeforePunctuationMarks(strings.Split(s, ""))
	result := strings.Join(r, "")

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}

func TestToUpperCaseFirstChar(t *testing.T) {
	s := "как быстро меняется мир! казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "Как быстро меняется мир! казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."

	r := toUpperCaseFirstChar(strings.Split(s, ""))
	result := strings.Join(r, "")

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}

func TestToUpperCaseCharsAfterPunctuationMarksAndNewLine(t *testing.T) {
	s := "как быстро меняется мир! казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! и многое из тех времен уже давно ушло."
	e := "как быстро меняется мир! Казалось бы,совсем недавно получал аттестат и диплом  ,а оказывается, " +
		"что ты уже давно взрослый мужик, причем уважаемый. ! И многое из тех времен уже давно ушло."

	r := toUpperCaseCharsAfterPunctuationMarksAndNewLine(strings.Split(s, ""))
	result := strings.Join(r, "")

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e)
	}
}
