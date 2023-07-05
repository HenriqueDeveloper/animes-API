package br.com.henrique.utils;

import org.springframework.http.ResponseEntity;

import br.com.henrique.dtos.AnimeDTO;
import br.com.henrique.enums.TypeAnime;
import br.com.henrique.enums.TypeOrderAnime;

public class AnimeUtil {

	public static boolean validar(ResponseEntity<AnimeDTO> response) {
		if (response.getBody().getData().isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null) {
			return false;
		}
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	public static boolean isCompatible(String valor) {
		if (valor.equalsIgnoreCase(TypeAnime.MOVIE.name()) || valor.equalsIgnoreCase(TypeAnime.MUSIC.name())
				|| valor.equalsIgnoreCase(TypeAnime.ONA.name()) || valor.equalsIgnoreCase(TypeAnime.OVA.name())
				|| valor.equalsIgnoreCase(TypeAnime.SPECIAL.name()) || valor.equalsIgnoreCase(TypeAnime.TV.name())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTypeCompative(String valor) {
		if (valor.equalsIgnoreCase(TypeOrderAnime.END_DATE.name()) || valor.equalsIgnoreCase(TypeOrderAnime.EPISODES.name())
				|| valor.equalsIgnoreCase(TypeOrderAnime.FAVORITE.name()) || valor.equalsIgnoreCase(TypeOrderAnime.MAL_ID.name())
				|| valor.equalsIgnoreCase(TypeOrderAnime.MEMBERS.name())
				|| valor.equalsIgnoreCase(TypeOrderAnime.POPULARITY.name()) | valor.equalsIgnoreCase(TypeOrderAnime.RANK.name())
				|| valor.equalsIgnoreCase(TypeOrderAnime.RATING.name()) || valor.equalsIgnoreCase(TypeOrderAnime.SCORE.name())
				|| valor.equalsIgnoreCase(TypeOrderAnime.SCORE_BY.name()) || valor.equalsIgnoreCase(TypeOrderAnime.START_DATE.name())
				|| valor.equalsIgnoreCase(TypeOrderAnime.TITLE.name()) || valor.equalsIgnoreCase(TypeOrderAnime.TYPE.name())) {
			return true;
		} else {
			return false;
		}
	}

}
