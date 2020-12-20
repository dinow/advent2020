package adventofcode;

import be.dno.advent2020.*;

public class DayTest {

	public static void main(String[] args) throws Exception {
		new Day19().run("day19.txt");
	}

}

//   \b(a)(((a)(a)\b|\b(b)(b))((a)(b)\b|\b(b)(a))\b|\b((a)(b)\b|\b(b)(a))((a)(a)\b|\b(b)(b)))(b)\b
//   \b(a)(((a)(a)|(b)(b))((a)(b)|(b)(a))|((a)(b)|(b)(a))((a)a|bb))(b)\b