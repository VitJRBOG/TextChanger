package chars

import (
	"testing"
)

func TestCyrToLatForAll(t *testing.T) {
	s := "Как быстро меняется мир! Казалось бы, совсем недавно получал аттестат и диплом, а оказывается, что ты " +
		"уже давно взрослый мужик, причем уважаемый. И многое из тех времен уже давно ушло."
	e := "Кaк быcтpo мeняeтcя миp! Кaзaлocь бы, coвceм нeдaвнo пoлyчaл aттecтaт и диплoм, a oкaзывaeтcя, чтo ты " +
		"yжe дaвнo взpocлый мyжик, пpичeм yвaжaeмый. И мнoгoe из тex вpeмeн yжe дaвнo yшлo."

	n, result := CyrToLatForAll(s)

	m := 62

	if result != e || n != m {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e,
			"\nchanges number got:", n,
			"\nchanges number expected:", m)
	}
}

func TestCyrToLatForKeywords(t *testing.T) {
	s := "Как быстро меняется мир! Казалось бы, совсем недавно получал аттестат и диплом, а оказывается, что ты " +
		"уже давно взрослый мужик, причем уважаемый. И многое из тех времен уже давно ушло."
	e := "Как быстро меняется мир! Казалось бы, coвceм недавно получал аттестат и диплом, а оказывается, чтo ты " +
		"yжe давно взрослый мужик, пpичeм уважаемый. И мнoгoe из тex времен yжe давно ушло."

	keywords := []string{
		"как", "совсем", "что", "уже", "причем", "многое", "тех",
	}

	n, result := CyrToLatForKeywords(s, keywords)

	m := 16

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e,
			"\nchanges number got:", n,
			"\nchanges number expected:", m)
	}
}

func TestLatToCyr(t *testing.T) {
	s := "Кaк быcтpo мeняeтcя миp! Кaзaлocь бы, coвceм нeдaвнo пoлyчaл aттecтaт и диплoм, a oкaзывaeтcя, чтo ты " +
		"yжe дaвнo взpocлый мyжик, пpичeм yвaжaeмый. И мнoгoe из тex вpeмeн yжe дaвнo yшлo."
	e := "Как быстро меняется мир! Казалось бы, совсем недавно получал аттестат и диплом, а оказывается, что ты " +
		"уже давно взрослый мужик, причем уважаемый. И многое из тех времен уже давно ушло."

	n, result := LatToCyr(s)

	m := 62

	if result != e {
		t.Error("For:", s,
			"\ngot:", result,
			"\nexpected:", e,
			"\nchanges number got:", n,
			"\nchanges number expected:", m)
	}
}
