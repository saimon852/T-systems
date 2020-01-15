package sk.tsystems.gamestudio.controler;

import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;





@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TranslatorController extends Abstract {
	
	
	@RequestMapping("/translator")
	public String translator() {
		message = "";
		return "translator";
	}

	@RequestMapping("/translator/translate")
	public String trans(String value) {
		message = "";
		try {
			String parseString = String.format(value);
			ha(parseString);
		} catch (Exception e) {
		}

		return "translator";
	}

	private void ha(String value) {
		if (value.equals("")) {
			message = "Preklad nesmie byt prázdny";
		}
		if (value.equals("dog")) {
			message = "Pes";
		}
		if (value.equals("cat")) {
			message = "Mačka";
		}
		if (value.equals("pes")) {
			message = "Dog";
		}
		if (value.equals("macka")|| value.equals("mačka")) {
			message = "Cat";
		}
		if (value.equals("goat")) {
			message = "Koza";
		}
		if (value.equals("pig")) {
			message = "Prasa";
		}
		if (value.equals("koza")) {
			message = "Goat";
		}
		if (value.equals("prasa")) {
			message = "Pig";
		}
		if (value.equals("mys")|| value.equals("myš")) {
			message = "Mouse";
		}
		if (value.equals("potkan")) {
			message = "Rat";
		}
		if (value.equals("mouse")) {
			message = "Myš";
		}
		if (value.equals("rat")) {
			message = "Potkan";
		}
		if (value.equals("fish")) {
			message = "Ryba";
		}
		if (value.equals("horse")) {
			message = "Kon";
		}
		if (value.equals("ryba")) {
			message = "Fish";
		}
		if (value.equals("kon")|| value.equals("kôň")) {
			message = "Horse";
		}
		if (value.equals("lion")) {
			message = "Lev";
		}
		if (value.equals("guinea pig")) {
			message = "Morske prasa";
		}
		if (value.equals("lev")) {
			message = "Lion";
		}
		if (value.equals("morske prasa")|| value.equals("morské prasa")) {
			message = "Guinea pig";
		}
		if (value.equals("zirafa") || value.equals("žirafa") ) {
			message = "Giraphe";
		}
		if (value.equals("giraphe")) {
			message = "Žirafa";
		}
		if (value.equals("rabbit")) {
			message = "Zajac";
		}
		if (value.equals("monkey")) {
			message = "Opica";
		}
		if (value.equals("zajac")) {
			message = "Rabbit";
		}
		if (value.equals("opica")) {
			message = "Monkey";
		
		}
		if (value.equals("krava")) {
			message = "Cow";
		}
		if (value.equals("cow")) {
			message = "Krava";
		
		}if (value.equals("klokan")) {
			message = "Kangaroo";
		}
		if (value.equals("kangaroo")) {
			message = "Klokan";
		}
	if (value.equals("zaba")|| value.equals("žaba")) {
		message = "Frog";
	}
	if (value.equals("frog")) {
		message = "Žaba";
	}
		}
	
	
}
